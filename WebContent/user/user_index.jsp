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
/*   	if(logcurrentUser.getUserID()==null){
  		 String userID=request.getParameter("userID");
  		 logcurrentUser.setUser(userID);
  		 logcurrentUser.setUser(session.getAttribute("userID").toString()); 
  	} */
  	logcurrentUser.setUser(session.getAttribute("userID").toString()); 
  	System.out.println("userID为：  "+logcurrentUser.getUserID());
  	%>
  <header>
    <div id="sitebranding">
      <h1><font face="楷体" size="10">网上投票系统</font></h1>
    </div>
    <div id="tagline">
      <p>
      <span class="right" style="font-size:15px;font-style:normal;">
      		<c>你好！</c> 
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
    <h5>
		<a><span style="color:black;font-weight:normal;font-size:15px;">您当前的位置：</span></a>->
		<a href="user_index.jsp"><span style="color:black;font-weight:normal;font-size:15px;">首页</span></a>->
		<a href="user_index.jsp"><span style="color:black;font-weight:normal;font-size:15px;">投票事件</span></a>
	</h5>
		<table width="715" height="192" border="1" align="right">
	<%
     	//String pageName = "user_index.jsp:";
	
		VoteInfoBean vib=new VoteInfoBean();
		ArrayList<Vote> alv=new ArrayList<Vote>();
		alv=vib.getallVoteFromcreatevote();
		UserVoteBean uvb=new UserVoteBean();
		
		if(alv == null)
		{
			   out.println("<tr><td colspan='3'>数据库中还没有任何投票事件！！！</td></tr>");
		   } 
		else{
			out.println("<tr>");
			out.println("<td><p style=\"color:red\"><big>"+"主题"+"</big></p></td>");
			out.println("<td><p><big>发布时间</big></p></td>");
			out.println("<td><p><big>投票情况</big></p></td>");
			out.println("<td><p><big>查看结果</big></p></td>");
			out.println("</tr>");
			for(int i=0;i<alv.size();i++){				
				out.println("<tr>");
				out.println("<td><a href=\"user_vote_voting.jsp?VoteID="+alv.get(i).getVoteID()+"\"><p style=\"color:red\"><big>"+alv.get(i).getVoteName()+"</big></p></a></td>");
				out.println("<td><p><big>"+alv.get(i).getStart()+"</big></p></td>");
				if(uvb.CheckUserVote(alv.get(i).getVoteID(),logcurrentUser.getUserID())){
					out.println("<td><p><big>已投票</big></p></td>");
					out.println("<td><p><big><a href=\"user_vote_check.jsp?result=0&VoteID="+alv.get(i).getVoteID()+"\">点击查看投票结果</a></big></p></td>");
				}else{
					out.println("<td><p><big><font color=\"red\">未投票</font></big></p></td>");
					out.println("<td><p><big></big></p></td>");
				}
				out.println("</tr>");				
			}
			
		}
	%>
  </table>
  </div>
   
<script type="text/javascript" src="js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</body>
</html>