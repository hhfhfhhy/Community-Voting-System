<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户注册</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/OVS/style_login.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript">
function check_NotNull(){
	var UserName = document.getElementById("UserName").value;
	var ValidIdentity=document.getElementById("ValidIdentity").value;
	var SecurityP=document.getElementById("SecurityP").value;;
	var SecurityA=document.getElementById("SecurityA").value;;
	var strUserName=UserName.replace(/(^\s*)|(\s*$)/g,'');
	var strValidIdentity=ValidIdentity.replace(/(^\s*)|(\s*$)/g,'');
	var strSecurityP=SecurityP.replace(/(^\s*)|(\s*$)/g,'');
	var strSecurityA=SecurityA.replace(/(^\s*)|(\s*$)/g,'');
	
	if(UserName==""||UserName==null||strUserName==""){
		alert("用户名不能为空！！！！");
		return false;
	}else if(ValidIdentity==""||ValidIdentity==null||strValidIdentity==""){
		alert("有效证件不能为空！！！！");
		return false;
	}else if(SecurityP==""||SecurityP==null||strSecurityP==""){
		alert("安全问题不能为空！！！！");
		return false;
	}else if(SecurityA==""||SecurityA==null||strSecurityA==""){
		alert("安全答案不能为空！！！！");
		return false;
	}
}
</script>
<body>
<div id="bodycontent">
		<form action="/OVS/RegisterServlet" name="myform" method="post" onsubmit="return check_NotNull()">
			<table>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><font color="red">用户名</font></td>
					<td>：</td>
					<td><input type="text" name="UserName" id="UserName" onkeyup="this.value=this.value.replace(/[, ]/g,'')"></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><font color="red">有效证件</font></td>
					<td>：</td>
					<td><input type="text" name="ValidIdentity" id="ValidIdentity" onkeyup="this.value=this.value.replace(/[, ]/g,'')">
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><font color="red">安全问题</font></td>
					<td>：</td>
					<td><input type="text" name="SecurityP" id="SecurityP" onkeyup="this.value=this.value.replace(/[, ]/g,'')"></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><font color="red">安全问题答案</font></td>
					<td>:</td>
					<td><input type="text" name="SecurityA" id="SecurityA" onkeyup="this.value=this.value.replace(/[, ]/g,'')"></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><font color="red">初始密码</font></td>
					<td>:</td>
					<td><input type="text" name="PWD" id="PWD" onkeyup="this.value=this.value.replace(/[, ]/g,'')"></td>
				</tr>
				
				

				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>

					<td>
						<div class="buttonarea">
							<input type="submit" value="确认" class="buttonStyle" /> <input
								type="reset" value="重置" class="buttonStyle" />
						</div>
					</td>
				</tr>

			</table>
		</form>

	</div>


</body>
</html>