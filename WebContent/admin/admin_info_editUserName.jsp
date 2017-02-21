<%@page import="com.ovs.db.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ovs.model.*"%>
<%@ page import="com.ovs.beans.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*,javax.sql.*,javax.naming.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>网上投票系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<link href="../admin.css" rel="stylesheet" type="text/css" />
<script src="/OVS/js/clock.js"></script>
</head>

<script type="text/javascript">
	function check_NotNull() {
		var AdminName = document.getElementById("AdminName").value;
		var strAdminName = AdminName.replace(/(^\s*)|(\s*$)/g, '');
		if (window.confirm('确认要修改用户名吗？')) {
			if (AdminName == "" || AdminName == null || strAdminName == "") {
				alert("用户名不能为空！！！！");
				return false;
			} else {
				return true;
			}
		}else{
			return false;
		}
	}
</script>

<jsp:useBean id="admcurrentUser" class="com.ovs.model.Admin"
	scope="session" />
<%
	if (admcurrentUser.getAdminID() == null) {
		admcurrentUser.setAdmin(session.getAttribute("userID")
				.toString());
	}
	if (admcurrentUser.getAdminID() == null) {
		out.println("<script type=\"text/javascript\">");
		out.println("var flag = confirm(\"亲爱的用户，您没有登录管理员角色，是否立即登录？\");");
		out.println("if(flag==true){");
		out.println("window.open('/OVS/login/login.jsp','_self','');");
		out.println("}else{");
		out.println("window.open('/OVS/login/checkOut.jsp','_self','');");
		out.println("}");
		out.println("</script>");
	}
%>
<header>
<div id="sitebranding">
	<h1>
		<font face="楷体" size="10">网上投票系统</font>
	</h1>
</div>
<div id="tagline">
	<p>
		<span class="right" style="font-size: 15px; font-style: normal;">
			<c>你好！ </c> <d><jsp:getProperty property="name"
				name="admcurrentUser" /> </d> <d id="txt"></d> <a
			href="/CSIOSystem/login/checkOut.jsp"> <b>退出</b>
		</a>
		</span>
	</p>
</div>
</header>
<nav>
<ul>
	<li><a href="admin_notice_list.jsp">通知公告</a></li>
	<li><a href="admin_vote_list.jsp">投票管理</a></li>
	<li><a href="admin_id_list.jsp">角色管理</a></li>
	<li><a href="admin_info_show.jsp">个人信息</a></li>
</ul>
</nav>
<div id="bodycontent">
	<h5>
		<a><span
			style="color: black; font-weight: normal; font-size: 15px;">您当前的位置：</span></a>
		<a href="admin_index.jsp"><span
			style="color: black; font-weight: normal; font-size: 15px;">首页</span></a>->
		<a href="#"><span
			style="color: black; font-weight: normal; font-size: 15px;">用户名修改</span></a>

		<br> <br>
	</h5>

	<fieldset>
		<legend>管理员个人信息</legend>

		<form action="/OVS/EditUserNameServlet" name="myform" method="post"
			onsubmit="return check_NotNull()">
			<table width="715" height="192" border="0">
				<tr>
					<th width="300" scope="col"><center>&nbsp;</center></th>
					<th width="300" scope="col"><center>&nbsp;</center></th>
					<th width="300" scope="col"><center>&nbsp;</center></th>
				</tr>

				<tr>
					<td><div align="center">
							<font color="red">账号</font>
						</div></td>
					<td><div align="center">
							<font color="black"><%=admcurrentUser.getAdminID()%></font>
						</div></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><div align="center">
							<font color="red">用户名</font>
						</div></td>
					<td><div align="center">
							<input type="text" name="AdminName" id="AdminName"
								value="<%=admcurrentUser.getName()%>"
								onkeyup="this.value=this.value.replace(/[, ]/g,'')">
						</div></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><input type="reset" value="重填"></td>
					<td><input type="submit" value="提交"></td>
					<td><input type="hidden" name="AdminID"
						value="<%=admcurrentUser.getAdminID()%>"></td>
				</tr>
			</table>
		</form>
	</fieldset>

</div>
</body>
</html>