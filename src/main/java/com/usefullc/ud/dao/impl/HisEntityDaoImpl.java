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
import com.usefullc.ud.dao.IHisEntityDao;
import com.usefullc.ud.domain.HisEntity;

@Service
public class HisEntityDaoImpl extends AbstractBaseDao implements IHisEntityDao {
	
	@Override
	public HisEntity getHisEntity(Long id) {
		 return (HisEntity) sqlSession.selectOne("HisEntityMapper.getHisEntity", id);
	}
	
	@Override
	public List<HisEntity> getHisEntityList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("HisEntityMapper.getHisEntityList",queryMap);
	}
	
	public Pagination<HisEntity> getHisEntityListPage(Map<String,Object> queryMap) {
		Pagination<HisEntity> page = getPagination(queryMap, "HisEntityMapper.getHisEntityListPage", "HisEntityMapper.getHisEntityListPageCount");
		return page;
	}

	@Override
	public void insertHisEntity(HisEntity domain) {
		sqlSession.insert("HisEntityMapper.insertHisEntity", domain);
	}

	@Override
	public void updateHisEntity(HisEntity domain) {
		sqlSession.update("HisEntityMapper.updateHisEntity", domain);
	}

	@Override
	public void deleteHisEntity(Long id) {
		sqlSession.delete("HisEntityMapper.deleteHisEntity", id);
	}

}
