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

import com.usefullc.ud.domain.EnumDataItem;

/**
 * 枚举数据项DAO
 * @author ${author}
 *
 * @2013-10-12 13
 */
public interface  IEnumDataItemDao {
	
	/**
	 * 获得枚举数据项
	 * @param id
	 * @return
	 */
	EnumDataItem getEnumDataItem(Long id);
	
	/**
	 * 获得枚举数据项列表
	 * @param queryMap
	 * @return
	 */
	List<EnumDataItem> getEnumDataItemList(Map<String,Object> queryMap);
	
	/**
	 * 获得枚举数据项分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<EnumDataItem> getEnumDataItemListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增枚举数据项
	 * @param domain
	 */
	void insertEnumDataItem(EnumDataItem domain);
	
	/**
	 * 修改枚举数据项
	 * @param domain
	 */
	void updateEnumDataItem(EnumDataItem domain);
	
	/**
	 * 删除枚举数据项
	 * @param id
	 */
	void deleteEnumDataItem(Long id);


}

