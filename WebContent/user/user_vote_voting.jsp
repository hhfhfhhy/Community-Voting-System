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
<!--   <script type="text/javascript">
function check_Valid(){
	var length=document.getElementById("OptionCount").value;
	var i=0,count=0;
	for(i=0;i<length;i++){
		var UserOption=document.getElementById("UserOption"+i).value;
		if(UserOption==null){
			count++;
			}
	}
	if(count==length){
		alert("请至少选择一项！！！！");
		return false;
		document.getElementById("submit").disabled=true;
	}
}
</script> -->
    <div id="sitebranding">
      <h1><font face="楷体" size="10">网上投票系统</font></h1>
    </div>
    <div id="tagline">
      <p>
      <span class="right" style="font-size:15px;font-style:normal;">
      		<c>你好！ </c> 
      		<d>  <%=logcurrentUser.getUserName() %> </d>   
      		<d id="txt" ></d> 
      		<b> <a href="/OVS/login/checkOut.jsp"> 退出</a></b> 
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
		<a href="#"><span style="color:black;font-weight:normal;font-size:15px;">投票事件内容</span></a>
	</h5>
		<%
		String VoteID = request.getParameter("VoteID");
		Vote currentVote = new Vote();
		ArrayList<VoteEvent> currentVoteEvent=new ArrayList<VoteEvent>();
	  	VoteInfoBean myVoteBean = new VoteInfoBean();
	  	currentVote = myVoteBean.getVote(VoteID);
	  	currentVoteEvent=myVoteBean.getVoteEvent(VoteID);
	%>
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
	
<form action="/OVS/UserVoteServlet" name="myform" method="post"
onSubmit="javascript:return window.confirm('确认提交选项吗？')">
			<table>
				<%
				if(currentVoteEvent==null){
					out.println("<tr><td>选项：</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>");
					out.println("<td><p style=\"color:red\"><big>暂无选项。</big></p></td></tr>");}
				else{
					out.println("<tr><td>选项：</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>");
					out.println("<td>&nbsp;</td></tr>");
					if(currentVote.getStatus().equals("y")){				
					for(int i=0;i<currentVoteEvent.size();i++){
						out.println("<tr><td>&nbsp;</td>");
						out.println("<td><input type=\"checkbox\" id=\"UserOption"+i+"\" name=\"UserOption"+i+"\" value=\""+currentVoteEvent.get(i).getOptionSequence()+"\" />");
						out.println("<style=\"color:red\"><big>"+currentVoteEvent.get(i).getOptionContent()+"</big></td>");
						out.println("<td>&nbsp;</td><td>&nbsp;</td>");
						out.println("<td>&nbsp;</td><td>&nbsp;</td></tr>");
					}
					}
					if(currentVote.getStatus().equals("n")){
						for(int i=0;i<currentVoteEvent.size();i++){
							out.println("<tr><td>&nbsp;</td>");
							out.println("<td><input type=\"radio\" id=\"UserOption\" name=\"UserOption\" value=\""+currentVoteEvent.get(i).getOptionSequence()+"\" />");
							out.println("<style=\"color:red\"><big>"+currentVoteEvent.get(i).getOptionContent()+"</big></td>");
							out.println("<td>&nbsp;</td><td>&nbsp;</td>");
							out.println("<td>&nbsp;</td><td>&nbsp;</td></tr>");
						}
					}
				}
				%>
				<tr><td><input type="reset" value="重填"/></td>
				<td><input type="submit" id="submit" value="提交"/></td>
				<td><input type="hidden" id="OptionCount" name="size" value="<%=currentVoteEvent.size()%>"/>
				<input type="hidden" name="VoteID" value="<%=VoteID%>"/>
				<input type="hidden" name="UserID" value="<%=logcurrentUser.getUserID()%>"/>
				<input type="hidden" name="Status" value="<%=currentVote.getStatus()%>"/></td></tr>
			</table>
			</form>
		</fieldset>
  </div>
   
<script type="text/javascript" src="js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</body>
</html>