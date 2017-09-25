/**
 * 
 */

function changeValidationCodeImage() {
	$(".validationCode_img").attr("src",
			"/farmland/user/validationCode" + "?" + Math.random());

}

function loginSubmit(form) {

	if (!checkInput()) {
		return false;
	}

	$.ajax({
		type : "POST",
		url : "/farmland/user/login",
		data : $("#loginForm").serialize(),
		success : function(msg) {

			console.log(msg);
			console.log(msg.resultCode);
			if (!msg.success) {
				switch (msg.resultCode) {
				case -1:
					alert("登录验证码错误");
					break;
				case -2:
					alert("登录密码错误");
					break;
				case -3:
					alert("登录帐号不存在");
					break;
				case -4:
					alert("验证码失效");
					break;
				default:
					break;
				}
			} else {
				window.location.href = "/farmland/map/index";

			}
		}
	});

}

function checkInput() {

	// 判断用户名

	if ($("input[name=username]").val() == null
			|| $("input[name=username]").val() == "") {

		alert("用户名不能为空");

		$("input[name=username]").focus();

		return false;

	}

	// 判断密码

	if ($("input[name=password]").val() == null
			|| $("input[name=password]").val() == "") {

		alert("密码不能为空");

		$("input[name=password]").focus();

		return false;

	}

	// 判断验证码

	if ($("input[name=validationCode]").val() == null
			|| $("input[name=validationCode]").val() == "") {

		alert("验证码不能为空");

		$("input[name=validationCode]").focus();

		return false;

	}

	return true;

}
