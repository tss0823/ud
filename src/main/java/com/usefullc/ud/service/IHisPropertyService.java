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
import com.usefullc.ud.domain.HisProperty;

/**
 * 历史属性 Service
 * 
 * @author tangss
 * @2014-01-14 14
 */
public interface IHisPropertyService {

    /**
     * 获得历史属性
     * 
     * @param id
     * @return
     */
    HisProperty getHisProperty(Long id);

    /**
     * 获得历史属性列表
     * 
     * @param queryMap
     * @return
     */
    List<HisProperty> getHisPropertyList(Map<String, Object> queryMap);

    /**
     * 获得历史属性分页列表
     * 
     * @param queryMap
     * @return
     */
    Pagination<HisProperty> getHisPropertyListPage(Map<String, Object> queryMap);

    /**
     * 新增历史属性
     * 
     * @param hisProperty
     */
    void insertHisProperty(HisProperty domain);

    /**
     * 修改历史属性
     * 
     * @param hisProperty
     */
    void updateHisProperty(HisProperty domain);

    /**
     * 删除历史属性
     * 
     * @param id
     */
    void deleteHisProperty(Long id);

    List<HisProperty> getHisPropertyByVer(Long entityId, Integer ver);

}
