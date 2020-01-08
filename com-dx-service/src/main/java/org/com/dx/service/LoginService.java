package org.com.dx.service;

import org.com.dx.bean.DmpEmployeeBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface LoginService {
	
	DmpEmployeeBean login(String account,String password);

	DmpEmployeeBean getUserInfo(String account);
}
