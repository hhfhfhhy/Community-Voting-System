package com.ovs.beans;

import java.sql.*;

import com.ovs.db.JDBC_Connection;
import com.ovs.model.Admin;

public class VoteEventBean {
	public VoteEventBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//����VoteID��ø�ͶƱ�¼�ѡ������
	public int getVoteEventCount(String VoteID) throws SQLException{
		int count=0;
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		String querySQL="select count(*) from voteevent where VoteID='"+VoteID+"';";
		System.out.println(querySQL);
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(querySQL);
			rs.next();
			count=Integer.parseInt(rs.getString("count(*)"));
			System.out.println(count);
			return count;		
		}catch(SQLException e){
			e.printStackTrace();
			return count;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}
	
	public String getOptionContent(String VoteID,String OptionSequence){
		//����VoteID��ѡ�����õ�ѡ������
		String OptionContent="";
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		String querySQL="select * from voteevent where VoteID='"+VoteID
				+"'and OptionSequence='"+OptionSequence+"';";
		System.out.println(querySQL);
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(querySQL);
			rs.next();
			OptionContent=rs.getString("OptionContent");
			System.out.println(OptionContent);
			return OptionContent;		
		}catch(SQLException e){
			e.printStackTrace();
			return OptionContent;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}

}
