package org.com.dx.web;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.com.dx.bean.DmpEmployeeBean;
import org.com.dx.common.RespData;
import org.com.dx.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/dxlogin")
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
//	@Resource
//	LoginService LoginService;
	
//	@ApiOperation("登录接口")
//	@RequestMapping(value="/submit", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
//    public RespData<DmpEmployeeBean> submit(@ApiParam(value = "员工账号", required = true) @RequestParam("account") String account,
//    												 @ApiParam(value = "密码", required = true) @RequestParam("password") String password) {
//			
//		try {	
//			DmpEmployeeBean dmpEmployeeBean = LoginService.login(account.trim(), password.trim());
//			if(dmpEmployeeBean!=null) {
//				return new RespData<DmpEmployeeBean>(RespData.SUCCESS,RespData.DEFAULT_MSG,dmpEmployeeBean);
//			}else {
//				return new RespData<DmpEmployeeBean>(RespData.FAIL,RespData.LOGIN_ERROR_MSG,null);
//			}
//		}catch (Exception e) {
//			log.error("登录异常：{}",e);
//			e.printStackTrace();
//		}
//		return null;
//    }
	
	@ApiOperation("拦截未登录")
	@RequestMapping(value="/nologin", method = {RequestMethod.GET,RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public RespData<String> nologin() {
		return new RespData<String>(RespData.FAIL,RespData.NOT_LOGIN_ERROR_MSG,"请先登录");
    }
	
	@ApiOperation("登录成功跳转信息，返回用户cookie")
	@RequestMapping(value="/success", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public RespData<Map<String,String>> successs(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		
		try {
			Collection<String> cStrings = httpServletResponse.getHeaders("Set-Cookie");
			Map<String, String> map = new HashMap<String,String>();
			String[] strings = {};
			for(String cooString:cStrings) {
				log.info("cooString:{}",cooString);
				strings = cooString.split(";");
				map.put(strings[0].split("=")[0], strings[0].split("=")[1]);
				
			}
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			log.info("userInfo:{}",auth.getName());
			map.put("username",auth.getName());
			return new RespData<Map<String,String>>(RespData.SUCCESS,RespData.DEFAULT_MSG,map);
		}catch (Exception e) {
			e.printStackTrace();
			return new RespData<Map<String,String>>(RespData.FAIL,RespData.ERROR_MSG,null);
		} 
//		Map<String, String> map = new HashMap<String,String>();
//		Cookie[] cookies = httpServletRequest.getCookies();
//		for(Cookie cookie:cookies) {
//			log.info("cookie.getName():{},cookie.getValue():{}",cookie.getName(),cookie.getValue());
//			map.put(cookie.getName(), cookie.getValue());
//		}
		
    }
}
