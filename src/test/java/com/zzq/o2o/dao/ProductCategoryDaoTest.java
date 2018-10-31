package com.zzq.o2o.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzq.o2o.BaseTest;
import com.zzq.o2o.entity.ProductCategory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest{
	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Test
	public void testBQueryByShopId() throws Exception{
		long shopId = 2;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategory(shopId);
		System.out.println("该店铺自定义类别数为:" + productCategoryList.size());
	}
	
	@Test
	public void testABatchInsertProductCategory() throws Exception{
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryName("1");
		productCategory.setPriority(1);
		productCategory.setCreateTime(new Date());
		productCategory.setShopId(2L);
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("2");
		productCategory2.setPriority(6);
		productCategory2.setCreateTime(new Date());
		productCategory2.setShopId(2L);
		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		productCategoryList.add(productCategory);
		productCategoryList.add(productCategory2);
		int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
		System.out.println("影响行数：" + effectedNum);
	}
	
	@Test
	public void testCDeleteProductCategory() throws Exception{
		long shopId = 2L;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategory(shopId);
		for(ProductCategory pc : productCategoryList) {
			if("1".equals(pc.getProductCategoryName()) || "2".equals(pc.getProductCategoryName())) {
				int effectedNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), shopId);
				System.out.println("影响行数" + effectedNum);
			}
		}
		
	}
}
