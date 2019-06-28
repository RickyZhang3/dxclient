package org.com.dx.service.impl;

import org.com.dx.bean.DmpEmployeeBean;
import org.com.dx.dao.DmpEmployeeBeanMapper;
import org.com.dx.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
	
	private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public DmpEmployeeBean login(String account, String password) {
		
		StringBuffer sqlBuffer = new StringBuffer();
		
		sqlBuffer = sqlBuffer.append("select * from DMP_EMPLOYEE where ACCOUNT = ？")
					.append(" and PASSORD = ？ and C_FLAG = 0  and rownum<=1 ");
		
		DmpEmployeeBean dmpEmployeeBean = jdbcTemplate.queryForObject(sqlBuffer.toString(), new DmpEmployeeBeanMapper(),account, password);
		return dmpEmployeeBean;
	}

}
