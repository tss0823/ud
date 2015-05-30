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
import com.usefullc.platform.web.form.TreeFrm;
import com.usefullc.ud.domain.Application;
import com.usefullc.ud.web.form.ApplicationFrm;

/**
 * 应用 Service
 * 
 * @author tangss
 * @2013-8-7 @上午11:13:42
 */
public interface IApplicationService {

    /**
     * 获得应用
     * 
     * @param id
     * @return
     */
    Application getApplication(Long id);

    /**
     * 获得应用列表
     * 
     * @param queryMap
     * @return
     */
    List<Application> getApplicationList(Map<String, Object> queryMap);

    /**
     * 获得应用分页列表
     * 
     * @param queryMap
     * @return
     */
    Pagination<Application> getApplicationListPage(Map<String, Object> queryMap);

    /**
     * 获得应用form分页列表
     * 
     * @param queryMap
     * @return
     */
    Pagination<ApplicationFrm> getApplicationFrmListPage(Map<String, Object> queryMap);

    /**
     * 新增应用
     * 
     * @param user
     */
    void insertApplication(Application domain);

    /**
     * 修改应用
     * 
     * @param user
     */
    void updateApplication(Application domain);

    /**
     * 删除应用
     * 
     * @param id
     */
    void deleteApplication(Long id);

    /**
     * 获得appFrm
     * 
     * @param id
     * @return
     */
    ApplicationFrm getApplicationFrm(Long id);

    /**
     * 升级应用
     * 
     * @param id
     */
    void upgrade(Long id);

    /**
     * 构建sql
     * 
     * @param id
     */
    String buildSql(Long id);

    /**
     * 构建app工程
     * 
     * @param id
     */
    void buildApp(Long id);

    /**
     * 构建同步app工程
     * 
     * @param id
     * @param entityIdArray
     */
    void buildSyncApp(Long id, Object[] entityIdArray);

    /**
     * 构建app工程
     * 
     * @param id
     * @param entityIdArray
     */
    void buildApp(Long id, Object[] entityIdArray);

    /**
     * 构建校验文件
     * 
     * @param id
     * @param entityIdArray
     */
    void buildValidApp(Long id, Object[] entityIdArray);

    /**
     * 上传sql
     * 
     * @param id
     */
    void uploadSql(Long id, MultipartFile sqlFile);

    /**
     * 获得插入sql
     * 
     * @param sqlFile
     * @return
     */
    String getInsertSql(Long id, MultipartFile sqlFile);

    /**
     * 同步sql
     * 
     * @param entityIds
     */
    String syncSql(Long appId, Object[] entityIds);

    /**
     * 构建sql
     * 
     * @param appId
     * @param entityIds
     * @return
     */
    String buildSql(Long appId, Object[] entityIds);

    /**
     * 构建删除sql
     * 
     * @param appId
     * @param entityIds
     * @return
     */
    String buildDelSql(Long appId, Object[] entityIds);

    /**
     * 同步校验
     * 
     * @param appId
     * @param entityIds
     * @return
     */
    void syncValid(Long appId, Object[] entityIds);

    /**
     * 获得应用树
     * 
     * @return
     */
    List<TreeFrm> getTreeList();
}
