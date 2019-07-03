package org.com.dx.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.com.dx.bean.DmpAudioBean;
import org.springframework.jdbc.core.RowMapper;

public class DmpAudioBeanMapper implements RowMapper<DmpAudioBean> {

	@Override
	public DmpAudioBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		DmpAudioBean dmpAudioBean = new DmpAudioBean();
		
		dmpAudioBean.setSourPhone(rs.getString("sour_phone"));
		dmpAudioBean.setCallfollowId(rs.getString("callflowid"));
		dmpAudioBean.setMarketTime(rs.getString("marking_time"));
		
		return dmpAudioBean;
	}

}
