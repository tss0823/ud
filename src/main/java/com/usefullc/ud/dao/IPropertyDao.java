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

import com.usefullc.ud.domain.Property;

/**
 * 属性DAO
 * @author tangss
 *
 * @2013-4-6 @上午10:36:10
 */
public interface  IPropertyDao {
	
	/**
	 * 获得属性
	 * @param id
	 * @return
	 */
	Property getProperty(Long id);
	
	/**
	 * 获得属性列表
	 * @param queryMap
	 * @return
	 */
	List<Property> getPropertyList(Map<String,Object> queryMap);
	
	/**
	 * 获得属性分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<Property> getPropertyListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增属性
	 * @param domain
	 */
	void insertProperty(Property domain);
	
	/**
	 * 修改属性
	 * @param domain
	 */
	void updateProperty(Property domain);
	
	/**
	 * 删除属性
	 * @param id
	 */
	void deleteProperty(Long id);


}

