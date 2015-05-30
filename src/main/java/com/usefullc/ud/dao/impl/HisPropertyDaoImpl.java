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
import com.usefullc.ud.dao.IHisPropertyDao;
import com.usefullc.ud.domain.HisProperty;

@Service
public class HisPropertyDaoImpl extends AbstractBaseDao implements IHisPropertyDao {
	
	@Override
	public HisProperty getHisProperty(Long id) {
		 return (HisProperty) sqlSession.selectOne("HisPropertyMapper.getHisProperty", id);
	}
	
	@Override
	public List<HisProperty> getHisPropertyList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("HisPropertyMapper.getHisPropertyList",queryMap);
	}
	
	public Pagination<HisProperty> getHisPropertyListPage(Map<String,Object> queryMap) {
		Pagination<HisProperty> page = getPagination(queryMap, "HisPropertyMapper.getHisPropertyListPage", "HisPropertyMapper.getHisPropertyListPageCount");
		return page;
	}

	@Override
	public void insertHisProperty(HisProperty domain) {
		sqlSession.insert("HisPropertyMapper.insertHisProperty", domain);
	}

	@Override
	public void updateHisProperty(HisProperty domain) {
		sqlSession.update("HisPropertyMapper.updateHisProperty", domain);
	}

	@Override
	public void deleteHisProperty(Long id) {
		sqlSession.delete("HisPropertyMapper.deleteHisProperty", id);
	}

}
