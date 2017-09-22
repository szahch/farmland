package com.szahch.pojo;

/**
 * 登录信息
 * 
 * @author AlexZHOU 2017.9.20
 *
 */
public class Login {

	private String username;

	private String password;

	private String validationCode;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getValidationCode() {
		return validationCode;
	}

	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}
}
