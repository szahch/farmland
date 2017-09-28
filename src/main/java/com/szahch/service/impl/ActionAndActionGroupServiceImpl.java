package com.szahch.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szahch.dao.ActionAndActionGroupDao;
import com.szahch.dao.ActionDao;
import com.szahch.pojo.Action;
import com.szahch.pojo.ActionAndActionGroup;
import com.szahch.pojo.ActionGroup;
import com.szahch.service.ActionAndActionGroupService;

/**
 * 接口ActionAndActionGroupService的实现Service类
 * 
 * @author AlexZHOU 2017.9.27
 *
 */
@Service
public class ActionAndActionGroupServiceImpl implements ActionAndActionGroupService {

	@Autowired
	private ActionAndActionGroupDao actionAndActionGroupDao;

	@Autowired
	private ActionDao actionDao;

	public List<ActionGroup> getActionGroupListbByAction(Action action) {

		return null;
	}

	public List<Action> getActionListByActionGroup(ActionGroup actionGroup) {

		return null;
	}

	/**
	 * 权限组列表查询权限，得到权限集合，不重复
	 * 
	 * @param actionGroupList
	 *            权限组列表
	 * @return 权限列表
	 */
	public List<Action> getActionListByActionGroupList(List<ActionGroup> actionGroupList) {

		List<Action> list = new ArrayList<Action>();

		for (int i = 0; i < actionGroupList.size(); i++) {
			ActionGroup actionGroup = actionGroupList.get(i);
			if (actionGroup != null) {

				List<ActionAndActionGroup> actionAndActionGroupList = actionAndActionGroupDao
						.queryByGroupId(actionGroup.getId());

				for (int j = 0; j < actionAndActionGroupList.size(); j++) {
					ActionAndActionGroup actionAndActiongroup = actionAndActionGroupList.get(j);
					if (actionAndActiongroup != null) {
						Action action = actionDao.queryById(actionAndActiongroup.getActionId());
						// 去重复
						if (!list.contains(action)) {
							list.add(action);
						}
					}
				}
			}
		}
		return list;
	}
}
