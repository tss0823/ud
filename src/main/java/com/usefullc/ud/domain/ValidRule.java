/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.domain;

import com.usefullc.platform.domain.BaseDomain;

/**
 * 校验规则
 * @author tangss
 *
 * @2014-03-14 08
 */
public class ValidRule extends BaseDomain {
	
	private static final long serialVersionUID = 1L;
	
	/**  ID * */
	private java.lang.Long id;
		
	/**  创建时间 * */
	private java.util.Date gmtCreate;
		
	/**  修改时间 * */
	private java.util.Date gmtModify;
		
	/**  删除状态（0：已删除；1：未删除） * */
	private java.lang.Boolean delState;
		
	/**  类型 * */
	private java.lang.String type;
		
	/**  JS正则 * */
	private java.lang.String jsRegex;
		
	/**  JAVA正则 * */
	private java.lang.String javaRegex;
		
	/**  名称 * */
	private java.lang.String name;
		
	
	public ValidRule(){
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
	public void setType(java.lang.String value) {
		this.type = value;
	}
	
	public java.lang.String getType() {
		return this.type;
	}
	public void setJsRegex(java.lang.String value) {
		this.jsRegex = value;
	}
	
	public java.lang.String getJsRegex() {
		return this.jsRegex;
	}
	public void setJavaRegex(java.lang.String value) {
		this.javaRegex = value;
	}
	
	public java.lang.String getJavaRegex() {
		return this.javaRegex;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	



}

