/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service;

import java.util.List;
import java.util.Map;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.web.form.TreeFrm;
import com.usefullc.ud.domain.ValidProperty;

/**
 * 校验属性 Service
 * 
 * @author tangss
 * @2014-03-13 15
 */
public interface IValidPropertyService {

    /**
     * 获得校验属性
     * 
     * @param id
     * @return
     */
    ValidProperty getValidProperty(Long id);

    /**
     * 获得校验属性列表
     * 
     * @param queryMap
     * @return
     */
    List<ValidProperty> getValidPropertyList(Map<String, Object> queryMap);

    /**
     * 获得校验属性分页列表
     * 
     * @param queryMap
     * @return
     */
    Pagination<ValidProperty> getValidPropertyListPage(Map<String, Object> queryMap);

    /**
     * 新增校验属性
     * 
     * @param validProperty
     */
    void insertValidProperty(ValidProperty domain);

    /**
     * 修改校验属性
     * 
     * @param validProperty
     */
    void updateValidProperty(ValidProperty domain);

    /**
     * 删除校验属性
     * 
     * @param id
     */
    void deleteValidProperty(Long id);

    /**
     * 获得校验属性树
     * 
     * @return
     */
    List<TreeFrm> getTreeList();

    /**
     * 保存或修改校验属性
     * 
     * @param entityId
     * @param msgName
     * @param ruleId
     * @param ruleParam
     * @param msg
     */
    void saveOrupdateProperty(Long[] ids, String[] msgName, Long[] ruleId, String[] ruleParam, String[] msg);

    /**
     * 根据校验项获得校验属性集合
     * 
     * @param validItemId
     * @return
     */
    List<ValidProperty> getValidPropertyListByValidItemId(Long validItemId);

}
