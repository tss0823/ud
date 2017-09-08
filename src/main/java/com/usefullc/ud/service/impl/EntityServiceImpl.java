/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service.impl;

import com.usefullc.platform.common.utils.BeanUtils;
import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.service.AbstractBaseService;
import com.usefullc.platform.web.form.TreeFrm;
import com.usefullc.ud.dao.IEntityDao;
import com.usefullc.ud.domain.Application;
import com.usefullc.ud.domain.Entity;
import com.usefullc.ud.domain.Property;
import com.usefullc.ud.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实体 Service 实现类
 * 
 * @author tangss
 * @2013-8-7 @上午11:09:06
 */
@Service
public class EntityServiceImpl extends AbstractBaseService implements IEntityService {

    @Autowired
    private IEntityDao          entityDao;

    @Autowired
    private IPropertyService    propertyService;

    @Autowired
    private IHisEntityService   hisEntityService;

    @Autowired
    private IApplicationService applicationService;

    @Autowired
    private IProjectService     projectService;

    @Override
    public Entity getEntity(Long id) {
        return entityDao.getEntity(id);
    }

    @Override
    public Entity getEntityByEnName(String enName) {
        return entityDao.getEntityByEnName(enName);
    }

    @Override
    public List<Entity> getEntityList(Map<String, Object> queryMap) {
        return entityDao.getEntityList(queryMap);
    }

    @Override
    public Pagination<Entity> getEntityListPage(Map<String, Object> queryMap) {
        return entityDao.getEntityListPage(queryMap);
    }

    @Override
    public void insertEntity(Entity entity) {
        Application app = applicationService.getApplication(entity.getAppId());
        entity.setVer(app.getVer());
        entityDao.insertEntity(entity);
    }

    @Override
    public void updateEntity(Entity entity) {
        // 插入历史记录
        Entity oldEntity = this.getEntity(entity.getId());
        // 修改新记录
        BeanUtils.beanCopy(entity, oldEntity);

        Application app = applicationService.getApplication(oldEntity.getAppId());
        oldEntity.setVer(app.getVer());
        entityDao.updateEntity(oldEntity);
    }

    @Override
    public void deleteEntity(Long id) {
        Entity entity = entityDao.getEntity(id);
        entity.setEnName(entity.getEnName()+"_"+entity.getId());
        entityDao.updateEntity(entity);
        entityDao.deleteEntity(id);
        this.propertyService.deletePropertyByEntityId(id);

    }

    /*
     * (non-Javadoc)
     * @see com.usefullc.ud.service.IEntityService#copy(java.lang.Long)
     */
    @Transactional
    @Override
    public void copy(Long id) {
        Entity domain = entityDao.getEntity(id);
        domain.setEnName(domain.getEnName() + "_copy");
        domain.setCnName(domain.getCnName() + "_copy");
        this.insertEntity(domain);
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("entityId", id);
        List<Property> list = propertyService.getPropertyList(queryMap);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (Property prop : list) {
            prop.setEntityId(domain.getId());
            propertyService.insertProperty(prop);
        }

    }

    @Transactional
    @Override
    public void deleteEntityByAppId(Long appId) {
        propertyService.deletePropertyByAppId(appId);
        this.sqlSession.delete("EntityMapper.deleteEntityByAppId", appId);
    }

    @Transactional
    @Override
    public void deleteEntityByIds(Object[] arryIds) {
        propertyService.deleteEntityListByEntityIds(arryIds);
        this.sqlSession.delete("EntityMapper.deleteEntityByIds", arryIds);
    }

    @Override
    public List<TreeFrm> getTreeList() {

        // 获得实体列表
        List<Entity> entityList = this.getEntityList(null);
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }

        List<TreeFrm> treeList = new ArrayList<TreeFrm>();
        for (Entity entity : entityList) {
            TreeFrm treeFrm = new TreeFrm();
            treeFrm.setId(entity.getId());
            treeFrm.setParentId(entity.getAppId());
            treeFrm.setName(entity.getCnName());
            treeList.add(treeFrm);
        }
        return treeList;
    }

    @Override
    public List<Entity> getEntityListByIds(Object[] entityIdArray) {
        return this.sqlSession.selectList("EntityMapper.getEntityListByIds", entityIdArray);
    }

    @Override
    public List<Entity> getEntityListByAppId(Long appId) {
        return this.sqlSession.selectList("EntityMapper.getEntityListByAppId", appId);
    }

    @Override
    public void insertEntityBatch(List<Entity> entityList) {
        this.sqlSession.insert("EntityMapper.insertEntityBatch", entityList);
    }

}
