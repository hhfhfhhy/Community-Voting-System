<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ page import="com.ovs.beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>网上投票系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/OVS/style_login.css" rel="stylesheet" type="text/css" />
</head>
<%
String UserID=request.getParameter("UserID"); 
UserInfoBean uib=new UserInfoBean();
%>
<script type="text/javascript">
function check(){
/* 	var check1=document.getElementById("newPWD1").value;
	var check2=document.getElementById("newPWD2").value;
	if(check1!=check2){
		alert("两次输入不一致！！！");
		return false;
	} */
	alert("两次输入不一致！！！");
	return false;
}
function check_NotNull(){
	var userID = document.getElementById("userID").value;
	/* var struserID=userID.replace(/(^\s*)|(\s*$)/g,''); */
	var struserID=userID.trim();
	
	if(userID==""||userID==null||struserID==""){
		alert("账号不能为空！！！！");
		return false;
	}
</script>
<body>
	<div class="logincontent">
		<div class="login">
			<fieldset>

				<form action="/OVS/FindPWD_ChangePWD" name="login" method="post" onsubmit="return check()">
					<center>
						<div>
							<label for="number" class="fixedwidth">你的账号是：</label> 
							<%=UserID%>
						</div>
					</center>
					<center>
						<div>
							<label for="number" class="fixedwidth">请输入新密码：</label> 
							<input type="password" name="newPWD1" id="newPWD1" />
						</div>
					</center>
					<center>
						<div>
							<label for="number" class="fixedwidth">请再输入一次：</label> <input
								type="password" name="newPWD2" id="newPWD2" />
						</div>
					</center>


					<center>
						<div class="buttonarea">
						<input type="hidden" name="UserID" value="<%=UserID%>" /> 
							<input type="submit" value="下一步" /> <input type="reset"
								value="重填" />
						</div>
					</center>
				</form>

			</fieldset>
		</div>
	</div>
</body>
</html>
