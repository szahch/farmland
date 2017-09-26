package com.szahch.pojo;

import java.util.List;

/**
 * 用户信息
 * 
 * @author AlexZHOU 2017.9.20
 *
 */
public class User {

	private List<UserForActionGroup> groups = null;

	private List<ActionGroup> actions = null;

	private int id;

	private String name;

	private String username;

	private String password;

	public User() {

	}

	public List<UserForActionGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<UserForActionGroup> groups) {
		this.groups = groups;
	}

	public List<ActionGroup> getActions() {
		return actions;
	}

	public void setActions(List<ActionGroup> actions) {
		this.actions = actions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + "]";
	}

}
