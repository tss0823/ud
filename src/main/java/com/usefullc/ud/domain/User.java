/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.domain;

import com.usefullc.platform.domain.BaseDomain;

/**
 * 用户
 * 
 * @author tangss
 * @2014-02-08 13
 */
public class User extends BaseDomain {

    private static final long serialVersionUID = 1L;

    /** ID * */
    private java.lang.Long    id;

    /** 中文名 * */
    private java.lang.String  cnName;

    /** 用户名 * */
    private java.lang.String  username;

    /** 密码 * */
    private java.lang.String  password;

    /** 创建时间 * */
    private java.util.Date    gmtCreate;

    /** 修改时间 * */
    private java.util.Date    gmtModify;

    /** 删除状态（0：已删除；1：未删除） * */
    private java.lang.Boolean delState;

    /** 角色(多个用逗号隔开） * */
    private java.lang.String  role;

    public User(){
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

    public java.lang.String getUsername() {
        return username;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    public void setPassword(java.lang.String value) {
        this.password = value;
    }

    public java.lang.String getPassword() {
        return this.password;
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

    public void setRole(java.lang.String value) {
        this.role = value;
    }

    public java.lang.String getRole() {
        return this.role;
    }

}
