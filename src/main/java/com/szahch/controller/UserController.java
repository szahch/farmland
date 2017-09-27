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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szahch.dto.Result;
import com.szahch.pojo.Login;
import com.szahch.pojo.User;
import com.szahch.service.UserService;
import com.szahch.utils.Constants;
import com.szahch.utils.ValidationCodeUtil;

/***
 * 登录控制器
 * 
 * @author AlexZHOU 2017.9.20
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * 登录验证码错误
	 */
	private static final int VALIDATION_CODE_WRONG = -1;

	/**
	 * 登录密码错误
	 */
	private static final int PASSWORD_WRONG = -2;

	/**
	 * 登录帐号不存在
	 */
	private static final int ACCOUNT_NOT_EXIT = -3;

	/**
	 * 验证码失效
	 */
	private static final int VALIDATION_CODE_ABATE = -4;

	@Autowired
	private HttpSession session;

	@Autowired
	private UserService loginService = null;

	/**
	 * 登录打开页面
	 * 
	 */
	@RequestMapping("/start")
	private String loginPage(Model model) {
		User usr = (User) session.getAttribute(Constants.USER_INFO);
		if (usr == null) {
			return "login";
		} else {
			return "redirect:/map/index";
		}
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

		session.setMaxInactiveInterval(5 * 60);

		OutputStream outS = response.getOutputStream();

		Map<String, Object> map = ValidationCodeUtil.createImage();

		session.setAttribute(Constants.LOGIN_VALIDATION_CODE, (String) map.get(ValidationCodeUtil.VALIDATION_CODE));

		ImageIO.write((BufferedImage) map.get(ValidationCodeUtil.IMAGE), "JPEG", outS);
	}

	/**
	 * 登录验证
	 * 
	 * @author AlexZHOU 2017.9.25
	 * @param login
	 *            登录上传来的信息
	 * 
	 * @return 返回登录结果
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/json;charset=utf-8" })
	@ResponseBody
	private Result<Login> login(Login login) {

		try {
			logger.debug(login.toString());

			if (session.getAttribute(Constants.LOGIN_VALIDATION_CODE) == null) {
				return new Result<Login>(false, VALIDATION_CODE_ABATE, "验证码失效");
			}
			String sessionValidationCode = (session.getAttribute(Constants.LOGIN_VALIDATION_CODE)).toString()
					.toLowerCase();
			String loginValidationCode = login.getValidationCode().toLowerCase();

			if (!sessionValidationCode.equals(loginValidationCode)) {
				return new Result<Login>(false, VALIDATION_CODE_WRONG, "登录验证码错误");
			}

			String password = loginService.getPasswordByUsername(login.getUsername());
			if (password == null) {
				return new Result<Login>(false, ACCOUNT_NOT_EXIT, "帐号不存在");
			}

			if (password.equals(login.getPassword())) {
				// 10分钟不操作就会断开
				session.setMaxInactiveInterval(10 * 60);
				session.setAttribute(Constants.USER_INFO, loginService.getUserByUsername(login.getUsername()));
				return new Result<Login>(true, Constants.NETWORK_SUCCEED_CODE, login);
			} else {
				return new Result<Login>(false, PASSWORD_WRONG, "密码错误");
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Result<Login>(false, Constants.INTERNAL_ERROR_CODE, Constants.INTERNAL_ERROR_STRING);
		}
	}

	/**
	 * 用户注销登录
	 * 
	 * @return 重定向到登录界面
	 */
	@RequestMapping("/signOut")
	public String signOut() {
		// 用户session注销
		session.invalidate();
		// 返回登录界面
		return "redirect:/user/start";
	}

}
