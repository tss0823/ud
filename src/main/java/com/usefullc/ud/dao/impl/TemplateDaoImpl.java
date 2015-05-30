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
import com.usefullc.ud.dao.ITemplateDao;
import com.usefullc.ud.domain.Template;

@Service
public class TemplateDaoImpl extends AbstractBaseDao implements ITemplateDao {
	
	@Override
	public Template getTemplate(Long id) {
		 return (Template) sqlSession.selectOne("TemplateMapper.getTemplate", id);
	}
	
	@Override
	public List<Template> getTemplateList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("TemplateMapper.getTemplateList",queryMap);
	}
	
	public Pagination<Template> getTemplateListPage(Map<String,Object> queryMap) {
		Pagination<Template> page = getPagination(queryMap, "TemplateMapper.getTemplateListPage", "TemplateMapper.getTemplateListPageCount");
		return page;
	}

	@Override
	public void insertTemplate(Template domain) {
		sqlSession.insert("TemplateMapper.insertTemplate", domain);
	}

	@Override
	public void updateTemplate(Template domain) {
		sqlSession.update("TemplateMapper.updateTemplate", domain);
	}

	@Override
	public void deleteTemplate(Long id) {
		sqlSession.delete("TemplateMapper.deleteTemplate", id);
	}

}
