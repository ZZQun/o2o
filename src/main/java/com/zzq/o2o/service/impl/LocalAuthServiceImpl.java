package com.zzq.o2o.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zzq.o2o.dao.LocalAuthDao;
import com.zzq.o2o.dto.LocalAuthExecution;
import com.zzq.o2o.entity.LocalAuth;
import com.zzq.o2o.enums.LocalAuthStateEnum;
import com.zzq.o2o.exception.LocalAuthOperationException;
import com.zzq.o2o.service.LocalAuthService;
import com.zzq.o2o.util.MD5;

@Service
public class LocalAuthServiceImpl implements LocalAuthService{

	@Autowired
	private LocalAuthDao localAuthDao;
	
	@Override
	public LocalAuth getLocalAuthByUsernameAndPwd(String username, String password) {
		return localAuthDao.queryLocalByUserNameAndPwd(username, MD5.getMd5(password));
	}

	@Override
	public LocalAuth getLocalAuthByUserId(long userId) {
		return localAuthDao.queryLocalByUserId(userId);
	}

	@Override
	@Transactional
	public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
		if(localAuth == null || localAuth.getPassword() == null || localAuth.getUsername() == null
				||localAuth.getPersonInfo() == null || localAuth.getPersonInfo().getUserId() == null) {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
		LocalAuth tempAuth = localAuthDao.queryLocalByUserId(localAuth.getPersonInfo().getUserId());
		if(tempAuth != null) {
			return new LocalAuthExecution(LocalAuthStateEnum.ONLY_ONE_ACCOUNT);
		}
		try {
			localAuth.setCreateTime(new Date());
			localAuth.setLastEditTime(new Date());
			localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
			int effectedNum = localAuthDao.insertLocalAuth(localAuth);
			if(effectedNum <= 0) {
				throw new LocalAuthOperationException("帐号绑定失败！");
			}else {
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS,localAuth);
			}
		} catch (Exception e) {
			throw new LocalAuthOperationException("insertLocalAuth error:" + e.getMessage());
		}
	}

	@Override
	@Transactional
	public LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword)
			throws LocalAuthOperationException {
		if(userId != null && username != null && password != null && newPassword != null
				&& !password.equals(newPassword)) {
			try {
				int effectedNum = localAuthDao.uodateLocalAuth(userId, username, MD5.getMd5(password), 
						MD5.getMd5(newPassword), new Date());
				if(effectedNum <= 0) {
					throw new LocalAuthOperationException("更新密码失败！");
				}
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
			} catch (Exception e) {
				throw new LocalAuthOperationException("更新密码失败：" + e.toString());
			}
		}else {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
	}

}
