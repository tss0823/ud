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
import com.usefullc.ud.dao.IGenFileDao;
import com.usefullc.ud.domain.GenFile;

@Service
public class GenFileDaoImpl extends AbstractBaseDao implements IGenFileDao {
	
	@Override
	public GenFile getGenFile(Long id) {
		 return (GenFile) sqlSession.selectOne("GenFileMapper.getGenFile", id);
	}
	
	@Override
	public List<GenFile> getGenFileList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("GenFileMapper.getGenFileList",queryMap);
	}
	
	public Pagination<GenFile> getGenFileListPage(Map<String,Object> queryMap) {
		Pagination<GenFile> page = getPagination(queryMap, "GenFileMapper.getGenFileListPage", "GenFileMapper.getGenFileListPageCount");
		return page;
	}

	@Override
	public void insertGenFile(GenFile domain) {
		sqlSession.insert("GenFileMapper.insertGenFile", domain);
	}

	@Override
	public void updateGenFile(GenFile domain) {
		sqlSession.update("GenFileMapper.updateGenFile", domain);
	}

	@Override
	public void deleteGenFile(Long id) {
		sqlSession.delete("GenFileMapper.deleteGenFile", id);
	}

}
