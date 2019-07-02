package org.com.dx.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.commons.io.input.DemuxInputStream;
import org.com.dx.bean.DmpEmployeeBean;
import org.com.dx.bean.DmpFeedBackInfo;
import org.com.dx.bean.DmpFeedBackResultBean;
import org.com.dx.bean.DmpMarketingDetailBean;
import org.com.dx.bean.DmpSourceLableBean;
import org.com.dx.dao.DmpEmployeeBeanMapper;
import org.com.dx.dao.DmpFeedBackResultBeanMapper;
import org.com.dx.service.YxTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class YxTagServiceImpl implements YxTagService {
	
	private static final Logger log = LoggerFactory.getLogger(YxTagServiceImpl.class);
	
	private static final String NUM_SUFFIX = "8923152";
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 

	@Override
	public List<DmpSourceLableBean> getTagList(String account) {
		
		StringBuffer stringBuffer  = new StringBuffer();
		List<DmpSourceLableBean> lableBeans = new ArrayList<DmpSourceLableBean>();
		
		stringBuffer.append("select t.dmp_lable_id,t.dmp_lable_name from DMP_SOURCE_LABLE t,DMP_EMPLOYEE n where t.DMP_LABLE_PEO=n.CREATER ")
					.append("and n.account = ").append(account)
					.append(" and upper(t.DMP_LABLE_FLAG) = 'Y' order by t.DMP_LABLE_NUM asc");
		
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

	@Override
	@Transactional
	public int addYxResult(DmpMarketingDetailBean dmpMarketingDetailBean) throws Exception {
		
		StringBuffer stringBuffer  = new StringBuffer();
		StringBuffer stringBuffer1  = new StringBuffer();
		dmpMarketingDetailBean.setSourPhone(dmpMarketingDetailBean.getSourPhone()+NUM_SUFFIX);
		//oracle
//		stringBuffer.append("insert into DMP_MARKETING_DETAIL  (DISTRU_ID,EMPLOYEE_ID,SOUR_PHONE,MARKING_TIME,FEEDBACK_FIRST_ID")
//		.append(" ,FEEDBACK_SEC_ID,CALLFLOWID,SOUR_SEQ,MARKING_TYPE,CUST_NAME,WECHAT,COMPANY,DEMAND,BUDGET,TALK_DETAIL,FLAG")
//		.append(" ,TYPES,LABLE_ID,REMARK) values ( ?,?,?,sysdate,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )");
		
		stringBuffer.append("insert into DMP_MARKETING_DETAIL  (DISTRU_ID,EMPLOYEE_ID,SOUR_PHONE,MARKING_TIME,FEEDBACK_FIRST_ID")
					.append(" ,FEEDBACK_SEC_ID,CALLFLOWID,SOUR_SEQ,MARKING_TYPE,CUST_NAME,WECHAT,COMPANY,DEMAND,BUDGET,TALK_DETAIL,FLAG")
					.append(" ,TYPES,LABLE_ID,REMARK) values ( ?,?,?,now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )");
		
		log.info("insert sql:{}",stringBuffer.toString());
		
		log.info("add yxResult params:{}",dmpMarketingDetailBean.getValues().length);
		
		stringBuffer1.append(" update DMP_EMPLOYEE_SOURCE t ")
					 .append(" set t.flag = '0', t.follow_time = sysdate")
					 .append(" where t.employee_id = ? ")
					 .append(" and t.distru_id = ?  ")
					 .append(" and t.sour_phone = ? ");
		
		log.info("update sql:{}",stringBuffer1.toString());
		
		int updateResult = jdbcTemplate.update(stringBuffer1.toString(), new Object[]{dmpMarketingDetailBean.getEmployeeId(), dmpMarketingDetailBean.getDistruId(), dmpMarketingDetailBean.getSourPhone()});
		
		log.info("更新资源表结果:{}",updateResult);
		
		return jdbcTemplate.update(stringBuffer.toString(),dmpMarketingDetailBean.getValues());
	}

	@Override
	public List<DmpFeedBackResultBean> getYxResultFL(String account, String disId) {
		StringBuffer stringBuffer  = new StringBuffer();
		
		stringBuffer.append(" select m.feedback_sec_id,nvl(m1.feedback_name, '未拨打客户') feedback_name, count(1) c_count ")
					.append(" from (select t.sour_phone, nvl(n.feedback_sec_id, '0') feedback_sec_id ")
					.append(" from dmp_employee_source t, ").append(" (select * from (select a.*, ")
					.append(" row_number() over(partition by a.distru_id, a.sour_phone order by a.marking_time desc) rn ")
					.append(" rom dmp_marketing_detail a where a.distru_id = ").append(disId)
					.append(" and a.employee_id = ").append(account).append(" where rn = 1) n ")
					.append(" where t.sour_phone = n.sour_phone(+) ")
					.append(" and t.recovery_flag = '0' and t.distru_id = ").append(disId).append(" and t.employee_id = ").append(account).append(" ) m, ")
					.append(" DMP_FEEDBACK_INFO m1").append(" where m.feedback_sec_id = m1.feedback_id(+)")
					.append(" roup by m.feedback_sec_id, m1.feedback_name").append(" order by m.feedback_sec_id asc");
		
		List<DmpFeedBackResultBean> dBackResultBeans = jdbcTemplate.query(stringBuffer.toString(), new DmpFeedBackResultBeanMapper());
					
		return dBackResultBeans;
	}

}
