package com.alex.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.szahch.config.RootConfig;
import com.szahch.pojo.Action;
import com.szahch.pojo.ActionGroup;
import com.szahch.service.ActionAndActionGroupService;

/**
 * ActionAndActionGroupService单元测试
 * 
 * @author AlexZHOU 2017.9.27
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class })
@WebAppConfiguration
@Transactional
public class ActionAndActionGroupServiceTest {

	@Resource
	private ActionAndActionGroupService service;

	@Test
	public void getActionListByActionGroupList() {

		List<ActionGroup> actionGroupList = new ArrayList<ActionGroup>();

		ActionGroup actionGroup = new ActionGroup();
		actionGroup.setId(1);
		actionGroup.setGroupName("Root");
		actionGroup.setDescription("管理员");
		actionGroupList.add(actionGroup);

		actionGroup = new ActionGroup();
		actionGroup.setId(21);
		actionGroup.setGroupName("User");
		actionGroup.setDescription("普通用户");
		actionGroupList.add(actionGroup);

		List<Action> action = service.getActionListByActionGroupList(actionGroupList);

		for (int i = 0; i < action.size(); i++) {
			if (action.get(i) != null) {
				System.out.println(action.toString());
			}
		}
	}

}
