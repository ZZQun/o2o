package com.zzq.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzq.o2o.BaseTest;
import com.zzq.o2o.dto.ImageHolder;
import com.zzq.o2o.dto.ProductExecution;
import com.zzq.o2o.entity.Product;
import com.zzq.o2o.entity.ProductCategory;
import com.zzq.o2o.entity.Shop;
import com.zzq.o2o.enums.ProductStateEnum;
import com.zzq.o2o.exception.ShopOperationException;

public class ProductServiceTest extends BaseTest{

	@Autowired
	private ProductService productService;
	
	@Test
	@Ignore
	public void testAddProduct() throws ShopOperationException,FileNotFoundException{
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(2L);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(14L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("测试商品11");
		product.setProductDesc("测试商品11");
		product.setPriority(20);
		product.setCreateTime(new Date());
		product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
		//创建略缩图文件流
		File productImg = new File("D:\\Others\\photo\\timg3.jpg");
		InputStream is = new FileInputStream(productImg);
		ImageHolder imageHolder = new ImageHolder(productImg.getName(),is);
		//添加两个商品详情图
		File productImg1 = new File("D:\\Others\\photo\\timg2.jpg");
		InputStream is1 = new FileInputStream(productImg1);
		File productImg2 = new File("D:\\Others\\photo\\timg1.jpg");
		InputStream is2 = new FileInputStream(productImg2);
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(productImg1.getName(), is1));
		productImgList.add(new ImageHolder(productImg2.getName(), is2));
		ProductExecution pe = productService.addProduct(product, imageHolder, productImgList);
	}
	
	@Test
	@Ignore
	public void testModifyProduct() throws ShopOperationException,FileNotFoundException{
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(2L);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(15L);
		product.setProductId(1L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("无敌奶茶");
		product.setProductDesc("超级无敌好喝奶茶");
		//创建略缩图文件流
		File productImg = new File("D:\\Others\\photo\\timg1.jpg");
		InputStream is = new FileInputStream(productImg);
		ImageHolder imageHolder = new ImageHolder(productImg.getName(),is);
		//添加两个商品详情图
		File productImg1 = new File("D:\\Others\\photo\\timg2.jpg");
		InputStream is1 = new FileInputStream(productImg1);
		File productImg2 = new File("D:\\Others\\photo\\timg3.jpg");
		InputStream is2 = new FileInputStream(productImg2);
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(productImg1.getName(), is1));
		productImgList.add(new ImageHolder(productImg2.getName(), is2));
		ProductExecution pe = productService.modifyProduct(product, imageHolder, productImgList);
		assertEquals(ProductStateEnum.SUCCESS.getState(),pe.getState());
	}
}
