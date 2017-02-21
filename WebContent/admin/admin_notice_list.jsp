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

<jsp:useBean id="admcurrentUser" class="com.ovs.model.Admin" scope="session"/>

<script type="text/javascript">

function check_result(){
	startTime();
	var result = <%=request.getParameter("insertResult")%>;
	if(result=="0"){
		alert("抱歉，通知发布失败！");
	}else if(result=="1"){
		alert("通知发布成功！");
	}
}
</script>

<body onload="check_result()">
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
			<a href="#"><span style="color: black; font-weight: normal; font-size: 15px;">通知公告</span></a>
			
			<br><br><a href="admin_notice_issue.jsp"><span class="right" style="color: black; font-weight: normal; font-size: 15px;">发布公告</span></a> 
		</h5>

		<fieldset>
			<legend>通知公告列表</legend>

			<table width="715" height="192" border="0">
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<%
					NotiInfoBean myNotiBean = new NotiInfoBean();
							
							int currPage = 1;
							int count=0;
							if(request.getParameter("page")!= null){
								currPage = Integer.parseInt(request.getParameter("page"));
							}
						
							int pages;
					  				ArrayList<Info> myNoti = new ArrayList<Info>();
							myNoti = myNotiBean.getTheNoti();
						  	
							if(myNoti==null)count=0;
							else	count = myNoti.size();
					
							myNoti = myNotiBean.findNoticeByPageAdmin(currPage);
							request.setAttribute("list", myNoti);
							if(count % Info.PAGE_SIZE == 0){
								pages = count / Info.PAGE_SIZE ; 
							}else {
								pages = count / Info.PAGE_SIZE +1;
							}
						System.out.println("count:"+count);
						System.out.println("pages:"+pages);
						System.out.println("Info.PAGE_SIZE:"+Info.PAGE_SIZE);
							StringBuffer sb = new StringBuffer();
							//通过循环构建分页条
							for(int i=1;i<=pages;i++){
								if(i == currPage){
									sb.append("【"+i+"】");
								}else{
									sb.append("<a href='admin_notice_list.jsp?page="+i+"'>"+i+"</a>");
								}
								sb.append("  ");
							}
							request.setAttribute("bar", sb.toString());
				 			

							if (myNoti == null) {
								out.println("<tr><td colspan='3'>暂无任何通知与公告</td></tr>");
							} else {
								for (int i = 0; i < myNoti.size(); i++) {
									out.println("<tr>");
									out.println("<td><div align=\"center\">【"
											+ myNoti.get(i).getInfoTime() + "】</div></td>");
									out.println("<td><a href=\"admin_notice_show.jsp?InfoID="
											+ myNoti.get(i).getInfoID() + "\"><u>"
											+ myNoti.get(i).getInfoTitle() + "</u></a></td>");
									out.println("<td><div align=\"center\"></div></td>");
									out.println("<td><div align=\"center\"></div></td>");
									out.println("<td><div align=\"center\"><a href=\"/OVS/DeleteInfoServlet?InfoID="+myNoti.get(i).getInfoID()+"\">删除</a></div></td>");
									out.println("</tr>");
								}
							}
				%>
				
				<tr>
 					<td align="center" colspan="5" >
 						<%=request.getAttribute("bar") %>
 					</td>
 				</tr>
 				
			</table>
		</fieldset>

	</div>
</body>
</html>