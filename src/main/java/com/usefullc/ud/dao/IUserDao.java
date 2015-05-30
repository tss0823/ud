/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.dao;
import java.util.List;
import java.util.Map;

import com.usefullc.platform.common.web.Pagination;

import com.usefullc.ud.domain.User;

/**
 * 用户DAO
 * @author tangss
 *
 * @2013-4-6 @上午10:36:10
 */
public interface  IUserDao {
	
	/**
	 * 获得用户
	 * @param id
	 * @return
	 */
	User getUser(Long id);
	
	/**
	 * 获得用户列表
	 * @param queryMap
	 * @return
	 */
	List<User> getUserList(Map<String,Object> queryMap);
	
	/**
	 * 获得用户分页列表
	 * @param queryMap
	 * @return
	 */
	Pagination<User> getUserListPage(Map<String,Object> queryMap) ;
	
	/**
	 * 新增用户
	 * @param domain
	 */
	void insertUser(User domain);
	
	/**
	 * 修改用户
	 * @param domain
	 */
	void updateUser(User domain);
	
	/**
	 * 删除用户
	 * @param id
	 */
	void deleteUser(Long id);


}

