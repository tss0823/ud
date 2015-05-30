/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service;
import java.util.List;
import java.util.Map;
import com.usefullc.ud.domain.ValidRule;
import com.usefullc.platform.common.web.Pagination;

/**
 *  校验规则 Service
 * @author tangss
 *
 * @2014-03-13 15
 */
public interface IValidRuleService {
	
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
	 * @param validRule
	 */
	void insertValidRule(ValidRule domain);
	
	/**
	 * 修改校验规则
	 * @param validRule
	 */
	void updateValidRule(ValidRule domain);
	
	/**
	 * 删除校验规则
	 * @param id
	 */
	void deleteValidRule(Long id);
    

}

