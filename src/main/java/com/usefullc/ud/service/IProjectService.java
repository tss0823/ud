/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service;

import java.util.List;
import java.util.Map;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.web.form.TreeFrm;
import com.usefullc.ud.domain.Project;

/**
 * 项目 Service
 * 
 * @author tangss
 * @2013-8-7 @上午11:13:42
 */
public interface IProjectService {

    /**
     * 获得项目
     * 
     * @param id
     * @return
     */
    Project getProject(Long id);

    /**
     * 获得项目列表
     * 
     * @param queryMap
     * @return
     */
    List<Project> getProjectList(Map<String, Object> queryMap);

    /**
     * 获得项目分页列表
     * 
     * @param queryMap
     * @return
     */
    Pagination<Project> getProjectListPage(Map<String, Object> queryMap);

    /**
     * 新增项目
     * 
     * @param user
     */
    void insertProject(Project domain);

    /**
     * 修改项目
     * 
     * @param user
     */
    void updateProject(Project domain);

    /**
     * 删除项目
     * 
     * @param id
     */
    void deleteProject(Long id);

    /**
     * 获得项目树
     * 
     * @return
     */
    List<TreeFrm> getTreeList();

}
