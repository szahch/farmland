package com.szahch.dao;

import org.springframework.stereotype.Repository;

import com.szahch.pojo.ActionGroup;

/**
 * 
 * @author AlexZHOU 2017.9.26
 *
 */
@Repository
public interface ActionGroupDao {

	/**
	 * 通过权限组id号获取权限组内容
	 * 
	 * @param id
	 *            权限组id号
	 * @return 权限组内容ActionGroup
	 */
	public ActionGroup queryById(int id);
}
