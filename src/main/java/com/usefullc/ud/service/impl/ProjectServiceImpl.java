/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.service.AbstractBaseService;
import com.usefullc.platform.web.form.TreeFrm;
import com.usefullc.ud.dao.IProjectDao;
import com.usefullc.ud.domain.Project;
import com.usefullc.ud.service.IProjectService;

/**
 * 项目 Service 实现类
 * 
 * @author tangss
 * @2013-8-7 @上午11:09:06
 */
@Service
public class ProjectServiceImpl extends AbstractBaseService implements IProjectService {

    @Autowired
    private IProjectDao projectDao;

    @Override
    public Project getProject(Long id) {
        return projectDao.getProject(id);
    }

    @Override
    public List<Project> getProjectList(Map<String, Object> queryMap) {
        return projectDao.getProjectList(queryMap);
    }

    @Override
    public Pagination<Project> getProjectListPage(Map<String, Object> queryMap) {
        return projectDao.getProjectListPage(queryMap);
    }

    @Override
    public void insertProject(Project project) {
        projectDao.insertProject(project);
    }

    @Override
    public void updateProject(Project project) {
        projectDao.updateProject(project);
    }

    @Override
    public void deleteProject(Long id) {
        projectDao.deleteProject(id);
    }

    /*
     * (non-Javadoc)
     * @see com.usefullc.ud.service.IProjectService#getTreeList()
     */
    @Override
    public List<TreeFrm> getTreeList() {
        List<Project> list = projectDao.getProjectList(null);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<TreeFrm> treeList = new ArrayList<TreeFrm>();
        TreeFrm treeFrm = new TreeFrm();
        treeFrm.setId(0L);
        treeFrm.setParentId(-1L);
        treeFrm.setName("所有项目");
        treeList.add(treeFrm);
        for (Project project : list) {
            treeFrm = new TreeFrm();
            treeFrm.setId(project.getId());
            treeFrm.setParentId(0L);
            treeFrm.setName(project.getName());
            treeList.add(treeFrm);
        }
        return treeList;
    }

}
