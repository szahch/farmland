package practice.servlet;

import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  

/**
 * Servlet implementation class LogoutServlet
 */

//注销servlet  
@WebServlet("/practice/servlet/LogoutServlet")
public class LogoutServlet extends HttpServlet {  
	 public LogoutServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        response.setCharacterEncoding("UTF-8");  
        response.setHeader("Content-type", "text/html;charset=UTF-8");  
        PrintWriter out = response.getWriter();  
        HttpSession session = request.getSession(false);  
        if (session == null) {  
            // 没登录，重定向到首页  
            String url = response.encodeRedirectURL(request.getContextPath()  
                    + "/practice/index.jsp");  
            response.sendRedirect(url);  
            return;  
        }  
        // 从session中移除登录状态  
        session.removeAttribute("user");  
        // 重定向到首页，URL重写方式  
        String url = response.encodeRedirectURL(request.getContextPath()  
                + "/practice/index.jsp");  
        response.sendRedirect(url);  
    }  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        doGet(request, response);  
    }  
  
}  
