package org.com.dx.service;

import org.com.dx.bean.DmpEmployeeBean;

public interface LoginService {
	
	DmpEmployeeBean login(String account,String password);
}
