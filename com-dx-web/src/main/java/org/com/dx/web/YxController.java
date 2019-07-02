package org.com.dx.web;

import org.com.dx.bean.DmpFeedBackInfo;
import org.com.dx.bean.DmpFeedBackResultBean;
import org.com.dx.bean.DmpMarketingDetailBean;
import org.com.dx.bean.DmpSourceLableBean;
import org.com.dx.common.RespData;
import org.com.dx.service.YxTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import javax.annotation.Resource;

@RestController
@RequestMapping("/yxTag")
public class YxController {
	
	private static final Logger log = LoggerFactory.getLogger(YxController.class);
	
	@Resource
	private YxTagService yxTagService;
	
	@ApiOperation("营销标签查询接口")
	@RequestMapping(value="/getYxTags", method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public RespData<List<DmpSourceLableBean>> getYxTags() {
		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			if(auth == null) {
				return new RespData(RespData.FAIL, RespData.NOT_LOGIN_ERROR_MSG,null);
			}
			
			log.info("auth:{},",auth.getName());
			List<DmpSourceLableBean> dmpSourceLableBeans =  yxTagService.getTagList(auth.getName());
			
			return new RespData<List<DmpSourceLableBean>>(RespData.SUCCESS, RespData.DEFAULT_MSG, dmpSourceLableBeans);
		} catch (Exception e) {
			log.error("查询异常:{}",e);
			e.printStackTrace();
			return new RespData(RespData.FAIL, RespData.ERROR_MSG,null);
		}
    }
	
	@ApiOperation("营销结果查询接口")
	@RequestMapping(value="/getYxResult", method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public RespData<List<DmpFeedBackInfo>> getYxResult() {
		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			log.info("auth:{}",auth.getName());
			List<DmpFeedBackInfo> dmpFeedBackInfos =  yxTagService.getYxResultList(auth.getName());
			
			return new RespData<List<DmpFeedBackInfo>>(RespData.SUCCESS, RespData.DEFAULT_MSG, dmpFeedBackInfos);
		} catch (Exception e) {
			log.error("查询异常:{}",e);
			e.printStackTrace();
			return new RespData(RespData.FAIL, RespData.ERROR_MSG,null);
		}
    }
	
	@ApiOperation("添加营销接口")
	@RequestMapping(value="/addYxResult", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public RespData<Integer> addYxResult(@ModelAttribute DmpMarketingDetailBean dmpMarketingDetailBean) {
		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			log.info("auth:{},",auth.getName());
			dmpMarketingDetailBean.setEmployeeId(auth.getName());
			
			int count =  yxTagService.addYxResult(dmpMarketingDetailBean);
			
			return new RespData<Integer>(RespData.SUCCESS, RespData.DEFAULT_MSG,count);
		} catch (Exception e) {
			log.error("保存营销信息异常:{}",e);
			e.printStackTrace();
			return new RespData<Integer>(RespData.FAIL, RespData.ERROR_MSG,0);
		}
    }
	
	@ApiOperation("获取营销结果分类接口")
	@RequestMapping(value="/getYxResultFL", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public RespData<DmpFeedBackResultBean> getYxResultFL(@ApiParam(value = "任务id", required = true)@RequestParam("disId") String disId) {
		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			log.info("auth:{},disId:{}",auth.getName(),disId);
			
			List<DmpFeedBackResultBean> dmpFeedBackResultBeans = yxTagService.getYxResultFL(auth.getName(), disId);
			
			return new RespData(RespData.SUCCESS, RespData.DEFAULT_MSG,dmpFeedBackResultBeans);
		} catch (Exception e) {
			log.error("查询营销结果分类信息异常:{}",e);
			e.printStackTrace();
			return new RespData(RespData.FAIL, RespData.ERROR_MSG,null);
		}
    }
}
