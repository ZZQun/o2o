package com.zzq.o2o.service;


import com.zzq.o2o.dto.WechatAuthExecution;
import com.zzq.o2o.entity.WechatAuth;
import com.zzq.o2o.exception.WechatAuthOperationException;


public interface WechatAuthService {

	/**
	 * 通过openId查找平台对应的微信账号
	 * @param openId
	 * @return
	 */
	WechatAuth getWechatAuthByOpenId(String openId);
	
	WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException;
}
