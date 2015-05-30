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
import com.usefullc.ud.dao.IValidItemPropertyDao;
import com.usefullc.ud.domain.ValidItemProperty;

@Service
public class ValidItemPropertyDaoImpl extends AbstractBaseDao implements IValidItemPropertyDao {
	
	@Override
	public ValidItemProperty getValidItemProperty(Long id) {
		 return (ValidItemProperty) sqlSession.selectOne("ValidItemPropertyMapper.getValidItemProperty", id);
	}
	
	@Override
	public List<ValidItemProperty> getValidItemPropertyList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("ValidItemPropertyMapper.getValidItemPropertyList",queryMap);
	}
	
	public Pagination<ValidItemProperty> getValidItemPropertyListPage(Map<String,Object> queryMap) {
		Pagination<ValidItemProperty> page = getPagination(queryMap, "ValidItemPropertyMapper.getValidItemPropertyListPage", "ValidItemPropertyMapper.getValidItemPropertyListPageCount");
		return page;
	}

	@Override
	public void insertValidItemProperty(ValidItemProperty domain) {
		sqlSession.insert("ValidItemPropertyMapper.insertValidItemProperty", domain);
	}

	@Override
	public void updateValidItemProperty(ValidItemProperty domain) {
		sqlSession.update("ValidItemPropertyMapper.updateValidItemProperty", domain);
	}

	@Override
	public void deleteValidItemProperty(Long id) {
		sqlSession.delete("ValidItemPropertyMapper.deleteValidItemProperty", id);
	}

}
