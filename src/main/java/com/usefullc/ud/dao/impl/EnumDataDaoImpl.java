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
import com.usefullc.ud.dao.IEnumDataDao;
import com.usefullc.ud.domain.EnumData;

@Service
public class EnumDataDaoImpl extends AbstractBaseDao implements IEnumDataDao {
	
	@Override
	public EnumData getEnumData(Long id) {
		 return (EnumData) sqlSession.selectOne("EnumDataMapper.getEnumData", id);
	}
	
	@Override
	public List<EnumData> getEnumDataList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("EnumDataMapper.getEnumDataList",queryMap);
	}
	
	public Pagination<EnumData> getEnumDataListPage(Map<String,Object> queryMap) {
		Pagination<EnumData> page = getPagination(queryMap, "EnumDataMapper.getEnumDataListPage", "EnumDataMapper.getEnumDataListPageCount");
		return page;
	}

	@Override
	public void insertEnumData(EnumData domain) {
		sqlSession.insert("EnumDataMapper.insertEnumData", domain);
	}

	@Override
	public void updateEnumData(EnumData domain) {
		sqlSession.update("EnumDataMapper.updateEnumData", domain);
	}

	@Override
	public void deleteEnumData(Long id) {
		sqlSession.delete("EnumDataMapper.deleteEnumData", id);
	}

}
