/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service.impl;


import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.service.AbstractBaseService;
import com.usefullc.ud.dao.IDbConfigureDao;
import com.usefullc.ud.domain.Application;
import com.usefullc.ud.domain.DbConfigure;
import com.usefullc.ud.service.IApplicationService;
import com.usefullc.ud.service.IDbConfigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 数据库配置   Service 实现类
 * @author tangss
 *
 * @2013-8-7 @上午11:09:06
 */
@Service
public class DbConfigureServiceImpl extends AbstractBaseService implements IDbConfigureService {
	
	@Autowired
	private IDbConfigureDao dbConfigureDao;

	@Autowired
	private IApplicationService applicationService;

	@Override
	public DbConfigure getDbConfigure(Long id) {
		return dbConfigureDao.getDbConfigure(id);
	}

	@Override
	public DbConfigure getDbConfigureByAppId(Long appId) {
		Application application = applicationService.getApplication(appId);
		DbConfigure dbConfigure = this.getDbConfigure(application.getDbId());
		return dbConfigure;
	}

	@Override
	public List<DbConfigure> getDbConfigureList(Map<String, Object> queryMap) {
		return dbConfigureDao.getDbConfigureList(queryMap);
	}
	
	@Override
	public Pagination<DbConfigure> getDbConfigureListPage(Map<String, Object> queryMap) {
		return dbConfigureDao.getDbConfigureListPage(queryMap);
	}
	

	@Override
	public void insertDbConfigure(DbConfigure dbConfigure) {
		dbConfigureDao.insertDbConfigure(dbConfigure);
	}

	@Override
	public void updateDbConfigure(DbConfigure dbConfigure) {
		dbConfigureDao.updateDbConfigure(dbConfigure);
	}

	@Override
	public void deleteDbConfigure(Long id) {
		dbConfigureDao.deleteDbConfigure(id);
	}




	
}
