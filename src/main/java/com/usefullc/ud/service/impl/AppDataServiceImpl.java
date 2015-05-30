/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.service.AbstractBaseService;
import com.usefullc.ud.dao.IAppDataDao;
import com.usefullc.ud.domain.AppData;
import com.usefullc.ud.service.IAppDataService;

/**
 *    Service 实现类
 * @author tangss
 *
 * @2013-8-7 @上午11:09:06
 */
@Service
public class AppDataServiceImpl extends AbstractBaseService implements IAppDataService {
	
	@Autowired
	private IAppDataDao appDataDao;

	@Override
	public AppData getAppData(Long id) {
		return appDataDao.getAppData(id);
	}

	@Override
	public List<AppData> getAppDataList(Map<String, Object> queryMap) {
		return appDataDao.getAppDataList(queryMap);
	}
	
	@Override
	public Pagination<AppData> getAppDataListPage(Map<String, Object> queryMap) {
		return appDataDao.getAppDataListPage(queryMap);
	}
	

	@Override
	public void insertAppData(AppData appData) {
		appDataDao.insertAppData(appData);
	}

	@Override
	public void updateAppData(AppData appData) {
		appDataDao.updateAppData(appData);
	}

	@Override
	public void deleteAppData(Long id) {
		appDataDao.deleteAppData(id);
	}




	
}
