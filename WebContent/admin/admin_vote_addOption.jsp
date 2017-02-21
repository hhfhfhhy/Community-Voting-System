<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ovs.model.*"%>
<%@ page import="com.ovs.beans.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*,javax.sql.*,javax.naming.*"%>
<jsp:useBean id="admcurrentUser" class="com.ovs.model.Admin" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../admin.css" rel="stylesheet" type="text/css" />
<title>网上投票系统</title>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
<script language="javascript">
	function findObj(theObj, theDoc) {
		var p, i, foundObj;
		if (!theDoc)
			theDoc = document;
		if ((p = theObj.indexOf("?")) > 0 && parent.frames.length) {
			theDoc = parent.frames[theObj.substring(p + 1)].document;
			theObj = theObj.substring(0, p);
		}
		if (!(foundObj = theDoc[theObj]) && theDoc.all)
			foundObj = theDoc.all[theObj];
		for (i = 0; !foundObj && i < theDoc.forms.length; i++)
			foundObj = theDoc.forms[i][theObj];
		for (i = 0; !foundObj && theDoc.layers && i < theDoc.layers.length; i++)
			foundObj = findObj(theObj, theDoc.layers[i].document);
		if (!foundObj && document.getElementById)
			foundObj = document.getElementById(theObj);
		return foundObj;
	}
	//添加一个参与人填写行
	function AddSignRow() { //读取最后一行的行号，存放在txtTRLastIndex文本框中 
		var txtTRLastIndex = findObj("txtTRLastIndex", document);
		var rowID = parseInt(txtTRLastIndex.value);
		var signFrame = findObj("SignFrame", document);
		//添加行
		var newTR = signFrame.insertRow(signFrame.rows.length);
		newTR.id = "SignItem" + rowID;

		var newNameTD = newTR.insertCell(0);//添加列:序号
		newNameTD.innerHTML = newTR.rowIndex.toString();//添加列内容

		//添加列:选项内容
		var newOptionContentTD = newTR.insertCell(1);
		//添加列内容
		newOptionContentTD.innerHTML = "<input name='txtOptionContent" + rowID + "' id='txtOptionContent" + rowID + "' type='text' size='90' />";

		//添加列:删除按钮
		var newDeleteTD = newTR.insertCell(2);
		//添加列内容
		newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a href='javascript:;' onclick=\"DeleteSignRow('SignItem"
				+ rowID + "')\">删除</a></div>";

		/* //添加列:序号
		var newNameTD = newTR.insertCell(0);
		//添加列内容
		newNameTD.innerHTML = newTR.rowIndex.toString();

		//添加列:姓名
		var newNameTD = newTR.insertCell(1);
		//添加列内容
		newNameTD.innerHTML = "<input name='txtName" + rowID + "' id='txtName" + rowID + "' type='text' size='12' />";

		//添加列:电子邮箱
		var newEmailTD = newTR.insertCell(2);
		//添加列内容
		newEmailTD.innerHTML = "<input name='txtEMail" + rowID + "' id='txtEmail" + rowID + "' type='text' size='20' />";

		//添加列:电话
		var newTelTD = newTR.insertCell(3);
		//添加列内容
		newTelTD.innerHTML = "<input name='txtTel" + rowID + "' id='txtTel" + rowID + "' type='text' size='10' />";

		//添加列:手机
		var newMobileTD = newTR.insertCell(4);
		//添加列内容
		newMobileTD.innerHTML = "<input name='txtMobile" + rowID + "' id='txtMobile" + rowID + "' type='text' size='12' />";

		//添加列:公司名
		var newCompanyTD = newTR.insertCell(5);
		//添加列内容
		newCompanyTD.innerHTML = "<input name='txtCompany" + rowID + "' id='txtCompany" + rowID + "' type='text' size='20' />";

		//添加列:删除按钮
		var newDeleteTD = newTR.insertCell(6);
		//添加列内容
		newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a href='javascript:;' onclick=\"DeleteSignRow('SignItem"
				+ rowID + "')\">删除</a></div>"; */

		//将行号推进下一行
		txtTRLastIndex.value = (rowID + 1).toString();
	}
	//删除指定行
	function DeleteSignRow(rowid) {
		var signFrame = findObj("SignFrame", document);
		var signItem = findObj(rowid, document);

		//获取将要删除的行的Index
		var rowIndex = signItem.rowIndex;

		//删除指定Index的行
		signFrame.deleteRow(rowIndex);

		//重新排列序号，如果没有序号，这一步省略
		for (i = rowIndex; i < signFrame.rows.length; i++) {
			signFrame.rows[i].cells[0].innerHTML = i.toString();
		}
	}
	//清空列表
	function ClearAllSign() {
		if (confirm('确定要清空所有选项吗？')) {
			var signFrame = findObj("SignFrame", document);
			var rowscount = signFrame.rows.length;

			//循环删除行,从最后一行往前删除
			for (i = rowscount - 1; i > 0; i--) {
				signFrame.deleteRow(i);
			}

			//重置最后行号为1
			var txtTRLastIndex = findObj("txtTRLastIndex", document);
			txtTRLastIndex.value = "1";

			//预添加一行
			AddSignRow();
		}
	}
	
