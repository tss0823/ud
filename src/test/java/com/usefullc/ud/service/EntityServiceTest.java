/**
 * 
 */
package com.usefullc.ud.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.usefullc.ud.domain.Entity;
import com.usefullc.platform.common.web.Pagination;

/**
 * @author tangss
 * @2013-6-13 @下午3:51:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-web.xml", "classpath:applicationContext.xml" })
@TransactionConfiguration(defaultRollback = false)
public class EntityServiceTest {

    @Resource
    IEntityService entityService;

    // @Test
    public void getEntity() {
        Entity entity = entityService.getEntity(4L);
        System.out.println(entity);
    }

    // @Test
    public void getEntityList() {
        List<Entity> list = entityService.getEntityList(new HashMap<String, Object>());
        System.out.println(list);
    }

    // @Test
    public void getEntityListPage() {
        Pagination<Entity> page = entityService.getEntityListPage(new HashMap<String, Object>());
        System.out.println(page);
    }

    @Test
    @Transactional
    public void insertEntity() {
        Entity entity = new Entity();
        entity.setAppId(1L);
        entity.setCnName("cs管理员1");
        entity.setEnName("test2");
        entityService.insertEntity(entity);
        System.out.println(entity);
    }

    // @Test
    public void deleteEntity() {
        entityService.deleteEntity(4L);
    }

}
