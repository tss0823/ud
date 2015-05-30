/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.domain;

import com.usefullc.platform.domain.BaseDomain;

/**
 * 应用
 * @author tangss
 *
 * @2014-03-28 10
 */
public class Application extends BaseDomain {
	
	private static final long serialVersionUID = 1L;
	
	/**  ID * */
	private java.lang.Long id;
		
	/**  中文名称 * */
	private java.lang.String cnName;
		
	/**  英文名称 * */
	private java.lang.String enName;
		
	/**  类型 * */
	private java.lang.String type;
		
	/**  svn地址 * */
	private java.lang.String svnLink;
		
	/**  创建时间 * */
	private java.util.Date gmtCreate;
		
	/**  修改时间 * */
	private java.util.Date gmtModify;
		
	/**  创建人 * */
	private java.lang.String creator;
		
	/**  数据库配置类型 * */
	private java.lang.Long dbId;
		
	/**  项目ID * */
	private java.lang.Long projectId;
		
	/**  删除状态（0：已删除；1：未删除） * */
	private java.lang.Boolean delState;
		
	/**  模板路径 * */
	private java.lang.String templatePath;
		
	/**  生成路径 * */
	private java.lang.String genPath;
		
	/**  包名 * */
	private java.lang.String packageName;
		
	/**  构建版本 * */
	private java.lang.Integer ver;
		
	/**  简称 * */
	private java.lang.String shortName;
		
	/**  模版ID * */
	private java.lang.Long templateId;
		
	
	public Application(){
	}

	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
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
	public void setType(java.lang.String value) {
		this.type = value;
	}
	
	public java.lang.String getType() {
		return this.type;
	}
	public void setSvnLink(java.lang.String value) {
		this.svnLink = value;
	}
	
	public java.lang.String getSvnLink() {
		return this.svnLink;
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
	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	
	public java.lang.String getCreator() {
		return this.creator;
	}
	public void setDbId(java.lang.Long value) {
		this.dbId = value;
	}
	
	public java.lang.Long getDbId() {
		return this.dbId;
	}
	public void setProjectId(java.lang.Long value) {
		this.projectId = value;
	}
	
	public java.lang.Long getProjectId() {
		return this.projectId;
	}
	public void setDelState(java.lang.Boolean value) {
		this.delState = value;
	}
	
	public java.lang.Boolean getDelState() {
		return this.delState;
	}
	public void setTemplatePath(java.lang.String value) {
		this.templatePath = value;
	}
	
	public java.lang.String getTemplatePath() {
		return this.templatePath;
	}
	public void setGenPath(java.lang.String value) {
		this.genPath = value;
	}
	
	public java.lang.String getGenPath() {
		return this.genPath;
	}
	public void setPackageName(java.lang.String value) {
		this.packageName = value;
	}
	
	public java.lang.String getPackageName() {
		return this.packageName;
	}
	public void setVer(java.lang.Integer value) {
		this.ver = value;
	}
	
	public java.lang.Integer getVer() {
		return this.ver;
	}
	public void setShortName(java.lang.String value) {
		this.shortName = value;
	}
	
	public java.lang.String getShortName() {
		return this.shortName;
	}
	public void setTemplateId(java.lang.Long value) {
		this.templateId = value;
	}
	
	public java.lang.Long getTemplateId() {
		return this.templateId;
	}
	



}

