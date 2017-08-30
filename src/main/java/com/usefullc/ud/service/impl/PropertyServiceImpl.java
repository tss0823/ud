/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service.impl;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.service.AbstractBaseService;
import com.usefullc.ud.dao.IPropertyDao;
import com.usefullc.ud.domain.Application;
import com.usefullc.ud.domain.Entity;
import com.usefullc.ud.domain.Property;
import com.usefullc.ud.service.IApplicationService;
import com.usefullc.ud.service.IEntityService;
import com.usefullc.ud.service.IHisPropertyService;
import com.usefullc.ud.service.IPropertyService;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 属性 Service 实现类
 * 
 * @author tangss
 * @2013-8-7 @上午11:09:06
 */
@Service
public class PropertyServiceImpl extends AbstractBaseService implements IPropertyService {

    @Autowired
    private IPropertyDao        propertyDao;

    @Autowired
    private IHisPropertyService hisPropertyService;

    @Autowired
    private IEntityService      entityService;

    @Autowired
    private IApplicationService applicationService;

    @Override
    public Property getProperty(Long id) {
        return propertyDao.getProperty(id);
    }

    @Override
    public List<Property> getPropertyList(Map<String, Object> queryMap) {
        return propertyDao.getPropertyList(queryMap);
    }

    @Override
    public Pagination<Property> getPropertyListPage(Map<String, Object> queryMap) {
        return propertyDao.getPropertyListPage(queryMap);
    }

    @Override
    public void deleteProperty(Long id) {
        // 删除
        propertyDao.deleteProperty(id);
    }

    @Override
    public void batchDeleteProperty(Object[] idArr) {
        // 删除
        this.sqlSession.delete("PropertyMapper.batchDeleteProperty", idArr);
    }

    @Override
    public void insertProperty(Property domain) {
        Entity entity = entityService.getEntity(domain.getEntityId());
        Application app = applicationService.getApplication(entity.getAppId());
        int ver = app.getVer();
        entity.setVer(ver);
        entityService.updateEntity(entity);
        domain.setVer(ver);
        propertyDao.insertProperty(domain);
    }

    @Override
    public void deletePropertyByEntityId(Long entityId) {
        this.sqlSession.delete("PropertyMapper.deletePropertyByEntityId", entityId);
    }

    @Override
    public void deletePropertyByAppId(Long appId) {
        this.sqlSession.delete("PropertyMapper.deletePropertyByAppId", appId);
    }

    @Override
    public void deleteEntityListByEntityIds(Object[] arrys) {
        this.sqlSession.delete("PropertyMapper.deleteEntityListByEntityIds", arrys);
    }

    @Override
    public void insertProperty(Long entityId, String[] enName, String[] cnName, String[] dataType, String[] length,
                               String[] defaultValue, String[] isNull, String[] primaryKey) {
        Entity entity = entityService.getEntity(entityId);
        Application app = applicationService.getApplication(entity.getAppId());
        int ver = app.getVer();
        entity.setVer(ver);
        entityService.updateEntity(entity);
        for (int i = 0; i < enName.length; i++) {
            if (StringUtils.isEmpty(enName[i])) {
                continue;
            }
            Property property = new Property();
            property.setEntityId(entityId);
            property.setEnName(enName[i]);
            property.setCnName(cnName[i]);
            property.setDataType(dataType[i]);
            property.setLength(length[i]);
            property.setDefaultValue(defaultValue[i]);
            property.setIsNull(BooleanUtils.toBooleanObject(isNull[i], "1", "0", ""));
            property.setPrimaryKey(BooleanUtils.toBooleanObject(primaryKey[i], "1", "0", ""));
            property.setVer(ver);
            propertyDao.insertProperty(property);
        }

    }

    @Transactional
    @Override
    public void updateProperty(Long entityId, String[] enName, String[] cnName, String[] dataType, String[] length,
                               String[] defaultValue, String[] isNull, String[] primaryKey, String[] remark,
                               String[] sourceType, String[] source, String[] checkGroup) {
        // 删除
        deletePropertyByEntityId(entityId);

        Entity entity = entityService.getEntity(entityId);

        int ver = 1;
        Application app = applicationService.getApplication(entity.getAppId());
        if (app != null) {
            ver = app.getVer();
        }

        entity.setVer(ver);
        entityService.updateEntity(entity);

        // 新增
        for (int i = 0; i < enName.length; i++) {
            if (StringUtils.isEmpty(enName[i])) {
                continue;
            }
            Property property = new Property();
            property.setEntityId(entityId);
            property.setEnName(enName[i]);
            property.setCnName(cnName[i]);
            property.setDataType(dataType[i]);
            property.setLength(length[i]);
            property.setDefaultValue(defaultValue[i]);
            if(StringUtils.isEmpty(isNull[i])){
                property.setIsNull(false);
            }else{
                if(isNull[i].equalsIgnoreCase("true") || isNull[i].equalsIgnoreCase("false")){
                    property.setIsNull(BooleanUtils.toBoolean(isNull[i]));
                }else{
                    property.setIsNull(BooleanUtils.toBooleanObject(isNull[i], "1", "0", ""));
                }
            }
            if(StringUtils.isEmpty(primaryKey[i])){
                property.setPrimaryKey(false);
            }else{
                if(primaryKey[i].equalsIgnoreCase("true") || primaryKey[i].equalsIgnoreCase("false")){
                    property.setPrimaryKey(BooleanUtils.toBoolean(primaryKey[i]));
                }else{
                    property.setPrimaryKey(BooleanUtils.toBooleanObject(primaryKey[i], "1", "0", ""));
                }
            }
            if(remark != null){
                property.setRemark(remark[i]);
            }
            if(sourceType != null){
                property.setSourceType(Integer.valueOf(sourceType[i]));
            }
            if(source != null){
                property.setSource(source[i]);
            }
            if(checkGroup != null){
                property.setCheckGroup(checkGroup[i]);
            }
            property.setVer(ver);
            propertyDao.insertProperty(property);
        }

    }

    @Override
    public List<Property> getPropertyListByEntityId(Long entityId) {
        return this.sqlSession.selectList("PropertyMapper.getPropertyListByEntityId", entityId);
    }

    @Override
    public void insertPropertyBatch(List<Property> propList) {
        this.sqlSession.insert("PropertyMapper.insertPropertyBatch", propList);
    }

    public static void main(String[] args) {
        System.out.println(BooleanUtils.toBooleanObject("true"));
    }

}
