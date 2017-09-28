package com.szahch.pojo;

/**
 * 权限与权限组对应表
 * 
 * 
 * 1.一个权限组可对应多个权限
 * 
 * 2.一个权限可对应多个组
 * 
 * @author AlexZHOU 2017.9.26
 *
 */
public class ActionAndActionGroup {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ActionAndActionGroup [id=" + id + ", actionId=" + actionId + ", groupId=" + groupId + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
}
