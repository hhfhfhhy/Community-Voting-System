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
public class CreateOptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOptionServlet() {
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
		String newOptionSequence="-1";
		int newOptionSequenceNum=0;		
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String VoteID = request.getParameter("VoteID");
		String OptionContent = new String(request.getParameter("newOption").getBytes("ISO-8859-1"), "UTF-8");	
		
		String QuerySQL ="select * from voteevent where VoteID='"+VoteID+"' order by OptionSequence desc;";
		//System.out.println(insertUserSQL);
		
		try {
			conn = JDBC_Connection.getConnection();
			stmt = conn.createStatement();
			rs=stmt.executeQuery(QuerySQL);
			if(rs.next()!=true){
				 newOptionSequence="-1";
				 newOptionSequenceNum=0;
			}else{
				 newOptionSequence=rs.getString("OptionSequence");
				 newOptionSequenceNum=Integer.parseInt(newOptionSequence)+1;				
			}
			String InsertSQL="insert into voteevent(VoteID,OptionSequence,OptionContent)values('"+VoteID+"','"+newOptionSequenceNum+"','"+OptionContent+"');";
			stmt.execute(InsertSQL);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ÃÌº” ß∞‹£°£°£°");
		} finally {
			JDBC_Connection.free(rs, conn, stmt);
			String url="/OVS/admin/admin_vote_show.jsp?VoteID="+VoteID;
			response.sendRedirect(url);	
		}
	}

}
