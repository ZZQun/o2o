package com.zzq.o2o.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzq.o2o.BaseTest;
import com.zzq.o2o.entity.ProductCategory;

public class ProductCategoryDaoTest extends BaseTest{
	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Test
	public void testQueryByShopId() throws Exception{
		long shopId = 13;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategory(shopId);
		System.out.println("该店铺自定义类别数为:" + productCategoryList.size());
	}
}
