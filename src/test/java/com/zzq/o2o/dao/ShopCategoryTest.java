package com.zzq.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.NativeWebRequest;

import com.zzq.o2o.BaseTest;
import com.zzq.o2o.entity.ShopCategory;

public class ShopCategoryTest extends BaseTest{
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Test
	public void testQueryShopCategory() {
		List<ShopCategory> shopCategorieList = shopCategoryDao.queryShopCategory(null);
		System.out.println("查询返回行数：" + shopCategorieList.size());
//		ShopCategory testCategory = new ShopCategory();
//		ShopCategory parentCategory = new ShopCategory();
//		parentCategory.setShopCategoryId(1L);
//		testCategory.setParent(parentCategory);
//		List<ShopCategory> shopCategorieList = shopCategoryDao.queryShopCategory(testCategory);
//		assertEquals(1, shopCategorieList.size());
	}
}
