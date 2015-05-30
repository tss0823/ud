/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.domain;

import com.usefullc.platform.domain.BaseDomain;

/**
 * 应用数据
 * @author tangss
 *
 * @2014-02-08 13
 */
public class AppData extends BaseDomain {
	
	private static final long serialVersionUID = 1L;
	
	/**  ID * */
	private java.lang.Long id;
		
	/**  键 * */
	private java.lang.String key;
		
	/**  值 * */
	private java.lang.String value;
		
	/**  应用ID * */
	private java.lang.Long appId;
		
	/**  删除状态 （0：已删除；1：未删除） * */
	private java.lang.Boolean delState;
		
	
	public AppData(){
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setKey(java.lang.String value) {
		this.key = value;
	}
	
	public java.lang.String getKey() {
		return this.key;
	}
	public void setValue(java.lang.String value) {
		this.value = value;
	}
	
	public java.lang.String getValue() {
		return this.value;
	}
	public void setAppId(java.lang.Long value) {
		this.appId = value;
	}
	
	public java.lang.Long getAppId() {
		return this.appId;
	}
	public void setDelState(java.lang.Boolean value) {
		this.delState = value;
	}
	
	public java.lang.Boolean getDelState() {
		return this.delState;
	}
	



}

