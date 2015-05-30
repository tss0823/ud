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
import com.usefullc.ud.dao.IEnumDataDao;
import com.usefullc.ud.domain.EnumData;
import com.usefullc.ud.service.IEnumDataService;


@Service
public class EnumDataServiceImpl extends AbstractBaseService implements IEnumDataService {
	
	@Autowired
	private IEnumDataDao enumDataDao;

	public EnumData getEnumData(Long id) {
		return enumDataDao.getEnumData(id);
	}

	public List<EnumData> getEnumDataList(Map<String, Object> queryMap) {
		return enumDataDao.getEnumDataList(queryMap);
	}
	
	@Override
	public Pagination<EnumData> getEnumDataListPage(Map<String, Object> queryMap) {
		return enumDataDao.getEnumDataListPage(queryMap);
	}
	

	public void insertEnumData(EnumData enumData) {
		enumDataDao.insertEnumData(enumData);
	}

	public void updateEnumData(EnumData enumData) {
		enumDataDao.updateEnumData(enumData);
	}

	public void deleteEnumData(Long id) {
		enumDataDao.deleteEnumData(id);
	}




	
}
