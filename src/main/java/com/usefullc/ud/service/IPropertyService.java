/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service;

import java.util.List;
import java.util.Map;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.ud.domain.Property;

/**
 * 属性 Service
 * 
 * @author tangss
 * @2013-8-7 @上午11:13:42
 */
public interface IPropertyService {

    /**
     * 获得属性
     * 
     * @param id
     * @return
     */
    Property getProperty(Long id);

    /**
     * 获得属性列表
     * 
     * @param queryMap
     * @return
     */
    List<Property> getPropertyList(Map<String, Object> queryMap);

    /**
     * 获得属性分页列表
     * 
     * @param queryMap
     * @return
     */
    Pagination<Property> getPropertyListPage(Map<String, Object> queryMap);

    /**
     * 新增属性
     * 
     * @param domain
     */
    void insertProperty(Property domain);

    /**
     * 根据entityId 删除
     * 
     * @param entityId
     */
    void deletePropertyByEntityId(Long entityId);

    /**
     * 根据appId 删除
     * 
     * @param appId
     */
    void deletePropertyByAppId(Long appId);

    /**
     * 根据entityId集合删除
     * 
     * @param arrys
     */
    void deleteEntityListByEntityIds(Object[] arrys);

    /**
     * 新增属性
     * 
     * @param user
     */
    void insertProperty(Long entityId, String[] enName, String[] cnName, String[] dataType, String[] length,
                        String[] defaultValue, String[] isNull, String[] primaryKey);

    /**
     * 修改属性
     * 
     * @param user
     */
    void updateProperty(Long entityId, String[] enName, String[] cnName, String[] dataType, String[] length,
                        String[] defaultValue, String[] isNull, String[] primaryKey, String[] remark,
                        String[] sourceType, String[] source, String[] checkGroup);

    /**
     * 删除属性
     * 
     * @param id
     */
    void deleteProperty(Long id);

    /**
     * 批量删除
     * 
     * @param idArr
     */
    void batchDeleteProperty(Object[] idArr);

    /**
     * 根据实体id获得属性集合
     * 
     * @param entityId
     * @return
     */
    List<Property> getPropertyListByEntityId(Long entityId);

    /**
     * 批量插入属性
     * 
     * @param propList
     */
    void insertPropertyBatch(List<Property> propList);

}
