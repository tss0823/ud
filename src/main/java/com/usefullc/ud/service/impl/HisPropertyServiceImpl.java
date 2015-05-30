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
import com.usefullc.ud.dao.IHisPropertyDao;
import com.usefullc.ud.domain.HisProperty;
import com.usefullc.ud.service.IHisPropertyService;

@Service
public class HisPropertyServiceImpl extends AbstractBaseService implements IHisPropertyService {

    @Autowired
    private IHisPropertyDao hisPropertyDao;

    @Override
    public HisProperty getHisProperty(Long id) {
        return hisPropertyDao.getHisProperty(id);
    }

    @Override
    public List<HisProperty> getHisPropertyList(Map<String, Object> queryMap) {
        return hisPropertyDao.getHisPropertyList(queryMap);
    }

    @Override
    public Pagination<HisProperty> getHisPropertyListPage(Map<String, Object> queryMap) {
        return hisPropertyDao.getHisPropertyListPage(queryMap);
    }

    @Override
    public void insertHisProperty(HisProperty hisProperty) {
        hisPropertyDao.insertHisProperty(hisProperty);
    }

    @Override
    public void updateHisProperty(HisProperty hisProperty) {
        hisPropertyDao.updateHisProperty(hisProperty);
    }

    @Override
    public void deleteHisProperty(Long id) {
        hisPropertyDao.deleteHisProperty(id);
    }

    /*
     * (non-Javadoc)
     * @see com.usefullc.ud.service.IHisPropertyService#getHisPropertyByVer(java.lang.Long, java.lang.Integer)
     */
    @Override
    public List<HisProperty> getHisPropertyByVer(Long entityId, Integer ver) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("entityId", entityId);
        paramMap.put("ver", ver);
        return this.sqlSession.selectList("HisPropertyMapper.getHisPropertyByVer", paramMap);
    }

}
