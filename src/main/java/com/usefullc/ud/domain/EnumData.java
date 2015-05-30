/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.domain;

import com.usefullc.platform.domain.BaseDomain;

/**
 * 枚举数据
 * @author tangss
 *
 * @2014-02-08 13
 */
public class EnumData extends BaseDomain {
	
	private static final long serialVersionUID = 1L;
	
	/**  ID * */
	private java.lang.Long id;
		
	/**   * */
	private java.lang.String name;
		
	/**  描述 * */
	private java.lang.String description;
		
	/**  数据类型(number：数字，text：字符) * */
	private java.lang.String dataType;
		
	/**  删除状态 （0：已删除；1：未删除） * */
	private java.lang.Boolean delState;
		
	
	public EnumData(){
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	
	public java.lang.String getDescription() {
		return this.description;
	}
	public void setDataType(java.lang.String value) {
		this.dataType = value;
	}
	
	public java.lang.String getDataType() {
		return this.dataType;
	}
	public void setDelState(java.lang.Boolean value) {
		this.delState = value;
	}
	
	public java.lang.Boolean getDelState() {
		return this.delState;
	}
	



}

