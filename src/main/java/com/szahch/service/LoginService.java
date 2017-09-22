package com.szahch.service;

/**
 * 
 * @author AlexZHOU 2017.9.21
 *
 */
public interface LoginService {

	/**
	 * 通过帐号获取用户密码
	 * 
	 * @param username
	 *            帐号
	 * @return 用户密码
	 */
	public String getPasswordByUsername(String username);

}
