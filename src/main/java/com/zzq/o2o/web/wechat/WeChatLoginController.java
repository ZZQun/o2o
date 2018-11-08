package com.zzq.o2o.web.wechat;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zzq.o2o.dto.UserAccessToken;
import com.zzq.o2o.dto.WeChatUser;
import com.zzq.o2o.dto.WechatAuthExecution;
import com.zzq.o2o.entity.PersonInfo;
import com.zzq.o2o.entity.WechatAuth;
import com.zzq.o2o.enums.WechatAuthStateEnum;
import com.zzq.o2o.service.PersonInfoService;
import com.zzq.o2o.service.WechatAuthService;
import com.zzq.o2o.util.wechat.WeChatUtil;


@Controller
@RequestMapping("wechatlogin")
/**
 * 
 * @author ZZQun
 *https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx55beeb243f497a10&redirect_uri=http://47.107.140.239/o2o/wechatlogin/logincheck&role_type=1&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
 */
public class WeChatLoginController {

	private static Logger log = LoggerFactory
			.getLogger(WeChatLoginController.class);

	@Autowired
	private PersonInfoService personInfoService;
	@Autowired
	private WechatAuthService wechatAuthService;

//	@Resource
//	private ShopService shopService;

//	@Resource
//	private ShopAuthMapService shopAuthMapService;

	private static final String FRONTEND = "1";
	private static final String SHOPEND = "2";

	@RequestMapping(value = "/logincheck", method = { RequestMethod.GET })
	public String doGet(HttpServletRequest request, HttpServletResponse response) {
		log.debug("weixin login get...");
		String code = request.getParameter("code");
		String roleType = request.getParameter("state");
		log.debug("weixin login code:" + code);
		WeChatUser user = null;
		String openId = null;
		WechatAuth auth = null;
		if (null != code) {
			UserAccessToken token;
			try {
				token = WeChatUtil.getUserAccessToken(code);
				log.debug("weixin login token:" + token.toString());
				String accessToken = token.getAccessToken();
				openId = token.getOpenId();
				user = WeChatUtil.getUserInfo(accessToken, openId);
				log.debug("weixin login user:" + user.toString());
				request.getSession().setAttribute("openId", openId);
				auth = wechatAuthService.getWechatAuthByOpenId(openId);
			} catch (IOException e) {
				log.error("error in getUserAccessToken or getUserInfo or findByOpenId: "
						+ e.toString());
				e.printStackTrace();
			}
		}
		if(auth == null) {
			PersonInfo personInfo = WeChatUtil.getPersonInfoFromRequest(user);
			auth = new WechatAuth();
			auth.setOpenId(openId);
			if (FRONTEND.equals(roleType)) {
				personInfo.setUserType(1);
			}else {
				personInfo.setUserType(2);
			}
			auth.setPersonInfo(personInfo);
			WechatAuthExecution we = wechatAuthService.register(auth);
			if(we.getState() != WechatAuthStateEnum.SUCCESS.getState()) {
				return null;
			}else {
				personInfo = personInfoService.getPersonInfoById(auth.getPersonInfo().getUserId());
				request.getSession().setAttribute("user",personInfo);
			}
		}
		//若用户点击的是前端展示系统按钮则进入前端展示系统
		if (FRONTEND.equals(roleType)) {
			return "frontend/index";
		}else {
			return "shop/shoplist";
		}
	}
}
