package com.alex.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.szahch.config.RootConfig;
import com.szahch.pojo.User;
import com.szahch.service.UserService;

/**
 * UserServic单元测试
 * 
 * @author AlexZHOU 2017.9.27
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@WebAppConfiguration
@Transactional
public class UserServiceTest {
	@Resource
	private UserService userService;

	@Test
	public void getPasswordByUsername() {
		String password = userService.getPasswordByUsername("850946554");
		System.out.println(password);
	}

	@Test
	public void getUserByUsername() {
		User user = userService.getUserByUsername("850946554");
		System.out.println(user.getName());
	}
}
