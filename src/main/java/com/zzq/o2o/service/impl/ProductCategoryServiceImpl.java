package com.zzq.o2o.service.impl;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzq.o2o.dao.ProductCategoryDao;
import com.zzq.o2o.dao.ProductDao;
import com.zzq.o2o.dto.ProductCategoryExecution;
import com.zzq.o2o.entity.ProductCategory;
import com.zzq.o2o.enums.ProductCategoryStateEnum;
import com.zzq.o2o.exception.ProductCategoryException;
import com.zzq.o2o.exception.ProductOperationException;
import com.zzq.o2o.service.ProductCategoryService;
import com.zzq.o2o.web.shopadmin.ProductCategoryManagementController;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		return productCategoryDao.queryProductCategory(shopId);
	}

	@Override
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryException {
		if(productCategoryList != null && productCategoryList.size() > 0) {
			try {
				int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if(effectedNum <= 0) {
					throw new ProductCategoryException("店铺类别创建失败！");
				}else {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new ProductCategoryException("batchAddProductCategory error:" + e.getMessage());
			}
		}else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}

	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryException {
		//先接触tb_product里的商品与productCategoryId的关联
		try {
			productDao.updateProductCategoryToNull(productCategoryId);
		} catch (Exception e) {
			throw new ProductCategoryException("deleteProductCategory error:" + e.getMessage());
		}
		//再删除该productCategory
		try {
			int effetedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if(effetedNum <= 0) {
				throw new ProductCategoryException("商品类别删除失败！");
			}else {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		} catch (Exception e) {
			 throw new ProductCategoryException("deleteProductCategory error:" + e.getMessage());
		}
	}

}
