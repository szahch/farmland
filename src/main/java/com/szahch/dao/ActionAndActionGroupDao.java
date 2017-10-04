package com.szahch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.szahch.pojo.ActionAndActionGroup;

/**
 * 权限和权限组对应表
 * 
 * @author AlexZHOU 2017.9.26
 *
 */
@Repository
public interface ActionAndActionGroupDao {
	/**
	 * 通过权限组id查询ActionAndActionGroup对照表列表
	 * 
	 * @param groupId
	 * @return ActionAndActionGroup对照表列表
	 */
	public List<ActionAndActionGroup> queryByGroupId(@Param("groupId") int groupId);
}
