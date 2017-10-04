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
import com.szahch.dao.UserAndActionGroupDao;
import com.szahch.pojo.UserAndActionGroup;

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
public class UserAndActionGroupDaoTest {
	@Resource
	private UserAndActionGroupDao userForActionGroupDao;

	/**
	 * 查询条目大于1条时将会报错
	 */
	@Test
	public void queryByUserId() {
		UserAndActionGroup group = userForActionGroupDao.queryByUserId(1);
		System.out.println(group.getGroupId());
	}
	
	@Test
	public void queryUserForActionGroupListByUserId() {
		List<UserAndActionGroup> list = userForActionGroupDao.queryUserForActionGroupListByUserId(1);
		for (int i = 0; i < list.size(); i++) {
			UserAndActionGroup group = list.get(i);
			System.out.println(group.toString());
		}
	}
}
