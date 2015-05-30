/*
 * Copyright 2010-2011 ESunny.com All right reserved. This software is the confidential and proprietary information of
 * ESunny.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ESunny.com.
 */
package com.usefullc.ud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usefullc.ud.domain.Entity;
import com.usefullc.ud.domain.Property;
import com.usefullc.ud.service.IDataService;
import com.usefullc.ud.service.IEntityService;
import com.usefullc.ud.service.IPropertyService;

/**
 * 类DataServiceImpl.java的实现描述：TODO 类实现描述
 * 
 * @author shengshang.tang 2014年5月5日 下午3:23:52
 */
@Service
public class DataServiceImpl implements IDataService {

    @Autowired
    private IEntityService   entityService;

    @Autowired
    private IPropertyService propertyService;

    @Transactional
    @Override
    public void saveTransfer(Long toAppId, Long[] entityIds) {
        List<Entity> entityList = entityService.getEntityListByIds(entityIds);

        for (Entity entity : entityList) {
            entity.setAppId(toAppId);

            List<Property> propList = this.propertyService.getPropertyListByEntityId(entity.getId());
            entity.setId(null);

            // 保存实体
            this.entityService.insertEntity(entity);

            Long entityId = entity.getId();

            // 保存实体
            for (Property prop : propList) {
                prop.setEntityId(entityId);
            }
            this.propertyService.insertPropertyBatch(propList);
        }

    }
}
