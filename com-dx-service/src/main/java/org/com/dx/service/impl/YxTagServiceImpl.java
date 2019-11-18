package org.com.dx.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.commons.io.input.DemuxInputStream;
import org.com.dx.bean.DmpAudioBean;
import org.com.dx.bean.DmpEmployeeBean;
import org.com.dx.bean.DmpFeedBackInfo;
import org.com.dx.bean.DmpFeedBackResultBean;
import org.com.dx.bean.DmpMarketingDetailBean;
import org.com.dx.bean.DmpSourceLableBean;
import org.com.dx.bean.PdBean;
import org.com.dx.bean.PdResourceBean;
import org.com.dx.dao.DmpAudioBeanMapper;
import org.com.dx.dao.DmpEmployeeBeanMapper;
import org.com.dx.dao.DmpFeedBackInfoMapper;
import org.com.dx.dao.DmpFeedBackResultBeanMapper;
import org.com.dx.dao.DmpSourceLableBeanMapper;
import org.com.dx.dao.Page;
import org.com.dx.dao.PaginationHelper;
import org.com.dx.dao.PdBeanMapper;
import org.com.dx.dao.PdResourceBeanMapper;
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
	
	private static final Integer PAGE_SIZE = 15;
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 

	@Override
	public Page<DmpSourceLableBean> getTagList(String account, Integer pageNo, Integer pageSize) {
		
		StringBuffer sqlCount  = new StringBuffer();
		StringBuffer stringBuffer  = new StringBuffer();
//		List<DmpSourceLableBean> lableBeans = new ArrayList<DmpSourceLableBean>();
		//oracle
//		sqlCount.append(" select count(0) from DMP_SOURCE_LABLE t,DMP_EMPLOYEE n where t.dmp_lable_peo=n.creater ")
//				   .append("and n.account = ? ").append(" and upper(t.dmp_lable_flag) = 'Y' order by t.dmp_lable_num asc");
//		
//		stringBuffer.append(" select t.dmp_lable_id,t.dmp_lable_name from DMP_SOURCE_LABLE t,DMP_EMPLOYEE n where t.dmp_lable_peo=n.creater ")
//					.append(" and n.account = ? ")
//					.append(" and upper(t.dmp_lable_flag) = 'Y' order by t.dmp_lable_num asc");
		
		//mysql
		sqlCount.append(" select count(0) from DMP_SOURCE_LABLE t,DMP_EMPLOYEE n where t.dmp_lable_peo=n.creater ")
		   .append(" and n.account = ? ").append(" and t.dmp_lable_flag = 'Y' order by t.dmp_lable_num asc");

		stringBuffer.append(" select t.dmp_lable_id,t.dmp_lable_name from DMP_SOURCE_LABLE t,DMP_EMPLOYEE n where t.dmp_lable_peo=n.creater ")
			.append(" and n.account = ? ")
			.append(" and t.dmp_lable_flag = 'Y' order by t.dmp_lable_num asc");
		
		PaginationHelper<DmpSourceLableBean> paginationHelper = new PaginationHelper<DmpSourceLableBean>();
		
		if(pageSize < 1) {
        	pageSize = PAGE_SIZE.intValue();
        }
		
		Page<DmpSourceLableBean> lablePage = paginationHelper.fetchPage(jdbcTemplate, sqlCount.toString(), stringBuffer.toString(), new Object[] {account}, pageNo, pageSize, new DmpSourceLableBeanMapper());
		
//		List<Map<String,Object>> rowMaps =  jdbcTemplate.queryForList(stringBuffer.toString(),new Object[] {account});
//		for(Map<String,Object> rowMap:rowMaps)	{
//			DmpSourceLableBean dmpSourceLableBean = new DmpSourceLableBean();
//			dmpSourceLableBean.setDmpLableId(rowMap.get("dmp_lable_id").toString());
//			dmpSourceLableBean.setDmpLableName(rowMap.get("dmp_lable_name").toString());
//			lableBeans.add(dmpSourceLableBean);
//		}
		return lablePage;
	}

	@Override
	public Page<DmpFeedBackInfo> getYxResultList(String account, Integer pageNo, Integer pageSize) {
		StringBuffer sqlCount  = new StringBuffer();
		StringBuffer stringBuffer  = new StringBuffer();
//		List<DmpFeedBackInfo> lableBeans = new ArrayList<DmpFeedBackInfo>();
		PaginationHelper<DmpFeedBackInfo> paginationHelper = new PaginationHelper<DmpFeedBackInfo>();
		
		sqlCount.append("select count(0) from DMP_FEEDBACK_INFO t where t.feedback_personal= ?")
		.append(" and t.feedback_flag='Y' order by t.feedback_num");
		
		stringBuffer.append("select t.feedback_id,t.feedback_name from DMP_FEEDBACK_INFO t where t.feedback_personal= ?")
					.append(" and t.feedback_flag='Y' order by t.feedback_num");
		
		if(pageSize < 1) {
        	pageSize = PAGE_SIZE.intValue();
        }
		Page<DmpFeedBackInfo> dPage = paginationHelper.fetchPage(jdbcTemplate, sqlCount.toString(), stringBuffer.toString(), new Object[]{account}, pageNo, pageSize, new DmpFeedBackInfoMapper());
		
//		List<Map<String,Object>> rowMaps =  jdbcTemplate.queryForList(stringBuffer.toString());
//		for(Map<String,Object> rowMap:rowMaps)	{
//			DmpFeedBackInfo dmpSourceLableBean = new DmpFeedBackInfo();
//			dmpSourceLableBean.setFeedbackId(rowMap.get("feedback_id").toString());
//			dmpSourceLableBean.setFeedbackName(rowMap.get("feedback_name").toString());
//			lableBeans.add(dmpSourceLableBean);
//		}
		return dPage;
	}

	@Override
	@Transactional
	public int addYxResult(DmpMarketingDetailBean dmpMarketingDetailBean) throws Exception {
		
		StringBuffer stringBuffer  = new StringBuffer();
		StringBuffer stringBuffer1  = new StringBuffer();
		dmpMarketingDetailBean.setSourPhone(String.valueOf(Long.valueOf(dmpMarketingDetailBean.getSourPhone())+Long.valueOf(NUM_SUFFIX)));
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
	public Page<DmpFeedBackResultBean> getYxResultFL(String account, String disId, Integer pageNo, Integer pageSize) {
		
		PaginationHelper<DmpFeedBackResultBean> paginationHelper = new PaginationHelper<DmpFeedBackResultBean>();
		
		StringBuffer sqlCount  = new StringBuffer();
		StringBuffer stringBuffer  = new StringBuffer();
		
			sqlCount.append(" select count(0) from (")
					.append(" select m.feedback_sec_id feedback_sec_id ,nvl(m1.feedback_name, '未拨打客户') feedback_name, count(1) c_count ")
					.append(" from (select t.sour_phone, nvl(n.feedback_sec_id, '0') feedback_sec_id ")
					.append(" from dmp_employee_source t, ").append(" (select * from (select a.*, ")
					.append(" row_number() over(partition by a.distru_id, a.sour_phone order by a.marking_time desc) rn ")
					.append(" from dmp_marketing_detail a where a.distru_id = ?")
					.append(" and a.employee_id = ?) ").append(" where rn = 1) n ")
					.append(" where t.sour_phone = n.sour_phone(+) ")
					.append(" and t.recovery_flag = '0' and t.distru_id = ? ").append(" and t.employee_id = ? ) m, ")
					.append(" DMP_FEEDBACK_INFO m1").append(" where m.feedback_sec_id = m1.feedback_id(+)")
					.append(" group by m.feedback_sec_id, m1.feedback_name").append(" order by m.feedback_sec_id asc ) ");
		
		stringBuffer.append(" select m.feedback_sec_id feedback_sec_id ,nvl(m1.feedback_name, '未拨打客户') feedback_name, count(1) c_count ")
					.append(" from (select t.sour_phone, nvl(n.feedback_sec_id, '0') feedback_sec_id ")
					.append(" from dmp_employee_source t, ").append(" (select * from (select a.*, ")
					.append(" row_number() over(partition by a.distru_id, a.sour_phone order by a.marking_time desc) rn ")
					.append(" from dmp_marketing_detail a where a.distru_id = ?")
					.append(" and a.employee_id = ? )").append(" where rn = 1) n ")
					.append(" where t.sour_phone = n.sour_phone(+) ")
					.append(" and t.recovery_flag = '0' and t.distru_id = ?").append(" and t.employee_id = ? ) m, ")
					.append(" DMP_FEEDBACK_INFO m1").append(" where m.feedback_sec_id = m1.feedback_id(+)")
					.append(" group by m.feedback_sec_id, m1.feedback_name").append(" order by m.feedback_sec_id asc ");
		
		if(pageSize < 1) {
        	pageSize = PAGE_SIZE.intValue();
        }
//		List<DmpFeedBackResultBean> dBackResultBeans = jdbcTemplate.query(stringBuffer.toString(), new DmpFeedBackResultBeanMapper());
		Page<DmpFeedBackResultBean> dBackResultBeans = paginationHelper.fetchPage(jdbcTemplate, sqlCount.toString(), stringBuffer.toString(), new Object[] {disId,account,disId,account}, pageNo, pageSize, new DmpFeedBackResultBeanMapper());	 
					
		return dBackResultBeans;
	}

	@Override
	public List<PdResourceBean> getPdResource(String account,String disId,String feedbackSecId, String flag) {
			StringBuffer stringBuffer  = new StringBuffer();
			List<PdResourceBean> pdResourceBeans = new ArrayList<PdResourceBean>();
			if(flag.equals("0")) {
				stringBuffer.append(" select t.sour_phone,to_char(t.follow_time, 'yyyy-mm-dd') follow_time,t.sour_remark_one,t.sour_remark_two,t.sour_remark_three,t.sour_seq ")
				.append(" from dmp_employee_source t where t.flag = '1' and t.recovery_flag = '0' ")
				.append(" and t.distru_id = ?")
				.append("  and t.employee_id = ? ");
				
				pdResourceBeans = jdbcTemplate.query(stringBuffer.toString(), new PdResourceBeanMapper(),new Object[] {disId,account});
			}else {
				stringBuffer.append(" select t.sour_phone,to_char(t.follow_time, 'yyyy-mm-dd') follow_time,t.sour_remark_one,t.sour_remark_two,t.sour_remark_three,t.sour_seq ")
				.append(" from dmp_employee_source t,  (select * from (select a.*, row_number() over(partition by a.distru_id, a.sour_phone order by a.marking_time desc) rn ")
				.append(" from dmp_marketing_detail a where a.distru_id = ?   and a.employee_id = ?  )   where rn = 1 and feedback_sec_id= ? ) n ")
				.append(" where t.sour_phone = n.sour_phone and t.distru_id = ? and t.employee_id = ?");
				
				pdResourceBeans = jdbcTemplate.query(stringBuffer.toString(), new PdResourceBeanMapper(),new Object[] {disId,account,feedbackSecId,disId,account});
			}
					
		return pdResourceBeans;
	}

	@Override
	public Page<PdBean> getPd(String account, Integer pageNo, Integer pageSize) {
		
		PaginationHelper<PdBean> paginationHelper = new PaginationHelper<PdBean>();
		
		StringBuffer sqlCount  = new StringBuffer();
		StringBuffer stringBuffer  = new StringBuffer();
//		List<PdBean> pdBeans = new ArrayList<PdBean>();
		//oracle 
//		sqlCount.append(" select count(0)  from dmp_employee_distrubute t,dmp_file_upload a ")
//				.append(" where t.account =  ? and t.source_id = a.dmp_sour_id and upper(a.dmp_online_flag) = 'Y' order by t.distru_time desc ");
//		
//		stringBuffer.append(" select t.distru_id,t.distru_name,t.sour_count,(select count(1) from dmp_employee_source n  where n.distru_id = t.distru_id and n.employee_id = t.account")
//					.append(" and n.flag = 0) do_count,to_char(t.distru_time, 'yyyy-mm-dd') tim  from dmp_employee_distrubute t,dmp_file_upload a ")
//					.append(" where t.account =  ? and t.source_id = a.dmp_sour_id and upper(a.dmp_online_flag) = 'Y' order by t.distru_time desc ");
		//mysql
		sqlCount.append(" select count(0)  from DMP_EMPLOYEE_DISTRUBUTE t,dmp_file_upload a ")
				.append(" where t.account =  ? and t.source_id = a.dmp_sour_id and a.dmp_online_flag = 'Y' order by t.distru_time desc ");

		stringBuffer.append(" select t.distru_id,t.distru_name,t.sour_count,(select count(1) from dmp_employee_source n  where n.distru_id = t.distru_id and n.employee_id = t.account")
					.append(" and n.flag = 0) do_count,to_char(t.distru_time, 'yyyy-mm-dd') tim  from DMP_EMPLOYEE_DISTRUBUTE t,dmp_file_upload a ")
					.append(" where t.account =  ? and t.source_id = a.dmp_sour_id and a.dmp_online_flag = 'Y' order by t.distru_time desc ");
			
//			pdBeans = jdbcTemplate.query(stringBuffer.toString(), new PdBeanMapper(),new Object[] {account});
		if(pageSize < 1) {
        	pageSize = PAGE_SIZE.intValue();
        }
		
		Page<PdBean> pdBeans = paginationHelper.fetchPage(jdbcTemplate, sqlCount.toString(), stringBuffer.toString(), new Object[] {account}, pageNo, pageSize, new PdBeanMapper());	
				
		return pdBeans;
	}

	@Override
	public Page<DmpAudioBean> getAudioList(String account, Integer pageNo, Integer pageSize) {
		PaginationHelper<DmpAudioBean> paginationHelper = new PaginationHelper<DmpAudioBean>();
		
		StringBuffer sqlCount  = new StringBuffer();
		StringBuffer stringBuffer  = new StringBuffer();
//		List<PdBean> pdBeans = new ArrayList<PdBean>();

		//mysql
		sqlCount.append(" select count(0)  from DMP_MARKETING_DETAIL t  ")
				.append(" where t.employee_id  =  ? order by t.marking_time desc ");

		stringBuffer.append(" select t.sour_phone,t.callflowid,t.marking_time from DMP_MARKETING_DETAIL t ")
					.append(" where t.employee_id =  ? order by t.marking_time desc ");
			
		if(pageSize < 1) {
        	pageSize = PAGE_SIZE.intValue();
        }
		
		Page<DmpAudioBean> dmpPage = paginationHelper.fetchPage(jdbcTemplate, sqlCount.toString(), stringBuffer.toString(), new Object[] {account}, pageNo, pageSize, new DmpAudioBeanMapper());	
				
		return dmpPage;
	}

}
