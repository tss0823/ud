/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.service.AbstractBaseService;
import com.usefullc.platform.web.form.TreeFrm;
import com.usefullc.ud.dao.IValidItemPropertyDao;
import com.usefullc.ud.domain.ValidItemProperty;
import com.usefullc.ud.service.IValidItemPropertyService;

@Service
public class ValidItemPropertyServiceImpl extends AbstractBaseService implements IValidItemPropertyService {

    @Autowired
    private IValidItemPropertyDao validItemPropertyDao;

    @Override
    public ValidItemProperty getValidItemProperty(Long id) {
        return validItemPropertyDao.getValidItemProperty(id);
    }

    @Override
    public List<ValidItemProperty> getValidItemPropertyList(Map<String, Object> queryMap) {
        return validItemPropertyDao.getValidItemPropertyList(queryMap);
    }

    @Override
    public Pagination<ValidItemProperty> getValidItemPropertyListPage(Map<String, Object> queryMap) {
        return validItemPropertyDao.getValidItemPropertyListPage(queryMap);
    }

    @Override
    public void insertValidItemProperty(ValidItemProperty validItemProperty) {
        validItemPropertyDao.insertValidItemProperty(validItemProperty);
    }

    @Override
    public void updateValidItemProperty(ValidItemProperty validItemProperty) {
        validItemPropertyDao.updateValidItemProperty(validItemProperty);
    }

    @Override
    public void deleteValidItemProperty(Long id) {
        validItemPropertyDao.deleteValidItemProperty(id);
    }

    @Override
    public void deleteValidItemPropertyByItemId(Long itemId) {
        this.sqlSession.delete("ValidItemPropertyMapper.deleteValidItemPropertyByItemId", itemId);
    }

    @Override
    public void saveOrUpdate(Long itemId, Long[] propIds) {
        // 先删除
        this.deleteValidItemPropertyByItemId(itemId);

        // 再保存
        if (propIds != null && propIds.length > 0) {
            for (Long propId : propIds) {
                ValidItemProperty vip = new ValidItemProperty();
                vip.setValidItemId(itemId);
                vip.setValidPropertyId(propId);
                this.insertValidItemProperty(vip);
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see com.usefullc.ud.service.IValidItemPropertyService#getValidItemPropertyListByItemId(java.lang.Long)
     */
    @Override
    public List<ValidItemProperty> getValidItemPropertyListByItemId(Long itemId) {
        return this.sqlSession.selectList("ValidItemPropertyMapper.getValidItemPropertyListByItemId", itemId);
    }

    @Override
    public List<TreeFrm> getTreeList(Long validItemId) {
        List<ValidItemProperty> propList = this.getValidItemPropertyListByItemId(validItemId);
        if (CollectionUtils.isEmpty(propList)) {
            return null;
        }

        List<TreeFrm> treeList = new ArrayList<TreeFrm>();
        for (ValidItemProperty entity : propList) {
            TreeFrm treeFrm = new TreeFrm();
            treeFrm.setId(entity.getValidPropertyId());
            treeFrm.setParentId(entity.getValidItemId());
            treeList.add(treeFrm);
        }
        return treeList;
    }

}
