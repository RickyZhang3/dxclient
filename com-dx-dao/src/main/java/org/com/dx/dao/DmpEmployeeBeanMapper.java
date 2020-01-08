package org.com.dx.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.com.dx.bean.DmpEmployeeBean;
import org.springframework.jdbc.core.RowMapper;

public class DmpEmployeeBeanMapper implements RowMapper<DmpEmployeeBean> {

	@Override
	public DmpEmployeeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		DmpEmployeeBean dmpEmployeeBean = new DmpEmployeeBean();
		
		dmpEmployeeBean.setAccount(rs.getString("ACCOUNT"));
//		dmpEmployeeBean.setEmployeeId(rs.getString("EMPLOYEE_ID"));
		dmpEmployeeBean.setPassword(rs.getString("PASSORD"));
		dmpEmployeeBean.setEmployeeName(rs.getString("employee_name"));
		dmpEmployeeBean.setChannelName(rs.getString("channel_name"));
		dmpEmployeeBean.setChannelId(rs.getString("channel_id"));
		dmpEmployeeBean.setCreTime(rs.getString("cre_time"));
		dmpEmployeeBean.setPhone(rs.getString("phone"));
		dmpEmployeeBean.setRoleName(rs.getString("role_name"));
		dmpEmployeeBean.setRoleId(rs.getString("role_id"));
		return dmpEmployeeBean;
	}

}
