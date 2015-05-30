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
import com.usefullc.ud.dao.IPropertyDao;
import com.usefullc.ud.domain.Property;

@Service
public class PropertyDaoImpl extends AbstractBaseDao implements IPropertyDao {
	
	@Override
	public Property getProperty(Long id) {
		 return (Property) sqlSession.selectOne("PropertyMapper.getProperty", id);
	}
	
	@Override
	public List<Property> getPropertyList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("PropertyMapper.getPropertyList",queryMap);
	}
	
	@Override
	public Pagination<Property> getPropertyListPage(Map<String,Object> queryMap) {
		Pagination<Property> page = getPagination(queryMap, "PropertyMapper.getPropertyListPage", "PropertyMapper.getPropertyListPageCount");
		return page;
	}

	@Override
	public void insertProperty(Property domain) {
		sqlSession.insert("PropertyMapper.insertProperty", domain);
	}

	@Override
	public void updateProperty(Property domain) {
		sqlSession.update("PropertyMapper.updateProperty", domain);
	}

	@Override
	public void deleteProperty(Long id) {
		sqlSession.delete("PropertyMapper.deleteProperty", id);
	}

}
