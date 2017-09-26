package com.szahch.pojo;

/**
 * 
 * 权限组
 * 
 * @author AlexZHOU 2017.9.26
 *
 */
public class ActionGroup {
	/**
	 * 组id号
	 */
	private int id;

	/**
	 * 组名
	 */
	private String groupName;

	/**
	 * 描述
	 */
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ActionGroup [id=" + id + ", groupName=" + groupName + ", description=" + description + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
