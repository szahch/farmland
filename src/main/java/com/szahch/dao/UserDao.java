package com.szahch.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.szahch.pojo.User;

/**
 * 用户Dao层信息
 * 
 * @author AlexZHOU 2017.9.21
 *
 */
@Repository
public interface UserDao {

	public User queryByUserName(@Param("username") String username);

	public User queryById(@Param("id") int id);
}
