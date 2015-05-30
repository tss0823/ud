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

import com.usefullc.ud.domain.Template;

/**
 * 模版DAO
 * @author tangss
 *
 * @2014-03-27 16
 */
public interface  ITemplateDao {
	
	/**
	 * 获得模版
	 * @param id
	 * @return
	 */
	Template getTemplate(Long id);
	
	/**
	 * 获得模版列表
	 * @param queryMap
	 * @return
	 */
	List<Template> getTemplateList(Map<String,Object> queryMap);
	
	/**
	 * 获得模版分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<Template> getTemplateListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增模版
	 * @param domain
	 */
	void insertTemplate(Template domain);
	
	/**
	 * 修改模版
	 * @param domain
	 */
	void updateTemplate(Template domain);
	
	/**
	 * 删除模版
	 * @param id
	 */
	void deleteTemplate(Long id);


}

