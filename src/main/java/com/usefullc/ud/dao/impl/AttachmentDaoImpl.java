/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.dao.AbstractBaseDao;
import com.usefullc.ud.dao.IAttachmentDao;
import com.usefullc.ud.domain.Attachment;

@Service
public class AttachmentDaoImpl extends AbstractBaseDao implements IAttachmentDao {
	
	@Override
	public Attachment getAttachment(Long id) {
		 return (Attachment) sqlSession.selectOne("AttachmentMapper.getAttachment", id);
	}
	
	@Override
	public List<Attachment> getAttachmentList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("AttachmentMapper.getAttachmentList",queryMap);
	}
	
	public Pagination<Attachment> getAttachmentListPage(Map<String,Object> queryMap) {
		Pagination<Attachment> page = getPagination(queryMap, "AttachmentMapper.getAttachmentListPage", "AttachmentMapper.getAttachmentListPageCount");
		return page;
	}

	@Override
	public void insertAttachment(Attachment domain) {
		sqlSession.insert("AttachmentMapper.insertAttachment", domain);
	}

	@Override
	public void updateAttachment(Attachment domain) {
		sqlSession.update("AttachmentMapper.updateAttachment", domain);
	}

	@Override
	public void deleteAttachment(Long id) {
		sqlSession.delete("AttachmentMapper.deleteAttachment", id);
	}

}
