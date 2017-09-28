package com.szahch.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.szahch.dao.ActionGroupDao;
import com.szahch.dao.UserAndActionGroupDao;
import com.szahch.pojo.ActionGroup;
import com.szahch.pojo.User;
import com.szahch.pojo.UserAndActionGroup;
import com.szahch.service.UserAndActionGroupService;

/**
 * UserAndActionGroupService实现Service类
 * 
 * @author AlexZHOU 2017.9.27
 *
 */
@Service
public class UserAndActionGroupServiceImpl implements UserAndActionGroupService {

	/**
	 * 权限组与用户对照表
	 */
	@Autowired
	private UserAndActionGroupDao userAndActionGroupDao;

	/**
	 * 权限表
	 */
	@Autowired
	private ActionGroupDao actionGroupDao;

	/**
	 * 通过用户查询权限组
	 * 
	 * 
	 * 步骤：
	 * 
	 * 1、获取用户对应的UserForActionGroup列表
	 * 
	 * 2、通过每个UserForActionGroup获取对应的权限组
	 * 
	 * @param user
	 *            用户
	 * @return 返回权限组列表
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<ActionGroup> getActionGroupListByUser(User user) {
		// 如果传入参数为空就直接返回
		if (user == null) {
			return null;
		}

		List<ActionGroup> actionGroupList = new ArrayList<ActionGroup>();

		int userId = user.getId();

		List<UserAndActionGroup> list = userAndActionGroupDao.queryUserForActionGroupListByUserId(userId);

		for (int i = 0; i < list.size(); i++) {
			UserAndActionGroup ufag = list.get(i);
			if (ufag != null) {
				actionGroupList.add(actionGroupDao.queryById(ufag.getGroupId()));
			}
		}
		return actionGroupList;
	}

	/**
	 * 通过权限组查询用户列表
	 * 
	 * @param actionGroup
	 *            权限组
	 * @return 用户列表
	 */
	public List<User> getUserByActionGroup(ActionGroup actionGroup) {

		return null;
	}

}
