<%@page import="com.ovs.db.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ovs.model.*" %>
<%@ page import="com.ovs.beans.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上投票系统</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/OVS/admin.css" rel="stylesheet" type="text/css" /> 
  <script src="/CSIOSystem/js/clock.js"></script>
</head>
<body onload="startTime()">
  <jsp:useBean id="logcurrentUser" class="com.ovs.model.User" scope="session" >
  </jsp:useBean>
    <%   	
  	if(logcurrentUser.getUserID()==null){
  		logcurrentUser.setUser(session.getAttribute("userID").toString());
  	}
  	System.out.println("userID为：  "+logcurrentUser.getUserID());
  	%>	
  <header>
    <div id="sitebranding">
      <h1><font face="楷体" size="10">网上投票系统</font></h1>
    </div>
    <div id="tagline">
      <p>
      <span class="right" style="font-size:15px;font-style:normal;">
      		<c>你好！ </c> 
      		<d>  <%=logcurrentUser.getUserName() %> </d>   
      		<d id="txt" ></d> 
      		<b> <a href="/CSIOSystem/login/checkOut.jsp"> 退出</a></b> 
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
		<%
		String VoteID = request.getParameter("VoteID");
	%>
	<p>你已经投过票了。请不要重复尝试。<a href="user_index.jsp">点击</a>返回</p>
  </div>
   
<script type="text/javascript" src="js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</body>
</html>