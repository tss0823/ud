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

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.ud.domain.GenFile;

/**
 * 生成文件 Service
 * 
 * @author tangss
 * @2014-03-27 16
 */
public interface IGenFileService {

    /**
     * 获得生成文件
     * 
     * @param id
     * @return
     */
    GenFile getGenFile(Long id);

    /**
     * 获得生成文件列表
     * 
     * @param queryMap
     * @return
     */
    List<GenFile> getGenFileList(Map<String, Object> queryMap);

    /**
     * 获得生成文件分页列表
     * 
     * @param queryMap
     * @return
     */
    Pagination<GenFile> getGenFileListPage(Map<String, Object> queryMap);

    /**
     * 新增生成文件
     * 
     * @param genFile
     */
    void insertGenFile(GenFile domain);

    /**
     * 修改生成文件
     * 
     * @param genFile
     */
    void updateGenFile(GenFile domain);

    /**
     * 删除生成文件
     * 
     * @param id
     */
    void deleteGenFile(Long id);

    /**
     * 下载文件
     * 
     * @param id
     * @param response
     */
    void download(Long id, HttpServletResponse response);

}
