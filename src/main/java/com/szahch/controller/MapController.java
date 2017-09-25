package com.szahch.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.szahch.pojo.User;
import com.szahch.utils.Constants;

/**
 * 地图界面控制器
 * 
 * @author AlexZHOU 2017.9.25
 *
 */
@Controller
@RequestMapping("/map")
public class MapController {
	@Autowired
	private HttpSession session;

	@RequestMapping("index")
	public String map(Model model) {
		User usr = (User) session.getAttribute(Constants.USER_INFO);
		if (usr == null) {
			return "redirect:/user/start";
		} else {
			model.addAttribute("user", usr);
			return "map";
		}
	}

}
