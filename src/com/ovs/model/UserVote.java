package com.ovs.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ovs.db.JDBC_Connection;

public class UserVote {
	
	private String UserID = null;
	private String VoteID = null;
	private String UserOption = null;
	
	
	public UserVote(){}
	public UserVote(String UserID, String VoteID,String UserOption) {
		super();
		this.UserID = UserID;
		this.VoteID = VoteID;
		this.UserOption=UserOption;
	}
	
	public String getVoteID() {
		return VoteID;
	}
	public void setVoteID(String VoteID) {
		this.VoteID = VoteID;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String UserID) {
		this.UserID = UserID;
	}
	public String getUserOption(){
		return UserOption;
	}
	public void setUserOption(String UserOption) {
		this.UserOption = UserOption;
	}
	
	
	public void setUserVote(String userID) throws SQLException {
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		
		// SQL Óï¾ä
		String QuerySQL = "SELECT * FROM uservote WHERE UserID ='"+ userID + "';";
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(QuerySQL);
			
			while(rs.next()){
				setUserID(userID);
				setVoteID(rs.getString("VoteID"));
				setUserOption(rs.getString("UserOption"));
				}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}


}
