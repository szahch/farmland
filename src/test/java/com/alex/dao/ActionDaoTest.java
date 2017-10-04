package com.alex.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.szahch.config.RootConfig;
import com.szahch.dao.ActionDao;
import com.szahch.pojo.Action;

/**
 * 
 * ActionDao单元测试
 * 
 * @author AlexZHOU 2017.9.27
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@WebAppConfiguration
@Transactional
public class ActionDaoTest {

	@Resource
	private ActionDao actionDao;

	@Test
	public void queryById() {

		Action action = actionDao.queryById(1);

		System.out.println(action.toString());
	}
	
	

}
