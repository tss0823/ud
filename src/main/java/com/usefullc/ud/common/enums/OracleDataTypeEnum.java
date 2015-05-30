/**
 * 
 */
package com.usefullc.ud.common.enums;

/**
 * 数据类型
 * 
 * @author tangss
 * @2013年9月6日 @下午4:22:25
 */
public enum OracleDataTypeEnum {

    STRING("java.lang.String", "VARCHAR"), LONG("java.lang.Long", "NUMBER"), INTEGER("java.lang.Integer", "NUMBER"),
    DOUBLE("java.lang.Double", "FLOAT"), BIGDECIMAL("java.math.BigDecimal", "FLOAT"), TIME("java.util.Date", "DATE"),
    FLOAT("java.lang.Float", "FLOAT"), BOOLEAN("java.lang.Boolean", "NUMBER");

    private String value;
    private String dbValue;

    private OracleDataTypeEnum(String value, String dbValue){
        this.value = value;
        this.dbValue = dbValue;
    }

    public String getValue() {
        return value;
    }

    public String getDbValue() {
        return dbValue;
    }

}
