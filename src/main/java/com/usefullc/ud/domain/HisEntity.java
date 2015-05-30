/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.domain;

import com.usefullc.platform.domain.BaseDomain;

/**
 * 历史实体
 * @author tangss
 *
 * @2014-03-12 09
 */
public class HisEntity extends BaseDomain {
	
	private static final long serialVersionUID = 1L;
	
	/**  ID * */
	private java.lang.Long id;
		
	/**  应用ID * */
	private java.lang.Long appId;
		
	/**  英文名 * */
	private java.lang.String cnName;
		
	/**  中文名 * */
	private java.lang.String enName;
		
	/**  项目ID * */
	private java.lang.Long projectId;
		
	/**  创建时间 * */
	private java.util.Date gmtCreate;
		
	/**  修改时间 * */
	private java.util.Date gmtModify;
		
	/**  删除状态 （0：已删除；1：未删除） * */
	private java.lang.Boolean delState;
		
	/**  版本 * */
	private java.lang.Integer ver;
		
	/**  主体Id * */
	private java.lang.Long mainId;
		
	/**  简称 * */
	private java.lang.String shortName;
		
	
	public HisEntity(){
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setAppId(java.lang.Long value) {
		this.appId = value;
	}
	
	public java.lang.Long getAppId() {
		return this.appId;
	}
	public void setCnName(java.lang.String value) {
		this.cnName = value;
	}
	
	public java.lang.String getCnName() {
		return this.cnName;
	}
	public void setEnName(java.lang.String value) {
		this.enName = value;
	}
	
	public java.lang.String getEnName() {
		return this.enName;
	}
	public void setProjectId(java.lang.Long value) {
		this.projectId = value;
	}
	
	public java.lang.Long getProjectId() {
		return this.projectId;
	}
	public void setGmtCreate(java.util.Date value) {
		this.gmtCreate = value;
	}
	
	public java.util.Date getGmtCreate() {
		return this.gmtCreate;
	}
	public void setGmtModify(java.util.Date value) {
		this.gmtModify = value;
	}
	
	public java.util.Date getGmtModify() {
		return this.gmtModify;
	}
	public void setDelState(java.lang.Boolean value) {
		this.delState = value;
	}
	
	public java.lang.Boolean getDelState() {
		return this.delState;
	}
	public void setVer(java.lang.Integer value) {
		this.ver = value;
	}
	
	public java.lang.Integer getVer() {
		return this.ver;
	}
	public void setMainId(java.lang.Long value) {
		this.mainId = value;
	}
	
	public java.lang.Long getMainId() {
		return this.mainId;
	}
	public void setShortName(java.lang.String value) {
		this.shortName = value;
	}
	
	public java.lang.String getShortName() {
		return this.shortName;
	}
	



}

