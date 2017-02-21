package com.ovs.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ovs.db.JDBC_Connection;
import com.ovs.model.User;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String UserName = new String(request.getParameter("UserName").getBytes("ISO-8859-1"), "UTF-8");
		String ValidIdentity = new String(request.getParameter("ValidIdentity").getBytes("ISO-8859-1"), "UTF-8");
		String SecurityP = new String(request.getParameter("SecurityP").getBytes("ISO-8859-1"), "UTF-8");
		String SecurityA = new String(request.getParameter("SecurityA").getBytes("ISO-8859-1"), "UTF-8");
		String PWD=request.getParameter("PWD");
		
		/*String insertUserSQL = "insert into user( UserID,UserName, ValidIdentity, SecurityP,SecurityA)" +
    			" values('"+UserName+"','"+UserName+"','"+ValidIdentity+"','"+SecurityP+"','"+SecurityA+"'); ";*/
		String insertUserSQL = "insert into user( UserName, ValidIdentity, SecurityP,SecurityA)" +
    			" values('"+UserName+"','"+ValidIdentity+"','"+SecurityP+"','"+SecurityA+"'); ";
		/*String insertLoginSQL="insert into login(ID,pwd,role)values('"+UserName+"','"+PWD+"','user');";*/
		//System.out.println(insertUserSQL);
		
		try {
			conn = JDBC_Connection.getConnection();
			stmt = conn.createStatement();
			stmt.execute(insertUserSQL);
			/*stmt.execute(insertLoginSQL);*/
			
			String querySQL="select * from user where UserName='"+UserName+"' and ValidIdentity='"
					+ValidIdentity+"' and SecurityP='"+SecurityP+"' and SecurityA='"+SecurityA+"' order by UserID desc";
			rs=stmt.executeQuery(querySQL);
			rs.next();
			String UserID=rs.getString("UserID");
			String insertLoginSQL="insert into login(ID,pwd,role)values('"+UserID+"','"+PWD+"','user');";
			stmt.execute(insertLoginSQL);
			/*	//System.out.println(querySQL);
			rs=stmt.executeQuery(querySQL);
			rs.next();
			
			String UserID=rs.getString("UserID");
			//Date RegisterDate=rs.getDate("RegisterDate");
			
			String insertLoginSQL= "insert into login( ID, pwd, role)" +
	    			" values('"+UserID+"','888888','user'); ";
			stmt.execute(insertLoginSQL);*/
			//User temp=new User(UserName, UserID, ValidIdentity, SecurityP, SecurityA,RegisterDate);
			response.sendRedirect("/OVS/user/user_temp.jsp?userID="+UserID);	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Connection.free(rs, conn, stmt);
		}
	}

}
