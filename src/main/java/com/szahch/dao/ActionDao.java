package com.szahch.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.szahch.pojo.Action;

/**
 * 
 * @author AlexZHOU 2017.9.26
 *
 */
@Repository
public interface ActionDao {
	/**
	 * 通过id号查询权限
	 * 
	 * @param id
	 *            权限id号
	 * @return 权限
	 */
	public Action queryById(@Param("id") int id);
}
