package org.com.dx.web;


import javax.annotation.Resource;
import org.com.dx.bean.DmpEmployeeBean;
import org.com.dx.common.RespData;
import org.com.dx.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Resource
	LoginService LoginService;
	
	@ApiOperation("登录接口")
	@RequestMapping(value="/submit", method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public RespData<DmpEmployeeBean> submit(@ApiParam(value = "员工账号", required = true) @RequestParam("account") String account,
    												 @ApiParam(value = "密码", required = true) @RequestParam("password") String password) {
		
		DmpEmployeeBean dmpEmployeeBean = LoginService.login(account, password);
		
		return new RespData<DmpEmployeeBean>(RespData.SUCCESS,RespData.DEFAULT_MSG,dmpEmployeeBean);
		
    }
}
