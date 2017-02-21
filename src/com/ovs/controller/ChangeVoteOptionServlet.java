package com.ovs.controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ovs.db.JDBC_Connection;
import com.sun.crypto.provider.RSACipher;

/**
 * Servlet implementation class UserVoteServlet
 */
public class ChangeVoteOptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeVoteOptionServlet() {
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
		String VoteID=request.getParameter("VoteID");
		String Status=request.getParameter("Status");
		String UserID=request.getParameter("UserID");
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		if(Status.equals("y")){//多选
			int size=Integer.parseInt(request.getParameter("size"));
			ArrayList<String> als=new ArrayList<String>();
			for(int i=0;i<size;i++){
				String UserOption=request.getParameter("UserOption"+i);
				if(UserOption!=null)als.add(UserOption);
			}
			
			if(als.size()==0||als==null)//若什么都没选则返回查看结果页面
				response.sendRedirect("/OVS/user/user_vote_check.jsp?result=0&VoteID="+VoteID);
			else{//若选择则进行处理
				try{
					conn=JDBC_Connection.getConnection();
					stmt=conn.createStatement();
					//先删除再插入数据
					String DeleteSQL="delete from uservote where UserID='"+UserID+"' and VoteID='"+VoteID+"';";
					stmt.execute(DeleteSQL);
					for(int i=0;i<als.size();i++){
						String InsertSQL="insert into uservote(UserID,VoteID,UserOption)values('"+UserID+"','"+VoteID+"','"+als.get(i)+"');";						
						stmt.execute(InsertSQL);
					}	
				}catch(SQLException e){
					System.out.println("修改选项失败！！！");
					e.printStackTrace();
					response.sendRedirect("/OVS/user/user_vote_check.jsp?result=0&VoteID="+VoteID);
				}finally{
					JDBC_Connection.free(rs, conn, stmt);
					response.sendRedirect("/OVS/user/user_vote_check.jsp?result=0&VoteID="+VoteID);
				}
			}
		}//多选
		if(Status.equals("n")){
			String UserOption=request.getParameter("UserOption");
			
			if(UserOption==null||UserOption.equals(""))//若什么都没选则返回查看结果页面
				response.sendRedirect("/OVS/user/user_vote_check.jsp?result=0&VoteID="+VoteID);
			else{
				try{
					conn=JDBC_Connection.getConnection();
					stmt=conn.createStatement();
					
					//先删除再插入数据
					String DeleteSQL="delete from uservote where UserID='"+UserID+"' and VoteID='"+VoteID+"';";
					stmt.execute(DeleteSQL);
                    String InsertSQL="insert into uservote(UserID,VoteID,UserOption)values('"+UserID+"','"+VoteID+"','"+UserOption+"');";						
				    stmt.execute(InsertSQL);
				}catch(SQLException e){
					System.out.println("修改选项失败！！！");
					e.printStackTrace();
					response.sendRedirect("/OVS/user/user_vote_check.jsp?result=0&VoteID="+VoteID);
				}finally{
					JDBC_Connection.free(rs, conn, stmt);
					response.sendRedirect("/OVS/user/user_vote_check.jsp?result=0&VoteID="+VoteID);
				}
			}
		}//单选

		
	}//doPost

}
