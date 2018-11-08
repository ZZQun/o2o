package com.zzq.o2o.service;

import com.zzq.o2o.dto.LocalAuthExecution;
import com.zzq.o2o.entity.LocalAuth;
import com.zzq.o2o.exception.LocalAuthOperationException;

public interface LocalAuthService {

	/**
	 * 通过帐号和密码获取平台帐号信息
	 * @param username
	 * @param password
	 * @return
	 */
	LocalAuth getLocalAuthByUsernameAndPwd(String username,String password);
	
	/**
	 * 通过userId获取平台信息
	 * @param userId
	 * @return
	 */
	LocalAuth getLocalAuthByUserId(long userId);
	
	/**
	 * 绑定微信，生成平台帐号
	 * @param localAuth
	 * @return
	 * @throws LocalAuthOperationException
	 */
	LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

	/**
	 * 修改平台帐号的登录密码
	 * @param userId
	 * @param username
	 * @param password
	 * @param newPassword
	 * @return
	 * @throws LocalAuthOperationException
	 */
	LocalAuthExecution modifyLocalAuth(Long userId,String username,String password,String newPassword)
		throws LocalAuthOperationException;
}
