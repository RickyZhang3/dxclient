package org.com.dx.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.com.dx.bean.DmpEmployeeBean;
import org.com.dx.dao.DmpEmployeeBeanMapper;
import org.com.dx.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginServiceImpl implements LoginService,UserDetailsService {
	
	private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public DmpEmployeeBean login(String account, String password) {
		
		StringBuffer sqlBuffer = new StringBuffer();
		
		sqlBuffer = sqlBuffer.append("select * from DMP_EMPLOYEE where ACCOUNT = ? ")
					.append(" and PASSORD = ? and C_FLAG = 0  limit 1 ");//and rownum<=1
		try {
			DmpEmployeeBean dmpEmployeeBean = jdbcTemplate.queryForObject(sqlBuffer.toString(), new DmpEmployeeBeanMapper(),new Object[]{account, password});
			return dmpEmployeeBean;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public DmpEmployeeBean getUserInfo(String account) {
		DmpEmployeeBean dmpEmployeeBean = new DmpEmployeeBean();
		try {
			StringBuffer sqlBuffer = new StringBuffer();

			//oracle
			sqlBuffer = sqlBuffer.append("select t.employee_name,t.account,t.passord,t.role_id,t.phone,n1.role_name,t.channel_id,n.channel_name,to_char(t.cre_time, 'yyyy-mm-dd') as cre_time" +
					" from DMP_EMPLOYEE t, DMP_CHANNEL_LIST n, DMP_ROLE_LIST n1  where t.CHANNEL_ID = n.CHANNEL_ID and t.ROLE_ID = n1.ROLE_ID and t.ACCOUNT = ? ")
					.append(" and t.C_FLAG = '0' and rownum<=1 ");
				dmpEmployeeBean = jdbcTemplate.queryForObject(sqlBuffer.toString(), new DmpEmployeeBeanMapper(),new Object[]{account});
				dmpEmployeeBean.setPassword("");
				log.info("user info:{}",dmpEmployeeBean.getPassword());
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		return dmpEmployeeBean;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("登录重写方法:{}",username);
		UserDetails userDetails = null;     
		try {           
			StringBuffer sqlBuffer = new StringBuffer();
			
			//oracle
			sqlBuffer = sqlBuffer.append("select t.employee_name,t.account,t.passord,t.role_id,t.phone,n1.role_name,t.channel_id,n.channel_name,to_char(t.cre_time, 'yyyy-mm-dd') as cre_time from DMP_EMPLOYEE t, DMP_CHANNEL_LIST n, DMP_ROLE_LIST n1  where t.CHANNEL_ID = n.CHANNEL_ID and t.ROLE_ID = n1.ROLE_ID and t.ACCOUNT = ? ")
						.append(" and t.C_FLAG = '0' and rownum<=1 ");
			
//			sqlBuffer = sqlBuffer.append("select t.* from DMP_EMPLOYEE t, DMP_CHANNEL_LIST n, DMP_ROLE_LIST n1  where t.CHANNEL_ID = n.CHANNEL_ID and t.ROLE_ID = n1.ROLE_ID and t.account = ? ")
//					.append(" and t.C_FLAG = '0' limit 1 ");
			try {
				DmpEmployeeBean dmpEmployeeBean = jdbcTemplate.queryForObject(sqlBuffer.toString(), new DmpEmployeeBeanMapper(),new Object[]{username});
				
				log.info("user info:{}",dmpEmployeeBean.getPassword());
				Collection<GrantedAuthority> authList = getAuthorities(); 
				userDetails = new User(username, new BCryptPasswordEncoder().encode(dmpEmployeeBean.getPassword()),true,true,true,true,authList);     
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}catch (Exception e) {         
			e.printStackTrace();        
		}       
		return userDetails; 
	}
	
	private Collection<GrantedAuthority> getAuthorities(){        
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();       
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));      
		authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));     
		return authList;    
	}

}
