package com.szahch.pojo;

/**
 * 用户与权限组对应
 * 
 * 1.一个用户可对应多个权限组
 * 
 * 2.一个权限组个对应多个用户
 * 
 * @author AlexZHOU 2017.9.26
 *
 */
public class UserAndActionGroup {

	private int id;

	private int groupId;

	private int userId;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserAndActionGroup [id=" + id + ", groupId=" + groupId + ", userId=" + userId + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
}
