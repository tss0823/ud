/*
 * Copyright 2010-2011 ESunny.com All right reserved. This software is the confidential and proprietary information of
 * ESunny.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ESunny.com.
 */
package com.usefullc.ud.service;

/**
 * 类IDataService.java的实现描述：TODO 类实现描述
 * 
 * @author shengshang.tang 2014年5月5日 下午3:23:32
 */
public interface IDataService {

    /**
     * 保存传输
     * 
     * @param toAppId
     * @param entityIds
     */
    void saveTransfer(Long toAppId, Long[] entityIds);

}
