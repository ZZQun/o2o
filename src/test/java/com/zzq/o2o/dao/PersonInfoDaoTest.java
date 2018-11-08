package com.zzq.o2o.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzq.o2o.BaseTest;
import com.zzq.o2o.entity.PersonInfo;

public class PersonInfoDaoTest extends BaseTest{
	@Autowired
	private PersonInfoDao personInfoDao;
	
	@Test
	@Ignore
	public void testAInsertPersonInfo() throws Exception{
		PersonInfo personInfo = new PersonInfo();
		personInfo.setName("小老李");
		personInfo.setGender("男");
		personInfo.setUserType(1);
		personInfo.setCreateTime(new Date());
		personInfo.setLastEditTime(new Date());
		personInfo.setEnableStatus(1);
		int effectedNum = personInfoDao.insertPersonInfo(personInfo);
		System.out.println("插入影响行数：" + effectedNum);
	}
	
	@Test
//	@Ignore
	public void testBQueryPersonInfo() {
		long userId = 3L;
		PersonInfo personInfo = personInfoDao.queryPersonInfoById(userId);
		System.out.println(personInfo.getName());
	}
}
