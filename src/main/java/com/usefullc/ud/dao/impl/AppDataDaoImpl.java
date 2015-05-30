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
import com.usefullc.ud.dao.IAppDataDao;
import com.usefullc.ud.domain.AppData;

@Service
public class AppDataDaoImpl extends AbstractBaseDao implements IAppDataDao {
	
	@Override
	public AppData getAppData(Long id) {
		 return (AppData) sqlSession.selectOne("AppDataMapper.getAppData", id);
	}
	
	@Override
	public List<AppData> getAppDataList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("AppDataMapper.getAppDataList",queryMap);
	}
	
	@Override
	public Pagination<AppData> getAppDataListPage(Map<String,Object> queryMap) {
		Pagination<AppData> page = getPagination(queryMap, "AppDataMapper.getAppDataListPage", "AppDataMapper.getAppDataListPageCount");
		return page;
	}

	@Override
	public void insertAppData(AppData domain) {
		sqlSession.insert("AppDataMapper.insertAppData", domain);
	}

	@Override
	public void updateAppData(AppData domain) {
		sqlSession.update("AppDataMapper.updateAppData", domain);
	}

	@Override
	public void deleteAppData(Long id) {
		sqlSession.delete("AppDataMapper.deleteAppData", id);
	}

}
