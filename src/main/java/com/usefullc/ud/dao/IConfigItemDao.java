/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.dao;
import java.util.List;
import java.util.Map;

import com.usefullc.platform.common.web.Pagination;

import com.usefullc.ud.domain.ConfigItem;

/**
 * 配置项DAO
 * @author tangss
 *
 * @2013-4-6 @上午10:36:10
 */
public interface  IConfigItemDao {
	
	/**
	 * 获得配置项
	 * @param id
	 * @return
	 */
	ConfigItem getConfigItem(Long id);
	
	/**
	 * 获得配置项列表
	 * @param queryMap
	 * @return
	 */
	List<ConfigItem> getConfigItemList(Map<String,Object> queryMap);
	
	/**
	 * 获得配置项分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<ConfigItem> getConfigItemListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增配置项
	 * @param domain
	 */
	void insertConfigItem(ConfigItem domain);
	
	/**
	 * 修改配置项
	 * @param domain
	 */
	void updateConfigItem(ConfigItem domain);
	
	/**
	 * 删除配置项
	 * @param id
	 */
	void deleteConfigItem(Long id);
	
 

}

