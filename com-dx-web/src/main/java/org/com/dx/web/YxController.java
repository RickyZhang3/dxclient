package org.com.dx.web;

import org.com.dx.bean.DmpAudioBean;
import org.com.dx.bean.DmpFeedBackInfo;
import org.com.dx.bean.DmpFeedBackResultBean;
import org.com.dx.bean.DmpMarketingDetailBean;
import org.com.dx.bean.DmpSourceLableBean;
import org.com.dx.bean.PdBean;
import org.com.dx.bean.PdResourceBean;
import org.com.dx.common.RespData;
import org.com.dx.dao.Page;
import org.com.dx.service.YxTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/yxTag")
public class YxController {
	
	private static final Logger log = LoggerFactory.getLogger(YxController.class);
	
	@Resource
	private YxTagService yxTagService;
	
	@ApiOperation("营销标签查询接口")
	@RequestMapping(value="/getYxTags", method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public RespData<Page<DmpSourceLableBean>> getYxTags(@ApiParam(value = "页码", required = true)@RequestParam("pageNo") Integer pageNo,
    													@ApiParam(value = "每页数据条数", required = true)@RequestParam("pageSize") Integer pageSize) {
		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			if(auth == null) {
				return new RespData(RespData.FAIL, RespData.NOT_LOGIN_ERROR_MSG,null);
			}
			
			log.info("auth:{},",auth.getName());
			Page<DmpSourceLableBean> page =  yxTagService.getTagList(auth.getName(), pageNo, pageSize);
			
			return new RespData<Page<DmpSourceLableBean>>(RespData.SUCCESS, RespData.DEFAULT_MSG, page);
		} catch (Exception e) {
			log.error("查询异常:{}",e);
			e.printStackTrace();
			return new RespData(RespData.FAIL, RespData.ERROR_MSG,null);
		}
    }
	
