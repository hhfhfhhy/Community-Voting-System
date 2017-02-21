package com.ovs.controller;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ovs.db.JDBC_Connection;

/**
 * Servlet implementation class FindPWD_account
 */
public class FindPWD_account extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPWD_account() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String UserID=request.getParameter("userID");
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String querySQL="select * from user where UserID='"+UserID+"';";
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(querySQL);
			
			if(rs.next()){
				response.sendRedirect("/OVS/login/securityAnswer.jsp?UserID="+UserID);
			}else{
				response.sendRedirect("/OVS/login/findPWD.jsp");
			}
		}catch(SQLException e){
			e.printStackTrace();
			response.sendRedirect("/OVS/login/findPWD.jsp");
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}

}
