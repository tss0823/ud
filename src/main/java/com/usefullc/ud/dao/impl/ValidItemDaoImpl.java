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
import com.usefullc.ud.dao.IValidItemDao;
import com.usefullc.ud.domain.ValidItem;

@Service
public class ValidItemDaoImpl extends AbstractBaseDao implements IValidItemDao {
	
	@Override
	public ValidItem getValidItem(Long id) {
		 return (ValidItem) sqlSession.selectOne("ValidItemMapper.getValidItem", id);
	}
	
	@Override
	public List<ValidItem> getValidItemList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("ValidItemMapper.getValidItemList",queryMap);
	}
	
	public Pagination<ValidItem> getValidItemListPage(Map<String,Object> queryMap) {
		Pagination<ValidItem> page = getPagination(queryMap, "ValidItemMapper.getValidItemListPage", "ValidItemMapper.getValidItemListPageCount");
		return page;
	}

	@Override
	public void insertValidItem(ValidItem domain) {
		sqlSession.insert("ValidItemMapper.insertValidItem", domain);
	}

	@Override
	public void updateValidItem(ValidItem domain) {
		sqlSession.update("ValidItemMapper.updateValidItem", domain);
	}

	@Override
	public void deleteValidItem(Long id) {
		sqlSession.delete("ValidItemMapper.deleteValidItem", id);
	}

}
