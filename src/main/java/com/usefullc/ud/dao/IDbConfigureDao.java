/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.dao;
import com.usefullc.platform.common.web.Pagination;
import com.usefullc.ud.domain.DbConfigure;

import java.util.List;
import java.util.Map;

/**
 * 数据库配置DAO
 * @author tangss
 *
 * @2013-4-6 @上午10:36:10
 */
public interface  IDbConfigureDao {
	
	/**
	 * 获得数据库配置
	 * @param id
	 * @return
	 */
	DbConfigure getDbConfigure(Long id);
	
	/**
	 * 获得数据库配置列表
	 * @param queryMap
	 * @return
	 */
	List<DbConfigure> getDbConfigureList(Map<String,Object> queryMap);
	
	/**
	 * 获得数据库配置分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<DbConfigure> getDbConfigureListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增数据库配置
	 * @param domain
	 */
	void insertDbConfigure(DbConfigure domain);
	
	/**
	 * 修改数据库配置
	 * @param domain
	 */
	void updateDbConfigure(DbConfigure domain);
	
	/**
	 * 删除数据库配置
	 * @param id
	 */
	void deleteDbConfigure(Long id);


}

