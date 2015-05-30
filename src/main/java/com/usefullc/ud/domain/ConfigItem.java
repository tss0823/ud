/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.domain;

import com.usefullc.platform.domain.BaseDomain;

/**
 * 配置项
 * @author tangss
 *
 * @2014-02-08 13
 */
public class ConfigItem extends BaseDomain {
	
	private static final long serialVersionUID = 1L;
	
	/**  ID * */
	private java.lang.Long id;
		
	/**  应用ID * */
	private java.lang.Long appId;
		
	/**  100 * */
	private java.lang.String key;
		
	/**  500 * */
	private java.lang.String value;
		
	/**  20 * */
	private java.lang.String module;
		
	/**  删除状态 * */
	private java.lang.Boolean delState;
		
	
	public ConfigItem(){
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
	public void setModule(java.lang.String value) {
		this.module = value;
	}
	
	public java.lang.String getModule() {
		return this.module;
	}
	public void setDelState(java.lang.Boolean value) {
		this.delState = value;
	}
	
	public java.lang.Boolean getDelState() {
		return this.delState;
	}
	



}

