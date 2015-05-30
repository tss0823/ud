/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.service.AbstractBaseService;
import com.usefullc.ud.dao.IHisEntityDao;
import com.usefullc.ud.domain.HisEntity;
import com.usefullc.ud.service.IHisEntityService;

@Service
public class HisEntityServiceImpl extends AbstractBaseService implements IHisEntityService {

    @Autowired
    private IHisEntityDao hisEntityDao;

    @Override
    public HisEntity getHisEntity(Long id) {
        return hisEntityDao.getHisEntity(id);
    }

    @Override
    public List<HisEntity> getHisEntityList(Map<String, Object> queryMap) {
        return hisEntityDao.getHisEntityList(queryMap);
    }

    @Override
    public Pagination<HisEntity> getHisEntityListPage(Map<String, Object> queryMap) {
        return hisEntityDao.getHisEntityListPage(queryMap);
    }

    @Override
    public void insertHisEntity(HisEntity hisEntity) {
        hisEntityDao.insertHisEntity(hisEntity);
    }

    @Override
    public void updateHisEntity(HisEntity hisEntity) {
        hisEntityDao.updateHisEntity(hisEntity);
    }

    @Override
    public void deleteHisEntity(Long id) {
        hisEntityDao.deleteHisEntity(id);
    }

    /*
     * (non-Javadoc)
     * @see com.usefullc.ud.service.IHisEntityService#getHisEntityByMainId(java.lang.Long)
     */
    @Override
    public List<HisEntity> getHisEntityByVer(Long appId, Integer ver) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("appId", appId);
        paramMap.put("ver", ver);
        return this.sqlSession.selectList("HisEntityMapper.getHisEntityByVer", paramMap);
    }

}
