package com.szahch.rpc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.szahch.test.practice.servlet.User;

//用户登陆servlet
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		List<User> list = DB.getAll();
		for (User user : list) {
			// 如果用户登录成功
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				HttpSession session = request.getSession();
				// 手动设置session的有效期为30分钟
				String sessionId = session.getId();
				Cookie cookie = new Cookie("JSESSIONID", sessionId);
				cookie.setMaxAge(60*30);
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
				// 登录成功后要存入用户的登录状态，key是用户对象的String形式value就是用户对象(model)！！别的页面应该能用到
				session.setAttribute("user", user);
				// 重定向到首页，URL重写方式
				String url = response.encodeRedirectURL(request.getContextPath() + "/index.html");
				response.sendRedirect(url);
				return;
			}
		}

		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("用户名或密码错误");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

// 模拟存储用户的数据库
class DB {
	private static List<User> list = new ArrayList<User>();
	static {
		list.add(new User("aaa", "123"));
		list.add(new User("bbb", "123"));
		list.add(new User("ccc", "123"));
	}

	public static List<User> getAll() {
		return list;
	}
}
