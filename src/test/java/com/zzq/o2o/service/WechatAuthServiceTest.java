package com.zzq.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzq.o2o.BaseTest;
import com.zzq.o2o.dto.WechatAuthExecution;
import com.zzq.o2o.entity.PersonInfo;
import com.zzq.o2o.entity.WechatAuth;
import com.zzq.o2o.enums.WechatAuthStateEnum;

public class WechatAuthServiceTest extends BaseTest{

	@Autowired
	private WechatAuthService wechatAuthService;
	
	@Test
	public void testRegister() {
		//新增一条微信号
		WechatAuth wechatAuth = new WechatAuth();
		PersonInfo personInfo = new PersonInfo();
		String openId = "44646kokokoko";
		//给微信帐号设置上用户信息，但不设置上用户Id
		//希望创建微信帐号的时候自动创建用户信息
		personInfo.setCreateTime(new Date());
		personInfo.setName("测试");
		personInfo.setUserType(1);
		wechatAuth.setPersonInfo(personInfo);
		wechatAuth.setOpenId(openId);
		wechatAuth.setCreateTime(new Date());
		WechatAuthExecution wec = wechatAuthService.register(wechatAuth);
		assertEquals(WechatAuthStateEnum.SUCCESS.getState(),wec.getState());
		wechatAuth = wechatAuthService.getWechatAuthByOpenId(openId);
		System.out.println(wechatAuth.getPersonInfo().getName());
	}
}