/* 	function check_NotNull(){
		var txtOptionContent = document.getElementById("txtOptionContent"+rowID).value;
		var strtxtOptionContent=txtOptionContent.replace(/(^\s*)|(\s*$)/g,'');
		
		if(txtOptionContent==""||txtOptionContent==null||strtxtOptionContent==""){
			alert("选项内容不能为空！！！！");
			return false;
		}
	} */

/* 	function result() {
		var tt = document.getElementById("num");
		tt.value = rowID;
		window.alert("num:" + rowID.toString() + "|tt.value:" + tt.value);
		return true;
	} */
</script>
</head>
<% 
String VoteID = request.getParameter("VoteID");
String AdminID=request.getParameter("AdminID");
//out.println(VoteID);
//out.println(AdminID);
%>
<body>
	<header>
	<div id="sitebranding">
		<h1>
			<font face="楷体" size="10">网上投票系统</font>
		</h1>
	</div>
	<div id="tagline">
		<p>
			<span class="right" style="font-size: 15px; font-style: normal;">
				<c>你好！ </c> <d><jsp:getProperty property="name"
					name="admcurrentUser" /> </d> <d id="txt"></d> <a
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
			<a><span
				style="color: black; font-weight: normal; font-size: 15px;">您当前的位置：</span></a>
			<a href="admin_vote_list.jsp"><span
				style="color: black; font-weight: normal; font-size: 15px;">投票管理</span></a>>
			<a href="#"><span
				style="color: black; font-weight: normal; font-size: 15px;">添加投票事件</span></a>
		</h5>



		<div>
			<form action="/OVS/AddVoteOptionServlet" id="myForm" name="myForm"
				method="post" onSubmit="return check_NotNull()">
				<table width="613" border="0" cellpadding="2" cellspacing="1"
					id="SignFrame">
					<tr id="trHeader">
						<td width="27" bgcolor="#96E0E2">序号</td>
						<td width="98" bgcolor="#96E0E2">选项内容</td>
						<td width="57" align="center" bgcolor="#96E0E2">&nbsp;</td>
					</tr>
				</table>
						<div>
			<input type="button" name="Submit" value="添加选项"
				onclick="AddSignRow()" /> <input type="button" name="Submit2"
				value="清空所有选项" onclick="ClearAllSign()" />
		</div>
				<br>
				<br>
				<input name='txtTRLastIndex' type='hidden' id='txtTRLastIndex'
					value="1" /> <input type="submit" value="提交" />
					<input type="hidden" name="VoteID" value="<%=VoteID%>"/>
					<input type="hidden" name="AdminID" value="<%=AdminID%>"/>
			</form>
		</div>

</body>
</html>
