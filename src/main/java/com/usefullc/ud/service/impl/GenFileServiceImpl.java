/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usefullc.platform.common.utils.IOUtils;
import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.service.AbstractBaseService;
import com.usefullc.ud.dao.IGenFileDao;
import com.usefullc.ud.domain.Attachment;
import com.usefullc.ud.domain.GenFile;
import com.usefullc.ud.service.IAttachmentService;
import com.usefullc.ud.service.IGenFileService;

@Service
public class GenFileServiceImpl extends AbstractBaseService implements IGenFileService {

    @Autowired
    private IGenFileDao        genFileDao;

    @Autowired
    private IAttachmentService attachmentService;

    @Override
    public GenFile getGenFile(Long id) {
        return genFileDao.getGenFile(id);
    }

    @Override
    public List<GenFile> getGenFileList(Map<String, Object> queryMap) {
        return genFileDao.getGenFileList(queryMap);
    }

    @Override
    public Pagination<GenFile> getGenFileListPage(Map<String, Object> queryMap) {
        return genFileDao.getGenFileListPage(queryMap);
    }

    @Override
    public void insertGenFile(GenFile genFile) {
        genFileDao.insertGenFile(genFile);
    }

    @Override
    public void updateGenFile(GenFile genFile) {
        genFileDao.updateGenFile(genFile);
    }

    @Override
    public void deleteGenFile(Long id) {
        genFileDao.deleteGenFile(id);
    }

    public void download(Long id, HttpServletResponse response) {
        GenFile domain = this.getGenFile(id);
        Attachment attachment = this.attachmentService.getAttachment(domain.getAttachmentId());
        IOUtils.download(attachment.getContent(), attachment.getName(), response);
    }

}
