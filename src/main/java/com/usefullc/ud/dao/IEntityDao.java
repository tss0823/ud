/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.dao;
import com.usefullc.platform.common.web.Pagination;
import com.usefullc.ud.domain.Entity;

import java.util.List;
import java.util.Map;

/**
 * 实体DAO
 * @author tangss
 *
 * @2013-4-6 @上午10:36:10
 */
public interface  IEntityDao {
	
	/**
	 * 获得实体
	 * @param id
	 * @return
	 */
	Entity getEntity(Long id);

	Entity getEntityByEnName(String enName);

	/**
	 * 获得实体列表
	 * @param queryMap
	 * @return
	 */
	List<Entity> getEntityList(Map<String,Object> queryMap);
	
	/**
	 * 获得实体分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<Entity> getEntityListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增实体
	 * @param domain
	 */
	void insertEntity(Entity domain);
	
	/**
	 * 修改实体
	 * @param domain
	 */
	void updateEntity(Entity domain);
	
	/**
	 * 删除实体
	 * @param id
	 */
	void deleteEntity(Long id);


}

