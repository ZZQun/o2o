package com.zzq.o2o.web.local;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzq.o2o.dto.LocalAuthExecution;
import com.zzq.o2o.entity.LocalAuth;
import com.zzq.o2o.entity.PersonInfo;
import com.zzq.o2o.enums.LocalAuthStateEnum;
import com.zzq.o2o.exception.LocalAuthOperationException;
import com.zzq.o2o.service.LocalAuthService;
import com.zzq.o2o.util.CodeUtil;
import com.zzq.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping(value="local",method={RequestMethod.GET,RequestMethod.POST})
public class LocalAuthController {

	@Autowired
	private LocalAuthService localAuthService;
	
	/**
	 * 将用户信息与平台帐号绑定
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/bindlocalauth",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> bindLocalAuth(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		if(!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success",false);
			modelMap.put("errMsg","验证码输入错误！");
			return modelMap;
		}
		String userName = HttpServletRequestUtil.getString(request, "userName");
		String password = HttpServletRequestUtil.getString(request, "password");
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
		if(userName != null && password != null && user != null && user.getUserId() != null) {
			LocalAuth localAuth = new LocalAuth();
			localAuth.setUsername(userName);
			localAuth.setPassword(password);
			localAuth.setPersonInfo(user);
			LocalAuthExecution le = localAuthService.bindLocalAuth(localAuth);
			if(le.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
				modelMap.put("success",true);
			}else {
				modelMap.put("success",false);
				modelMap.put("errMsg",le.getStateInfo());
			}
		}else {
			modelMap.put("success",false);
			modelMap.put("errMsg","用户名和密码均不能为空！");
		}
		return modelMap;
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/changelocalpwd",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> changeLocalPwd(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		if(!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success",false);
			modelMap.put("errMsg","验证码输入错误！");
			return modelMap;
		}
		String userName = HttpServletRequestUtil.getString(request, "userName");
		String password = HttpServletRequestUtil.getString(request, "password");
		String newPassword = HttpServletRequestUtil.getString(request, "newPassword");
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
		if(userName != null && password != null && user != null && user.getUserId() != null
				&& !password.equals(newPassword)) {
			try {
				LocalAuth localAuth = localAuthService.getLocalAuthByUserId(user.getUserId());
				if(localAuth == null || localAuth.getUsername().equals(userName)) {
					modelMap.put("success",false);
					modelMap.put("errMsg","输入的帐号非本次登录的帐号！");
					return modelMap;
				}
				LocalAuthExecution le = localAuthService.modifyLocalAuth(user.getUserId(), userName, password, newPassword);
				if(le.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
					modelMap.put("success",true);
				}else {
					modelMap.put("success",false);
					modelMap.put("errMsg",le.getStateInfo());
				}
			} catch (LocalAuthOperationException e) {
				modelMap.put("success",false);
				modelMap.put("errMsg",e.toString());
				return modelMap;
			}
		}else {
			modelMap.put("success",false);
			modelMap.put("errMsg","请输入正确的信息！");
		}
		return modelMap;
	}
	
	@RequestMapping(value="/logincheck",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> logincheck(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		boolean needVerify = HttpServletRequestUtil.getBoolean(request, "needVerify");
		if(needVerify && !CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success",false);
			modelMap.put("errMsg","验证码输入错误！");
			return modelMap;
		}
		String userName = HttpServletRequestUtil.getString(request, "userName");
		String password = HttpServletRequestUtil.getString(request, "password");
		if(userName != null && password != null) {
			LocalAuth localAuth = localAuthService.getLocalAuthByUsernameAndPwd(userName, password);
			if(localAuth != null) {
				modelMap.put("success",true);
				request.getSession().setAttribute("user",localAuth.getPersonInfo());
			}else {
				modelMap.put("success",false);
				modelMap.put("errMsg","用户名或密码错误！");
			}
		}else {
			modelMap.put("success",false);
			modelMap.put("errMsg","用户名或密码均不能为空！");
		}
		return modelMap;
	}
	
	/**
	 * 当用户点击登出按钮时候注销session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> loginout(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		request.getSession().setAttribute("user",null);
		modelMap.put("success",true);
		return modelMap;
	}
}
