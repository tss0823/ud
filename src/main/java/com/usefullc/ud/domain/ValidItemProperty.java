/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.domain;

import com.usefullc.platform.domain.BaseDomain;

/**
 * 校验项属性
 * @author tangss
 *
 * @2014-03-13 16
 */
public class ValidItemProperty extends BaseDomain {
	
	private static final long serialVersionUID = 1L;
	
	/**  ID * */
	private java.lang.Long id;
		
	/**  校验项ID * */
	private java.lang.Long validItemId;
		
	/**  校验属性ID * */
	private java.lang.Long validPropertyId;
		
	
	public ValidItemProperty(){
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setValidItemId(java.lang.Long value) {
		this.validItemId = value;
	}
	
	public java.lang.Long getValidItemId() {
		return this.validItemId;
	}
	public void setValidPropertyId(java.lang.Long value) {
		this.validPropertyId = value;
	}
	
	public java.lang.Long getValidPropertyId() {
		return this.validPropertyId;
	}
	



}

