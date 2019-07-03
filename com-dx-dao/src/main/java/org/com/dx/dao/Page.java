package org.com.dx.dao;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	
	//页码
	private Integer pageNo = 1;
	//每页条数
	private Integer pageSize = 15;
	//总条数
	private Integer totalCount = 0;
	//结果集
	private List<T> resultList = new ArrayList<T>();
	
	public Page(){
		
	}
	
	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
		if(pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
	
	public long getTotalPages() {
		if (totalCount < 0) {
			return -1;
		}
 
		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}
	
	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}
	
	/**
	 * 取得下页的页号, 序号从1开始.
	 * 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (isHasNext()) {
			return pageNo + 1;
		} else {
			return pageNo;
		}
	}
	
	
}
