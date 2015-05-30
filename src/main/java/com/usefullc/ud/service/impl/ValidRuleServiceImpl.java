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
import com.usefullc.ud.dao.IValidRuleDao;
import com.usefullc.ud.domain.ValidRule;
import com.usefullc.ud.service.IValidRuleService;


@Service
public class ValidRuleServiceImpl extends AbstractBaseService implements IValidRuleService {
	
	@Autowired
	private IValidRuleDao validRuleDao;

	public ValidRule getValidRule(Long id) {
		return validRuleDao.getValidRule(id);
	}

	public List<ValidRule> getValidRuleList(Map<String, Object> queryMap) {
		return validRuleDao.getValidRuleList(queryMap);
	}
	
	@Override
	public Pagination<ValidRule> getValidRuleListPage(Map<String, Object> queryMap) {
		return validRuleDao.getValidRuleListPage(queryMap);
	}
	

	public void insertValidRule(ValidRule validRule) {
		validRuleDao.insertValidRule(validRule);
	}

	public void updateValidRule(ValidRule validRule) {
		validRuleDao.updateValidRule(validRule);
	}

	public void deleteValidRule(Long id) {
		validRuleDao.deleteValidRule(id);
	}




	
}
