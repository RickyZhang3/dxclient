package org.com.dx.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {
	
	private static final Logger log = LoggerFactory.getLogger(HelloController.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/hello")
    public Integer index() {
		
		int count = jdbcTemplate.queryForObject("select count(*) from DMP_EMPLOYEE_SOURCE", Integer.class);
		log.info("测试数据库查询语句:{}",count);
        return count;
    }
}
