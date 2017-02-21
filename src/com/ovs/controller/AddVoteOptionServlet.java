package com.ovs.controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ovs.db.JDBC_Connection;

/**
 * Servlet implementation class AddVoteOptionServlet
 */
public class AddVoteOptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddVoteOptionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		ArrayList<String> OptionContentArray = new ArrayList<String>();
		// System.out.println("获得到：");

		String VoteID = request.getParameter("VoteID");
		String adminID=request.getParameter("AdminID");

		for (int i = 1; i < 20; i++) {
			if(request.getParameter("txtOptionContent"+i)!=null){
				if(request.getParameter("txtOptionContent"+i).length()!=0){
				String OptionContent = new String(request.getParameter("txtOptionContent" + i).getBytes("ISO-8859-1"), "UTF-8");
				//System.out.println(OptionContent);	
				OptionContentArray.add(OptionContent);
				}//if
			}//if
		}//for

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		if (OptionContentArray.size() == 0 || OptionContentArray.isEmpty())
			{System.out.println("没有选项，请重新插入！！！");
		    String url="/OVS/admin/admin_vote_addOption.jsp?VoteID="+VoteID+"&AdminID="+adminID;
		    response.sendRedirect(url);
			}else {
			for (int i = 0; i < OptionContentArray.size(); i++) {
				System.out.println(OptionContentArray.get(i));
				String insertSql = "insert into voteevent( VoteID, OptionSequence, OptionContent)" +
		    			" values('"+VoteID+"','"+i+"','"+OptionContentArray.get(i)+"'); ";
				System.out.println(insertSql);

				try {
					conn = JDBC_Connection.getConnection();
					stmt = conn.createStatement();
					stmt.execute(insertSql);
					JDBC_Connection.free(rs, conn, stmt);
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					JDBC_Connection.free(rs, conn, stmt);
				}
			}// for
			response.sendRedirect("/OVS/admin/admin_vote_list.jsp");
			
			
		}//else

	}

}
