package com.szahch.service;

import java.util.List;

import com.szahch.pojo.Action;
import com.szahch.pojo.ActionGroup;

/**
 * 
 * 权限与权限组对照服务类
 * 
 * 1.单个权限查询权限组列表，一对多
 * 
 * 2.单个权限组查询权限列表，一对多
 * 
 * 3.权限组列表查询权限，得到权限集合，不重复
 * 
 * @author AlexZHOU 2017.9.27
 *
 */
public interface ActionAndActionGroupService {

	/**
	 * 单个权限查询权限组列表
	 * 
	 * @param action
	 *            单个权限
	 * @return 可以执行这个权限的权限组列表
	 */
	public List<ActionGroup> getActionGroupListbByAction(Action action);

	/**
	 * 单个权限组查询权限列表
	 * 
	 * @param actionGroup
	 *            单个权限组
	 * @return 这个权限组下允许执行的权限
	 */
	public List<Action> getActionListByActionGroup(ActionGroup actionGroup);

	/**
	 * 权限组列表查询权限，得到权限集合，不重复
	 * 
	 * @param actionGroupList
	 *            权限组列表
	 * @return 权限列表
	 */
	public List<Action> getActionListByActionGroupList(List<ActionGroup> actionGroupList);
}