	@ApiOperation("营销结果查询接口")
	@RequestMapping(value="/getYxResult", method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public RespData<Page<DmpFeedBackInfo>> getYxResult(@ApiParam(value = "页码", required = true)@RequestParam("pageNo") Integer pageNo,
			@ApiParam(value = "每页数据条数", required = true)@RequestParam("pageSize") Integer pageSize) {
		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			log.info("auth:{}",auth.getName());
			Page<DmpFeedBackInfo> dmpFeedBackInfos =  yxTagService.getYxResultList(auth.getName(), pageNo, pageSize);
			
			return new RespData<Page<DmpFeedBackInfo>>(RespData.SUCCESS, RespData.DEFAULT_MSG, dmpFeedBackInfos);
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
	@RequestMapping(value="/getYxResultFL", method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public RespData<Page<DmpFeedBackResultBean>> getYxResultFL(@ApiParam(value = "页码", required = true)@RequestParam("pageNo") Integer pageNo,
			@ApiParam(value = "每页数据条数", required = true)@RequestParam("pageSize") Integer pageSize,
			@ApiParam(value = "任务id", required = true)@RequestParam("disId") String disId) {
		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			log.info("auth:{},disId:{}",auth.getName(),disId);
			
			Page<DmpFeedBackResultBean> dmpFeedBackResultBeans = yxTagService.getYxResultFL(auth.getName(), disId, pageNo, pageSize);
			
			return new RespData(RespData.SUCCESS, RespData.DEFAULT_MSG,dmpFeedBackResultBeans);
		} catch (Exception e) {
			log.error("查询营销结果分类信息异常:{}",e);
			e.printStackTrace();
			return new RespData(RespData.FAIL, RespData.ERROR_MSG,null);
		}
    }
	
	@ApiOperation("查询派单接口")
	@RequestMapping(value="/getPd", method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public RespData<Page<PdBean>> getPd(@ApiParam(value = "页码", required = true)@RequestParam("pageNo") Integer pageNo,
			@ApiParam(value = "每页数据条数", required = true)@RequestParam("pageSize") Integer pageSize) {
		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			log.info("auth:{}",auth.getName());
			
			Page<PdBean> pdBeans = yxTagService.getPd(auth.getName(), pageNo, pageSize);
			return new RespData(RespData.SUCCESS, RespData.DEFAULT_MSG,pdBeans);
		} catch (Exception e) {
			log.error("查询派单异常:{}",e);
			e.printStackTrace();
			return new RespData(RespData.FAIL, RespData.ERROR_MSG,null);
		}
    }
	
	@ApiOperation("查询派单资源接口")
	@RequestMapping(value="/getPdResource", method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public RespData<PdResourceBean> getPdResource(@ApiParam(value = "任务id", required = true)@RequestParam("disId") String disId, @ApiParam(value = "营销结果id，flag 为1时传递", required = true)@RequestParam("feedbackSecId") String feedbackSecId,@ApiParam(value = "flag", required = true)@RequestParam("flag") String flag) {
		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			log.info("auth:{},disId:{}",auth.getName(),disId);
			
			List<PdResourceBean> pdResourceBeans = yxTagService.getPdResource(auth.getName(), disId, feedbackSecId, flag);
			
			return new RespData(RespData.SUCCESS, RespData.DEFAULT_MSG,pdResourceBeans);
		} catch (Exception e) {
			log.error("查询派单资源异常:{}",e);
			e.printStackTrace();
			return new RespData(RespData.FAIL, RespData.ERROR_MSG,null);
		}
    }
	
	@ApiOperation("营销录音信息查询接口")
	@RequestMapping(value="/getAudioListJsonp", method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public void getAudioListJsonp(@ApiParam(value = "页码", required = true)@RequestParam("pageNo") Integer pageNo,
			@ApiParam(value = "每页数据条数", required = true)@RequestParam("pageSize") Integer pageSize,
			@RequestParam("username") String username,HttpServletRequest request
			, HttpServletResponse response) throws IOException {
		response.setContentType("text/javascript");
		response.setCharacterEncoding("UTF-8");
		String jsonpCallback = request.getParameter("callback");
		try {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			log.info("auth:{}",username);
			Page<DmpAudioBean> dmpAudiosPage =  yxTagService.getAudioList(username, pageNo, pageSize);
			
//			return new RespData<Page<DmpAudioBean>>(RespData.SUCCESS, RespData.DEFAULT_MSG, dmpAudiosPage);
			RespData<Page<DmpAudioBean>> respData =  new RespData<Page<DmpAudioBean>>(RespData.SUCCESS, RespData.DEFAULT_MSG, dmpAudiosPage);
			response.getWriter().write(jsonpCallback + "(" + JSON.toJSONString(respData) + ")");
//			return jsonpCallback + "(" + JSON.toJSONString(respData) + ")";
//			return JSON.toJSONString(respData);
		} catch (Exception e) {
			log.error("查询异常:{}",e);
			e.printStackTrace();
//			return new RespData(RespData.FAIL, RespData.ERROR_MSG,null);
//			return jsonpCallback + "(" + JSON.toJSONString(new RespData(RespData.FAIL, RespData.ERROR_MSG,null)) + ")";
//			return JSON.toJSONString(new RespData(RespData.FAIL, RespData.ERROR_MSG,null));
			response.getWriter().write(jsonpCallback + "(" + JSON.toJSONString(new RespData(RespData.FAIL, RespData.ERROR_MSG,null)) + ")");
		}
    }
	
	@ApiOperation("营销录音信息查询接口")
	@RequestMapping(value="/getAudioList", method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public RespData<Page<DmpAudioBean>> getAudioList(@ApiParam(value = "页码", required = true)@RequestParam("pageNo") Integer pageNo,
			@ApiParam(value = "每页数据条数", required = true)@RequestParam("pageSize") Integer pageSize , @RequestParam("username") String username) {
		try {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			log.info("auth:{}",username);
			Page<DmpAudioBean> dmpAudiosPage =  yxTagService.getAudioList(username, pageNo, pageSize);
			
			return new RespData<Page<DmpAudioBean>>(RespData.SUCCESS, RespData.DEFAULT_MSG, dmpAudiosPage);
		} catch (Exception e) {
			log.error("查询异常:{}",e);
			e.printStackTrace();
			return new RespData(RespData.FAIL, RespData.ERROR_MSG,null);
		}
    }
}
