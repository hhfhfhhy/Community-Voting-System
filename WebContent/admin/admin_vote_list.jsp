<%@page import="com.ovs.db.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ovs.model.*" %>
<%@ page import="com.ovs.beans.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String state = request.getParameter("state");
%>
<script type="text/javascript">
function check_state()
{
	startTime();
	var state = <%=state%>;
	if(state=="1"){
		alert("课程删除成功！！");
		}else if(state=="0"){
			alert("课程删除失败！！");
			}
}
</script> 
  <script src="/OVS/js/clock.js"></script>
<head>
  <title>网上投票系统</title>
  <meta http-equiv="Content-Type" content="text/html; charset=gbk" />
  <link href="../admin.css" rel="stylesheet" type="text/css" />  
</head>
<jsp:useBean id="admcurrentUser" class="com.ovs.model.Admin" scope="session"/>
<%
	String adminID = session.getAttribute("userID").toString();
	//System.out.println("在管理员投票管理主页展示时依据的userID为：  "+session.getAttribute("userID").toString());
%>
<jsp:useBean id="allVoteInfo" class="com.ovs.beans.VoteInfoBean" scope="session"></jsp:useBean>

    <div id="sitebranding">
      <h1><font face="楷体" size="10">网上投票系统</font></h1>
    </div>
    <div id="tagline">
    <p>
     	<span class="right" style="font-size:15px;font-style:normal;">
     		<c>你好！ </c>
     		<d><jsp:getProperty property="name" name="admcurrentUser"/>  </d> 
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
	<p><a><span style="color:black;font-weight:normal;font-size:15px;">您当前的位置：</span></a>
	<a href="admin_index.jsp"><span style="color:black;font-weight:normal;font-size:15px;">首页></span></a>
	<a href="#"><span style="color:black;font-weight:normal;font-size:15px;">投票管理</span></a></p>
	
	</h5>
	
	<table width="365" align="left">
					 <tr>
						<td><strong>搜索事件:</strong></td>
					</tr> 
					<tr><td>&nbsp;</td></tr>
					<tr>
						
						<td>
							<form action="admin_vote_search.jsp" method="post" >
								<input type="text" name="VoteName" id="VoteName"/>
								<input type="hidden" name="identifier" id="identifier" value="byName" ></input>
								<input type="submit" value="按名称查询" class="buttonStyle" id="searchByNamebt" name="searchByNamebt" />
							</form>
						</td>

					 </tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					
					<tr> 
						<td>
							<form action="admin_vote_search.jsp" method="post" >
								<input type="text" name="VoteID" id="VoteID"/>
								<input type="hidden" name="identifier" id="identifier" value="byID" ></input>
								<input type="submit" value="按编号查询" class="buttonStyle" id="searchByIDbt" name="searchByIDbt" />
							</form>
						</td>
						<tr>
						<tr>
						<td>&nbsp;</td>
					</tr>
					
						<td><a href="admin_vote_add.jsp"><input type="button"  onclick="window.open('admin_vote_add.jsp')"
						value="添加投票事件"/></a></td> 
						</tr>
					</tr>
				</table>

	<table width="715" height="192" border="1" align="right">
	<%
		ArrayList<Vote> allVote = new ArrayList<Vote>();
     	allVote = allVoteInfo.getallVoteFromcreatevote(); 
		
		if(allVote == null)
		{
			   out.println("<tr><td colspan='3'>暂时没有任何投票事件！！！点击创建</td></tr>");
		   } 
		else{
			out.println("<tr>");
			out.println("<td><p style=\"color:red\"><big>"+"主题"+"</big></p></td>");
			out.println("<td><p><big>"+"发布时间"+"</big></p></td>");
			out.println("<td><p><big>&nbsp;</big></p></td>");
			out.println("<td><p><big>&nbsp;</big></p></td>");
			out.println("</tr>");
			for(int i=0;i<allVote.size();i++){				
				out.println("<tr>");
				out.println("<td><a href=\"admin_vote_show.jsp?VoteID="+allVote.get(i).getVoteID()+"\"><p style=\"color:red\"><big>"+allVote.get(i).getVoteName()+"</big></p></a></td>");
				out.println("<td><p><big>"+allVote.get(i).getStart()+"</big></p></td>");
				out.println("<td><p><big><a href=\"admin_vote_delete.jsp?VoteID="
				+allVote.get(i).getVoteID()+"\">删除事件</a></big></p></td>");
				out.println("<td><p><big><a href=\"admin_vote_check.jsp?VoteID="
						+allVote.get(i).getVoteID()+"&result=0\">查看结果</a></big></p></td>");
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
  