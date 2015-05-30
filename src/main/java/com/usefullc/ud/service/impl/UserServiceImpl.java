/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.usefullc.platform.common.dto.UserInfoSpringDto;
import com.usefullc.platform.common.utils.MD5Utils;
import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.service.AbstractBaseService;
import com.usefullc.platform.service.auth.IUserManager;
import com.usefullc.ud.dao.IUserDao;
import com.usefullc.ud.domain.User;
import com.usefullc.ud.service.IUserService;

/**
 * 用户 Service 实现类
 * 
 * @author tangss
 * @2013-8-7 @上午11:09:06
 */
@Service
public class UserServiceImpl extends AbstractBaseService implements IUserService, IUserManager {

    @Autowired
    private IUserDao userDao;

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public List<User> getUserList(Map<String, Object> queryMap) {
        return userDao.getUserList(queryMap);
    }

    @Override
    public Pagination<User> getUserListPage(Map<String, Object> queryMap) {
        return userDao.getUserListPage(queryMap);
    }

    @Override
    public void insertUser(User user) {
        user.setPassword(MD5Utils.toMD5(user.getPassword()));
        userDao.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    /*
     * (non-Javadoc)
     * @see com.usefullc.platform.service.IUserManager#getUserByUserName(java.lang.String)
     */
    @Override
    public UserInfoSpringDto getUserByUserName(String userName) {
        User user = this.sqlSession.selectOne("UserMapper.getUserByUsername", userName);
        if (user == null) {
            return null;
        }
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        UserInfoSpringDto userInfo = new UserInfoSpringDto(user.getUsername(), user.getPassword(), true, true, true,
                                                           true, authorities);
        userInfo.setCnName(user.getUsername());
        return userInfo;
    }

}
