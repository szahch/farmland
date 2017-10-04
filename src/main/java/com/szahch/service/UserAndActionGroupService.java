package com.szahch.service;

import java.util.List;

import com.szahch.pojo.ActionGroup;
import com.szahch.pojo.User;

/**
 * 
 * 权限组与用户对照查询服务
 * 
 * 1.用户查询权限组，一对多
 * 
 * 2.权限组查询用户，一对多
 * 
 * @author AlexZHOU 2017.9.27
 *
 */
public interface UserAndActionGroupService {

	/**
	 * 通过用户查询权限组
	 * 
	 * @param user
	 *            用户
	 * @return 返回权限组列表
	 */
	public List<ActionGroup> getActionGroupListByUser(User user);

	/**
	 * 通过权限组查询用户列表
	 * 
	 * @param actionGroup
	 *            权限组
	 * @return 用户列表
	 */
	public List<User> getUserByActionGroup(ActionGroup actionGroup);

}
