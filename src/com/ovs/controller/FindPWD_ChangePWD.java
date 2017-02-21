package com.ovs.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ovs.db.JDBC_Connection;

/**
 * Servlet implementation class FindPWD_ChangePWD
 */
public class FindPWD_ChangePWD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPWD_ChangePWD() {
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
		String newPWD1=request.getParameter("newPWD1");
		String newPWD2=request.getParameter("newPWD2");
		String UserID=request.getParameter("UserID");
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		if(newPWD1.equals(newPWD2)){
			try{
				conn=JDBC_Connection.getConnection();
				stmt=conn.createStatement();
				
				String updateSQL="update login set pwd='"+newPWD1+"' where ID='"+UserID+"';";
				stmt.execute(updateSQL);

			}catch(Exception e){
				e.printStackTrace();
			}finally{
				JDBC_Connection.free(rs, conn, stmt);
				response.sendRedirect("/OVS/login/login.jsp");
			}
		}else{
			response.sendRedirect("/OVS/login/changePWD.jsp?UserID="+UserID);
		}
	}

}
