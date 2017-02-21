<%@ page language="java" contentType="text/html; charset=gb2312"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>网上投票系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../admin.css" rel="stylesheet" type="text/css" /> 
  <script src="/OVS/js/clock.js"></script>
</head>

<jsp:useBean id="admcurrentUser" class="com.ovs.model.Admin" scope="session"/>

<script type="text/javascript">
function check_NotNull(){
	var noticeTitle = document.getElementById("noticeTitle").value;
	var noticeContents=document.getElementById("noticeContents").value;
	var strnoticeTitle=noticeTitle.replace(/(^\s*)|(\s*$)/g,'');
	var strnoticeContents=noticeContents.replace(/(^\s*)|(\s*$)/g,'');
	
	if(noticeTitle==""||noticeTitle==null||strnoticeTitle==""){
		alert("标题不能为空！！！！");
		return false;
	}else if(noticeContents==""||noticeContents==null||strnoticeContents==""){
		alert("内容不能为空！！！！");
		return false;
	}
}
</script>

<body onload="startTime()">
<%
	if(admcurrentUser.getAdminID()==null){
  		admcurrentUser.setAdmin(session.getAttribute("userID").toString());
  	}
  	if(admcurrentUser.getAdminID()== null){
		out.println("<script type=\"text/javascript\">");
		out.println("var flag = confirm(\"亲爱的用户，您没有登录管理员角色，是否立即登录？\");");
		out.println("if(flag==true){");
		out.println("window.open('/CSIOSystem/login/login.jsp','_self','');");
		out.println("}else{");
		out.println("window.open('/CSIOSystem/login/checkOut.jsp','_self','');");
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
				</c> <d><%=admcurrentUser.getName() %></d> <d id="txt"></d> <a
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
			<a><span
				style="color: black; font-weight: normal; font-size: 15px;">您当前的位置：</span></a><a
				href="admin_notice_list.jsp"><span
				style="color: black; font-weight: normal; font-size: 15px;">通知公告</span></a><<a
				href="admin_notice_issue.jsp"><span
				style="color: black; font-weight: normal; font-size: 15px;">发布公告</span></a>
		</h5>
		<hr />
			<form action="/OVS/StoreNoticeServlet" name="adminnotice" onSubmit="return check_NotNull()" method="post" enctype="multipart/form-data">
		<table>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>标题</td>
					<td>:</td>
					<td><input type="text" name="noticeTitle" id="noticeTitle" onkeyup="this.value=this.value.replace(/[, ]/g,'')"/><div id="title_tip" style="display:none" >*锟斤拷锟芥�锟斤拷娑����/div></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>内容</td>
					<td>:</td>
					<td><textarea rows="10" name="noticeContents" id="noticeContents" cols="50"></textarea></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>附件</td>
					<td>:</td>
					<td>
						<div class="buttonarea">
							<input type="file" value="file1" name="file1">
						</div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><input type="submit" value="提交" class="buttonStyle"> </td>
				</tr>
		</table>
			</form>



	</div>
</body>
</html>
