<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.ovs.model.*"%>
<%@ page import="com.ovs.beans.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>网上投票系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../admin.css" rel="stylesheet" type="text/css" /> 
  <script src="/OVS/js/clock.js"></script>
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
	String VoteID = request.getParameter("VoteID");
	Vote currentVote = new Vote();
	ArrayList<VoteEvent> currentVoteEvent=new ArrayList<VoteEvent>();
  	VoteInfoBean myVoteBean = new VoteInfoBean();
  	currentVote = myVoteBean.getVote(VoteID);
  	currentVoteEvent=myVoteBean.getVoteEvent(VoteID);
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
			<a href="admin_index.jsp"><span style="color:black;font-weight:normal;font-size:15px;">首页</span></a>
			<%out.print("->"); %><a href="admin_vote_list.jsp"><span style="color: black; font-weight: normal; font-size: 15px;">投票管理</span></a>
			<%out.print("->"); %><a href="#"><span style="color: black; font-weight: normal; font-size: 15px;">确认删除界面</span></a>
		</h5>

		</br>
		
		<form action="/OVS/DeleteVoteServlet" name="myform" method="post"
onSubmit="javascript:return window.confirm('确认删除<%=currentVote.getVoteName()%>吗？')">
		<fieldset>
			<legend>投票事件内容</legend>
			<table width="715" height="192" border="1">
				<tr>
					<td>
						<p>
							<span style="color: black; font-weight: normal; font-size: 15px;">
								<%=currentVote.getVoteContent() %> </span>
						</p>
					</td>
				</tr>
			</table>
			<table>
				<%
				if(currentVoteEvent==null){
					out.println("<tr><td>选项：</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>");
					out.println("<td><p style=\"color:red\"><big>暂无选项。点击<a href=\"admin_vote_addOption.jsp?VoteID="+VoteID+"&AdminID="+admcurrentUser.getAdminID()+"\">添加</a>。</big></p></td></tr>");}
				else{
					out.println("<tr><td>选项：</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>");
					out.println("<td>&nbsp;</td></tr>");
					for(int i=0;i<currentVoteEvent.size();i++){
						out.println("<tr><td>&nbsp;</td>");
						out.println("<td><p style=\"color:red\"><big>"+currentVoteEvent.get(i).getOptionContent()+"</big></p></td>");
						out.println("<td>&nbsp;</td><td>&nbsp;</td>");
						out.println("<td>&nbsp;</td><td>&nbsp;</td>");
					}
				}
				%>
				<tr><td><a href="/OVS/admin_vote_list.jsp"><input type="button" value="返回" onclick="window.location.href('admin_vote_list.jsp')"/></a></td>
				<td><input type="submit" value="删除"/></td>
				<td><input type="hidden" name="VoteID" value=<%=VoteID %>/></td><td>&nbsp;</td></tr>
			</table>
		</fieldset>
		</form>
	</div>
</body>
</html>
