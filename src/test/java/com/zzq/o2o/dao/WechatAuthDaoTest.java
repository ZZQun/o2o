package com.zzq.o2o.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.NativeWebRequest;

import com.google.zxing.qrcode.decoder.Version.ECB;
import com.zzq.o2o.BaseTest;
import com.zzq.o2o.entity.PersonInfo;
import com.zzq.o2o.entity.WechatAuth;

public class WechatAuthDaoTest extends BaseTest{
	@Autowired
	private WechatAuthDao wechatAuthDao;
	
	@Test
//	@Ignore
	public void testAInsertWechatAuth() throws Exception{
		WechatAuth wechatAuth = new WechatAuth();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1L);
		wechatAuth.setPersonInfo(personInfo);
		wechatAuth.setOpenId("dsadsa3113a1da");
		wechatAuth.setCreateTime(new Date());
		int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);
		System.out.println("插入影响行数：" + effectedNum);
	}
	
	@Test
	@Ignore
	public void testBQueryWechatAuthByOpenId() throws Exception{
		String openId = "dsadsa3d13a1da";
		WechatAuth wechatAuth = wechatAuthDao.queryWechatInfoByOpenId(openId);
		System.out.println("查询结果：" + wechatAuth.getWechatAuthed());
	}
}
