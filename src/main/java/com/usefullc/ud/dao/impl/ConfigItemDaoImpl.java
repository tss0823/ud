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
import com.usefullc.ud.dao.IConfigItemDao;
import com.usefullc.ud.domain.ConfigItem;

@Service
public class ConfigItemDaoImpl extends AbstractBaseDao implements IConfigItemDao {
	
	@Override
	public ConfigItem getConfigItem(Long id) {
		 return (ConfigItem) sqlSession.selectOne("ConfigItemMapper.getConfigItem", id);
	}
	
	@Override
	public List<ConfigItem> getConfigItemList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("ConfigItemMapper.getConfigItemList",queryMap);
	}
	
	@Override
	public Pagination<ConfigItem> getConfigItemListPage(Map<String,Object> queryMap) {
		Pagination<ConfigItem> page = getPagination(queryMap, "ConfigItemMapper.getConfigItemListPage", "ConfigItemMapper.getConfigItemListPageCount");
		return page;
	}

	@Override
	public void insertConfigItem(ConfigItem domain) {
		sqlSession.insert("ConfigItemMapper.insertConfigItem", domain);
	}

	@Override
	public void updateConfigItem(ConfigItem domain) {
		sqlSession.update("ConfigItemMapper.updateConfigItem", domain);
	}

	@Override
	public void deleteConfigItem(Long id) {
		sqlSession.delete("ConfigItemMapper.deleteConfigItem", id);
	}

}
