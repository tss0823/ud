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

import com.usefullc.ud.domain.EnumData;

/**
 * 枚举数据DAO
 * @author ${author}
 *
 * @2013-10-12 13
 */
public interface  IEnumDataDao {
	
	/**
	 * 获得枚举数据
	 * @param id
	 * @return
	 */
	EnumData getEnumData(Long id);
	
	/**
	 * 获得枚举数据列表
	 * @param queryMap
	 * @return
	 */
	List<EnumData> getEnumDataList(Map<String,Object> queryMap);
	
	/**
	 * 获得枚举数据分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<EnumData> getEnumDataListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增枚举数据
	 * @param domain
	 */
	void insertEnumData(EnumData domain);
	
	/**
	 * 修改枚举数据
	 * @param domain
	 */
	void updateEnumData(EnumData domain);
	
	/**
	 * 删除枚举数据
	 * @param id
	 */
	void deleteEnumData(Long id);


}

