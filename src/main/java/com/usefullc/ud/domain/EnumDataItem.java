/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.domain;

import com.usefullc.platform.domain.BaseDomain;

/**
 * 枚举数据项
 * @author tangss
 *
 * @2014-02-08 13
 */
public class EnumDataItem extends BaseDomain {
	
	private static final long serialVersionUID = 1L;
	
	/**  ID * */
	private java.lang.Long id;
		
	/**  父ID * */
	private java.lang.Long parentId;
		
	/**  值 * */
	private java.lang.String value;
		
	/**  文本 * */
	private java.lang.String text;
		
	/**  扩展文本 * */
	private java.lang.String extText;
		
	/**  删除状态 （0：已删除；1：未删除） * */
	private java.lang.Boolean delState;
		
	
	public EnumDataItem(){
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setParentId(java.lang.Long value) {
		this.parentId = value;
	}
	
	public java.lang.Long getParentId() {
		return this.parentId;
	}
	public void setValue(java.lang.String value) {
		this.value = value;
	}
	
	public java.lang.String getValue() {
		return this.value;
	}
	public void setText(java.lang.String value) {
		this.text = value;
	}
	
	public java.lang.String getText() {
		return this.text;
	}
	public void setExtText(java.lang.String value) {
		this.extText = value;
	}
	
	public java.lang.String getExtText() {
		return this.extText;
	}
	public void setDelState(java.lang.Boolean value) {
		this.delState = value;
	}
	
	public java.lang.Boolean getDelState() {
		return this.delState;
	}
	



}

