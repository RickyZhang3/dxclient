package org.com.dx.bean;

import java.io.Serializable;
import java.util.Date;

public class DmpEmployeeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1285668703207362019L;
	
//	private String employeeId;
	
	private String employeeName;
	
	private String account;
	
	private String password;
	
	private String roleId;
	
	private String creTime;
	
	private String creater;
	
	private String cFlag;
	
	private String email;
	
	private String phone;
	
	private Date changeDate;
	
	private String channelId;
	
	private String amendPersion;
	
	private String accounttype;
	
	private Date passTime;

//	public String getEmployeeId() {
//		return employeeId;
//	}
//
//	public void setEmployeeId(String employeeId) {
//		this.employeeId = employeeId;
//	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getCreTime() {
		return creTime;
	}

	public void setCreTime(String creTime) {
		this.creTime = creTime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getcFlag() {
		return cFlag;
	}

	public void setcFlag(String cFlag) {
		this.cFlag = cFlag;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getAmendPersion() {
		return amendPersion;
	}

	public void setAmendPersion(String amendPersion) {
		this.amendPersion = amendPersion;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public Date getPassTime() {
		return passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}
	
}
