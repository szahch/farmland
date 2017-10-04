package com.szahch.service;

import com.szahch.pojo.ActionGroup;

/**
 * 权限组服务接口
 * 
 * @author AlexZHOU 2017.9.27
 *
 */
public interface ActionGroupService {

	/**
	 * 通过权限组id号查询
	 * 
	 * @param id
	 *            权限组id号
	 * @return 返回权限组信息
	 */
	public ActionGroup queryById(int id);
	
	
}
