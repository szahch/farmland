package com.szahch.pojo;

/**
 * 用户与权限组对应
 * 
 * @author AlexZHOU 2017.9.26
 *
 */
public class UserForActionGroup {
	private int id;

	private int groupId;

	private int userId;

	private ActionGroup actionGroup;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public ActionGroup getActionGroup() {
		return actionGroup;
	}

	public void setActionGroup(ActionGroup actionGroup) {
		this.actionGroup = actionGroup;
	}

}
