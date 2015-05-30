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

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.ud.domain.User;

/**
 * @author tangss
 * @2013-6-13 @下午3:51:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-web.xml", "classpath:applicationContext.xml" })
public class UserServiceTest {

    @Resource
    IUserService userService;

    // @Test
    public void getUser() {
        User user = userService.getUser(4L);
        System.out.println(user);
    }

    // @Test
    public void getUserList() {
        List<User> list = userService.getUserList(new HashMap<String, Object>());
        System.out.println(list);
    }

    @Test
    public void getUserListPage() {
        Pagination<User> page = userService.getUserListPage(new HashMap<String, Object>());
        System.out.println(page);
    }

    // @Test
    public void insertUser() {
        User user = new User();
        // user.setUserName("test");
        user.setCnName("cs管理员");
        user.setPassword("test");
        userService.insertUser(user);
        System.out.println(user);
    }

    // @Test
    public void updateUser() {
        User user = new User();
        user.setId(4L);
        // .setUserName("admin");
        user.setCnName("cs管理员");
        user.setPassword("123456");
        userService.updateUser(user);
    }

    // @Test
    public void deleteUser() {
        userService.deleteUser(4L);
    }

}
