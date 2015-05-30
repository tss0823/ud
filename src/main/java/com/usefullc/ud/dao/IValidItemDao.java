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

import com.usefullc.ud.domain.ValidItem;

/**
 * 校验项DAO
 * @author tangss
 *
 * @2014-03-13 15
 */
public interface  IValidItemDao {
	
	/**
	 * 获得校验项
	 * @param id
	 * @return
	 */
	ValidItem getValidItem(Long id);
	
	/**
	 * 获得校验项列表
	 * @param queryMap
	 * @return
	 */
	List<ValidItem> getValidItemList(Map<String,Object> queryMap);
	
	/**
	 * 获得校验项分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<ValidItem> getValidItemListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增校验项
	 * @param domain
	 */
	void insertValidItem(ValidItem domain);
	
	/**
	 * 修改校验项
	 * @param domain
	 */
	void updateValidItem(ValidItem domain);
	
	/**
	 * 删除校验项
	 * @param id
	 */
	void deleteValidItem(Long id);


}

