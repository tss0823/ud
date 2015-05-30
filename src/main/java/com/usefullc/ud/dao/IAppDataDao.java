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

import com.usefullc.ud.domain.AppData;

/**
 * DAO
 * @author tangss
 *
 * @2013-4-6 @上午10:36:10
 */
public interface  IAppDataDao {
	
	/**
	 * 获得
	 * @param id
	 * @return
	 */
	AppData getAppData(Long id);
	
	/**
	 * 获得列表
	 * @param queryMap
	 * @return
	 */
	List<AppData> getAppDataList(Map<String,Object> queryMap);
	
	/**
	 * 获得分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<AppData> getAppDataListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增
	 * @param domain
	 */
	void insertAppData(AppData domain);
	
	/**
	 * 修改
	 * @param domain
	 */
	void updateAppData(AppData domain);
	
	/**
	 * 删除
	 * @param id
	 */
	void deleteAppData(Long id);


}

