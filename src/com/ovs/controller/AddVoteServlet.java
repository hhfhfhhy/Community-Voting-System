package com.ovs.controller;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ovs.db.JDBC_Connection;

/**
 * Servlet implementation class AddVoteServlet
 */
public class AddVoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddVoteServlet() {
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
		System.out.println("AddVoteServlet------------------------------------------\n");
				
		String adminID=request.getParameter("admin");
		//String name=request.getParameter("name");
		String status=request.getParameter("status");
		//String votecontent=request.getParameter("VoteContent");
		String name =new String(request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
		String votecontent = new String(request.getParameter("VoteContent").getBytes("ISO-8859-1"), "UTF-8");
		
	    
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
    	String insertSql = "insert into createvote( AdminID, Status, VoteContent,VoteName)" +
    			" values ('"+adminID+"','"+status+"','"+votecontent+"','"+name+"') ";
    	System.out.println(insertSql);
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			stmt.execute(insertSql);
			
			String querySql="select * from createvote where VoteContent='"+votecontent+"' and AdminID='"
			+adminID+"'order by Start desc;";
			System.out.println(querySql);
			rs=stmt.executeQuery(querySql);
			rs.next();
			String VoteID=rs.getString("VoteID");
			System.out.println(VoteID);
				
			String url="/OVS/admin/admin_vote_addOption.jsp?VoteID="+VoteID+"&AdminID="+adminID;
			//String url="/OVS/admin/admin_vote_addOption.jsp";
			//System.out.println(url);
			response.sendRedirect(url);
		}catch(SQLException e){
			e.printStackTrace();
			response.sendRedirect("/OVS/admin/admin_vote_add.jsp");
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
/*		for(int i=0;i<10;i++){
			String OptionContent = request.getParameter("txtOptionContent"+i);
			System.out.println(OptionContent);
		}*/
		
	}

}
