/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.service.AbstractBaseService;
import com.usefullc.ud.common.enums.ModuleEnum;
import com.usefullc.ud.dao.IConfigItemDao;
import com.usefullc.ud.domain.ConfigItem;
import com.usefullc.ud.service.IConfigItemService;

/**
 * 配置项 Service 实现类
 * 
 * @author tangss
 * @2013-8-7 @上午11:09:06
 */
@Service
public class ConfigItemServiceImpl extends AbstractBaseService implements IConfigItemService {

    @Autowired
    private IConfigItemDao configItemDao;

    @Override
    public ConfigItem getConfigItem(Long id) {
        return configItemDao.getConfigItem(id);
    }

    @Override
    public List<ConfigItem> getConfigItemList(Map<String, Object> queryMap) {
        return configItemDao.getConfigItemList(queryMap);
    }

    @Override
    public Pagination<ConfigItem> getConfigItemListPage(Map<String, Object> queryMap) {
        return configItemDao.getConfigItemListPage(queryMap);
    }

    @Override
    public void insertConfigItem(ConfigItem configItem) {
        configItemDao.insertConfigItem(configItem);
    }

    @Override
    public void updateConfigItem(ConfigItem configItem) {
        configItemDao.updateConfigItem(configItem);
    }

    @Override
    public void deleteConfigItem(Long id) {
        configItemDao.deleteConfigItem(id);
    }

    @Override
    public List<List<ConfigItem>> getConfigItemListByAppId(Long appId) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("appId", appId);
        List<ConfigItem> list = configItemDao.getConfigItemList(queryMap);
        List<List<ConfigItem>> resultList = new ArrayList<List<ConfigItem>>();
        List<String> keyList = new ArrayList<String>();
        for (ConfigItem configItem : list) {
            if (configItem.getModule().equals(ModuleEnum.DEV.getCode())) {
                keyList.add(configItem.getKey());
            }
        }
        for (String key : keyList) {
            List<ConfigItem> itemList = new ArrayList<ConfigItem>();
            resultList.add(itemList);
            for (ModuleEnum moduleEnum : ModuleEnum.values()) {
                for (ConfigItem configItem : list) {
                    if (configItem.getModule().equals(moduleEnum.getCode()) && configItem.getKey().equals(key)) {
                        itemList.add(configItem);
                        break;
                    }
                }
            }
        }
        return resultList;
    }

    /**
     * 插入配置项
     * 
     * @param projectId
     * @param key
     * @param modules
     * @param vals
     */
    @Override
    @Transactional
    public void insertConfigItem(Long appId, String key, String[] modules, String[] vals) {
        for (int i = 0; i < modules.length; i++) {
            String module = modules[i];
            String value = vals[i];
            ConfigItem configItem = new ConfigItem();
            configItem.setAppId(appId);
            configItem.setKey(key);
            configItem.setModule(module);
            configItem.setValue(value);
            configItemDao.insertConfigItem(configItem);
        }
    }

    /**
     * 删除配置项
     * 
     * @param key
     */
    @Override
    public void deleteConfigItemByKey(Long appId, String key) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("appId", appId);
        queryMap.put("key", key);
        this.sqlSession.delete("ConfigItemMapper.deleteConfigItemByKey", queryMap);
    }

    /**
     * 删除配置项
     * 
     * @param key
     */
    @Override
    public void deleteConfigItemByAppId(Long appId) {
        this.deleteConfigItemByKey(appId, null);
    }

    /**
     * 修改配置项
     * 
     * @param configItem
     */
    @Override
    public void updateConfigItem(Long appId, String key, String[] modules, String[] vals) {
        // 先删除
        this.deleteConfigItemByKey(appId, key);
        // 后新增
        this.insertConfigItem(appId, key, modules, vals);
    }

    /**
     * 修改所有配置项
     * 
     * @param configItem
     */
    @Override
    public void updateAllConfigItem(Long[] ids, String[] values) {
        List<ConfigItem> list = new ArrayList<ConfigItem>();
        for (int i = 0; i < ids.length; i++) {
            Long id = ids[i];
            String value = values[i];
            ConfigItem domain = new ConfigItem();
            domain.setId(id);
            domain.setValue(value);
            list.add(domain);
        }
        this.sqlSession.update("ConfigItemMapper.updateAllConfigItem", list);
    }

    @Transactional
    @Override
    public void uploadProp(Long appId, MultipartFile propFile, String module) {
        InputStream is = null;
        try {
            is = propFile.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null) {
                int n = line.indexOf("=");
                if (n == -1) {
                    continue;
                }
                String key = line.substring(0, n);
                String value = line.substring(n + 1);
                String modules[] = null;
                String vals[] = null;
                if (StringUtils.isEmpty(module)) {
                    modules = new String[] { ModuleEnum.DEV.getCode(), ModuleEnum.DEBUG.getCode(),
                            ModuleEnum.TEST.getCode(), ModuleEnum.TRUE.getCode() };
                    vals = new String[] { value, value, value, value };
                } else {
                    modules = new String[] { module };
                    vals = new String[] { value };
                }
                this.insertConfigItem(appId, key, modules, vals);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
