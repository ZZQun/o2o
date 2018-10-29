package com.zzq.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzq.o2o.BaseTest;
import com.zzq.o2o.entity.Area;
import com.zzq.o2o.entity.PersonInfo;
import com.zzq.o2o.entity.Shop;
import com.zzq.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest{
	@Autowired 
	private ShopDao shopDao;
	
	@Test
	public void testQueryShopListAndCount() {
		Shop shopCondition = new Shop();
		shopCondition.setShopName("test2");
		List<Shop> shopList1 = shopDao.queryShopList(shopCondition, 0, 5);
		System.out.println("店铺列表的大小:" + shopList1.size());
	}
	
	@Test
	@Ignore
	public void testQueryByShopId() {
		long shopId = 2L;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println("areaId=" + shop.getArea().getAreaId());
		System.out.println("areaName=" + shop.getArea().getAreaName());
	}
	@Test
	@Ignore
	public void testInsertShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setPersonInfo(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("test");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);		
	}
	
	@Test
	@Ignore
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(2L);
		shop.setShopName("老狗奶茶店");
		shop.setShopDesc("老狗开的奶茶店！");
		shop.setShopAddr("C612");
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
		
	}
	
}
