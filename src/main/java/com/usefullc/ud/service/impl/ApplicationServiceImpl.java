/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.usefullc.platform.common.utils.BeanUtils;
import com.usefullc.platform.common.utils.DateUtils;
import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.service.AbstractBaseService;
import com.usefullc.platform.web.form.TreeFrm;
import com.usefullc.ud.common.ApplicationBuilder;
import com.usefullc.ud.common.bo.ApplicationBo;
import com.usefullc.ud.common.bo.EntityBo;
import com.usefullc.ud.common.bo.PropertyBo;
import com.usefullc.ud.common.bo.TemplateBo;
import com.usefullc.ud.common.bo.ValidItemBo;
import com.usefullc.ud.common.bo.ValidPropertyBo;
import com.usefullc.ud.common.db.DbBuilder;
import com.usefullc.ud.common.db.DbBuilderFactory;
import com.usefullc.ud.common.enums.GenFileBisTypeEnum;
import com.usefullc.ud.dao.IApplicationDao;
import com.usefullc.ud.domain.Application;
import com.usefullc.ud.domain.Attachment;
import com.usefullc.ud.domain.DbConfigure;
import com.usefullc.ud.domain.Entity;
import com.usefullc.ud.domain.GenFile;
import com.usefullc.ud.domain.HisEntity;
import com.usefullc.ud.domain.HisProperty;
import com.usefullc.ud.domain.Property;
import com.usefullc.ud.domain.Template;
import com.usefullc.ud.domain.ValidItem;
import com.usefullc.ud.domain.ValidProperty;
import com.usefullc.ud.domain.ValidRule;
import com.usefullc.ud.service.IApplicationService;
import com.usefullc.ud.service.IAttachmentService;
import com.usefullc.ud.service.IDbConfigureService;
import com.usefullc.ud.service.IEntityService;
import com.usefullc.ud.service.IGenFileService;
import com.usefullc.ud.service.IHisEntityService;
import com.usefullc.ud.service.IHisPropertyService;
import com.usefullc.ud.service.IPropertyService;
import com.usefullc.ud.service.ITemplateService;
import com.usefullc.ud.service.IValidItemPropertyService;
import com.usefullc.ud.service.IValidItemService;
import com.usefullc.ud.service.IValidPropertyService;
import com.usefullc.ud.service.IValidRuleService;
import com.usefullc.ud.web.form.ApplicationFrm;

/**
 * 应用 Service 实现类
 * 
 * @author tangss
 * @2013-8-7 @上午11:09:06
 */
@Service
public class ApplicationServiceImpl extends AbstractBaseService implements IApplicationService {

    @Autowired
    private IApplicationDao           applicationDao;

    @Autowired
    private IEntityService            entityService;

    @Autowired
    private IPropertyService          propertyService;

    @Autowired
    private IHisEntityService         hisEntityService;

    @Autowired
    private IHisPropertyService       hisPropertyService;

    @Autowired
    private IDbConfigureService       dbConfigureService;

    @Autowired
    private IValidPropertyService     validPropertyService;

    @Autowired
    private IValidItemService         validItemService;

    @Autowired
    private IValidItemPropertyService validItemPropertyService;

    @Autowired
    private ITemplateService          templateService;

    @Autowired
    private IGenFileService           genFileService;

    @Autowired
    private IAttachmentService        attachmentService;

    @Autowired
    private IValidRuleService         validRuleService;

    @Override
    public Application getApplication(Long id) {
        return applicationDao.getApplication(id);
    }

    @Override
    public List<Application> getApplicationList(Map<String, Object> queryMap) {
        return applicationDao.getApplicationList(queryMap);
    }

    @Override
    public Pagination<Application> getApplicationListPage(Map<String, Object> queryMap) {
        return applicationDao.getApplicationListPage(queryMap);
    }

    @Override
    public Pagination<ApplicationFrm> getApplicationFrmListPage(Map<String, Object> queryMap) {
        return applicationDao.getApplicationFrmListPage(queryMap);
    }

    @Override
    public void insertApplication(Application application) {
        application.setVer(1);
        applicationDao.insertApplication(application);
    }

    @Override
    public void updateApplication(Application application) {
        applicationDao.updateApplication(application);
    }

