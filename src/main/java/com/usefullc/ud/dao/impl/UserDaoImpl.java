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
import com.usefullc.ud.dao.IUserDao;
import com.usefullc.ud.domain.User;

@Service
public class UserDaoImpl extends AbstractBaseDao implements IUserDao {
	
	@Override
	public User getUser(Long id) {
		 return (User) sqlSession.selectOne("UserMapper.getUser", id);
	}
	
	@Override
	public List<User> getUserList(Map<String,Object> queryMap) {
		 return sqlSession.selectList("UserMapper.getUserList",queryMap);
	}
	
	@Override
	public Pagination<User> getUserListPage(Map<String,Object> queryMap) {
		Pagination<User> page = getPagination(queryMap, "UserMapper.getUserListPage", "UserMapper.getUserListPageCount");
		return page;
	}

	@Override
	public void insertUser(User domain) {
		sqlSession.insert("UserMapper.insertUser", domain);
	}

	@Override
	public void updateUser(User domain) {
		sqlSession.update("UserMapper.updateUser", domain);
	}

	@Override
	public void deleteUser(Long id) {
		sqlSession.delete("UserMapper.deleteUser", id);
	}

}
