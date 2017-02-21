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
 * Servlet implementation class RegisterServlet
 */
public class EditVoteContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditVoteContentServlet() {
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
		
		String VoteID = request.getParameter("VoteID");
		String newVoteContent = new String(request.getParameter("newVoteContent").getBytes("ISO-8859-1"), "UTF-8");	
		
		String UpdateSQL = "update createvote set VoteContent='"+newVoteContent+"'where VoteID="+VoteID+";";
		
		try {
			conn = JDBC_Connection.getConnection();
			stmt = conn.createStatement();
			stmt.execute(UpdateSQL);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ÐÞ¸ÄÊ§°Ü£¡£¡£¡");
		} finally {
			JDBC_Connection.free(rs, conn, stmt);
			String url="/OVS/admin/admin_vote_show.jsp?VoteID="+VoteID;
			response.sendRedirect(url);	
		}
	}

}
