/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service;
import java.util.List;
import java.util.Map;
import com.usefullc.ud.domain.User;
import com.usefullc.platform.common.web.Pagination;

/**
 *  用户 Service
 * @author tangss
 *
 * @2013-8-7 @上午11:13:42
 */
public interface IUserService {
	
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
	 * @param user
	 */
	void insertUser(User domain);
	
	/**
	 * 修改用户
	 * @param user
	 */
	void updateUser(User domain);
	
	/**
	 * 删除用户
	 * @param id
	 */
	void deleteUser(Long id);
    

}

