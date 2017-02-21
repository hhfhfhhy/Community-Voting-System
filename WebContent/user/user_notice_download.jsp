<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.ovs.model.*"%>
<%@ page import="com.ovs.beans.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.jspsmart.upload.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

  <%
  	String attachmentAddress = request.getParameter("attachmentAddress");
  %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>附件下载</title>
</head>
<body>
<%

try{
SmartUpload su=new SmartUpload();
su.initialize(pageContext);
//这句话是为了防止浏览器自动打开文件
su.setContentDisposition(null);
out.println(attachmentAddress+"<br>");
su.downloadFile(attachmentAddress);
}catch(Exception e){
	//out.clear();
	e.printStackTrace();
	out.println("文件下载出错了，可能是文件不存在<br>");
}

out.println("<input type=\"button\" value=\"点击返回公告\" onclick=\"window.location.href('user_notice_list.jsp')\">");
%>
</body>
</html>