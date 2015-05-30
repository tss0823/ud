/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.ud.domain.Template;

/**
 * 模版 Service
 * 
 * @author tangss
 * @2014-03-27 16
 */
public interface ITemplateService {

    /**
     * 获得模版
     * 
     * @param id
     * @return
     */
    Template getTemplate(Long id);

    /**
     * 获得模版列表
     * 
     * @param queryMap
     * @return
     */
    List<Template> getTemplateList(Map<String, Object> queryMap);

    /**
     * 获得模版分页列表
     * 
     * @param queryMap
     * @return
     */
    Pagination<Template> getTemplateListPage(Map<String, Object> queryMap);

    /**
     * 新增模版
     * 
     * @param template
     */
    void insertTemplate(Template domain, MultipartFile tplFile);

    /**
     * 修改模版
     * 
     * @param template
     */
    void updateTemplate(Template domain, MultipartFile tplFile);

    /**
     * 删除模版
     * 
     * @param id
     */
    void deleteTemplate(Long id);

    /**
     * 下载模版
     * 
     * @param id
     * @param response
     */
    void download(Long id, HttpServletResponse response);

}
