package com.ovs.controller;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ovs.db.JDBC_Connection;

/**
 * Servlet implementation class ResetPWDServlet
 */
public class ResetPWDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPWDServlet() {
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
		String resetUserID = request.getParameter("resetUserID");
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		String updateSQL = "UPDATE login SET pwd='888888' WHERE ID='"+resetUserID+"'";
		System.out.println("ResetPWDServlet---------------------------------------\n÷ÿ÷√√‹¬ÎµƒSQL”Ôæ‰Œ™updateSQL£∫"+updateSQL);
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			stmt.execute(updateSQL);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
			response.sendRedirect("/OVS/admin/admin_index.jsp");
		}
		
	}

}
