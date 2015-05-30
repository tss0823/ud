/*
 * Copyright 2010-2011 ESunny.com All right reserved. This software is the confidential and proprietary information of
 * ESunny.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ESunny.com.
 */
package com.usefullc.ud.common.db;

import java.io.InputStream;
import java.util.List;

import com.usefullc.ud.common.bo.ApplicationBo;
import com.usefullc.ud.common.bo.EntityBo;
import com.usefullc.ud.common.bo.PropertyBo;

/**
 * 类DbBuilder.java的实现描述：数据库构建接口
 * 
 * @author shengshang.tang 2014年3月11日 上午10:33:01
 */
public interface DbBuilder {

    /**
     * 构建所有表
     * 
     * @param entityList
     * @return
     */
    String buildAllTableSql(ApplicationBo appBo, List<EntityBo> entityList);

    /**
     * 构建单个表
     * 
     * @param entity
     * @param propList
     * @return
     */
    String buildTableSql(ApplicationBo appBo, EntityBo entity, List<PropertyBo> propList);

    /**
     * 获得删除表sql
     * 
     * @param entity
     * @param propList
     * @return
     */
    String buildDelTableSql(ApplicationBo appBo, List<EntityBo> entityList);

    /**
     * 构建单个修改表
     * 
     * @param entityBo
     * @return
     */
    String buildTableAlertSql(String shortName, EntityBo entityBo);

    /**
     * 获得要处理的创建表sql集合
     * 
     * @param is
     * @return
     */
    List<String> getCreateTableSql(InputStream is);

    /**
     * sql字符转实体对象
     * 
     * @param sql
     * @return
     */
    EntityBo parseSqlToBo(String shortName, String sql);
}
