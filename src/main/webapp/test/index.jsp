<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
<head>  
<title>首页</title>  
</head>  
  
<body>  
    <!-- ${user.username}取出session域中的user对象，显示它的username -->  
    欢迎您：${user.username}  
    <a href="/Farmland/practice/login.jsp">登录</a>  
    <a href="/Farmland/practice/servlet/LogoutServlet">注销</a>  
    <br />  
    <br />  
    <br />  
</body>  
</html> 