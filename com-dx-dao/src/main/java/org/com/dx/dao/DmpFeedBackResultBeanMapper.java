package org.com.dx.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.com.dx.bean.DmpFeedBackResultBean;
import org.springframework.jdbc.core.RowMapper;

public class DmpFeedBackResultBeanMapper implements RowMapper<DmpFeedBackResultBean> {

	@Override
	public DmpFeedBackResultBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		DmpFeedBackResultBean dmpFeedBackResultBean = new DmpFeedBackResultBean();
		dmpFeedBackResultBean.setCount(rs.getInt("c_count"));
		dmpFeedBackResultBean.setFeedbackName(rs.getString("feedback_name"));
		dmpFeedBackResultBean.setFeedbackSecId(rs.getString("feedback_sec_id"));
		
		return dmpFeedBackResultBean;
	}

}
