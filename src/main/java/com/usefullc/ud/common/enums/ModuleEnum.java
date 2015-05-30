/** 
 * Copyright 2012 mipo.com All right reserved. This software is the 
 * confidential and proprietary information of mipo.com ("Confidential 
 * Information"). You shall not disclose such Confidential Information and shall 
 * use it only in accordance with the terms of the license agreement you entered 
 * into with mipo.com.
 */
package com.usefullc.ud.common.enums;

/**
 * 类ModuleEnum.java的实现描述：项目运行环境模式
 * 
 * @author <a href=tangshengshan@mipuu.net>shengshan.tang</a>
 * @version V1.0
 * @date 2012-11-23
 * @time 下午3:29:23
 */
public enum ModuleEnum {

	DEV("dev", "开发"), DEBUG("debug", "联调"), TEST("test", "测试"), TRUE("true",
			"线上");

	private String code;

	private String value;

	/**
	 * 
	 */
	private ModuleEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

}
