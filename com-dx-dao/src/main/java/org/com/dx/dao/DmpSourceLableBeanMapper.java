package org.com.dx.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.com.dx.bean.DmpSourceLableBean;
import org.springframework.jdbc.core.RowMapper;

public class DmpSourceLableBeanMapper implements RowMapper<DmpSourceLableBean> {

	@Override
	public DmpSourceLableBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		DmpSourceLableBean dmpSourceLableBean = new DmpSourceLableBean();
		dmpSourceLableBean.setDmpLableId(rs.getString("dmp_lable_id"));
		dmpSourceLableBean.setDmpLableName(rs.getString("dmp_lable_name").toString());
		
		return dmpSourceLableBean;
	}
	
	
}
