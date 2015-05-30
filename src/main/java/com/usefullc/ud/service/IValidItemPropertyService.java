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
import com.usefullc.ud.domain.ValidItemProperty;

/**
 * 校验项属性 Service
 * 
 * @author tangss
 * @2014-03-13 15
 */
public interface IValidItemPropertyService {

    /**
     * 获得校验项属性
     * 
     * @param id
     * @return
     */
    ValidItemProperty getValidItemProperty(Long id);

    /**
     * 获得校验项属性列表
     * 
     * @param queryMap
     * @return
     */
    List<ValidItemProperty> getValidItemPropertyList(Map<String, Object> queryMap);

    /**
     * 获得校验项属性分页列表
     * 
     * @param queryMap
     * @return
     */
    Pagination<ValidItemProperty> getValidItemPropertyListPage(Map<String, Object> queryMap);

    /**
     * 新增校验项属性
     * 
     * @param validItemProperty
     */
    void insertValidItemProperty(ValidItemProperty domain);

    /**
     * 修改校验项属性
     * 
     * @param validItemProperty
     */
    void updateValidItemProperty(ValidItemProperty domain);

    /**
     * 删除校验项属性
     * 
     * @param id
     */
    void deleteValidItemProperty(Long id);

    /**
     * 根据校验项id删除校验项属性
     * 
     * @param itemId
     */
    void deleteValidItemPropertyByItemId(Long itemId);

    /**
     * 保存或删除校验项属性
     * 
     * @param itemId
     * @param propIds
     */
    void saveOrUpdate(Long itemId, Long[] propIds);

    /**
     * 根据校验规则项获得列表
     * 
     * @param itemId
     * @return
     */
    List<ValidItemProperty> getValidItemPropertyListByItemId(Long itemId);

    /**
     * 获得校验项属性树
     * 
     * @return
     */
    List<TreeFrm> getTreeList(Long validItemId);

}
