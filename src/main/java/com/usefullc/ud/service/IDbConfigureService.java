/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service;
import java.util.List;
import java.util.Map;
import com.usefullc.ud.domain.DbConfigure;
import com.usefullc.platform.common.web.Pagination;

/**
 *  数据库配置 Service
 * @author tangss
 *
 * @2013-8-7 @上午11:13:42
 */
public interface IDbConfigureService {
	
	/**
	 * 获得数据库配置
	 * @param id
	 * @return
	 */
	DbConfigure getDbConfigure(Long id);

	DbConfigure getDbConfigureByAppId(Long appId);

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
	 * @param user
	 */
	void insertDbConfigure(DbConfigure domain);
	
	/**
	 * 修改数据库配置
	 * @param user
	 */
	void updateDbConfigure(DbConfigure domain);
	
	/**
	 * 删除数据库配置
	 * @param id
	 */
	void deleteDbConfigure(Long id);
    

}

