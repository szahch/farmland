package com.szahch.pojo;

/**
 * 权限
 * 
 * @author AlexZHOU 2017.9.26
 *
 */
public class Action {

	/**
	 * 权限id号
	 */
	private int id;

	/**
	 * 权限名称
	 */
	private String actionName;

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

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Action [id=" + id + ", actionName=" + actionName + ", description=" + description + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
