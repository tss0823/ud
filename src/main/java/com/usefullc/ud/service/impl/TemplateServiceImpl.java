/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.usefullc.platform.common.utils.DateUtils;
import com.usefullc.platform.common.utils.IOUtils;
import com.usefullc.platform.common.utils.StrUtils;
import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.service.AbstractBaseService;
import com.usefullc.ud.dao.ITemplateDao;
import com.usefullc.ud.domain.Attachment;
import com.usefullc.ud.domain.Template;
import com.usefullc.ud.service.IAttachmentService;
import com.usefullc.ud.service.ITemplateService;

@Service
public class TemplateServiceImpl extends AbstractBaseService implements ITemplateService {

    @Autowired
    private ITemplateDao       templateDao;

    @Autowired
    private IAttachmentService attachmentService;

    @Override
    public Template getTemplate(Long id) {
        return templateDao.getTemplate(id);
    }

    @Override
    public List<Template> getTemplateList(Map<String, Object> queryMap) {
        return templateDao.getTemplateList(queryMap);
    }

    @Override
    public Pagination<Template> getTemplateListPage(Map<String, Object> queryMap) {
        return templateDao.getTemplateListPage(queryMap);
    }

    @Transactional
    @Override
    public void insertTemplate(Template template, MultipartFile tplFile) {
        // 文件复制处理
        String time = DateUtils.getCurTime("yyyyMMddHHmmss");
        String postFix = FilenameUtils.getExtension(tplFile.getOriginalFilename());
        String oppositePath = StrUtils.join(template.getType(), template.getName(), time + "." + postFix);
        try {
            // 保存附件
            Attachment attachment = new Attachment();
            attachment.setContent(tplFile.getBytes());
            attachment.setType(null);
            attachment.setName(oppositePath);
            attachment.setDescription(null);
            attachmentService.insertAttachment(attachment);
            template.setAttachmentId(attachment.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 保存
        template.setPath(oppositePath);
        templateDao.insertTemplate(template);
    }

    @Transactional
    @Override
    public void updateTemplate(Template template, MultipartFile tplFile) {
        // 文件复制处理
        if (tplFile != null && StringUtils.isNotEmpty(tplFile.getOriginalFilename())) {
            String time = DateUtils.getCurTime("yyyyMMddHHmmss");
            String postFix = FilenameUtils.getExtension(tplFile.getOriginalFilename());
            String oppositePath = StrUtils.join(template.getType(), template.getName(), time + "." + postFix);
            try {

                // 修改附件
                if (template.getAttachmentId() == null) {
                    // 保存附件
                    Attachment attachment = new Attachment();
                    attachment.setContent(tplFile.getBytes());
                    attachment.setType(null);
                    attachment.setName(oppositePath);
                    attachment.setDescription(null);
                    attachmentService.insertAttachment(attachment);
                    template.setAttachmentId(attachment.getId());
                } else { // 修改
                    Attachment attachment = this.attachmentService.getAttachment(template.getAttachmentId());
                    if (attachment == null) { // 对于老数据丢失情况
                        attachment = new Attachment();
                        attachment.setContent(tplFile.getBytes());
                        attachment.setType(null);
                        attachment.setName(oppositePath);
                        attachment.setDescription(null);
                        attachmentService.insertAttachment(attachment);
                        template.setAttachmentId(attachment.getId());
                    }
                    attachment.setContent(tplFile.getBytes());
                    attachment.setType(null);
                    attachment.setName(oppositePath);
                    attachment.setDescription(null);
                    attachmentService.updateAttachment(attachment);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            // 保存
            template.setPath(oppositePath);
        }
        templateDao.updateTemplate(template);
    }

    @Override
    public void deleteTemplate(Long id) {
        templateDao.deleteTemplate(id);
    }

    @Override
    public void download(Long id, HttpServletResponse response) {
        Template domain = this.getTemplate(id);
        Attachment attachment = this.attachmentService.getAttachment(domain.getAttachmentId());
        IOUtils.download(attachment.getContent(), attachment.getName(), response);
    }

}
