/*
 * Copyright 2010-2011 ESunny.com All right reserved. This software is the confidential and proprietary information of
 * ESunny.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ESunny.com.
 */
package com.usefullc.ud.common.enums;

/**
 * 类GenFileBisTypeEnum.java的实现描述：TODO 类实现描述
 * 
 * @author shengshang.tang 2014年4月1日 下午3:52:23
 */
public enum GenFileBisTypeEnum {

    ALL("all", "所有web"),

    SINGLE("single", "单个web"),

    VALID("valid", "单个校验");

    private String value;

    private String text;

    /**
     * @param value
     * @param text
     */
    private GenFileBisTypeEnum(String value, String text){
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

}
