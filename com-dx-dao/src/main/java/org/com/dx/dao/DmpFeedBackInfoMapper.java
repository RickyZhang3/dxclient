package org.com.dx.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.com.dx.bean.DmpFeedBackInfo;
import org.springframework.jdbc.core.RowMapper;

public class DmpFeedBackInfoMapper implements RowMapper<DmpFeedBackInfo> {

	@Override
	public DmpFeedBackInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		DmpFeedBackInfo dmpFeedBackInfo = new DmpFeedBackInfo();
		dmpFeedBackInfo.setFeedbackId(rs.getString("feedback_id").toString());
		dmpFeedBackInfo.setFeedbackName(rs.getString("feedback_name").toString());
		return dmpFeedBackInfo;
	}
	

}
