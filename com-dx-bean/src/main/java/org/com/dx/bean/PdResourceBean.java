package org.com.dx.bean;

import io.swagger.annotations.ApiModelProperty;

public class PdResourceBean {
	
	@ApiModelProperty(name = "sourPhone", value = "资源号码")
	private String  sourPhone;
	
	@ApiModelProperty(name = "followTime", value = "跟踪时间")
	private String  followTime;
	
	@ApiModelProperty(name = "sourceMarkOne", value = "资源备注1")
	private String  sourceMarkOne;
	
	@ApiModelProperty(name = "sourceMarkTwo", value = "资源备注2")
	private String  sourceMarkTwo;
	
	@ApiModelProperty(name = "sourceMarkThree", value = "资源备注3")
	private String  sourceMarkThree;
	
	@ApiModelProperty(name = "sourSeq", value = "资源序号")
	private String  sourSeq;

	public String getSourPhone() {
		return sourPhone;
	}

	public void setSourPhone(String sourPhone) {
		this.sourPhone = sourPhone;
	}

	public String getFollowTime() {
		return followTime;
	}

	public void setFollowTime(String followTime) {
		this.followTime = followTime;
	}

	public String getSourceMarkOne() {
		return sourceMarkOne;
	}

	public void setSourceMarkOne(String sourceMarkOne) {
		this.sourceMarkOne = sourceMarkOne;
	}

	public String getSourceMarkTwo() {
		return sourceMarkTwo;
	}

	public void setSourceMarkTwo(String sourceMarkTwo) {
		this.sourceMarkTwo = sourceMarkTwo;
	}

	public String getSourceMarkThree() {
		return sourceMarkThree;
	}

	public void setSourceMarkThree(String sourceMarkThree) {
		this.sourceMarkThree = sourceMarkThree;
	}

	public String getSourSeq() {
		return sourSeq;
	}

	public void setSourSeq(String sourSeq) {
		this.sourSeq = sourSeq;
	}
	
}
