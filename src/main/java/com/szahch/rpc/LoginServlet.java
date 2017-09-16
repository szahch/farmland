// package com.szahch.rpc;
//
// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.ArrayList;
// import java.util.List;
//
// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.Cookie;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;
//
//
//
// import com.szahch.test.practice.servlet.User;
//
//// 鐢ㄦ埛鐧婚檰servlet
// @WebServlet("/LoginServlet")
// public class LoginServlet extends HttpServlet {
//
// /**
// *
// */
// private static final long serialVersionUID = 1L;
//
// public LoginServlet() {
// super();
// // TODO Auto-generated constructor stub
// }
//
// public void doGet(HttpServletRequest request, HttpServletResponse response)
// throws ServletException, IOException {
// String username = request.getParameter("username");
// String password = request.getParameter("password");
//
// List<User> list = DB.getAll();
// for (User user : list) {
// // 濡傛灉鐢ㄦ埛鐧诲綍鎴愬姛
// if (user.getUsername().equals(username) &&
// user.getPassword().equals(password)) {
// HttpSession session = request.getSession();
// // 鎵嬪姩璁剧疆session鐨勬湁鏁堟湡涓�30鍒嗛挓
// String sessionId = session.getId();
// Cookie cookie = new Cookie("JSESSIONID", sessionId);
// cookie.setMaxAge(60*30);
// cookie.setPath(request.getContextPath());
// response.addCookie(cookie);
// //
// 鐧诲綍鎴愬姛鍚庤瀛樺叆鐢ㄦ埛鐨勭櫥褰曠姸鎬侊紝key鏄敤鎴峰璞＄殑String褰㈠紡value灏辨槸鐢ㄦ埛瀵硅薄(model)锛侊紒鍒殑椤甸潰搴旇鑳界敤鍒�
// session.setAttribute("user", user);
// // 閲嶅畾鍚戝埌棣栭〉锛孶RL閲嶅啓鏂瑰紡
// String url = response.encodeRedirectURL(request.getContextPath() +
// "/index.html");
// response.sendRedirect(url);
// return;
// }
// }
//
// response.setCharacterEncoding("UTF-8");
// response.setHeader("Content-type", "text/html;charset=UTF-8");
// PrintWriter out = response.getWriter();
// out.print("鐢ㄦ埛鍚嶆垨瀵嗙爜閿欒");
// }
//
// public void doPost(HttpServletRequest request, HttpServletResponse response)
// throws ServletException, IOException {
// doGet(request, response);
// }
//
// }
//
//// 妯℃嫙瀛樺偍鐢ㄦ埛鐨勬暟鎹簱
// class DB {
// private static List<User> list = new ArrayList<User>();
// static {
// list.add(new User("aaa", "123"));
// list.add(new User("bbb", "123"));
// list.add(new User("ccc", "123"));
// }
//
// public static List<User> getAll() {
// return list;
// }
// }