    @Transactional
    @Override
    public void deleteApplication(Long id) {
        applicationDao.deleteApplication(id);
        entityService.deleteEntityByAppId(id);

    }

    @Override
    public ApplicationFrm getApplicationFrm(Long id) {
        Application app = applicationDao.getApplication(id);
        ApplicationFrm frm = BeanUtils.beanCopy(app, ApplicationFrm.class);
        // 获得模版
        if (frm.getTemplateId() != null) {
            Template template = this.templateService.getTemplate(frm.getTemplateId());
            frm.setTemplateName(template.getName());
        }
        return frm;

    }

    @Transactional
    @Override
    public void upgrade(Long id) {
        // 当前版本递增
        Application application = getApplication(id);
        Integer ver = application.getVer();
        if (ver == null) {
            ver = 1;
        }
        int newVer = ver + 1;
        application.setVer(newVer);
        applicationDao.updateApplication(application);

        // 复制历史记录
        List<Entity> entityList = this.entityService.getEntityListByAppId(id);
        if (CollectionUtils.isEmpty(entityList)) {
            return;
        }
        for (Entity entity : entityList) {
            Long entityId = entity.getId();
            HisEntity hisEntity = BeanUtils.beanCopy(entity, HisEntity.class);
            hisEntity.setMainId(entityId);
            hisEntity.setVer(ver);
            // 新增历史实体
            hisEntityService.insertHisEntity(hisEntity);

            // 获得属性集合
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.put("entityId", entityId);
            List<Property> propertyList = propertyService.getPropertyList(queryMap);
            if (CollectionUtils.isEmpty(propertyList)) {
                return;
            }
            for (Property prop : propertyList) {
                HisProperty hisProperty = BeanUtils.beanCopy(prop, HisProperty.class);
                hisProperty.setMainId(prop.getId());
                hisProperty.setVer(ver);
                // 新增历史属性
                hisPropertyService.insertHisProperty(hisProperty);
            }
        }
    }

    @Transactional
    @Override
    public String buildSql(Long id) {
        ApplicationBo appBo = getAppBo(id, null);
        List<EntityBo> entityList = appBo.getEntityBoList();
        DbBuilder dbBuilder = DbBuilderFactory.getDbBuilder(appBo.getDbConfigure().getType());
        String sql = dbBuilder.buildAllTableSql(appBo, entityList);
        return sql;
    }

    @Transactional
    @Override
    public void buildApp(Long id) {
        ApplicationBuilder ab = new ApplicationBuilder();
        ApplicationBo appBo = getAppBo(id, null);
        Attachment attachment = ab.buildApp(appBo);
        // 保存attachemnt
        this.attachmentService.insertAttachment(attachment);

        // 新增生成记录
        GenFile genFile = new GenFile();
        genFile.setAppId(appBo.getId());
        genFile.setName(appBo.getEnName() + "_" + DateUtils.getCurTime("yyyyMMDDHHSS"));
        genFile.setType(appBo.getTemplateBo().getType());
        genFile.setPath(attachment.getName());
        genFile.setBisType(GenFileBisTypeEnum.ALL.getValue());
        genFile.setAttachmentId(attachment.getId());
        genFileService.insertGenFile(genFile);

    }

    @Transactional
    @Override
    public void buildSyncApp(Long id, Object[] entityIdArray) {
        ApplicationBuilder ab = new ApplicationBuilder();
        ApplicationBo appBo = getAppBo(id, entityIdArray);
        Attachment attachment = ab.buildAppSingle(appBo);
        // 保存attachemnt
        this.attachmentService.insertAttachment(attachment);

        // 新增生成记录
        GenFile genFile = new GenFile();
        genFile.setAppId(appBo.getId());
        genFile.setName(appBo.getEnName() + "_" + DateUtils.getCurTime("yyyyMMDDHHSS"));
        genFile.setType(appBo.getTemplateBo().getType());
        genFile.setPath(attachment.getName());
        genFile.setBisType(GenFileBisTypeEnum.SINGLE.getValue());
        genFile.setAttachmentId(attachment.getId());
        genFileService.insertGenFile(genFile);
    }

