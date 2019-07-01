package org.com.dx.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.com.dx.bean.DmpFeedBackInfo;
import org.com.dx.bean.DmpSourceLableBean;
import org.com.dx.service.YxTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class YxTagServiceImpl implements YxTagService {
	
	private static final Logger log = LoggerFactory.getLogger(YxTagServiceImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 

	@Override
	public List<DmpSourceLableBean> getTagList(String dmpLablePeo) {
		
		StringBuffer stringBuffer  = new StringBuffer();
		List<DmpSourceLableBean> lableBeans = new ArrayList<DmpSourceLableBean>();
		
		stringBuffer.append("select t.dmp_lable_id,t.dmp_lable_name from DMP_SOURCE_LABLE t where t.dmp_lable_peo=")
					.append(dmpLablePeo)
					.append(" and t.dmp_lable_flag='Y' order by t.dmp_lable_num");
		
		List<Map<String,Object>> rowMaps =  jdbcTemplate.queryForList(stringBuffer.toString());
		for(Map<String,Object> rowMap:rowMaps)	{
			DmpSourceLableBean dmpSourceLableBean = new DmpSourceLableBean();
			dmpSourceLableBean.setDmpLableId(rowMap.get("dmp_lable_id").toString());
			dmpSourceLableBean.setDmpLableName(rowMap.get("dmp_lable_name").toString());
			lableBeans.add(dmpSourceLableBean);
		}
		return lableBeans;
	}

	@Override
	public List<DmpFeedBackInfo> getYxResultList(String dmpLablePeo) {
		StringBuffer stringBuffer  = new StringBuffer();
		List<DmpFeedBackInfo> lableBeans = new ArrayList<DmpFeedBackInfo>();
		
		stringBuffer.append("select t.feedback_id,t.feedback_name from DMP_FEEDBACK_INFO t where t.feedback_personal=")
					.append(dmpLablePeo)
					.append(" and t.feedback_flag='Y' order by t.feedback_num");
		
		List<Map<String,Object>> rowMaps =  jdbcTemplate.queryForList(stringBuffer.toString());
		for(Map<String,Object> rowMap:rowMaps)	{
			DmpFeedBackInfo dmpSourceLableBean = new DmpFeedBackInfo();
			dmpSourceLableBean.setFeedbackId(rowMap.get("feedback_id").toString());
			dmpSourceLableBean.setFeedbackName(rowMap.get("feedback_name").toString());
			lableBeans.add(dmpSourceLableBean);
		}
		return lableBeans;
	}

}
