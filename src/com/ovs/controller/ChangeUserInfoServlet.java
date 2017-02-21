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
public class ChangeUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeUserInfoServlet() {
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
		
		String newUserID = new String(request.getParameter("newUserID").getBytes("ISO-8859-1"), "UTF-8");
		String newUserPWD = new String(request.getParameter("newUserPWD").getBytes("ISO-8859-1"), "UTF-8");
		String newSP = new String(request.getParameter("newSP").getBytes("ISO-8859-1"), "UTF-8");
		String newSA = new String(request.getParameter("newSA").getBytes("ISO-8859-1"), "UTF-8");
		String UserID = new String(request.getParameter("UserID").getBytes("ISO-8859-1"), "UTF-8");
/*		String SecurityP = new String(request.getParameter("SecurityP").getBytes("ISO-8859-1"), "UTF-8");
		String SecurityA = new String(request.getParameter("SecurityA").getBytes("ISO-8859-1"), "UTF-8");
		String PWD=request.getParameter("PWD");*/
		
/*		String insertUserSQL = "insert into user( UserID,UserName, ValidIdentity, SecurityP,SecurityA)" +
    			" values('"+UserName+"','"+UserName+"','"+ValidIdentity+"','"+SecurityP+"','"+SecurityA+"'); ";
		String insertLoginSQL="insert into login(ID,pwd,role)values('"+UserName+"','"+PWD+"','user');";
		//System.out.println(insertUserSQL);
*/		
		
		String updateSQL1="update user set UserID='"+newUserID+"',UserName='"+newUserID+"',SecurityP='"+newSP
				+"',SecurityA='"+newSA+"' where UserID='"+UserID+"';";
		String updateSQL2="update login set pwd='"+newUserPWD+"',ID='"+newUserID+"' where ID='"+UserID+"';";
		try {
			conn = JDBC_Connection.getConnection();
			stmt = conn.createStatement();
			stmt.execute(updateSQL1);
			stmt.execute(updateSQL2);
			
/*			String querySQL="select * from user where UserName='"+UserName+"' and ValidIdentity='"
					+ValidIdentity+"' and SecurityP='"+SecurityP+"' and SecurityA='"+SecurityA+"'";*/
			String querySQL="select * from user where UserID='"+newUserID+"'and UserName='"+newUserID+"';";
			rs=stmt.executeQuery(querySQL);
			rs.next();
		    UserID=rs.getString("UserID");
			/*	//System.out.println(querySQL);
			rs=stmt.executeQuery(querySQL);
			rs.next();
			
			String UserID=rs.getString("UserID");
			//Date RegisterDate=rs.getDate("RegisterDate");
			
			String insertLoginSQL= "insert into login( ID, pwd, role)" +
	    			" values('"+UserID+"','888888','user'); ";
			stmt.execute(insertLoginSQL);*/
			//User temp=new User(UserName, UserID, ValidIdentity, SecurityP, SecurityA,RegisterDate);
			response.sendRedirect("/OVS/login/login.jsp");	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Connection.free(rs, conn, stmt);
		}
	}

}