    @Transactional
    @Override
    public void buildApp(Long id, Object[] entityIdArray) {
        ApplicationBuilder ab = new ApplicationBuilder();
        ApplicationBo appBo = getAppBo(id, entityIdArray);
        Attachment attachment = ab.buildApp(appBo);
        // 保存attachemnt
        this.attachmentService.insertAttachment(attachment);

        // 新增生成记录
        GenFile genFile = new GenFile();
        genFile.setAppId(appBo.getId());
        genFile.setName(appBo.getEnName() + "_" + DateUtils.getCurTime("yyyyMMDDHHSS"));
        genFile.setType(appBo.getTemplateBo().getType());
        genFile.setPath(attachment.getName());
        genFile.setBisType(GenFileBisTypeEnum.SINGLE.getValue());
        genFile.setAttachmentId(attachment.getId());
        genFileService.insertGenFile(genFile);
    }

    @Transactional
    @Override
    public void buildValidApp(Long id, Object[] entityIdArray) {
        ApplicationBuilder ab = new ApplicationBuilder();
        ApplicationBo appBo = getForValidAppBo(id, entityIdArray);
        Attachment attachment = ab.buildValidAppSingle(appBo);
        // 保存attachemnt
        this.attachmentService.insertAttachment(attachment);

        // 新增生成记录
        GenFile genFile = new GenFile();
        genFile.setAppId(appBo.getId());
        genFile.setName(appBo.getEnName() + "_" + DateUtils.getCurTime("yyyyMMDDHHSS"));
        genFile.setType(appBo.getTemplateBo().getType());
        genFile.setPath(attachment.getName());
        genFile.setBisType(GenFileBisTypeEnum.VALID.getValue());
        genFile.setAttachmentId(attachment.getId());
        genFileService.insertGenFile(genFile);
    }

    private ApplicationBo getAppBo(Long appId, Object[] entityIdArray) {
        Application application = getApplication(appId);
        ApplicationBo appBo = BeanUtils.beanCopy(application, ApplicationBo.class);

        // 获得数据库配置
        DbConfigure dbConfigure = dbConfigureService.getDbConfigure(appBo.getDbId());
        appBo.setDbConfigure(dbConfigure);

        // 获得模版
        Template template = templateService.getTemplate(appBo.getTemplateId());
        TemplateBo templateBo = BeanUtils.beanCopy(template, TemplateBo.class);

        // 获得模版附件
        Attachment attachment = attachmentService.getAttachment(template.getAttachmentId());
        templateBo.setAttachment(attachment);
        appBo.setTemplateBo(templateBo);

        // 获得实体列表
        List<Entity> entityList = null;
        // 获得前一个版本历史实体记录
        int ver = application.getVer() - 1;
        if (ArrayUtils.isEmpty(entityIdArray)) {
            entityList = this.entityService.getEntityListByAppId(appId);
        } else {
            entityList = this.entityService.getEntityListByIds(entityIdArray);
            List<HisEntity> preHisEntityList = this.hisEntityService.getHisEntityByVer(appId, ver);
            appBo.setPreHisEntityList(preHisEntityList);
        }
        if (CollectionUtils.isEmpty(entityList)) {
            return appBo;
        }
        for (Entity entity : entityList) {
            EntityBo entityBo = BeanUtils.beanCopy(entity, EntityBo.class);
            appBo.addEntityBo(entityBo);
            // 获得属性列表
            List<Property> propList = this.propertyService.getPropertyListByEntityId(entityBo.getId());
            if (CollectionUtils.isEmpty(propList)) {
                continue;
            }
            for (Property property : propList) {
                PropertyBo propertyBo = BeanUtils.beanCopy(property, PropertyBo.class);
                // 构建验证代码

                entityBo.addProp(propertyBo);
            }

            // 获得历史属性列表
            List<HisProperty> preHisPropList = this.hisPropertyService.getHisPropertyByVer(entityBo.getId(), ver);
            entityBo.setPreHisPropList(preHisPropList);
        }
        return appBo;
    }

