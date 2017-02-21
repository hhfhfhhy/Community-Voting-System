package com.ovs.beans;

import java.sql.*;
import java.util.ArrayList;

import com.ovs.db.JDBC_Connection;


public class UserVoteBean {
	public UserVoteBean() {
		super();
	}
	
	
/*	public ArrayList<Vote> CheckUserVote(String UserID){//在createvote表获取用户已经投票的事件
		String QuerySQL="select * from createvote where VoteID in(select VoteID from uservote where UserID='"+UserID+"');";
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Vote> alv=new ArrayList<Vote>();
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(QuerySQL);
			
			while(rs.next()){
				Vote temp=new Vote();
				
				temp.setAdminID(rs.getString("AdminID").toString());
				temp.setVoteID(rs.getString("VoteID"));
				temp.setStatus(rs.getString("Status").toString());
				temp.setVoteContent(rs.getString("VoteContent").toString());
				temp.setStart(rs.getDate("Start"));
				temp.setEnd(rs.getDate("End"));
				temp.setVoteName(rs.getString("VoteName"));
				
				alv.add(temp);
			}
			if(alv.size()==0||alv==null){
				return null;
			}else{
				return alv;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}*/
	
	public boolean CheckUserVote(String VoteID,String UserID){
		//检查用户ID为UserID的用户是否已经对编号VoteID的事件投票
		String QuerySQL="select * from uservote where VoteID='"+VoteID+"' and UserID='"+UserID+"';";
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(QuerySQL);
			
			if(rs.next()==true)return true;//若用户投票，则uservote有记录，返回值为true
			else return false;

		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}
	
	
	public ArrayList<String> getTheUserOption(String VoteID,String UserID){
		//获得用户ID为UserID的用户对编号VoteID的事件投票的选项
		//String QuerySQL="select * from uservote where VoteID='"+VoteID+"' and UserID='"+UserID+"';";
		String QuerySQL="select * from voteevent where VoteID='"+VoteID+"' and OptionSequence in(select UserOption from uservote where VoteID='"+VoteID
				+"' and UserID='"+UserID+"');";
		ArrayList<String> TheUserOption=new ArrayList<String>();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(QuerySQL);
			
			while(rs.next()){
				String temp=rs.getString("OptionContent");
				TheUserOption.add(temp);
			}
			return TheUserOption;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}
	
	public int getCertainOptionCount(String VoteID,String UserOption){
		//获得ID为VoteID的某个UserOption选项个数，这样用count除以投票总数就是比例
		String QuerySQL="select count(*) from uservote where VoteID='"+VoteID+"' and UserOption='"+UserOption+"';";
		int count=0;
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(QuerySQL);
			rs.next();
			count=Integer.parseInt(rs.getString("count(*)"));
			//System.out.println(count);
			return count;		
		}catch(SQLException e){
			e.printStackTrace();
			return count;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}
	
	
	public int getTotalOptionCount(String VoteID){
		//获得ID为VoteID的某个所有用户投票选项个数，这样count就是总数
		String QuerySQL="select count(*) from uservote where VoteID='"+VoteID+"';";
		int count=0;
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(QuerySQL);
			rs.next();
			count=Integer.parseInt(rs.getString("count(*)"));
			//System.out.println(count);
			return count;		
		}catch(SQLException e){
			e.printStackTrace();
			return count;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}
	
	
	public int getTheUserCount(String VoteID){
		//获得VoteID事件已经投票的用户个数
		String QuerySQL="select distinct UserID from uservote where VoteID='"+VoteID+"';";
		int count=0;
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(QuerySQL);
			while(rs.next()){
				count++;
			}
			return count;		
		}catch(SQLException e){
			e.printStackTrace();
			return count;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}
}
