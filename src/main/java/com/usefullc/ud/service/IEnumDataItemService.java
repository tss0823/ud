/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service;
import java.util.List;
import java.util.Map;
import com.usefullc.ud.domain.EnumDataItem;
import com.usefullc.platform.common.web.Pagination;

/**
 *  枚举数据项 Service
 * @author ${author}
 *
 * @2013-10-12 13
 */
public interface IEnumDataItemService {
	
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
	 * @param enumDataItem
	 */
	void insertEnumDataItem(EnumDataItem domain);
	
	/**
	 * 修改枚举数据项
	 * @param enumDataItem
	 */
	void updateEnumDataItem(EnumDataItem domain);
	
	/**
	 * 删除枚举数据项
	 * @param id
	 */
	void deleteEnumDataItem(Long id);
	

	/**
	 * 修改枚举数据项
	 * @param parentId
	 * @param value
	 * @param text
	 * @param extText
	 */
	void updateEnumDataItem(Long parentId,String [] value,String [] text,String [] extText);
    

}

