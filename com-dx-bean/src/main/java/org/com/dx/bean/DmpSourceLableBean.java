package org.com.dx.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 营销标签表
 * @author zhangxinghua
 *
 */
public class DmpSourceLableBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5476435250373188839L;
	
	private String dmpLableId;
	
	private String dmpLableName;
	
	private String dmpLableDesc;
	
	private String dmpLabePeo;
	
	private Date dmpLableDate;
	
	private String dmpLableFlag;

	public String getDmpLableId() {
		return dmpLableId;
	}

	public void setDmpLableId(String dmpLableId) {
		this.dmpLableId = dmpLableId;
	}

	public String getDmpLableName() {
		return dmpLableName;
	}

	public void setDmpLableName(String dmpLableName) {
		this.dmpLableName = dmpLableName;
	}

	public String getDmpLableDesc() {
		return dmpLableDesc;
	}

	public void setDmpLableDesc(String dmpLableDesc) {
		this.dmpLableDesc = dmpLableDesc;
	}

	public String getDmpLabePeo() {
		return dmpLabePeo;
	}

	public void setDmpLabePeo(String dmpLabePeo) {
		this.dmpLabePeo = dmpLabePeo;
	}

	public Date getDmpLableDate() {
		return dmpLableDate;
	}

	public void setDmpLableDate(Date dmpLableDate) {
		this.dmpLableDate = dmpLableDate;
	}

	public String getDmpLableFlag() {
		return dmpLableFlag;
	}

	public void setDmpLableFlag(String dmpLableFlag) {
		this.dmpLableFlag = dmpLableFlag;
	}
	
	

}
