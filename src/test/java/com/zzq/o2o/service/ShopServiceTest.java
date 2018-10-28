package com.zzq.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zzq.o2o.BaseTest;
import com.zzq.o2o.dao.ShopDao;
import com.zzq.o2o.dto.ShopExecution;
import com.zzq.o2o.entity.Area;
import com.zzq.o2o.entity.PersonInfo;
import com.zzq.o2o.entity.Shop;
import com.zzq.o2o.entity.ShopCategory;
import com.zzq.o2o.enums.ShopStateEnum;
import com.zzq.o2o.exception.ShopOperationException;

public class ShopServiceTest extends BaseTest{
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testModifyShop() throws ShopOperationException,FileNotFoundException{
		Shop shop = new Shop();
		shop.setShopId(13L);
		shop.setShopName("小老狗");
		File shopImg = new File("D:\\Others\\photo\\xiaohuangren.jpg");
		InputStream is = new FileInputStream(shopImg);
		ShopExecution shopExecution = shopService.modifyShop(shop, is, "sky.png");
		System.out.println("新的图片地址：" + shopExecution.getShop().getShopImg());
	}
	
	@Test
	@Ignore
	public void testAddShop() throws FileNotFoundException {
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
		shop.setShopName("test4");
		shop.setShopDesc("test4");
		shop.setShopAddr("test4");
		shop.setPhone("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg = new File("D:\\Others\\photo\\xiaohuangren.jpg");
		InputStream is = new FileInputStream(shopImg);
		ShopExecution se = shopService.addShop(shop, is, shopImg.getName());
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
	
}