    private ApplicationBo getForValidAppBo(Long appId, Object[] validItemIdArray) {
        Application application = getApplication(appId);
        ApplicationBo appBo = BeanUtils.beanCopy(application, ApplicationBo.class);

        // 获得模版
        Template template = templateService.getTemplate(appBo.getTemplateId());
        TemplateBo templateBo = BeanUtils.beanCopy(template, TemplateBo.class);

        // 获得模版附件
        Attachment attachment = attachmentService.getAttachment(template.getAttachmentId());
        templateBo.setAttachment(attachment);
        appBo.setTemplateBo(templateBo);

        // 获得校验规则列表
        List<ValidRule> validRuleList = validRuleService.getValidRuleList(null);
        appBo.setValidRuleList(validRuleList);

        // 获得校验项列表
        List<ValidItem> validItemList = null;
        if (ArrayUtils.isEmpty(validItemIdArray)) {
            validItemList = this.validItemService.getValidItemListByAppId(appId);
        } else {
            validItemList = this.validItemService.getValidItemListByIds(validItemIdArray);
        }
        if (CollectionUtils.isEmpty(validItemList)) {
            return appBo;
        }
        for (ValidItem entity : validItemList) {
            ValidItemBo itemBo = BeanUtils.beanCopy(entity, ValidItemBo.class);
            appBo.addValidItemBo(itemBo);

            // 获得属性列表
            List<ValidProperty> propList = this.validPropertyService.getValidPropertyListByValidItemId(entity.getId());

            if (CollectionUtils.isEmpty(propList)) {
                continue;
            }
            for (ValidProperty property : propList) {
                ValidPropertyBo validPropertyBo = BeanUtils.beanCopy(property, ValidPropertyBo.class);
                itemBo.addProp(validPropertyBo);
            }

        }
        return appBo;
    }

