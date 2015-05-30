/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.domain;

import com.usefullc.platform.domain.BaseDomain;

/**
 * 历史属性
 * 
 * @author tangss
 * @2014-02-08 13
 */
public class HisProperty extends BaseDomain {

    private static final long serialVersionUID = 1L;

    /** ID * */
    private java.lang.Long    id;

    /** 中文名 * */
    private java.lang.String  cnName;

    /** 英文名 * */
    private java.lang.String  enName;

    /** 实体id * */
    private java.lang.Long    entityId;

    /** 创建时间 * */
    private java.util.Date    gmtCreate;

    /** 修改时间 * */
    private java.util.Date    gmtModify;

    /** 删除状态 （0：已删除；1：未删除） * */
    private java.lang.Boolean delState;

    /** 数据类型 * */
    private java.lang.String  dataType;

    /** 长度 * */
    private java.lang.String  length;

    /** 是否主键（0：否；1：是） * */
    private java.lang.Boolean primaryKey;

    /** 默认值 * */
    private java.lang.String  defaultValue;

    /** 是否为空（0：否；1：是） * */
    private java.lang.Boolean isNull;

    /** 排序 * */
    private java.lang.Integer order;

    /** 输入类型(0：手动输入；1：枚举；2：数据集） * */
    private java.lang.Integer sourceType;

    /** 输入来源 * */
    private java.lang.String  source;

    /** 备注 * */
    private java.lang.String  remark;

    /** 校验分组 * */
    private java.lang.String  checkGroup;

    /** 版本 * */
    private java.lang.Integer ver;

    /** 主体Id * */
    private java.lang.Long    mainId;

    public HisProperty(){
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

    public void setEntityId(java.lang.Long value) {
        this.entityId = value;
    }

    public java.lang.Long getEntityId() {
        return this.entityId;
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

    public void setPrimaryKey(java.lang.Boolean value) {
        this.primaryKey = value;
    }

    public java.lang.Boolean getPrimaryKey() {
        return this.primaryKey;
    }

    public void setDefaultValue(java.lang.String value) {
        this.defaultValue = value;
    }

    public java.lang.String getDefaultValue() {
        return this.defaultValue;
    }

    public void setIsNull(java.lang.Boolean value) {
        this.isNull = value;
    }

    public java.lang.Boolean getIsNull() {
        return this.isNull;
    }

    public void setOrder(java.lang.Integer value) {
        this.order = value;
    }

    public java.lang.Integer getOrder() {
        return this.order;
    }

    public void setSourceType(java.lang.Integer value) {
        this.sourceType = value;
    }

    public java.lang.Integer getSourceType() {
        return this.sourceType;
    }

    public void setSource(java.lang.String value) {
        this.source = value;
    }

    public java.lang.String getSource() {
        return this.source;
    }

    public void setRemark(java.lang.String value) {
        this.remark = value;
    }

    public java.lang.String getRemark() {
        return this.remark;
    }

    public void setCheckGroup(java.lang.String value) {
        this.checkGroup = value;
    }

    public java.lang.String getCheckGroup() {
        return this.checkGroup;
    }

    public void setVer(java.lang.Integer value) {
        this.ver = value;
    }

    public java.lang.Integer getVer() {
        return this.ver;
    }

    public void setMainId(java.lang.Long value) {
        this.mainId = value;
    }

    public java.lang.Long getMainId() {
        return this.mainId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(entityId);
        sb.append("#");
        sb.append(cnName);
        sb.append("#");
        sb.append(enName);
        sb.append("#");
        sb.append(dataType);
        sb.append("#");
        sb.append(length);
        sb.append("#");
        sb.append(primaryKey);
        sb.append("#");
        sb.append(defaultValue);
        sb.append("#");
        sb.append(isNull);
        sb.append("#");
        sb.append(sourceType);
        sb.append("#");
        sb.append(source);
        sb.append("#");
        sb.append(remark);
        sb.append("#");
        sb.append(checkGroup);
        return sb.toString();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return toString().equals(obj.toString());
    }

}
