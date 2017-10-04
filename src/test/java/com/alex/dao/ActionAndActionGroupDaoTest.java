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
import com.szahch.dao.ActionAndActionGroupDao;
import com.szahch.pojo.ActionAndActionGroup;

/**
 * ActionAndActionGroupDao单元测试
 * 
 * @author AlexZHOU 2017.9.27
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@WebAppConfiguration
@Transactional
public class ActionAndActionGroupDaoTest {

	@Resource
	private ActionAndActionGroupDao actionAndActionGroupDao;

	@Test
	public void queryByGroupId() {
		List<ActionAndActionGroup> list = actionAndActionGroupDao.queryByGroupId(1);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		
	}

}
