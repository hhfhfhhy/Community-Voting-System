<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.ovs.model.*"%>
<%@ page import="com.ovs.beans.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html lang="en">
<head>
<title>网上投票系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../admin.css" rel="stylesheet" type="text/css" /> 
  <script src="/OVS/js/clock.js"></script>
<script language="javascript">
	function delcfm() {
		if (!confirm("确认要删除？")) {
			window.event.returnValue = false;
		}
	}
</script>
</head>

<jsp:useBean id="admcurrentUser" class="com.ovs.model.Admin" scope="session"/>

<body onload="startTime()">
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
				class="right" style="font-size: 15px; font-style: normal;"><c>你好！
				</c>
				<d><jsp:getProperty property="name" name="admcurrentUser"/> </d> <d id="txt"></d> <a href="/OVS/login/checkOut.jsp"> <b>退出</b>
			</a></span>
		</p>
	</div>
	</header>
	<nav>
	<%
	String InfoID = request.getParameter("InfoID");
  	Info currentInfo = new Info();
  	NotiInfoBean myNotiBean = new NotiInfoBean();
  	currentInfo = myNotiBean.getNotice(InfoID);
	%>
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
			<a href="admin_notice_list.jsp"><span style="color: black; font-weight: normal; font-size: 15px;">通知公告</span></a><!-- -> -->
			<%-- <a href="#"><span style="color: black; font-weight: normal; font-size: 15px;"><%=currentInfo.getInfoTitle()%></span></a> --%>
		</h5>

		</br>
		
					<table align="center">
				<tr>
					<td>
								<%=currentInfo.getInfoTitle() %>
					</td>
				</tr>
			</table>
		
		<br>
			
			<legend>公告内容</legend>
			<table width="715" height="192" border="1" align="center">
				<tr>
					<td>
						<p>
							<span style="color: black; font-weight: normal; font-size: 15px;">
								<%=currentInfo.getInfoContent() %> </span>
						</p>
					</td>
				</tr>
			</table>
			
			<table align="center">
				<tr>
					<td>
						<a href="admin_notice_editInfo.jsp?InfoID=<%=currentInfo.getInfoID()%>">修改内容</a>
					</td>
				</tr>
			</table>

			<table>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>

					<td>
							<%
							if(currentInfo.getAttachmentAddress()==null){
						 		out.println("<input type=\"button\" value=\"返回\" class=\"buttonStyle\" onclick=\"window.location.href('admin_notice_list.jsp')\">");
						 	}
						 	else{
						 		out.println("<input type=\"button\" value=\"返回\" class=\"buttonStyle\" onclick=\"window.location.href('admin_notice_list.jsp')\">");
						 		out.print("<input type=\"button\" value=\"附件下载\" class=\"buttonStyle\" onclick=\"window.location.href('admin_notice_download.jsp?attachmentAddress=");
						 		out.print(currentInfo.getAttachmentAddress()); 
						 		out.print("')\">");
						 	}
							%></td>
				</tr>
			</table>
	</div>
</body>
</html>
