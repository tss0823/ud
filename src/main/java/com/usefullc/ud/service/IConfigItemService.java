/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.ud.domain.ConfigItem;

/**
 * 配置项 Service
 * 
 * @author tangss
 * @2013-8-7 @上午11:13:42
 */
public interface IConfigItemService {

    /**
     * 获得配置项
     * 
     * @param id
     * @return
     */
    ConfigItem getConfigItem(Long id);

    /**
     * 获得配置项列表
     * 
     * @param queryMap
     * @return
     */
    List<ConfigItem> getConfigItemList(Map<String, Object> queryMap);

    /**
     * 获得配置项分页列表
     * 
     * @param queryMap
     * @return
     */
    Pagination<ConfigItem> getConfigItemListPage(Map<String, Object> queryMap);

    /**
     * 新增配置项
     * 
     * @param user
     */
    void insertConfigItem(ConfigItem domain);

    /**
     * 修改配置项
     * 
     * @param user
     */
    void updateConfigItem(ConfigItem domain);

    /**
     * 删除配置项
     * 
     * @param id
     */
    void deleteConfigItem(Long id);

    /**
     * 获得配置项列表
     * 
     * @param appId
     * @return
     */
    List<List<ConfigItem>> getConfigItemListByAppId(Long appId);

    /**
     * 插入配置项
     * 
     * @param appId
     * @param key
     * @param modules
     * @param vals
     */
    void insertConfigItem(Long appId, String key, String[] modules, String[] vals);

    /**
     * 修改配置项
     * 
     * @param appId
     * @param key
     * @param modules
     * @param vals
     */
    void updateConfigItem(Long appId, String key, String[] modules, String[] vals);

    /**
     * 修改所有配置项
     * 
     * @param ids
     * @param values
     */
    void updateAllConfigItem(Long[] ids, String[] values);

    /**
     * 删除配置项
     * 
     * @param key
     */
    void deleteConfigItemByKey(Long appId, String key);

    /**
     * 删除配置项
     * 
     * @param key
     */
    public void deleteConfigItemByAppId(Long appId);

    /**
     * 上传初始化prop
     * 
     * @param appId
     * @param propFile
     * @param module
     */
    void uploadProp(Long appId, MultipartFile propFile, String module);

}
