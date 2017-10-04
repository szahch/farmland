package com.szahch.service;

import com.szahch.pojo.User;

/**
 * 
 * @author AlexZHOU 2017.9.21
 *
 */
public interface UserService {

	/**
	 * 通过用户帐号获取用户密码</b> 1.帐号查询成功，返回密码</b> 2.帐号查询失败，返回空</b>
	 * 
	 * @param username
	 *            用户帐号
	 */
	public String getPasswordByUsername(String username);

	/**
	 * 通过用户帐号获取用户所有信息</b> 1.帐号查询成功，用户所有信息</b> 2.帐号查询失败，返回空</b>
	 * 
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username);

}
