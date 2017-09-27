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

		<form id="loginForm" name="loginForm" action="/farmland/user/login"
			method="post">
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
					onclick="changeValidationCodeImage()" class="validationCode_img"
					src="/farmland/user/validationCode">
			</p>

			<p>
				<input id="login_submit_button" type="button" value="登录"
					onclick="loginSubmit(this.form);">
			</p>

		</form>

	</div>

</body>
</html>
