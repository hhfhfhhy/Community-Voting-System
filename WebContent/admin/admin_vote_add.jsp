<%@page import="com.ovs.db.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ovs.model.*"%>
<%@ page import="com.ovs.beans.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String result = request.getParameter("result");
%>
<script type="text/javascript">
	function check_result() {
		startTime();
		var result =
<%=result%>
	;
		if (result == "1") {
			alert("课程添加成功！！");
		} else if (result == "0") {
			alert("课程添加失败！！");
		}
	}
</script>
<script type="text/javascript">
	function check_addCourse() {

		//从表单获得数据，以备验证
		var name = document.getElementById("name").value;
		var courseID = document.getElementById("courseID").value;

		//这个alert 供调试用，显示提问的 title和askContents
		alert("添加的投票名为" + name + "\n投票事件ID为" + courseID);

		//逻辑验证
		if (name == '' || courseID == '') { //输入框为空
			alert("输入框不能为空！请重新输入！");
			return false;
		}
	}
</script>
<head>
<title>网上投票系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<link href="../admin.css" rel="stylesheet" type="text/css" />
<script src="/OVS/js/clock.js"></script>
</head>

<jsp:useBean id="admcurrentUser" class="com.ovs.model.Admin"
	scope="session" />

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
		<p>
			<span class="right" style="font-size: 15px; font-style: normal;">
				<c>你好！ </c> <d><jsp:getProperty property="name"
					name="admcurrentUser" /> </d> <d id="txt"></d> <a
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
				style="color: black; font-weight: normal; font-size: 15px;">您当前的位置：</span></a>
			<a href="admin_vote_list.jsp"><span
				style="color: black; font-weight: normal; font-size: 15px;">投票管理</span></a>>
			<a href="#"><span
				style="color: black; font-weight: normal; font-size: 15px;">添加投票事件</span></a>
		</h5>

		<form action="/OVS/AddVoteServlet" name="myform" method="post">
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
					<td>标题或者主题</td>
					<td>：</td>
					<td><input type="text" name="name" id="name" value="无主题"></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>是否多选</td>
					<td>：</td>
					<td><input type="radio" name="status" id="status" value="y">是
						<input type="radio" name="status" id="status" value="n">否
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>事件描述</td>
					<td>:</td>
					<td><textarea rows="10" name="VoteContent" cols="50"></textarea></td>
				</tr>



<!-- 				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>选项内容</td>
					<td>A：</td>
					<td><textarea rows="3" cols="50" name="OptionContent"></textarea>
					</td>
				</tr> -->

				<%-- <tr>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>授课教师</td>
<td>：</td>
<td>
<select name="teacherID" id="teacherID">
<%	
		String pageName = "admin_course_add.jsp:------------------------\n";
		

		System.out.println(pageName+"显示所有老师");
		ArrayList<Teacher> allTeacher = new ArrayList<Teacher>();
	
		allTeacher = allTeacherInfo.getallTeacherFromteacher();
		for(int i=0;i<allTeacher.size();i++){
						
			out.println("<option value=\""+allTeacher.get(i).getTeacherID()+"\">"+allTeacher.get(i).getName()+"</option>");
						
		}
%>
</select>
</td>
</tr> --%>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>

					<td>
						<div class="buttonarea">
						<input type="hidden" name="admin" value="<%=admcurrentUser.getAdminID()%>"/>
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