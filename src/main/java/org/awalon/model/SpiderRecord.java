package org.awalon.model;

import java.util.Date;

public class SpiderRecord {
	
	private String crawlerTime;
	private Date createTime;
	private Date updateTime;
	//数据同步状态 0：不同步   1：同步
	private Integer status = 0;
	
	
	public String getCrawlerTime() {
		return crawlerTime;
	}
	public void setCrawlerTime(String crawlerTime) {
		this.crawlerTime = crawlerTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
