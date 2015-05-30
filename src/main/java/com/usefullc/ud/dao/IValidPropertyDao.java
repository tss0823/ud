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

import com.usefullc.ud.domain.ValidProperty;

/**
 * 校验属性DAO
 * @author tangss
 *
 * @2014-03-13 15
 */
public interface  IValidPropertyDao {
	
	/**
	 * 获得校验属性
	 * @param id
	 * @return
	 */
	ValidProperty getValidProperty(Long id);
	
	/**
	 * 获得校验属性列表
	 * @param queryMap
	 * @return
	 */
	List<ValidProperty> getValidPropertyList(Map<String,Object> queryMap);
	
	/**
	 * 获得校验属性分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<ValidProperty> getValidPropertyListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增校验属性
	 * @param domain
	 */
	void insertValidProperty(ValidProperty domain);
	
	/**
	 * 修改校验属性
	 * @param domain
	 */
	void updateValidProperty(ValidProperty domain);
	
	/**
	 * 删除校验属性
	 * @param id
	 */
	void deleteValidProperty(Long id);


}

