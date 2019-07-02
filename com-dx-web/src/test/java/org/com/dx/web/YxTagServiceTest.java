package org.com.dx.web;

import org.com.dx.Application;
import org.com.dx.bean.DmpMarketingDetailBean;
import org.com.dx.service.YxTagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class YxTagServiceTest {
	
	private static final Logger log = LoggerFactory.getLogger(YxTagServiceTest.class);
	
	@Autowired
    private YxTagService yxTagService;
	
	@Test
	public void addYxResult() throws Exception{
		
		DmpMarketingDetailBean dmpMarketingDetailBean = new DmpMarketingDetailBean();
		dmpMarketingDetailBean.setEmployeeId("1");
		dmpMarketingDetailBean.setDistruId("666");
		dmpMarketingDetailBean.setBudget("budget");
		dmpMarketingDetailBean.setCompany("company");
		dmpMarketingDetailBean.setCallflowId("callflowId");
		dmpMarketingDetailBean.setCustName("custName");
		dmpMarketingDetailBean.setDemand("demand");
		dmpMarketingDetailBean.setFeedbackFirstId("feedbackFirstId");
		dmpMarketingDetailBean.setFeedbackSecId("feedbackSecId");
		dmpMarketingDetailBean.setFlag("0");
		dmpMarketingDetailBean.setLableId("lableId");
		dmpMarketingDetailBean.setMarkingType("1");
		dmpMarketingDetailBean.setRemark("备注");
		dmpMarketingDetailBean.setSourPhone("18823456789");
		dmpMarketingDetailBean.setSourSeq("sourSeq");
		dmpMarketingDetailBean.setWechat("wechat");
		dmpMarketingDetailBean.setTypes("types");
		dmpMarketingDetailBean.setTalkDetail("talkDetail");
		
		log.info("result:{}",yxTagService.addYxResult(dmpMarketingDetailBean));
	}
}
