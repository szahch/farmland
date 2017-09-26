package com.alex.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.szahch.config.RootConfig;
import com.szahch.dao.UserForActionGroupDao;
import com.szahch.pojo.UserForActionGroup;

/**
 * 权限组测试
 * 
 * @author AlexZHOU 2017.9.26
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@WebAppConfiguration
@Transactional
public class UserForActionGroupDaoTest {
	@Resource
	private UserForActionGroupDao userForActionGroupDao;

	@Test
	public void queryByUserId() {
		UserForActionGroup group = userForActionGroupDao.queryByUserId(1);
		System.out.println(group.getGroupId());
	}

	@Test
	public void queryUserForActionGroupListByUserId() {
		List<UserForActionGroup> list = userForActionGroupDao.queryUserForActionGroupListByUserId(1);
		for (int i = 0; i < list.size(); i++) {
			UserForActionGroup group = list.get(i);
			System.out.println(group.getActionGroup().toString());
		}
	}
}
