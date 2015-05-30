/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.dao;

import java.util.List;
import java.util.Map;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.ud.domain.Application;
import com.usefullc.ud.web.form.ApplicationFrm;

/**
 * 应用DAO
 * 
 * @author tangss
 * @2013-4-6 @上午10:36:10
 */
public interface IApplicationDao {

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
     * @param domain
     */
    void insertApplication(Application domain);

    /**
     * 修改应用
     * 
     * @param domain
     */
    void updateApplication(Application domain);

    /**
     * 删除应用
     * 
     * @param id
     */
    void deleteApplication(Long id);

}
