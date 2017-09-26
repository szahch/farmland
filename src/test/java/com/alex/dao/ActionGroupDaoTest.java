package com.alex.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.szahch.config.RootConfig;
import com.szahch.dao.ActionGroupDao;
import com.szahch.pojo.ActionGroup;

/**
 * 权限组单元测试
 * 
 * @author AlexZHOU 2017.9.26
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@WebAppConfiguration
@Transactional
public class ActionGroupDaoTest {

	@Resource
	private ActionGroupDao actionGroupDao;

	@Test
	public void queryById() {
		ActionGroup actionGroup = actionGroupDao.queryById(21);
		System.out.println(actionGroup.toString());
	}
}
