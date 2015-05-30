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
import com.usefullc.ud.dao.IDbConfigureDao;
import com.usefullc.ud.domain.DbConfigure;

@Service
public class DbConfigureDaoImpl extends AbstractBaseDao implements IDbConfigureDao {
	
	@Override
	public DbConfigure getDbConfigure(Long id) {
		 return (DbConfigure) sqlSession.selectOne("DbConfigureMapper.getDbConfigure", id);
	}
	
	@Override
	public List<DbConfigure> getDbConfigureList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("DbConfigureMapper.getDbConfigureList",queryMap);
	}
	
	@Override
	public Pagination<DbConfigure> getDbConfigureListPage(Map<String,Object> queryMap) {
		Pagination<DbConfigure> page = getPagination(queryMap, "DbConfigureMapper.getDbConfigureListPage", "DbConfigureMapper.getDbConfigureListPageCount");
		return page;
	}

	@Override
	public void insertDbConfigure(DbConfigure domain) {
		sqlSession.insert("DbConfigureMapper.insertDbConfigure", domain);
	}

	@Override
	public void updateDbConfigure(DbConfigure domain) {
		sqlSession.update("DbConfigureMapper.updateDbConfigure", domain);
	}

	@Override
	public void deleteDbConfigure(Long id) {
		sqlSession.delete("DbConfigureMapper.deleteDbConfigure", id);
	}

}
