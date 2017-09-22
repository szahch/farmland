package com.szahch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.szahch.dao.UserDao;
import com.szahch.pojo.User;
import com.szahch.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserDao userDao = null;

	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public String getPasswordByUsername(String username) {
		System.out.println("getPasswordByUsername");
		User user = userDao.queryByUserName(username);
		if (user == null) {
			System.out.println("User is null");
		}else {
			System.out.println( user.getPassword());
		}
		return user.getPassword();
	}

}
