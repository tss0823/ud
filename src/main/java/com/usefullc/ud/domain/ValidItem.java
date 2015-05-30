/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.domain;

import com.usefullc.platform.domain.BaseDomain;

/**
 * 校验项
 * @author tangss
 *
 * @2014-03-13 16
 */
public class ValidItem extends BaseDomain {
	
	private static final long serialVersionUID = 1L;
	
	/**  ID * */
	private java.lang.Long id;
		
	/**  创建时间 * */
	private java.util.Date gmtCreate;
		
	/**  修改时间 * */
	private java.util.Date gmtModify;
		
	/**  删除状态（0：已删除；1：未删除） * */
	private java.lang.Boolean delState;
		
	/**  实体ID * */
	private java.lang.Long entityId;
		
	/**  英文名称 * */
	private java.lang.String enName;
		
	/**  中文名称 * */
	private java.lang.String cnName;
		
	/**  应用ID * */
	private java.lang.Long appId;
		
	
	public ValidItem(){
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
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
	public void setEntityId(java.lang.Long value) {
		this.entityId = value;
	}
	
	public java.lang.Long getEntityId() {
		return this.entityId;
	}
	public void setEnName(java.lang.String value) {
		this.enName = value;
	}
	
	public java.lang.String getEnName() {
		return this.enName;
	}
	public void setCnName(java.lang.String value) {
		this.cnName = value;
	}
	
	public java.lang.String getCnName() {
		return this.cnName;
	}
	public void setAppId(java.lang.Long value) {
		this.appId = value;
	}
	
	public java.lang.Long getAppId() {
		return this.appId;
	}
	



}

