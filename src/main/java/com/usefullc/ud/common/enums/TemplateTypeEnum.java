/**
 * 
 */
package com.usefullc.ud.common.enums;

/**
 * 模版类型枚举
 * 
 * @author tangss
 * @2013年9月6日 @下午4:22:25
 */
public enum TemplateTypeEnum {

    java("java"), doc("doc"), html("html");

    private String value;

    private TemplateTypeEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
