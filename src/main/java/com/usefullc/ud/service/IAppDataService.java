/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service;
import java.util.List;
import java.util.Map;
import com.usefullc.ud.domain.AppData;
import com.usefullc.platform.common.web.Pagination;

/**
 *   Service
 * @author tangss
 *
 * @2013-8-7 @上午11:13:42
 */
public interface IAppDataService {
	
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
	 * @param user
	 */
	void insertAppData(AppData domain);
	
	/**
	 * 修改
	 * @param user
	 */
	void updateAppData(AppData domain);
	
	/**
	 * 删除
	 * @param id
	 */
	void deleteAppData(Long id);
    

}

