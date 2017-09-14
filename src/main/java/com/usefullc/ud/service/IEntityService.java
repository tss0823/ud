/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service;

import java.util.List;
import java.util.Map;

import com.usefullc.ud.domain.Entity;
import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.web.form.TreeFrm;

/**
 * 实体 Service
 * 
 * @author tangss
 * @2013-8-7 @上午11:13:42
 */
public interface IEntityService {

    /**
     * 获得实体
     * 
     * @param id
     * @return
     */
    Entity getEntity(Long id);

    Entity getEntityByEnName(Long appId,String enName);

    /**
     * 获得实体列表
     * 
     * @param queryMap
     * @return
     */
    List<Entity> getEntityList(Map<String, Object> queryMap);

    /**
     * 获得实体分页列表
     * 
     * @param queryMap
     * @return
     */
    Pagination<Entity> getEntityListPage(Map<String, Object> queryMap);

    /**
     * 新增实体
     * 
     * @param user
     */
    void insertEntity(Entity domain);

    /**
     * 修改实体
     * 
     * @param user
     */
    void updateEntity(Entity domain);

    /**
     * 删除实体
     * 
     * @param id
     */
    void deleteEntity(Long id);

    /**
     * 复制实体
     * 
     * @param id
     */
    void copy(Long id);

    /**
     * 根据appId删除实体
     * 
     * @param appId
     */
    void deleteEntityByAppId(Long appId);

    /**
     * 根据id集合删除实体
     * 
     * @param arryIds
     */
    void deleteEntityByIds(Object[] arryIds);

    /**
     * 获得实体树
     * 
     * @return
     */
    List<TreeFrm> getTreeList();

    /**
     * 根据实体id集合获得实体集合
     * 
     * @param entityIdArray
     * @return
     */
    List<Entity> getEntityListByIds(Object[] entityIdArray);

    /**
     * 根据应用id集合获得实体集合
     * 
     * @param appId
     * @return
     */
    List<Entity> getEntityListByAppId(Long appId);

    /**
     * 批量插入实体
     * 
     * @param entityList
     */
    void insertEntityBatch(List<Entity> entityList);

}
