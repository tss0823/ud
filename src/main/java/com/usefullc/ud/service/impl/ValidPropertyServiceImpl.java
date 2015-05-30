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
import org.springframework.transaction.annotation.Transactional;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.service.AbstractBaseService;
import com.usefullc.platform.web.form.TreeFrm;
import com.usefullc.ud.dao.IValidPropertyDao;
import com.usefullc.ud.domain.ValidProperty;
import com.usefullc.ud.service.IValidPropertyService;

@Service
public class ValidPropertyServiceImpl extends AbstractBaseService implements IValidPropertyService {

    @Autowired
    private IValidPropertyDao validPropertyDao;

    @Override
    public ValidProperty getValidProperty(Long id) {
        return validPropertyDao.getValidProperty(id);
    }

    @Override
    public List<ValidProperty> getValidPropertyList(Map<String, Object> queryMap) {
        return validPropertyDao.getValidPropertyList(queryMap);
    }

    @Override
    public Pagination<ValidProperty> getValidPropertyListPage(Map<String, Object> queryMap) {
        return validPropertyDao.getValidPropertyListPage(queryMap);
    }

    @Override
    public void insertValidProperty(ValidProperty validProperty) {
        validPropertyDao.insertValidProperty(validProperty);
    }

    @Override
    public void updateValidProperty(ValidProperty validProperty) {
        validPropertyDao.updateValidProperty(validProperty);
    }

    @Override
    public void deleteValidProperty(Long id) {
        validPropertyDao.deleteValidProperty(id);
    }

    @Override
    public List<TreeFrm> getTreeList() {

        List<ValidProperty> propList = this.getValidPropertyList(null);
        if (CollectionUtils.isEmpty(propList)) {
            return null;
        }

        List<TreeFrm> treeList = new ArrayList<TreeFrm>();
        for (ValidProperty entity : propList) {
            TreeFrm treeFrm = new TreeFrm();
            treeFrm.setId(entity.getId());
            treeFrm.setParentId(entity.getEntityId());
            treeFrm.setName(entity.getCnName());
            treeList.add(treeFrm);
        }
        return treeList;
    }

    @Transactional
    @Override
    public void saveOrupdateProperty(Long[] ids, String[] msgName, Long[] ruleId, String[] ruleParam, String[] msg) {
        // 删除
        // this.sqlSession.delete("ValidPropertyMapper.deleteValidPropertyByEntityId", entityId);

        // 修改
        for (int i = 0; i < ids.length; i++) {
            Long id = ids[i];
            if (id == null) {
                continue;
            }
            ValidProperty oldProperty = this.getValidProperty(id);
            oldProperty.setMsgName(msgName[i]);
            oldProperty.setRuleId(ruleId[i]);
            oldProperty.setRuleParam(ruleParam[i]);
            // oldProperty.setMsg(source[i]);
            this.updateValidProperty(oldProperty);
        }

    }

    @Override
    public List<ValidProperty> getValidPropertyListByValidItemId(Long validItemId) {
        return sqlSession.selectList("ValidPropertyMapper.getValidPropertyListByValidItemId", validItemId);
    }

}
