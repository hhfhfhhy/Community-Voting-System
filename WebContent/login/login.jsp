<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>网上投票系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/OVS/style_login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="logincontent">
		<div class="login">
			<fieldset>
				<form name="login" action="/OVS/Login" method="post">
					<center>
						<div>
							<label for="number" class="fixedwidth">账号：</label> <input
								type="text" name="userID" id="number" />
						</div>
					</center>

					<center>
						<div>
							<label for="password" class="fixedwidth">密码：</label> <input
								type="password" name="password" id="password" />
						</div>
					</center>

					<center>
						<div class="buttonarea">
							<input type="submit" value="登录" /> <input type="reset"
								value="重填" />
						</div>
					</center>
				</form>

			</fieldset>

			<center>
				<div style="color: #FF0000">
					<h3><a href="register.jsp">没有账号？点击注册</a></h3>
				</div>
			</center>
				<div style="color: #FF0000" align="right">
					<h5><a href="findPWD.jsp"><font color="red">忘记密码？</font></a></h5>
				</div>

		</div>
	</div>
</body>
</html>
