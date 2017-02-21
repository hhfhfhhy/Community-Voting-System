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
function check_NewPWD(){
	var check1=document.getElementById("newPWD1").value;
	var check2=document.getElementById("newPWD2").value;
	if(check1!=check2){
		alert("新旧密码不同！！！！");
		return false
	}
}

function check_OldPWD(){
	var check1=document.getElementById("checkOldPWD").value;
	var check2=document.getElementById("oldPWD").value;
	if(check1!=check2){
		alert("旧密码错误！！！！");
		return false
	}
}

function check_Invalid(){
	var oldPWD = document.getElementById("oldPWD").value;
	var newPWD1=document.getElementById("newPWD1").value;
	var newPWD2=document.getElementById("newPWD2").value;;
	var stroldPWD=oldPWD.replace(/(^\s*)|(\s*$)/g,'');
	var strnewPWD1=newPWD1.replace(/(^\s*)|(\s*$)/g,'');
	var strnewPWD2=newPWD2.replace(/(^\s*)|(\s*$)/g,'');
	
	if(oldPWD==""||oldPWD==null||stroldPWD==""){
		alert("旧密码不能为空！！！！");
		return false;
	}else if(newPWD1==""||newPWD1==null||strnewPWD1==""){
		alert("新密码不能为空！！！！");
		return false;
	}else if(newPWD2==""||newPWD2==null||strnewPWD2==""){
		alert("再次输入不能为空！！！！");
		return false;
	}
	 check_OldPWD();
	 check_NewPWD();
}


</script>
<jsp:useBean id="admcurrentUser" class="com.ovs.model.Admin" scope="session"/>
<%
	if(admcurrentUser.getAdminID()==null){
  		admcurrentUser.setAdmin(session.getAttribute("userID").toString());
  	}
  	if(admcurrentUser.getAdminID()== null){
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
			<span
				class="right" style="font-size: 15px; font-style: normal;"> <c>你好！
				</c> <d><jsp:getProperty property="name" name="admcurrentUser"/> </d> <d id="txt"></d> <a
				href="/OVS/login/checkOut.jsp"> <b>退出</b>
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
			<a><span style="color: black; font-weight: normal; font-size: 15px;">您当前的位置：</span></a>
			<a href="admin_index.jsp"><span style="color:black;font-weight:normal;font-size:15px;">首页</span></a>->
			<a href="#"><span style="color: black; font-weight: normal; font-size: 15px;">修改密码</span></a>
			
			<br><br>
</h5>

		<fieldset>
			<legend>管理员密码修改</legend>
			<form action="/OVS/ChangeAdminPWDServlet" name="myform" method="post" onsubmit="return check_Invalid()">
			<table width="715" height="192" border="0">
				<tr>
					<th width="300" scope="col"><center>&nbsp;</center></th>
					<th width="300" scope="col"><center>&nbsp;</center></th>
					<th width="300" scope="col"><center>&nbsp;</center></th>
				</tr>
				<tr>
					<td><center>旧的密码：</center></td>
					<td><center><input type="password" name="oldPWD" id="oldPWD" onkeyup="this.value=this.value.replace(/[, ]/g,'')"></center></td>
					<td><center>&nbsp;</center></td>
				</tr>
				<tr>
					<td><center>新的密码：</center></td>
					<td><center><input type="password" name="newPWD1" id="newPWD1" onkeyup="this.value=this.value.replace(/[, ]/g,'')"></center></td>
					<td><center>&nbsp;</center></td>
				</tr>
				<tr>
					<td><center>再次输入新的密码：</center></td>
					<td><center><input type="password" name="newPWD2" id="newPWD2" onkeyup="this.value=this.value.replace(/[, ]/g,'')"></center></td>
					<td><center>&nbsp;</center></td>
				</tr>
				<%Login log=new Login();
				Login currentAdmin=log.getLoginInfo(admcurrentUser.getAdminID());
				String checkOldPWD=currentAdmin.getPWD();%>
				<tr>
					<td><center><input type="reset" value="重置"></center></td>
					<td><center><input type="hidden" name="AdminID" value="<%=admcurrentUser.getAdminID()%>">
					<input type="hidden" id="checkOldPWD" name="checkOldPWD" value="<%=checkOldPWD%>"></center></td>
					<td><center><input type="submit" value="提交"></center></td>
				</tr>				
			</table>
			</form>
		</fieldset>

	</div>
</body>
</html>