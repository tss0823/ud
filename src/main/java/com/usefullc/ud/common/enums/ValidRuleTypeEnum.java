/**
 * 
 */
package com.usefullc.ud.common.enums;

/**
 * 校验类型类型
 * 
 * @author tangss
 * @2013年9月6日 @下午4:22:25
 */
public enum ValidRuleTypeEnum {

    CHARACTER("character"),

    DIGITS("digits"),

    INTEGER("integer"),

    MONEY("money"),

    URL("url"),

    CODE_ZIP("codeZip"),

    MOBILE_PHONE("mobilePhone"),

    TELPHONE("telphone"),

    AGE("age"),

    EMAIL("email"),

    CUSTOM("custom");

    private String value;

    private ValidRuleTypeEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
