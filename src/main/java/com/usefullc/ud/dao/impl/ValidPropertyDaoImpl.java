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
import com.usefullc.ud.dao.IValidPropertyDao;
import com.usefullc.ud.domain.ValidProperty;

@Service
public class ValidPropertyDaoImpl extends AbstractBaseDao implements IValidPropertyDao {
	
	@Override
	public ValidProperty getValidProperty(Long id) {
		 return (ValidProperty) sqlSession.selectOne("ValidPropertyMapper.getValidProperty", id);
	}
	
	@Override
	public List<ValidProperty> getValidPropertyList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("ValidPropertyMapper.getValidPropertyList",queryMap);
	}
	
	public Pagination<ValidProperty> getValidPropertyListPage(Map<String,Object> queryMap) {
		Pagination<ValidProperty> page = getPagination(queryMap, "ValidPropertyMapper.getValidPropertyListPage", "ValidPropertyMapper.getValidPropertyListPageCount");
		return page;
	}

	@Override
	public void insertValidProperty(ValidProperty domain) {
		sqlSession.insert("ValidPropertyMapper.insertValidProperty", domain);
	}

	@Override
	public void updateValidProperty(ValidProperty domain) {
		sqlSession.update("ValidPropertyMapper.updateValidProperty", domain);
	}

	@Override
	public void deleteValidProperty(Long id) {
		sqlSession.delete("ValidPropertyMapper.deleteValidProperty", id);
	}

}
