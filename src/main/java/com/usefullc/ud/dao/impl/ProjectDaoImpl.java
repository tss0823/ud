/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.dao.AbstractBaseDao;
import com.usefullc.ud.dao.IProjectDao;
import com.usefullc.ud.domain.Project;

@Service
public class ProjectDaoImpl extends AbstractBaseDao implements IProjectDao {
	
	@Override
	public Project getProject(Long id) {
		 return (Project) sqlSession.selectOne("ProjectMapper.getProject", id);
	}
	
	@Override
	public List<Project> getProjectList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("ProjectMapper.getProjectList",queryMap);
	}
	
	@Override
	public Pagination<Project> getProjectListPage(Map<String,Object> queryMap) {
		Pagination<Project> page = getPagination(queryMap, "ProjectMapper.getProjectListPage", "ProjectMapper.getProjectListPageCount");
		return page;
	}

	@Override
	public void insertProject(Project domain) {
		sqlSession.insert("ProjectMapper.insertProject", domain);
	}

	@Override
	public void updateProject(Project domain) {
		sqlSession.update("ProjectMapper.updateProject", domain);
	}

	@Override
	public void deleteProject(Long id) {
		sqlSession.delete("ProjectMapper.deleteProject", id);
	}

}
