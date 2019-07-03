package org.com.dx.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.com.dx.bean.PdResourceBean;
import org.springframework.jdbc.core.RowMapper;

public class PdResourceBeanMapper implements RowMapper<PdResourceBean> {

	@Override
	public PdResourceBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PdResourceBean pdResourceBean = new PdResourceBean();
		
		pdResourceBean.setSourPhone(rs.getString("sour_phone"));
		pdResourceBean.setFollowTime(rs.getString("follow_time"));
		pdResourceBean.setSourceMarkOne(rs.getString("sour_remark_one"));
		pdResourceBean.setSourceMarkTwo(rs.getString("sour_remark_two"));
		pdResourceBean.setSourceMarkThree(rs.getString("sour_remark_three"));
		pdResourceBean.setSourSeq(rs.getString("sour_seq"));
		
		return pdResourceBean;
	}

}
