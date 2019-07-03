package org.com.dx.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.com.dx.bean.PdBean;
import org.springframework.jdbc.core.RowMapper;

public class PdBeanMapper implements RowMapper<PdBean> {

	@Override
	public PdBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		PdBean pdBean = new PdBean();
		
		pdBean.setDistruId(rs.getString("distru_id"));
		pdBean.setDistruName(rs.getString("distru_name"));
		pdBean.setDoCount(rs.getString("sour_count"));
		pdBean.setSourCount(rs.getString("do_count"));
		pdBean.setTim(rs.getString("tim"));
		
		return pdBean;
	}

}
