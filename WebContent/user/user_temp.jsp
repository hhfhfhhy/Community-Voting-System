<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@page import="com.ovs.beans.UserInfoBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户注册</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/OVS/style_login.css" rel="stylesheet" type="text/css" />
</head>
<%String userID=request.getParameter("userID"); 
UserInfoBean uib=new UserInfoBean();
String PWD=uib.getUserPWD(userID);
%>
<body>
<%out.println("你的登陆账号为"+userID+"<br>");
out.println("初始密码为"+PWD+"<br>");
out.println("现在开始<font color=\"red\"><a href=\"/OVS/login/login.jsp\">登陆</a></font>");
%>
</body>
</html>