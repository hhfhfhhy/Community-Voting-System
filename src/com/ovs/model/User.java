package com.ovs.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.ovs.db.JDBC_Connection;

public class User {
	
	private String UserID = null;
	private String UserName = null;
	private String ValidIdentity = null;
	private String SecurityP = null;
	private String SecurityA = null;
	private Date RegisterDate = null;

	
	public User(){}
	public User(String UserName, String UserID,String ValidIdentity,String SecurityP,String SecurityA,Date RegisterDate) {
		super();
		this.UserName = UserName;
		this.UserID = UserID;
		this.ValidIdentity=ValidIdentity;
		this.SecurityA=SecurityA;
		this.SecurityP=SecurityP;
		this.RegisterDate=RegisterDate;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String UserName) {
		this.UserName = UserName;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String UserID) {
		this.UserID = UserID;
	}
	public String getValidIdentity(){
		return ValidIdentity;
	}
	public void setValidIdentity(String ValidIdentity) {
		this.ValidIdentity = ValidIdentity;
	}
	public String getSecurityP(){
		return SecurityP;
	}
	public void setSecurityP(String SecurityP) {
		this.SecurityP = SecurityP;
	}
	public String getSecurityA(){
		return SecurityA;
	}
	public void setSecurityA(String SecurityA) {
		this.SecurityA = SecurityA;
	}
	public Date getRegisterDate() {
		return RegisterDate;
	}
	public void setRegisterDate(Date RegisterDate) {
		this.RegisterDate = RegisterDate;
	}
	
	
	public void setUser(String userID) throws SQLException {
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		
		// SQL Óï¾ä
		String QuerySQL = "SELECT * FROM user WHERE UserID ='"
				+ userID + "';";
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(QuerySQL);
			
			while(rs.next()){
				setUserID(userID);
				setUserName(rs.getString("UserName"));
				setValidIdentity(rs.getString("ValidIdentity"));
				setRegisterDate(rs.getDate("RegisterDate"));
				setSecurityP(rs.getString("SecurityP"));
				setSecurityA(rs.getString("SecurityA"));
				}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}



	}
	
	
	
}
