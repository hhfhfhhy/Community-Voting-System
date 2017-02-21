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
public class UserVoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserVoteServlet() {
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
		
		if(Status.equals("y")){//��ѡ
			int size=Integer.parseInt(request.getParameter("size"));
			ArrayList<String> als=new ArrayList<String>();
			for(int i=0;i<size;i++){
				String UserOption=request.getParameter("UserOption"+i);
				if(UserOption!=null)als.add(UserOption);
			}
			
			if(als.size()==0||als==null)//��ʲô��ûѡ�����
				response.sendRedirect("/OVS/user/user_vote_error.jsp?VoteID="+VoteID);
			else{//��ѡ������д���
				try{
					conn=JDBC_Connection.getConnection();
					stmt=conn.createStatement();
					
					
					String QuerySQL="select * from uservote where UserID='"+UserID+"' and VoteID='"+VoteID+"';";
					//System.out.println(QuerySQL);
					rs=stmt.executeQuery(QuerySQL);
					if(rs.next()==false){//�����û�û��ͶƱ
						for(int i=0;i<als.size();i++){
							String InsertSQL="insert into uservote(UserID,VoteID,UserOption)values('"+UserID+"','"+VoteID+"',"+als.get(i)+");";
							stmt.execute(InsertSQL);					
						}
						response.sendRedirect("/OVS/user/user_vote_success.jsp?VoteID="+VoteID);
					}
					else{//�Ѿ�ͶƱ��
						response.sendRedirect("/OVS/user/user_vote_error1.jsp?VoteID="+VoteID);
					}		
				}catch(SQLException e){
					System.out.println("����ʧ�ܣ�����");
					e.printStackTrace();
					response.sendRedirect("/OVS/user/user_vote_error2.jsp?VoteID="+VoteID);
				}finally{
					JDBC_Connection.free(rs, conn, stmt);
				}
			}
		}//��ѡ
		if(Status.equals("n")){
			String UserOption=request.getParameter("UserOption");
			
			if(UserOption==null||UserOption.equals(""))//��ʲô��ûѡ�����
				response.sendRedirect("/OVS/user/user_vote_error.jsp?VoteID="+VoteID);
			else{
				try{
					conn=JDBC_Connection.getConnection();
					stmt=conn.createStatement();
					
					String QuerySQL="select * from uservote where UserID='"+UserID+"' and VoteID='"+VoteID+"';";
					//System.out.println(QuerySQL);
					rs=stmt.executeQuery(QuerySQL);
					
					if(rs.next()==false){//�����û�û��ͶƱ
							String InsertSQL="insert into uservote(UserID,VoteID,UserOption)values('"+UserID+"','"+VoteID+"','"+UserOption+"');";
							stmt.execute(InsertSQL);					
						
						response.sendRedirect("/OVS/user/user_vote_success.jsp?VoteID="+VoteID);
					}else{//�Ѿ�ͶƱ��
						response.sendRedirect("/OVS/user/user_vote_error1.jsp?VoteID="+VoteID);
					}	
				}catch(SQLException e){
					System.out.println("����ʧ�ܣ�����");
					e.printStackTrace();
					response.sendRedirect("/OVS/user/user_vote_error2.jsp?VoteID="+VoteID);
				}finally{
					JDBC_Connection.free(rs, conn, stmt);
				}
			}
		}//��ѡ

		
	}//doPost

}
