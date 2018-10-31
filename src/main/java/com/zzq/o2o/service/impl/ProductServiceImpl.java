package com.zzq.o2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.coyote.http11.filters.VoidInputFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzq.o2o.dao.ProductDao;
import com.zzq.o2o.dao.ProductImgDao;
import com.zzq.o2o.dto.ImageHolder;
import com.zzq.o2o.dto.ProductExecution;
import com.zzq.o2o.entity.Product;
import com.zzq.o2o.entity.ProductImg;
import com.zzq.o2o.enums.ProductStateEnum;
import com.zzq.o2o.exception.ProductOperationException;
import com.zzq.o2o.service.ProductService;
import com.zzq.o2o.util.ImageUtil;
import com.zzq.o2o.util.PageCalculator;
import com.zzq.o2o.util.PathUtil;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;
	
	@Override
	@Transactional
	//1.处理缩略图，获取缩略图相对路径并赋值给product
	//2.往tb_product写入商品信息，获取productId
	//3.结合productId批量处理商品详情图
	//4.将商品详情图列表插入tb_product_img
	public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws ProductOperationException {
		//空值判断
		if(product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			//给商品设置上默认属性
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			//默认为上架的状态
			product.setEnableStatus(1);
			//若商品缩略图不为空则添加
			if(thumbnail != null) {
				addThumnail(product,thumbnail);
			}
			try {
				//创建商品信息
				int effectedNum = productDao.insertProduct(product);
				if(effectedNum <= 0) {
					throw new ProductOperationException("创建商品失败！");
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品失败：" + e.toString());
			}
			//若商品详情图不为空则添加
			if(productImgList != null && productImgList.size() > 0) {
				addProductImgList(product,productImgList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS,product);
		}else {
			//传参为空则返回空值错误信息
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
		
	}

	@Override
	@Transactional
	//1.若缩略图参数有值，则处理缩略图，若原先存在缩略图则先删除在添加新图，之后获取缩略图相对路径并赋值给product
	//2.若商品详情图列表参数有值，对商品详情图列表进行上述操作
	//3.将tb_product_img下面的该商品原先的商品详情图记录全部清除
	//4.更新tb_product_img tb_product的信息
	public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws ProductOperationException {
		//空值判断
		if(product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			//给商品设置上默认属性
			product.setLastEditTime(new Date());
			//若商品缩略图不为空且原有缩略图不为空则删除原有缩略图并添加
			if(thumbnail != null) {
				//先获取一遍原有信息，因为原来的信息有图片地址
				Product tempProduct = productDao.queryProductById(product.getProductId());
				if(tempProduct.getImgAddr() != null) {
					ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
				}
				addThumnail(product, thumbnail);
			}
			//如果有新存入的商品详情图，则将原先的删除，并添加新的图片
			if(productImgList != null &&productImgList.size() > 0) {
				deleteProductImgList(product.getProductId());
				addProductImgList(product, productImgList);
			}
			try {
				//更新商品信息
				int effectedNum = productDao.updateProduct(product);
				if(effectedNum <= 0) {
					throw new ProductOperationException("更新商品信息失败！");
				}
				return new ProductExecution(ProductStateEnum.SUCCESS,product);
			} catch (Exception e) {
				throw new ProductOperationException("更新商品信息失败" + e.toString());
			}
		}else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}
	
	/**
	 * 添加商品详情图
	 * @param product
	 * @param productImgList
	 */
	private void addProductImgList(Product product, List<ImageHolder> productImgList) {
		//获取图片存储路径，这里直接放在相应店铺的文件夹底下
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgs = new ArrayList<ProductImg>();
		//遍历图片依次去处理，并添加进productImg实体类里
		for(ImageHolder productHolderImgList : productImgList) {
			String imgAddr = ImageUtil.generateNormalImg(productHolderImgList, dest);
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgs.add(productImg);
		}
		//如果确实是有图片需要添加，就执行批量添加操作
		if(productImgs.size() > 0) {
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgs);
				if(effectedNum <= 0) {
					throw new ProductOperationException("创建商品详细图片失败！");
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品详细图片失败：" + e.toString());
			}
		}
	}

	/**
	 * 添加缩略图
	 * @param product
	 * @param thumbnail
	 */
	private void addThumnail(Product product, ImageHolder thumbnail) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);
	}
	
	/**
	 * 删除原有的详情图
	 * @param productId
	 */
	private void deleteProductImgList(long productId) {
		//根据productId获取原来的图片
		List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
		//删除原来的图片
		for(ProductImg productImg : productImgList) {
			ImageUtil.deleteFileOrPath(productImg.getImgAddr());
		}
		//删除数据库原有的图片信息
		productImgDao.deleteProductImgByProductId(productId);
	}
	
	@Override
	public Product getProductById(long productId) {
		return productDao.queryProductById(productId);
	}

	@Override
	public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
		//页码转换为数据库的行码，并调用dao层取回指定页码的商品列表
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
		//基于同样的查询条件返回该查询条件下的商品总数
		int count = productDao.queryProductCount(productCondition);
		ProductExecution pe = new ProductExecution();
		pe.setCount(count);
		pe.setProductList(productList);
		return pe;
	}
}
