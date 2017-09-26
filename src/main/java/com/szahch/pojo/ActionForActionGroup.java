package com.szahch.pojo;

/**
 * 权限与权限组对应表
 * 
 * @author AlexZHOU 2017.9.26
 *
 */
public class ActionForActionGroup {
	private int id;

	private int actionId;

	private int groupId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
}
