package com.szahch.dao;

import org.springframework.stereotype.Repository;

import com.szahch.pojo.ActionForActionGroup;

/**
 * 
 * @author AlexZHOU 2017.9.26
 *
 */
@Repository
public interface ActionForActionGroupDao {
	/**
	 * 通过用户id查询权限组
	 * 
	 * @param userId
	 * @return 单个权限组
	 */
	public ActionForActionGroup queryByUserId(int userId);
}
