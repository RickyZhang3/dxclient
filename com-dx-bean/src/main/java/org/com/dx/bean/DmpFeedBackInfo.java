package org.com.dx.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 营销结果信息
 * @author zhangxinghua
 *
 */
public class DmpFeedBackInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6289165344253417128L;
	
	private String feedbackId;
	
	private String feedbackName;
	
	private String feedbackPersonal;
	
	private Date feedbackDate;
	
	private String feedbackCompany;
	
	private String feedbackNum;
	
	private String feedbackFlag;

	public String getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getFeedbackName() {
		return feedbackName;
	}

	public void setFeedbackName(String feedbackName) {
		this.feedbackName = feedbackName;
	}

	public String getFeedbackPersonal() {
		return feedbackPersonal;
	}

	public void setFeedbackPersonal(String feedbackPersonal) {
		this.feedbackPersonal = feedbackPersonal;
	}

	public Date getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public String getFeedbackCompany() {
		return feedbackCompany;
	}

	public void setFeedbackCompany(String feedbackCompany) {
		this.feedbackCompany = feedbackCompany;
	}

	public String getFeedbackNum() {
		return feedbackNum;
	}

	public void setFeedbackNum(String feedbackNum) {
		this.feedbackNum = feedbackNum;
	}

	public String getFeedbackFlag() {
		return feedbackFlag;
	}

	public void setFeedbackFlag(String feedbackFlag) {
		this.feedbackFlag = feedbackFlag;
	}
	

}
