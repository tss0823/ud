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
import com.usefullc.ud.domain.HisEntity;

/**
 * 历史实体 Service
 * 
 * @author tangss
 * @2014-01-14 14
 */
public interface IHisEntityService {

    /**
     * 获得历史实体
     * 
     * @param id
     * @return
     */
    HisEntity getHisEntity(Long id);

    /**
     * 获得历史实体列表
     * 
     * @param queryMap
     * @return
     */
    List<HisEntity> getHisEntityList(Map<String, Object> queryMap);

    /**
     * 获得历史实体分页列表
     * 
     * @param queryMap
     * @return
     */
    Pagination<HisEntity> getHisEntityListPage(Map<String, Object> queryMap);

    /**
     * 新增历史实体
     * 
     * @param hisEntity
     */
    void insertHisEntity(HisEntity domain);

    /**
     * 修改历史实体
     * 
     * @param hisEntity
     */
    void updateHisEntity(HisEntity domain);

    /**
     * 删除历史实体
     * 
     * @param id
     */
    void deleteHisEntity(Long id);

    List<HisEntity> getHisEntityByVer(Long appId, Integer ver);

}
