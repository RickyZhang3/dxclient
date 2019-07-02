package org.com.dx.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class DmpMarketingDetailBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4885394360410255414L;
	
	@ApiModelProperty(name = "distruId", value = "派单id",required = true)
	private String distruId;
	
	@ApiModelProperty(name = "employeeId", value = "员工id",required = true)
	private String employeeId;
	
	@ApiModelProperty(name = "sourPhone", value = "资源号码",required = true)
	private String sourPhone;
	
	@ApiModelProperty(name = "markingTime", value = "营销时间",required = false)
	private Date markingTime;
	
	@ApiModelProperty(name = "feedbackFirstId", value = "首次营销id",required = false)
	private String feedbackFirstId;
	
	@ApiModelProperty(name = "feedbackSecId", value = "二次营销id",required = false)
	private String feedbackSecId;
	
	@ApiModelProperty(name = "callflowId", value = "跟踪id",required = false)
	private String callflowId;
	
	@ApiModelProperty(name = "sourSeq", value = "资源序号",required = false)
	private String sourSeq;
	
	@ApiModelProperty(name = "markingType", value = "营销类型",required = false)
	private String markingType;
	
	@ApiModelProperty(name = "custName", value = "客户姓名",required = true)
	private String custName;
	
	@ApiModelProperty(name = "wechat", value = "客户微信",required = false)
	private String wechat;
	
	@ApiModelProperty(name = "company", value = "公司",required = false)
	private String company;
	
	@ApiModelProperty(name = "demand", value = "demand",required = false)
	private String demand;
	
	@ApiModelProperty(name = "budget", value = "budget",required = false)
	private String budget;
	
	@ApiModelProperty(name = "talkDetail", value = "talkDetail",required = false)
	private String talkDetail;
	
	@ApiModelProperty(name = "flag", value = "营销标识 0:已营销 1：未营销",required = false)
	private String flag;
	
	@ApiModelProperty(name = "types", value = "types",required = false)
	private String types;
	
	@ApiModelProperty(name = "lableId", value = "lableId",required = false)
	private String lableId;
	
	@ApiModelProperty(name = "remark", value = "备注",required = false)
	private String remark;

	public String getDistruId() {
		return distruId;
	}

	public void setDistruId(String distruId) {
		this.distruId = distruId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getSourPhone() {
		return sourPhone;
	}

	public void setSourPhone(String sourPhone) {
		this.sourPhone = sourPhone;
	}

	public Date getMarkingTime() {
		return markingTime;
	}

	public void setMarkingTime(Date markingTime) {
		this.markingTime = markingTime;
	}

	public String getFeedbackFirstId() {
		return feedbackFirstId;
	}

	public void setFeedbackFirstId(String feedbackFirstId) {
		this.feedbackFirstId = feedbackFirstId;
	}

	public String getFeedbackSecId() {
		return feedbackSecId;
	}

	public void setFeedbackSecId(String feedbackSecId) {
		this.feedbackSecId = feedbackSecId;
	}

	public String getCallflowId() {
		return callflowId;
	}

	public void setCallflowId(String callflowId) {
		this.callflowId = callflowId;
	}

	public String getSourSeq() {
		return sourSeq;
	}

	public void setSourSeq(String sourSeq) {
		this.sourSeq = sourSeq;
	}

	public String getMarkingType() {
		return markingType;
	}

	public void setMarkingType(String markingType) {
		this.markingType = markingType;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getTalkDetail() {
		return talkDetail;
	}

	public void setTalkDetail(String talkDetail) {
		this.talkDetail = talkDetail;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getLableId() {
		return lableId;
	}

	public void setLableId(String lableId) {
		this.lableId = lableId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String[] getValues() {
		String[] str = {
				this.distruId,this.employeeId,this.sourPhone,this.feedbackFirstId,this.feedbackSecId,this.callflowId,this.sourSeq,this.markingType,
				this.custName,this.wechat,this.company,this.demand,this.budget,this.talkDetail,this.flag,this.types,this.lableId,this.remark
		};
		return str;
	}
	
}
