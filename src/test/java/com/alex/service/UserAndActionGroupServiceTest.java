package com.alex.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.szahch.config.RootConfig;
import com.szahch.pojo.ActionGroup;
import com.szahch.pojo.User;
import com.szahch.service.UserAndActionGroupService;

/**
 * UserAndActionGroupService单元测试
 * 
 * @author AlexZHOU 2017.9.27
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@WebAppConfiguration
@Transactional
public class UserAndActionGroupServiceTest {

	@Resource
	private UserAndActionGroupService userAndActionGroupService;

	@Test
	public void getActionGroupListByUser() {
		User user = new User();
		user.setId(1);
		List<ActionGroup> list = userAndActionGroupService.getActionGroupListByUser(user);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null) {
				System.out.println(list.get(i).toString());
			}
		}
	}
}
