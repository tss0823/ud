/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.domain;

import com.usefullc.platform.domain.BaseDomain;

/**
 * 数据库配置
 * @author tangss
 *
 * @2014-03-11 14
 */
public class DbConfigure extends BaseDomain {
	
	private static final long serialVersionUID = 1L;
	
	/**  ID * */
	private java.lang.Long id;
		
	/**  地址 * */
	private java.lang.String url;
		
	/**  驱动 * */
	private java.lang.String driver;
		
	/**  用户名 * */
	private java.lang.String user;
		
	/**  密码 * */
	private java.lang.String password;
		
	/**  类型 * */
	private java.lang.String type;
		
	/**  删除状态（0：已删除；1：未删除） * */
	private java.lang.Boolean delState;
		
	/**  表空间 * */
	private java.lang.String tableSpace;
		
	/**  名称 * */
	private java.lang.String name;
		
	
	public DbConfigure(){
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	public void setDriver(java.lang.String value) {
		this.driver = value;
	}
	
	public java.lang.String getDriver() {
		return this.driver;
	}
	public void setUser(java.lang.String value) {
		this.user = value;
	}
	
	public java.lang.String getUser() {
		return this.user;
	}
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	public java.lang.String getPassword() {
		return this.password;
	}
	public void setType(java.lang.String value) {
		this.type = value;
	}
	
	public java.lang.String getType() {
		return this.type;
	}
	public void setDelState(java.lang.Boolean value) {
		this.delState = value;
	}
	
	public java.lang.Boolean getDelState() {
		return this.delState;
	}
	public void setTableSpace(java.lang.String value) {
		this.tableSpace = value;
	}
	
	public java.lang.String getTableSpace() {
		return this.tableSpace;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	



}

