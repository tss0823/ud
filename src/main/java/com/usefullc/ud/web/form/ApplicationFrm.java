/*
 * Copyright 2010-2011 ESunny.com All right reserved. This software is the confidential and proprietary information of
 * ESunny.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ESunny.com.
 */
package com.usefullc.ud.web.form;

import com.usefullc.ud.domain.Application;

/**
 * 类ApplicationFrm.java的实现描述：TODO 类实现描述
 * 
 * @author shengshang.tang 2014年3月28日 上午10:51:53
 */
public class ApplicationFrm extends Application {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String            templateName;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

}
