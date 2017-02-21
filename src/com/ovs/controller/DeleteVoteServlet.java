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
 * Servlet implementation class DeleteVoteOptionServlet
 */
public class DeleteVoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteVoteServlet() {
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
		String VoteID = request.getParameter("VoteID");
		System.out.println("要删除的VoteID为"+VoteID);
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		String deleteSQL1 = "delete from uservote where VoteID ='"+VoteID+"';";
		String deleteSQL2 = "delete from voteevent where VoteID ='"+VoteID+"';";
	    String deleteSQL3 = "delete from createvote where VoteID ='"+VoteID+"';";
	    
	    try{
	    	conn=JDBC_Connection.getConnection();
	    	stmt=conn.createStatement();
	    	stmt.execute(deleteSQL1);
	    	stmt.execute(deleteSQL2);
	    	stmt.execute(deleteSQL3);
	    	    	
	    }catch(SQLException e){
	    	e.printStackTrace();
	    	System.out.println("删除失败！可能是条目已经不存在。");
	    }finally{
	    	JDBC_Connection.free(rs, conn, stmt);
	    	response.sendRedirect("/OVS/admin/admin_vote_list.jsp?");
	    }
	}

}
