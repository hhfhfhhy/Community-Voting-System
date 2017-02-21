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
public class DeleteVoteOptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String pageName = "DeleteVoteOptionServlet:---------------------------------------------\n";
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteVoteOptionServlet() {
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
		String OptionSequence = request.getParameter("OptionSequence");
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		System.out.println(pageName+"接收到的VoteID为"+VoteID);
		System.out.println(pageName+"接收到的OptionSequence为"+OptionSequence);
		
	    String deleteSQL = "delete from voteevent where VoteID ='"+VoteID+"' and OptionSequence='"+OptionSequence+"';";
		String updateSQL="update voteevent set OptionSequence=OptionSequence-1 where OptionSequence>="+OptionSequence+" and VoteID="+VoteID+";";
	    
	    try{
	    	conn=JDBC_Connection.getConnection();
	    	stmt=conn.createStatement();
	    	stmt.execute(deleteSQL);
	    	stmt.execute(updateSQL);
	    	    	
	    }catch(SQLException e){
	    	e.printStackTrace();
	    	System.out.println("删除失败！可能是条目已经不存在。");
	    }finally{
	    	JDBC_Connection.free(rs, conn, stmt);
	    	response.sendRedirect("/OVS/admin/admin_vote_show.jsp?VoteID="+VoteID);
	    }
	}

}
