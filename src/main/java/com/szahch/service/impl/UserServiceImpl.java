package com.szahch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.szahch.dao.UserDao;
import com.szahch.pojo.User;
import com.szahch.service.UserService;

/**
 * 
 * @author AlexZHOU 2017.9.21
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao = null;

	/**
	 * 通过用户帐号获取用户密码</b> 1.帐号查询成功，返回密码</b> 2.帐号查询失败，返回空</b>
	 * 
	 * @param username
	 *            用户帐号
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public String getPasswordByUsername(String username) {
		User user = userDao.queryByUserName(username);
		if (user == null) {
			return null;
		}
		return user.getPassword();
	}

	/**
	 * 通过用户帐号获取用户所有信息</b> 1.帐号查询成功，用户所有信息</b> 2.帐号查询失败，返回空</b>
	 * 
	 * @param username
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public User getUserByUsername(String username) {
		// FIXME 补充功能
		User user = userDao.queryByUserName(username);
		if (user == null) {
			return null;
		}
		return user;
	}

}
