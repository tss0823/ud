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
import com.usefullc.ud.dao.IApplicationDao;
import com.usefullc.ud.domain.Application;
import com.usefullc.ud.web.form.ApplicationFrm;

@Service
public class ApplicationDaoImpl extends AbstractBaseDao implements IApplicationDao {

    @Override
    public Application getApplication(Long id) {
        return (Application) sqlSession.selectOne("ApplicationMapper.getApplication", id);
    }

    @Override
    public List<Application> getApplicationList(Map<String, Object> queryMap) {
        return sqlSession.selectList("ApplicationMapper.getApplicationList", queryMap);
    }

    @Override
    public Pagination<Application> getApplicationListPage(Map<String, Object> queryMap) {
        Pagination<Application> page = getPagination(queryMap, "ApplicationMapper.getApplicationListPage",
                                                     "ApplicationMapper.getApplicationListPageCount");
        return page;
    }

    @Override
    public Pagination<ApplicationFrm> getApplicationFrmListPage(Map<String, Object> queryMap) {
        Pagination<ApplicationFrm> page = getPagination(queryMap, "ApplicationMapper.getApplicationFrmListPage",
                                                        "ApplicationMapper.getApplicationFrmListPageCount");
        return page;
    }

    @Override
    public void insertApplication(Application domain) {
        sqlSession.insert("ApplicationMapper.insertApplication", domain);
    }

    @Override
    public void updateApplication(Application domain) {
        sqlSession.update("ApplicationMapper.updateApplication", domain);
    }

    @Override
    public void deleteApplication(Long id) {
        sqlSession.delete("ApplicationMapper.deleteApplication", id);
    }

}
