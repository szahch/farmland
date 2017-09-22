package com.szahch.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.szahch.pojo.Login;
import com.szahch.service.LoginService;
import com.szahch.utils.Constants;
import com.szahch.utils.ValidationCodeUtil;

/***
 * 登录控制器
 * 
 * @author AlexZHOU 2017.9.20
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService = null;

	@RequestMapping(value = "/hello")
	private ModelAndView hello() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}

	/**
	 * 登录打开页面
	 * 
	 */
	@RequestMapping("/start")
	private ModelAndView loginPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}

	@RequestMapping(value = "/login")
	private ModelAndView login(Login login) {

		System.out.println(login.getPassword());
		System.out.println(login.getUsername());
		System.out.println(login.getValidationCode());
		ModelAndView mv = new ModelAndView();
		// mv.setViewName("login");

//		String password = loginService.getPasswordByUsername(login.getUsername());
//
//		System.out.println("real password:" + password);
		
		System.out.println(loginService.getPasswordByUsername("850946554"));

		mv.addObject("return", "succeed");

		return mv;
	}

	/**
	 * 获取登录验证码
	 * 
	 * @param validationCode
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/validationCode")
	private void validationCode(@RequestParam(value = "validationCode", required = false) String validationCode,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		session.setMaxInactiveInterval(5 * 60);

		OutputStream outS = response.getOutputStream();

		Map<String, Object> map = ValidationCodeUtil.createImage();

		session.setAttribute(Constants.LOGIN_VALIDATION_CODE, (String) map.get(ValidationCodeUtil.VALIDATION_CODE));

		ImageIO.write((BufferedImage) map.get(ValidationCodeUtil.IMAGE), "JPEG", outS);
	}

}
