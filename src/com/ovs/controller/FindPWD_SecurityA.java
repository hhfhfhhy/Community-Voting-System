package com.ovs.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ovs.db.JDBC_Connection;

/**
 * Servlet implementation class FindPWD_SecurityA
 */
public class FindPWD_SecurityA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPWD_SecurityA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String SecurityA=request.getParameter("SecurityA");
		String UserID=request.getParameter("UserID");
		
		
		String querySQL="select * from user where UserID='"+UserID+"';";
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(querySQL);
			
			rs.next();
			String SecurityAnswer=rs.getString("SecurityA");
			if(SecurityAnswer.equals(SecurityA)){
				response.sendRedirect("/OVS/login/changePWD.jsp?UserID="+UserID);
			}
			else{
				response.sendRedirect("/OVS/login/securityAnswer.jsp?UserID="+UserID);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
		
	}

}
