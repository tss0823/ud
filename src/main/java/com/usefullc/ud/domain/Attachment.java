/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.domain;

import com.usefullc.platform.domain.BaseDomain;

/**
 * 附件
 * 
 * @author tangss
 * @2014-04-01 17
 */
public class Attachment extends BaseDomain {

    private static final long serialVersionUID = 1L;

    /** 类别 * */
    private java.lang.String  type;

    /** ID * */
    private java.lang.Long    id;

    /** 创建时间 * */
    private java.util.Date    gmtCreate;

    /** 修改时间 * */
    private java.util.Date    gmtModify;

    /** 删除状态（0：已删除；1：未删除） * */
    private java.lang.Boolean delState;

    /** 内容 * */
    private byte[]            content;

    /** 名称 * */
    private java.lang.String  name;

    /** 描述 * */
    private java.lang.String  description;

    public Attachment(){
    }

    public void setType(java.lang.String value) {
        this.type = value;
    }

    public java.lang.String getType() {
        return this.type;
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

    public void setContent(byte[] value) {
        this.content = value;
    }

    public byte[] getContent() {
        return this.content;
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

}
