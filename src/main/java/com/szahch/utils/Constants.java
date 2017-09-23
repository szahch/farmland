package com.szahch.utils;

/**
 * 常量类
 * 
 * @author AlexZHOU 2017.9.20
 *
 */
public class Constants {
	/***
	 * 登录验证码
	 */
	public static final String LOGIN_VALIDATION_CODE = "LoginValidationCode";

	/**
	 * Session 保留登录使用者的信息
	 */
	public static final String USER_INFO = "UserInfo";

	/**
	 * 网络传输正确返回代码
	 */
	public static final int NETWORK_SUCCEED_CODE = 200;

	/**
	 * 发生内部错误代码，原因未知
	 */
	public static final int INTERNAL_ERROR_CODE = 201;

	/**
	 * 发生内部错误提示信息
	 */
	public static final String INTERNAL_ERROR_STRING = "内部错误,请联系管理员";
}
