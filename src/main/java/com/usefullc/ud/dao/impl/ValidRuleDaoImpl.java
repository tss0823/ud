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
import com.usefullc.ud.dao.IValidRuleDao;
import com.usefullc.ud.domain.ValidRule;

@Service
public class ValidRuleDaoImpl extends AbstractBaseDao implements IValidRuleDao {
	
	@Override
	public ValidRule getValidRule(Long id) {
		 return (ValidRule) sqlSession.selectOne("ValidRuleMapper.getValidRule", id);
	}
	
	@Override
	public List<ValidRule> getValidRuleList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("ValidRuleMapper.getValidRuleList",queryMap);
	}
	
	public Pagination<ValidRule> getValidRuleListPage(Map<String,Object> queryMap) {
		Pagination<ValidRule> page = getPagination(queryMap, "ValidRuleMapper.getValidRuleListPage", "ValidRuleMapper.getValidRuleListPageCount");
		return page;
	}

	@Override
	public void insertValidRule(ValidRule domain) {
		sqlSession.insert("ValidRuleMapper.insertValidRule", domain);
	}

	@Override
	public void updateValidRule(ValidRule domain) {
		sqlSession.update("ValidRuleMapper.updateValidRule", domain);
	}

	@Override
	public void deleteValidRule(Long id) {
		sqlSession.delete("ValidRuleMapper.deleteValidRule", id);
	}

}
