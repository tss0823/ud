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
import org.springframework.transaction.annotation.Transactional;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.service.AbstractBaseService;
import com.usefullc.ud.dao.IValidItemDao;
import com.usefullc.ud.domain.ValidItem;
import com.usefullc.ud.service.IValidItemPropertyService;
import com.usefullc.ud.service.IValidItemService;

@Service
public class ValidItemServiceImpl extends AbstractBaseService implements IValidItemService {

    @Autowired
    private IValidItemDao             validItemDao;

    @Autowired
    private IValidItemPropertyService validItemPropertyService;

    @Override
    public ValidItem getValidItem(Long id) {
        return validItemDao.getValidItem(id);
    }

    @Override
    public List<ValidItem> getValidItemList(Map<String, Object> queryMap) {
        return validItemDao.getValidItemList(queryMap);
    }

    @Override
    public Pagination<ValidItem> getValidItemListPage(Map<String, Object> queryMap) {
        return validItemDao.getValidItemListPage(queryMap);
    }

    @Transactional
    @Override
    public void insertValidItem(ValidItem validItem, Long[] propIds) {
        validItemDao.insertValidItem(validItem);
        validItemPropertyService.saveOrUpdate(validItem.getId(), propIds);
    }

    @Transactional
    @Override
    public void updateValidItem(ValidItem validItem, Long[] propIds) {
        validItemDao.updateValidItem(validItem);
        validItemPropertyService.saveOrUpdate(validItem.getId(), propIds);
    }

    @Override
    public void deleteValidItem(Long id) {
        validItemDao.deleteValidItem(id);
    }

    @Override
    public List<ValidItem> getValidItemListByAppId(Long appId) {
        return sqlSession.selectList("ValidItemMapper.getValidItemListByAppId", appId);
    }

    @Override
    public List<ValidItem> getValidItemListByIds(Object[] ids) {
        return sqlSession.selectList("ValidItemMapper.getValidItemListByIds", ids);
    }

}
