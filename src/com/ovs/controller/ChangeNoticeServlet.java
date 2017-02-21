package com.ovs.controller;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.ovs.db.*;
import com.jspsmart.upload.SmartUpload;

/**
 * Servlet implementation class StoreNoticeServlet
 */
public class ChangeNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeNoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ChangeNoticeServlet------------------------------------------\n");
	
		String InfoID = request.getParameter("InfoInfo");
		System.out.println(InfoID);
		String InfoTitle =new String(request.getParameter("newInfoTitle").getBytes("ISO-8859-1"), "UTF-8");
		//String InfoTitle = new String(request.getParameter("newInfoTitle").getBytes("gb2312"),"gb2312");
		System.out.println(InfoTitle);
		String InfoContent =new String(request.getParameter("newInfoContent").getBytes("ISO-8859-1"), "UTF-8");
		//String InfoContent = new String(request.getParameter("newInfoContent").getBytes("gb2312"),"gb2312");
		System.out.println(InfoContent);
		
		String updateSQL="update info set InfoContent='"+InfoContent+"' , InfoTitle='"
		+InfoTitle+"' where InfoID='"+InfoID+"';";//注意此处不能用and要用逗号！！！
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try{	
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			stmt.execute(updateSQL);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
			response.sendRedirect("/OVS/admin/admin_notice_show.jsp?InfoID="+InfoID);
		}	
	}

}
