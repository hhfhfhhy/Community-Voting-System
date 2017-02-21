package com.ovs.controller;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ovs.db.JDBC_Connection;

/**
 * Servlet implementation class DeleteUserServlet
 */
public class DeleteInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String pageName = "DeleteUserServlet:---------------------------------------------\n";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteInfoServlet() {
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
		String InfoID = request.getParameter("InfoID");
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		System.out.println(pageName+"接收到的InfoID为"+InfoID);
		
	    String deleteSQL = "delete from info where InfoID ='"+InfoID+"';";
	    
	    try{
	    	conn=JDBC_Connection.getConnection();
	    	stmt=conn.createStatement();
	    	stmt.execute(deleteSQL);
	    	    	
	    }catch(SQLException e){
	    	e.printStackTrace();
	    	System.out.println("删除失败！可能是公告已经不存在。");
	    }finally{
	    	JDBC_Connection.free(rs, conn, stmt);
	    	response.sendRedirect("/OVS/admin/admin_notice_list.jsp");
	    }
		
		
		
	}

}
