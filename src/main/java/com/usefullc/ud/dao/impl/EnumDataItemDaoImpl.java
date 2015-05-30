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
import com.usefullc.ud.dao.IEnumDataItemDao;
import com.usefullc.ud.domain.EnumDataItem;

@Service
public class EnumDataItemDaoImpl extends AbstractBaseDao implements IEnumDataItemDao {
	
	@Override
	public EnumDataItem getEnumDataItem(Long id) {
		 return (EnumDataItem) sqlSession.selectOne("EnumDataItemMapper.getEnumDataItem", id);
	}
	
	@Override
	public List<EnumDataItem> getEnumDataItemList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("EnumDataItemMapper.getEnumDataItemList",queryMap);
	}
	
	public Pagination<EnumDataItem> getEnumDataItemListPage(Map<String,Object> queryMap) {
		Pagination<EnumDataItem> page = getPagination(queryMap, "EnumDataItemMapper.getEnumDataItemListPage", "EnumDataItemMapper.getEnumDataItemListPageCount");
		return page;
	}

	@Override
	public void insertEnumDataItem(EnumDataItem domain) {
		sqlSession.insert("EnumDataItemMapper.insertEnumDataItem", domain);
	}

	@Override
	public void updateEnumDataItem(EnumDataItem domain) {
		sqlSession.update("EnumDataItemMapper.updateEnumDataItem", domain);
	}

	@Override
	public void deleteEnumDataItem(Long id) {
		sqlSession.delete("EnumDataItemMapper.deleteEnumDataItem", id);
	}

}
