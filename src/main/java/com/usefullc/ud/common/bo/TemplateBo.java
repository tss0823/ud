/*
 * Copyright 2010-2011 ESunny.com All right reserved. This software is the confidential and proprietary information of
 * ESunny.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ESunny.com.
 */
package com.usefullc.ud.common.bo;

import com.usefullc.ud.domain.Attachment;
import com.usefullc.ud.domain.Template;

/**
 * 类TemplateBo.java的实现描述：TODO 类实现描述
 * 
 * @author shengshang.tang 2014年4月1日 下午5:47:08
 */
public class TemplateBo extends Template {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Attachment        attachment;

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

}
