<%@page import="com.ovs.db.*"%>
<%@ page language="java" contentType="text/html; charset=gb2312"
	pageEncoding="UTF-8"%>
<%@ page import="com.ovs.model.*"%>
<%@ page import="com.ovs.beans.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上投票系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/OVS/admin.css" rel="stylesheet" type="text/css" />
<script src="/OVS/js/clock.js"></script>
</head>
<jsp:useBean id="admcurrentUser" class="com.ovs.model.Admin"
	scope="session">
</jsp:useBean>
<%
	if (admcurrentUser.getAdminID() == null) {
		admcurrentUser.setAdmin(session.getAttribute("userID")
				.toString());
	}
	System.out.println("在主页展示时依据的管理员userID为：  "
			+ admcurrentUser.getAdminID());
%>

<script type="text/javascript">
	function check_NotNull() {
		var noticeTitle = document.getElementById("noticeTitle").value;
		var noticeContents = document.getElementById("noticeContents").value;
		var strnoticeTitle = noticeTitle.replace(/(^\s*)|(\s*$)/g, '');
		var strnoticeContents = noticeContents.replace(/(^\s*)|(\s*$)/g, '');

		if (noticeTitle == "" || noticeTitle == null || strnoticeTitle == "") {
			alert("标题不能为空！！！！");
			return false;
		} else if (noticeContents == "" || noticeContents == null
				|| strnoticeContents == "") {
			alert("内容不能为空！！！！");
			return false;
		}
	}
</script>

<script type="text/javascript">
	function check_resetPWD() {
		var resetUserID = document.getElementById("resetUserID").value;

		alert("要重置的用户账号：" + resetUserID);
		if (resetUserID == "") {
			alert("输入框不能为空！！！！");
			document.getElementById("resetUserID").value = '';
			return false;
		}
		document.myform.submitAnswer.disabled = true;
	}
</script>
<script type="text/javascript">
	function check_result() {
		startTime();
		var result =
<%=request.getParameter("insertResult")%>
	;
		if (result == "0") {
			alert("抱歉，通知发布失败！");
		} else if (result == "1") {
			alert("通知发布成功！");
		}
	}
	function check_vote_name() {
		var name = document.getElementById("VoteName").value;
		if (name == ""||name==null) {
			alert("搜索的投票事件名称不能为空，请重新输入！");
			return false;
		} else {
			return true;
		}
	}
	function check_vote_id(){
		var ID = document.getElementById("VoteID").value;
		if(ID==""||ID==null){
			alert("搜索的投票事件编号不能为空，请重新输入！");
			return false;
		} else{
			return true;
		}
	}
	function getRole() {
		var role = document.getElementById("role").value;
		var roleByID = document.getElementById("roleByID").value;
		var roleByName = document.getElementById("roleByName").value;
		if (role == null) {
			alert("必须选定角色");
			role.focus();
			return false;
		} else if ("tea".equals(role.value)) {
			roleByID.value = "tea";
			roleByName.value = "tea";
			return true;
		} else if ("stu".equals(role.value)) {
			roleByID.value = "stu";
			roleByName.value = "stu";
			return true;
		}
	}
	function check_searchByName() {
		var teaByName = document.getElementById("teaByName").value;
		var stuByName = document.getElementById("stuByName").value;
		if (teaByName.checked == false && stuByName.checked == false) {
			alert("必须选定查找角色");
			return false;
		}
		return true;
	}
	function check_searchByID() {
		var teaByID = document.getElementById("teaByID").value;
		var stuByID = document.getElementById("stuByID").value;
		if (teaByID.checked == false && stuByID.checked == false) {
			alert("必须选定查找角色");
			return false;
		}
		return true;
	}
</script>

