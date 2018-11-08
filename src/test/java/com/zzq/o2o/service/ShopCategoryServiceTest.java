package com.zzq.o2o.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzq.o2o.BaseTest;
import com.zzq.o2o.entity.ShopCategory;

public class ShopCategoryServiceTest extends BaseTest{

	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@Test
	public void testAGetShopCategoryList() {
		ShopCategory shopCategoryCondition = null;
		List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
		System.out.println("查询结果的行数：" + shopCategoryList.size());
	}
}
