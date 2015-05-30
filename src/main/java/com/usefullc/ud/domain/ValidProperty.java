/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.domain;

import com.usefullc.platform.domain.BaseDomain;

/**
 * 校验属性
 * @author tangss
 *
 * @2014-03-17 10
 */
public class ValidProperty extends BaseDomain {
	
	private static final long serialVersionUID = 1L;
	
	/**  ID * */
	private java.lang.Long id;
		
	/**  创建时间 * */
	private java.util.Date gmtCreate;
		
	/**  修改时间 * */
	private java.util.Date gmtModify;
		
	/**  删除状态（0：已删除；1：未删除） * */
	private java.lang.Boolean delState;
		
	/**  中文 * */
	private java.lang.String cnName;
		
	/**  英文 * */
	private java.lang.String enName;
		
	/**  是否为空（0：否；1：是） * */
	private java.lang.Boolean isNull;
		
	/**  规则ID * */
	private java.lang.Long ruleId;
		
	/**  规则参数 * */
	private java.lang.String ruleParam;
		
	/**  非空消息 * */
	private java.lang.String msgForNull;
		
	/**  长度消息 * */
	private java.lang.String msgForLen;
		
	/**  规则消息 * */
	private java.lang.String msgForRule;
		
	/**  数据类型 * */
	private java.lang.String dataType;
		
	/**  数据长度 * */
	private java.lang.String length;
		
	/**  实体ID * */
	private java.lang.Long entityId;
		
	/**  属性ID * */
	private java.lang.Long propertyId;
		
	/**  消息名称 * */
	private java.lang.String msgName;
		
	
	public ValidProperty(){
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
	public void setIsNull(java.lang.Boolean value) {
		this.isNull = value;
	}
	
	public java.lang.Boolean getIsNull() {
		return this.isNull;
	}
	public void setRuleId(java.lang.Long value) {
		this.ruleId = value;
	}
	
	public java.lang.Long getRuleId() {
		return this.ruleId;
	}
	public void setRuleParam(java.lang.String value) {
		this.ruleParam = value;
	}
	
	public java.lang.String getRuleParam() {
		return this.ruleParam;
	}
	public void setMsgForNull(java.lang.String value) {
		this.msgForNull = value;
	}
	
	public java.lang.String getMsgForNull() {
		return this.msgForNull;
	}
	public void setMsgForLen(java.lang.String value) {
		this.msgForLen = value;
	}
	
	public java.lang.String getMsgForLen() {
		return this.msgForLen;
	}
	public void setMsgForRule(java.lang.String value) {
		this.msgForRule = value;
	}
	
	public java.lang.String getMsgForRule() {
		return this.msgForRule;
	}
	public void setDataType(java.lang.String value) {
		this.dataType = value;
	}
	
	public java.lang.String getDataType() {
		return this.dataType;
	}
	public void setLength(java.lang.String value) {
		this.length = value;
	}
	
	public java.lang.String getLength() {
		return this.length;
	}
	public void setEntityId(java.lang.Long value) {
		this.entityId = value;
	}
	
	public java.lang.Long getEntityId() {
		return this.entityId;
	}
	public void setPropertyId(java.lang.Long value) {
		this.propertyId = value;
	}
	
	public java.lang.Long getPropertyId() {
		return this.propertyId;
	}
	public void setMsgName(java.lang.String value) {
		this.msgName = value;
	}
	
	public java.lang.String getMsgName() {
		return this.msgName;
	}
	



}

