/*
 * Copyright 2010-2011 ESunny.com All right reserved. This software is the confidential and proprietary information of
 * ESunny.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ESunny.com.
 */
package com.usefullc.ud.web.form;

import java.io.Serializable;

/**
 * 类CommonJsonForm.java的实现描述：TODO 类实现描述
 * 
 * @author shengshang.tang 2014年3月7日 下午5:32:00
 */
public class CommonJsonForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7708472515351043322L;

    private String            result;

    private Object            data;

    /**
     * @param result
     * @param data
     */
    public CommonJsonForm(String result, Object data){
        super();
        this.result = result;
        this.data = data;
    }
    /**
     * @param result
     * @param data
     */
    public CommonJsonForm(Integer result, Object data){
        super();
        this.result = result.toString();
        this.data = data;
    }
    /**
     * @param result
     */
    public CommonJsonForm(Integer result){
        super();
        this.result = result.toString();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