<body onload="check_result()">
	<%
		if (admcurrentUser.getAdminID() == null) {
			admcurrentUser.setAdmin(session.getAttribute("userID")
					.toString());
		}
		if (admcurrentUser.getAdminID() == null) {
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
		<!--     <p>By reading we enrich the mind, by conversation we polish it! -->
		<span class="right" style="font-size: 15px; font-style: normal;">
			<c>你好！ </c> <d><%=admcurrentUser.getName()%></d> <d id="txt"></d> <a
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
			<p>
				<a><span
					style="color: black; font-weight: normal; font-size: 15px;">您当前的位置：</span></a>
				<a href="#"><span
					style="color: black; font-weight: normal; font-size: 15px;">首页</span></a>
				<span class="right"
					style="color: black; font-weight: normal; font-size: 15px;">
					<%
						if ("open".equals(application.getAttribute("state"))) {
							out.println("<form action=\"/CSIOSystem/ChangeSystemStateServlet\" method=\"post\">");
							out.println("<input type=\"hidden\" id=\"state\" name=\"state\" value=\"lock\"/>");
							out.println("<input type=\"submit\" id=\"lockSubmit\" name=\"lockSubmit\" value=\"封锁系统\" class=\"buttonStyle\"/>");
							out.println("</form>");
						} else {
							out.println("<form action=\"/CSIOSystem/ChangeSystemStateServlet\" method=\"post\">");
							out.println("<input type=\"hidden\" id=\"state\" name=\"state\" value=\"open\"/>");
							/* out.println("<input type=\"submit\" id=\"lockSubmit\" name=\"lockSubmit\" value=\"开放系统\" class=\"buttonStyle\"/>"); */
							out.println("</form>");
						}
					%>
				</span>
			</p>
		</h5>
		<table width="1050" height="420" border="2" bordercolor="#BED8F3">
			<tr>
				<!-- 这是发通知的模块 -->
				<th width="600" scope="col">
					<form action="/OVS/StoreNoticeServlet" name="adminnotice"
						onSubmit="return check_NotNull()" method="post"
						enctype="multipart/form-data">
						<table>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>标题</td>
								<td>:</td>
								<td><input type="text" name="noticeTitle" id="noticeTitle"
									onkeyup="this.value=this.value.replace(/[, ]/g,'')" />
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>内容</td>
								<td>:</td>
								<td><textarea rows="10" name="noticeContents"
										id="noticeContents" cols="50"></textarea></td>
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
								<td><input type="submit" value="提交" class="buttonStyle">
								</td>
							</tr>
						</table>
					</form>
				</th>

				<!-- 这是投票事件搜索的模块 -->
				<th width="600" scope="col">
					<table>
						<tr>
							<td><strong>搜索投票事件:</strong></td>
						</tr>
						<!-- <tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>事件名称</td>
							<td>：</td>
							<td>
								<form action="admin_vote_search.jsp" method="post" onsubmit="return check_vote_name()">
									<input type="text" name="VoteName" id="VoteName" /> <input
										type="hidden" name="identifier" id="identifier" value="byName"></input>
									<input type="submit" value="按名称查询" class="buttonStyle"
										id="searchByNamebt" name="searchByNamebt" />
								</form>
							</td>
						</tr> -->
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>事件编号</td>
							<td>：</td>
							<td>
								<form action="admin_vote_search.jsp" method="post" onsubmit="return check_vote_id()">
									<input type="text" name="VoteID" id="VoteID" /> <input
										type="hidden" name="identifier" id="identifier" value="byID"></input>
									<input type="submit" value="查询" class="buttonStyle"
										id="searchByIDbt" name="searchByIDbt" />
								</form>
							</td>
						</tr>
					</table>
				</th>
			</tr> 

			<tr>
				<td>
					<!-- 一键重置的模块 -->
					<form action="/OVS/ResetPWDServlet" onSubmit="return check_resetPWD()" method="post">
						<table>
							<tr>
								<td><strong>重置密码:</strong></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td><strong>账号</strong></td>
								<td>：</td>
								<td><input type="text" name="resetUserID" id="resetUserID"></td>
							</tr>

							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>
									<div class="buttonarea">
										<input type="submit" value="一键重置" class="buttonStyle" />
									</div>
								</td>
							</tr>
						</table>
					</form>
				</td>

				<td>
					<!-- 搜索用户模块 -->
					<table>
						<%-- <tr>
						<td><strong>搜索用户:</strong></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>
							<input type="radio" name="role" id="role" value="tea" />老师
						    <input type="radio" name="role" id="role" value="stu" />学生
						</td>
						<td>&nbsp;</td>
					</tr> --%>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<!-- <td><strong>用户名</strong></td>
							<td>：</td>
							<td>
								<form action="admin_id_search.jsp" method="post"
									onSubmit="return check_searchByName()">
									<input type="text" name="userName" id="userName" /> <input
										type="hidden" name="roleByName" id="roleByName" /> <input
										type="hidden" name="identifier" id="identifier" value="byName"></input>
									<input type="radio" name="role" id="teaByName" value="tea"
										checked />管理员 <input type="radio" name="role" id="stuByName"
										value="stu" />用户 <input type="submit" value="按名称查询"
										class="buttonStyle" />
								</form>
							</td> -->
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<!-- <td><strong>用户编号</strong></td>
							<td>：</td>
							<td>
								<form action="admin_id_search.jsp" method="post"
									onSubmit="return check_searchByID()">
									<input type="text" name="userID" id="userID" /> <input
										type="hidden" name="roleByID" id="roleByID" /> <input
										type="hidden" name="identifier" id="identifier" value="byID"></input>
									 <input type="submit" value="查询"
										class="buttonStyle" />
								</form>
							</td> -->
						</tr>
					</table>

				</td>
			</tr>
		</table>
	</div>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
</body>
</html>
