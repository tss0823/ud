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

import com.usefullc.ud.domain.ValidRule;

/**
 * 校验规则DAO
 * @author tangss
 *
 * @2014-03-13 15
 */
public interface  IValidRuleDao {
	
	/**
	 * 获得校验规则
	 * @param id
	 * @return
	 */
	ValidRule getValidRule(Long id);
	
	/**
	 * 获得校验规则列表
	 * @param queryMap
	 * @return
	 */
	List<ValidRule> getValidRuleList(Map<String,Object> queryMap);
	
	/**
	 * 获得校验规则分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<ValidRule> getValidRuleListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增校验规则
	 * @param domain
	 */
	void insertValidRule(ValidRule domain);
	
	/**
	 * 修改校验规则
	 * @param domain
	 */
	void updateValidRule(ValidRule domain);
	
	/**
	 * 删除校验规则
	 * @param id
	 */
	void deleteValidRule(Long id);


}

