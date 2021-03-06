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
<jsp:useBean id="admcurrentUser" class="com.ovs.model.User"
	scope="session" />
<%
admcurrentUser.setUser(session.getAttribute("userID").toString());
/*   	if(admcurrentUser.getUserID()== null){
		out.println("<script type=\"text/javascript\">");
		out.println("var flag = confirm(\"亲爱的用户，您没有登录管理员角色，是否立即登录？\");");
		out.println("if(flag==true){");
		out.println("window.open('/OVS/login/login.jsp','_self','');");
		out.println("}else{");
		out.println("window.open('/OVS/login/checkOut.jsp','_self','');");
		out.println("}");
		out.println("</script>"); 
	} */
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
			<c>你好！ </c> <d><%=admcurrentUser.getUserName() %> </d> <d id="txt"></d> <a
			href="/OVS/login/checkOut.jsp"> <b>退出</b>
		</a>
		</span>
	</p>
</div>
</header>
<nav>
<ul>
	<li><a href="user_index.jsp">投票事件</a></li>
	<li><a href="user_notice_list.jsp">通知公告</a></li>
	<li><a href="user_info_show.jsp">个人信息</a></li>
</ul>
</nav>
<div id="bodycontent">
	<h5>
		<a><span
			style="color: black; font-weight: normal; font-size: 15px;">您当前的位置：</span></a>
		<a href="user_index.jsp"><span
			style="color: black; font-weight: normal; font-size: 15px;">首页</span></a>->
		<a href="#"><span
			style="color: black; font-weight: normal; font-size: 15px;">个人信息</span></a>

		<br>
		<br>
	</h5>

	<fieldset>
		<legend>个人信息</legend>

		<table width="715" height="192" border="0">
			<tr>
					<th width="300" scope="col">&nbsp;</th>
					<th width="300" scope="col">&nbsp;</th>
					<th width="300" scope="col">&nbsp;</th>
					<th width="300" scope="col">&nbsp;</th>
					<th width="300" scope="col">&nbsp;</th>
<!-- 					<th width="300" scope="col"><center>&nbsp;</center></th> -->
			</tr>
			<%
			UserInfoBean myUserBean=new UserInfoBean();	
		    User myCurrentUser=new User();
					myCurrentUser = myUserBean.getCertainUser(admcurrentUser.getUserID());				 			
					if (myCurrentUser == null) {
						out.println("<tr><td colspan='3'>此用户信息丢失！！！</td></tr>");
					} else {
						out.println("<tr>");
						out.println("<td><div align=\"center\"><font color=\"red\">用户账号</font></div></td>");
						out.println("<td><font color=\"red\">用户名</font></td>"); 
						out.println("<td><font color=\"red\">有效证件</font></td>"); 
					 	out.println("<td><div align=\"center\"><font color=\"red\">注册日期</font></div></td>");
						out.println("<td><div align=\"center\"><font color=\"red\">安全问题</font></div></td>");
						/* out.println("<td><div align=\"center\"><font color=\"red\">安全问题答案</font></div></td>"); */
						out.println("</tr>");
						
						out.println("<tr>");
						out.println("<td><div align=\"center\"><font color=\"black\">"+myCurrentUser.getUserID()+"</font></div></td>");
						out.println("<td><font color=\"black\">"+myCurrentUser.getUserName()+"</font></td>"); 
						out.println("<td><font color=\"black\">"+myCurrentUser.getValidIdentity()+"</font></td>"); 
					 	out.println("<td><div align=\"center\"><font color=\"black\">"+myCurrentUser.getRegisterDate()+"</font></div></td>");
						out.println("<td><div align=\"center\"><font color=\"black\">"+myCurrentUser.getSecurityP()+"</font></div></td>");
						/* out.println("<td><div align=\"center\"><font color=\"black\">"+myCurrentUser.getSecurityA()+"</font></div></td>"); */
						out.println("</tr>");								
					}
		%>	
		<tr><td><a href="user_info_edit.jsp">修改个人信息</a></td><td></td><td></td><td></td><td></td>
		</tr>
		</table>
	</fieldset>

</div>
</body>
</html>