/**
 * 
 */
package com.usefullc.ud.common.enums;

/**
 * 对象数据类型
 * 
 * @author tangss
 * @2013年9月6日 @下午4:22:25
 */
public enum DataTypeEnum {

    STRING("java.lang.String"),

    LONG("java.lang.Long"),

    INTEGER("java.lang.Integer"),

    TIME("java.util.Date"),

    BIGDECIMAL("java.math.BigDecimal"),

    BOOLEAN("java.lang.Boolean");

    private String value;

    private DataTypeEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