    @Transactional
    @Override
    public void uploadSql(Long id, MultipartFile sqlFile) {
        InputStream is = null;
        try {
            is = sqlFile.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Application app = getApplication(id);
        DbConfigure dbConfigure = dbConfigureService.getDbConfigure(app.getDbId());
        DbBuilder dbBuilder = DbBuilderFactory.getDbBuilder(dbConfigure.getType());

        List<String> sqlList = dbBuilder.getCreateTableSql(is);
        if (CollectionUtils.isEmpty(sqlList)) {
            throw new RuntimeException("get sql file content faild! please check the file!");
        }
        for (String createSql : sqlList) {
            // 处理table 业务
            EntityBo bo = dbBuilder.parseSqlToBo(app.getShortName(), createSql); // 解析获得实体
            bo.setAppId(id);
            this.saveEntity(bo); // to save entity
        }
    }

    private void saveEntity(EntityBo entityBo) {
        Entity entity = BeanUtils.beanCopy(entityBo, Entity.class);
        this.entityService.insertEntity(entity);
        List<PropertyBo> propBoList = entityBo.getPropList();
        for (PropertyBo propBo : propBoList) {
            Property property = BeanUtils.beanCopy(propBo, Property.class);
            property.setEntityId(entity.getId());
            this.propertyService.insertProperty(property);
        }
    }

    @Override
    public String getInsertSql(Long id, MultipartFile sqlFile) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream input = sqlFile.getInputStream();
            List<String> list = IOUtils.readLines(input, "utf-8");
            if (CollectionUtils.isEmpty(list)) {
                return null;
            }
            for (String line : list) {
                if (!line.startsWith("INSERT")) {
                    continue;
                }
                // 获得括号中的数据

                int start = line.indexOf("(") + 1;
                int end = line.indexOf(")");
                String str = line.substring(start, end);
                String startStr = line.substring(0, start);
                startStr = startStr.replaceFirst("`", "auth_");
                startStr = startStr.replace("`", "");
                sb.append(startStr);

                StringTokenizer st = new StringTokenizer(str, ",");
                while (st.hasMoreElements()) {
                    String strToken = st.nextToken();
                    if (StringUtils.isEmpty(strToken)) {
                        sb.append(strToken);
                        sb.append(",");
                        continue;
                    }
                    if (strToken.split("-").length == 3) { // 时间格式
                        strToken = "to_date(" + strToken + ",'YYYY-MM-DD HH24:MI:SS')";
                    }
                    sb.append(strToken);
                    sb.append(",");
                }
                sb.delete(sb.length() - 1, sb.length());
                sb.append(line.substring(end));
                sb.append("\n");

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String sql = sb.toString();
        return sql;
    }

    /*
     * (non-Javadoc)
     * @see com.usefullc.ud.service.IApplicationService#syncSql(java.lang.Object[])
     */
    @Override
    public String syncSql(Long appId, Object[] entityIds) {
        ApplicationBo appBo = getAppBo(appId, entityIds);
        DbBuilder dbBuilder = DbBuilderFactory.getDbBuilder(appBo.getDbConfigure().getType());
        // 获得前一个版本历史实体记录
        List<HisEntity> preHisEntityList = appBo.getPreHisEntityList();

        List<EntityBo> entityList = appBo.getEntityBoList();
        StringBuilder sb = new StringBuilder();
        for (EntityBo entityBo : entityList) {
            boolean hasHis = false;
            // 判断看是否存在历史
            if (CollectionUtils.isNotEmpty(preHisEntityList)) {
                for (HisEntity hisEntity : preHisEntityList) {
                    if (hisEntity.getMainId().equals(entityBo.getId())) { // 存在历史
                        hasHis = true;
                        break;
                    }
                }
            }
            if (hasHis) { // 修改sql
                String str = dbBuilder.buildTableAlertSql(appBo.getShortName(), entityBo);
                sb.append(str);

            } else { // 创建sql
                String str = dbBuilder.buildTableSql(appBo, entityBo, entityBo.getPropList());
                sb.append(str);
            }
        }
        return sb.toString();

    }

    @Override
    public String buildSql(Long appId, Object[] entityIds) {
        ApplicationBo appBo = getAppBo(appId, entityIds);
        List<EntityBo> entityList = appBo.getEntityBoList();
        DbBuilder dbBuilder = DbBuilderFactory.getDbBuilder(appBo.getDbConfigure().getType());
        String sql = dbBuilder.buildAllTableSql(appBo, entityList);
        return sql;

    }

    @Override
    public String buildDelSql(Long appId, Object[] entityIds) {
        ApplicationBo appBo = getAppBo(appId, entityIds);
        List<EntityBo> entityList = appBo.getEntityBoList();
        DbBuilder dbBuilder = DbBuilderFactory.getDbBuilder(appBo.getDbConfigure().getType());
        String sql = dbBuilder.buildDelTableSql(appBo, entityList);
        return sql;

    }

    /*
     * (non-Javadoc)
     * @see com.usefullc.ud.service.IApplicationService#syncSql(java.lang.Object[])
     */
    @Transactional
    @Override
    public void syncValid(Long appId, Object[] entityIds) {
        ApplicationBo appBo = getAppBo(appId, entityIds);

        Map<String, Object> vpMap = new HashMap<String, Object>();
        List<EntityBo> entityList = appBo.getEntityBoList();
        for (EntityBo entityBo : entityList) {
            List<PropertyBo> propList = entityBo.getPropList();
            vpMap.put("entityId", entityBo.getId());
            List<ValidProperty> vpList = this.validPropertyService.getValidPropertyList(vpMap);
            for (PropertyBo propBo : propList) {
                boolean exsist = false;
                if (CollectionUtils.isNotEmpty(vpList)) {
                    for (ValidProperty vp : vpList) {
                        if (propBo.getId().equals(vp.getPropertyId())) {
                            // 存在就修改
                            BeanUtils.beanCopy(propBo, vp);
                            vp.setMsgName(propBo.getCnName());
                            this.validPropertyService.updateValidProperty(vp);
                            exsist = true;
                            break;
                        }
                    }
                }
                if (!exsist) {
                    ValidProperty vp = new ValidProperty();
                    BeanUtils.beanCopy(propBo, vp);
                    vp.setPropertyId(propBo.getId());
                    vp.setMsgName(propBo.getCnName());
                    this.validPropertyService.insertValidProperty(vp);
                }
            }
        }
    }

    @Override
    public List<TreeFrm> getTreeList() {
        List<Application> list = applicationDao.getApplicationList(null);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<TreeFrm> treeList = new ArrayList<TreeFrm>();
        for (Application app : list) {
            TreeFrm treeFrm = new TreeFrm();
            treeFrm.setId(app.getId());
            treeFrm.setParentId(app.getProjectId());
            treeFrm.setName(app.getCnName());
            treeList.add(treeFrm);
        }
        return treeList;
    }

}
