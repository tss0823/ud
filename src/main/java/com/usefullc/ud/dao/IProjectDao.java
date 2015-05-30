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

import com.usefullc.ud.domain.Project;

/**
 * 项目DAO
 * @author tangss
 *
 * @2013-4-6 @上午10:36:10
 */
public interface  IProjectDao {
	
	/**
	 * 获得项目
	 * @param id
	 * @return
	 */
	Project getProject(Long id);
	
	/**
	 * 获得项目列表
	 * @param queryMap
	 * @return
	 */
	List<Project> getProjectList(Map<String,Object> queryMap);
	
	/**
	 * 获得项目分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<Project> getProjectListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增项目
	 * @param domain
	 */
	void insertProject(Project domain);
	
	/**
	 * 修改项目
	 * @param domain
	 */
	void updateProject(Project domain);
	
	/**
	 * 删除项目
	 * @param id
	 */
	void deleteProject(Long id);


}

