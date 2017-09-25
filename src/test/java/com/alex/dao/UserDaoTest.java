package com.alex.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.szahch.config.RootConfig;
import com.szahch.dao.UserDao;
import com.szahch.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@WebAppConfiguration
@Transactional
public class UserDaoTest {

	@Resource
	private UserDao userDao;

	@Test
	public void queryByUserName() {
		User user = userDao.queryByUserName("850946554");
		System.out.println(user.getPassword());
	}

	@Test
	public void queryById() {
		User user = userDao.queryById(1);
		System.out.println(user.getId());
	}

}
