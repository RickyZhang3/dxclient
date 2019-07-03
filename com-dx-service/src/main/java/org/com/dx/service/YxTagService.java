package org.com.dx.service;

import java.util.List;

import org.com.dx.bean.DmpAudioBean;
import org.com.dx.bean.DmpFeedBackInfo;
import org.com.dx.bean.DmpFeedBackResultBean;
import org.com.dx.bean.DmpMarketingDetailBean;
import org.com.dx.bean.DmpSourceLableBean;
import org.com.dx.bean.PdBean;
import org.com.dx.bean.PdResourceBean;
import org.com.dx.dao.Page;

public interface YxTagService {
	
	Page<DmpSourceLableBean> getTagList(String account, Integer pageNo, Integer pageSize);
	
	Page<DmpFeedBackInfo> getYxResultList(String account, Integer pageNo, Integer pageSize);
	
	int addYxResult(DmpMarketingDetailBean dmpMarketingDetailBean) throws Exception;
	
	Page<DmpFeedBackResultBean> getYxResultFL(String account, String disId, Integer pageNo, Integer pageSize);
	
	List<PdResourceBean> getPdResource(String account, String disId,String feedbackSecId, String flag);
	
	Page<PdBean> getPd(String account, Integer pageNo, Integer pageSize);
	
	Page<DmpAudioBean> getAudioList(String account, Integer pageNo, Integer pageSize);
	
	
}
