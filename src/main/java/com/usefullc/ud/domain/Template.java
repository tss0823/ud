/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.domain;

import com.usefullc.platform.domain.BaseDomain;

/**
 * 模版
 * @author tangss
 *
 * @2014-04-01 17
 */
public class Template extends BaseDomain {
	
	private static final long serialVersionUID = 1L;
	
	/**  ID * */
	private java.lang.Long id;
		
	/**  创建时间 * */
	private java.util.Date gmtCreate;
		
	/**  修改时间 * */
	private java.util.Date gmtModify;
		
	/**  删除状态（0：已删除；1：未删除） * */
	private java.lang.Boolean delState;
		
	/**  应用ID * */
	private java.lang.Long appId;
		
	/**  名称 * */
	private java.lang.String name;
		
	/**  类型 * */
	private java.lang.String type;
		
	/**  路径 * */
	private java.lang.String path;
		
	/**  附件ID * */
	private java.lang.Long attachmentId;
		
	
	public Template(){
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
	public void setAppId(java.lang.Long value) {
		this.appId = value;
	}
	
	public java.lang.Long getAppId() {
		return this.appId;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setType(java.lang.String value) {
		this.type = value;
	}
	
	public java.lang.String getType() {
		return this.type;
	}
	public void setPath(java.lang.String value) {
		this.path = value;
	}
	
	public java.lang.String getPath() {
		return this.path;
	}
	public void setAttachmentId(java.lang.Long value) {
		this.attachmentId = value;
	}
	
	public java.lang.Long getAttachmentId() {
		return this.attachmentId;
	}
	



}

