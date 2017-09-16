package com.szahch.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.szahch.pojo.User;

@Repository
public interface UserDao {

	public User queryById(@Param("id") int id);
	
}
