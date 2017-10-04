package com.szahch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.szahch.pojo.UserAndActionGroup;

/**
 * 
 * @author AlexZHOU 2017.9.26
 *
 */
@Repository
public interface UserAndActionGroupDao {

	/**
	 * 通过用户id查询单个权限组
	 * 
	 * @return 返回用户权限组
	 */
	public UserAndActionGroup queryByUserId(@Param("userId") int userId);

	/**
	 * 通过用户id查询单个权限组列表(多个)
	 * 
	 * @return 返回权限组列表
	 */
	public List<UserAndActionGroup> queryUserForActionGroupListByUserId(@Param("userId") int userId);
}
