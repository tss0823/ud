/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.dao.impl;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.dao.AbstractBaseDao;
import com.usefullc.ud.dao.IEntityDao;
import com.usefullc.ud.domain.Entity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EntityDaoImpl extends AbstractBaseDao implements IEntityDao {
	
	@Override
	public Entity getEntity(Long id) {
		 return (Entity) sqlSession.selectOne("EntityMapper.getEntity", id);
	}

	@Override
	public Entity getEntityByEnName(String enName) {
		return (Entity) sqlSession.selectOne("EntityMapper.getEntityByEnName", enName);
	}

	@Override
	public List<Entity> getEntityList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("EntityMapper.getEntityList",queryMap);
	}
	
	@Override
	public Pagination<Entity> getEntityListPage(Map<String,Object> queryMap) {
		Pagination<Entity> page = getPagination(queryMap, "EntityMapper.getEntityListPage", "EntityMapper.getEntityListPageCount");
		return page;
	}

	@Override
	public void insertEntity(Entity domain) {
		sqlSession.insert("EntityMapper.insertEntity", domain);
	}

	@Override
	public void updateEntity(Entity domain) {
		sqlSession.update("EntityMapper.updateEntity", domain);
	}

	@Override
	public void deleteEntity(Long id) {
		sqlSession.delete("EntityMapper.deleteEntity", id);
	}

}
