<%@page import="com.ovs.db.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ovs.model.*" %>
<%@ page import="com.ovs.beans.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>网上投票系统</title>
  <meta http-equiv="Content-Type" content="text/html; charset=gbk" />
  <link href="../admin.css" rel="stylesheet" type="text/css" />  
</head>
<jsp:useBean id="admcurrentUser" class="com.ovs.model.Admin" scope="session" >
  </jsp:useBean>
  
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
      <h1><font face="楷体" size="10">网上投票系统</font></h1>
    </div>
    <div id="tagline">
    <span class="right" style="font-size:15px;font-style:normal;">
     		<c>你好！ </c>
     		<d><%=admcurrentUser.getName() %></d> 
     		<d id="txt"></d>
     		<a href="/OVS/login/checkOut.jsp">  <b>退出</b> </a>
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
		<p><span style="color:black;font-weight:normal;font-size:15px;">您当前的位置：</span>->
			<a href="admin_index.jsp"><span style="color:black;font-weight:normal;font-size:15px;">首页</span></a>->
			<a href="#"><span style="color:black;font-weight:normal;font-size:15px;">查询投票事件</span></a>
		</p>
	</h5>
	
	<%
		System.out.println("admin_course_search.jsp------------------------------------------\n");
		
		String identifier = new String(request.getParameter("identifier").getBytes("ISO-8859-1"), "UTF-8");
		ArrayList<Vote> resultVote = new ArrayList<Vote>(); 
		VoteInfoBean myVoteBean = new VoteInfoBean(); 
		String searchItem = null;
		
		if("byName".equals(identifier)){
			String VoteName = new String(request.getParameter("VoteName").getBytes("ISO-8859-1"),"UTF-8");
			searchItem = VoteName;
			resultVote = myVoteBean.searchVote(identifier, searchItem);
		}else if("byID".equals(identifier)){
			String VoteID = request.getParameter("VoteID");
			searchItem = VoteID;
			resultVote = myVoteBean.searchVote(identifier, searchItem);
		}
		
		if(resultVote==null||resultVote.size()==0){
			out.println("<center>没有找到与'"+searchItem+"'相关的投票事件</center>");
		}else{		
		out.println("查找结果：");
		out.println("<center>");
		out.println("<table border='1px'>");
		out.println("<tr><td>标题</td><td>编号</td></tr>");
		for(int i=0;i<resultVote.size();i++){
			out.println("<tr>");
			out.println("<td><a href=\"admin_vote_show.jsp?VoteID="+resultVote.get(i).getVoteID()+"\">"+resultVote.get(i).getVoteName()+"</a></td>");
			out.println("<td>"+resultVote.get(i).getVoteID()+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</center>");
		}
		
	%>
	
  </div>
<script type="text/javascript" src="js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</body>
</html>	