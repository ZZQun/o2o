package com.zzq.o2o.service;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzq.o2o.BaseTest;
import com.zzq.o2o.dto.LocalAuthExecution;
import com.zzq.o2o.entity.LocalAuth;
import com.zzq.o2o.entity.PersonInfo;
import com.zzq.o2o.enums.LocalAuthStateEnum;

public class LocalAuthServiceTest extends BaseTest{
	
	@Autowired
	private LocalAuthService localAuthService;
	
	@Test
	@Ignore
	public void TestABindLocalAuth() {
		LocalAuth localAuth = new LocalAuth();
		PersonInfo personInfo = new PersonInfo();
		String username = "testusername1";
		String password = "testpassword";
		personInfo.setUserId(5L);
		localAuth.setPersonInfo(personInfo);
		localAuth.setUsername(username);
		localAuth.setPassword(password);
		LocalAuthExecution lo = localAuthService.bindLocalAuth(localAuth);
		assertEquals(LocalAuthStateEnum.SUCCESS.getState(),lo.getState());
	}
	
	@Test
	public void TestBModifyLocalAuth() {
		String password = "testpassword";
		String newPassword = "test11111";
		LocalAuthExecution lo = localAuthService.modifyLocalAuth(1L, "testusername", password, newPassword);
		assertEquals(LocalAuthStateEnum.SUCCESS.getState(),lo.getState());
	}
}
