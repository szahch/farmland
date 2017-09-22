<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<html>

<head>

<meta charset="UTF-8">

<title>登录界面</title>

<script src="../js/jquery-3.2.1.min.js"></script>

<script src="../js/login.js"></script>

<link href="../css/login.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<div id="login">

		<h1>耕地动态监测信息管理系统</h1>

		<form method="post" action="/farmland/login/login">
			<p>
				<input type="text" name="username" id="user" placeholder="用户名">
			</p>

			<p>
				<input type="password" name="password" id="pwd" placeholder="密码">
			</p>

			<p>
				<input id="validationCode" type="text" name="validationCode"
					placeholder="验证码"> <img
					style="vertical-align: middle; margin-bottom: 30px;"
					class="validationCode_img" src="/farmland/login/validationCode">
			</p>

			<p>
				<input type="submit" class="loginform_submit" id="submit" value="登录">
			</p>

		</form>

	</div>
	
	time:${requestScope.return}

</body>
</html>
