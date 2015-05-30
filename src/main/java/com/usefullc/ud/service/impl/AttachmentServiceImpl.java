/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.service.AbstractBaseService;
import com.usefullc.ud.dao.IAttachmentDao;
import com.usefullc.ud.domain.Attachment;
import com.usefullc.ud.service.IAttachmentService;


@Service
public class AttachmentServiceImpl extends AbstractBaseService implements IAttachmentService {
	
	@Autowired
	private IAttachmentDao attachmentDao;

	public Attachment getAttachment(Long id) {
		return attachmentDao.getAttachment(id);
	}

	public List<Attachment> getAttachmentList(Map<String, Object> queryMap) {
		return attachmentDao.getAttachmentList(queryMap);
	}
	
	@Override
	public Pagination<Attachment> getAttachmentListPage(Map<String, Object> queryMap) {
		return attachmentDao.getAttachmentListPage(queryMap);
	}
	

	public void insertAttachment(Attachment attachment) {
		attachmentDao.insertAttachment(attachment);
	}

	public void updateAttachment(Attachment attachment) {
		attachmentDao.updateAttachment(attachment);
	}

	public void deleteAttachment(Long id) {
		attachmentDao.deleteAttachment(id);
	}




	
}
