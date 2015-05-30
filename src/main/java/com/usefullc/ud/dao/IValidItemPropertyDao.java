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

import com.usefullc.ud.domain.ValidItemProperty;

/**
 * 校验项属性DAO
 * @author tangss
 *
 * @2014-03-13 15
 */
public interface  IValidItemPropertyDao {
	
	/**
	 * 获得校验项属性
	 * @param id
	 * @return
	 */
	ValidItemProperty getValidItemProperty(Long id);
	
	/**
	 * 获得校验项属性列表
	 * @param queryMap
	 * @return
	 */
	List<ValidItemProperty> getValidItemPropertyList(Map<String,Object> queryMap);
	
	/**
	 * 获得校验项属性分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<ValidItemProperty> getValidItemPropertyListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增校验项属性
	 * @param domain
	 */
	void insertValidItemProperty(ValidItemProperty domain);
	
	/**
	 * 修改校验项属性
	 * @param domain
	 */
	void updateValidItemProperty(ValidItemProperty domain);
	
	/**
	 * 删除校验项属性
	 * @param id
	 */
	void deleteValidItemProperty(Long id);


}

