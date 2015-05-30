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
import com.usefullc.ud.domain.ValidItem;

/**
 * 校验项 Service
 * 
 * @author tangss
 * @2014-03-13 15
 */
public interface IValidItemService {

    /**
     * 获得校验项
     * 
     * @param id
     * @return
     */
    ValidItem getValidItem(Long id);

    /**
     * 获得校验项列表
     * 
     * @param queryMap
     * @return
     */
    List<ValidItem> getValidItemList(Map<String, Object> queryMap);

    /**
     * 获得校验项分页列表
     * 
     * @param queryMap
     * @return
     */
    Pagination<ValidItem> getValidItemListPage(Map<String, Object> queryMap);

    /**
     * 新增校验项
     * 
     * @param validItem
     */
    void insertValidItem(ValidItem validItem, Long[] propIds);

    /**
     * 修改校验项
     * 
     * @param validItem
     */
    void updateValidItem(ValidItem validItem, Long[] propIds);

    /**
     * 删除校验项
     * 
     * @param id
     */
    void deleteValidItem(Long id);

    /**
     * 根据appID获得校验项列表
     * 
     * @param appId
     * @return
     */
    List<ValidItem> getValidItemListByAppId(Long appId);

    /**
     * 根据id集合获得校验项列表
     * 
     * @param ids
     * @return
     */
    List<ValidItem> getValidItemListByIds(Object[] ids);

}
