/*
 * Copyright 2010-2011 ESunny.com All right reserved. This software is the confidential and proprietary information of
 * ESunny.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with ESunny.com.
 */
package com.usefullc.ud.common.db;

import java.util.HashMap;
import java.util.Map;

import com.usefullc.ud.common.enums.DbTypeEnum;

/**
 * 类DbBuilderFactory.java的实现描述：TODO 类实现描述
 * 
 * @author shengshang.tang 2014年3月11日 上午10:33:41
 */
public class DbBuilderFactory {

    private static DbBuilder              mysqlBuilder  = new DbMysqlBuilder();
    private static DbBuilder              oracleBuilder = new DbOracleBuilder();

    private static Map<String, DbBuilder> dbMap         = new HashMap<String, DbBuilder>();

    static {
        dbMap.put(DbTypeEnum.MYSQL.getValue(), mysqlBuilder);
        dbMap.put(DbTypeEnum.ORACLE.getValue(), oracleBuilder);
    }

    private DbBuilderFactory(){
    }

    /**
     * 获得数据库构建对象
     * 
     * @param dbType
     * @return
     */
    public static DbBuilder getDbBuilder(String dbType) {
        return dbMap.get(dbType);
    }
}
