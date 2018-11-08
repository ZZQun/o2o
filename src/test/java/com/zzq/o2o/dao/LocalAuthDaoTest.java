package com.zzq.o2o.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzq.o2o.BaseTest;
import com.zzq.o2o.entity.LocalAuth;
import com.zzq.o2o.entity.PersonInfo;

public class LocalAuthDaoTest extends BaseTest{

	@Autowired
	private LocalAuthDao localAuthDao;
	
	private static final String username = "testusername";
	private static final String password = "testpassword";
	
	@Test
	@Ignore
	public void TestAInsertLocalAuth() throws Exception{
		LocalAuth localAuth = new LocalAuth();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1L);
		localAuth.setPersonInfo(personInfo);
		localAuth.setUsername(username);
		localAuth.setPassword(password);
		localAuth.setCreateTime(new Date());
		int effectedNum = localAuthDao.insertLocalAuth(localAuth);
		System.out.println("插入影响行数：" + effectedNum);
	}
	
	@Test
	@Ignore
	public void testBQueryLocalAuthByUsernameAndPwd() throws Exception{
		LocalAuth localAuth = localAuthDao.queryLocalByUserNameAndPwd(username, password);
		System.out.println("查询用户的名字：" + localAuth.getPersonInfo().getName());
	}
	
	@Test
	@Ignore
	public void testCQueryLocalAuthByUserId() throws Exception{
		LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
		System.out.println("查询用户的名字：" + localAuth.getPersonInfo().getName());
	}
	
	@Test
	public void testDUpdateLocalAuth() throws Exception{
		Date now = new Date();
		int effectedNum = localAuthDao.uodateLocalAuth(1L, username, password,"testpassword1",now);
		System.out.println("更新影响行数：" + effectedNum);
	}
}
