/*
 * Copyright 2010-2011 ESunny.com All right reserved. This software is the confidential and proprietary information of
 * ESunny.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ESunny.com.
 */
package com.usefullc.ud.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.usefullc.platform.common.utils.ResultDataFormUtils;
import com.usefullc.platform.web.form.ResultDataForm;
import com.usefullc.ud.service.IDataService;

/**
 * 类DataController.java的实现描述：数据controler
 * 
 * @author shengshang.tang 2014年5月5日 下午3:03:54
 */
@Controller
@RequestMapping(value = "/data")
public class DataController {

    @Autowired
    private IDataService dataService;

    /**
     * 进入数据传输
     * 
     * @return
     */
    @RequestMapping(value = "transfer.htm")
    public String transfer() {
        return "/data/transfer";
    }

    /**
     * 保存传输数据
     * 
     * @return
     */
    @RequestMapping(value = "saveTransfer.htm")
    @ResponseBody
    public ResultDataForm saveTransfer(@RequestParam Long toAppId, @RequestParam Long[] entityIds, Model model) {
        dataService.saveTransfer(toAppId, entityIds);
        return ResultDataFormUtils.success();
    }
}
