package org.com.dx.service;

import java.util.List;

import org.com.dx.bean.DmpFeedBackInfo;
import org.com.dx.bean.DmpFeedBackResultBean;
import org.com.dx.bean.DmpMarketingDetailBean;
import org.com.dx.bean.DmpSourceLableBean;

public interface YxTagService {
	
	List<DmpSourceLableBean> getTagList(String dmpLablePeo);
	
	List<DmpFeedBackInfo> getYxResultList(String dmpLablePeo);
	
	int addYxResult(DmpMarketingDetailBean dmpMarketingDetailBean) throws Exception;
	
	List<DmpFeedBackResultBean> getYxResultFL(String account, String disId);
	
}
