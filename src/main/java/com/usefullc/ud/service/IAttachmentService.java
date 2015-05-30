/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service;
import java.util.List;
import java.util.Map;
import com.usefullc.ud.domain.Attachment;
import com.usefullc.platform.common.web.Pagination;

/**
 *  附件 Service
 * @author tangss
 *
 * @2014-04-01 17
 */
public interface IAttachmentService {
	
	/**
	 * 获得附件
	 * @param id
	 * @return
	 */
	Attachment getAttachment(Long id);
	
	/**
	 * 获得附件列表
	 * @param queryMap
	 * @return
	 */
	List<Attachment> getAttachmentList(Map<String,Object> queryMap);
	
	/**
	 * 获得附件分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<Attachment> getAttachmentListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增附件
	 * @param attachment
	 */
	void insertAttachment(Attachment domain);
	
	/**
	 * 修改附件
	 * @param attachment
	 */
	void updateAttachment(Attachment domain);
	
	/**
	 * 删除附件
	 * @param id
	 */
	void deleteAttachment(Long id);
    

}

