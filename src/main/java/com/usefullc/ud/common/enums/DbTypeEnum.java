/**
 * 
 */
package com.usefullc.ud.common.enums;

/**
 * 数据库类型
 * 
 * @author tangss
 * @2013年9月6日 @下午4:22:25
 */
public enum DbTypeEnum {

    MYSQL("mysql"), ORACLE("oracle"), SQLSERVER("sqlserver"), DB2("db2"), SQLLITE("sqllite");

    private String value;

    private DbTypeEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
