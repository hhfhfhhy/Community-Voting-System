package com.ovs.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ovs.db.JDBC_Connection;

public class Admin {
	
	private String name = null;
	private String adminID = null;
//	private String headPhoto = null;  //头像保存地址
	
	public Admin(){}
/*	public Admin(String name, String adminID, String headPhoto) {
		super();
		this.name = name;
		this.adminID = adminID;
		this.headPhoto = headPhoto;
	}*/
	public Admin(String name, String adminID) {
		super();
		this.name = name;
		this.adminID = adminID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdminID() {
		return adminID;
	}
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
/*	public String getHeadPhoto() {
		return headPhoto;
	}
	public void setHeadPhoto(String headPhoto) {
		this.headPhoto = headPhoto;
	}*/
/*	public void setAdmin(String userID) throws SQLException {
		
		// SQL 语句
		String QuerySQL = "SELECT * FROM admin WHERE adminID ='"
				+ userID + "';";
		//执行 SQL 
		ResultSet rs = com.csios.db.DBQuery.query(QuerySQL);


//		log.info("根据用户名查询密码完毕");
		if(rs!=null){
		setAdminID(userID);
		setName(rs.getString("name"));
		setHeadPhoto(rs.getString("headPhoto"));
		}
		com.csios.db.DBQuery.closeDB();

	}*/
	
	
	public void setAdmin(String userID) throws SQLException {
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		
		// SQL 语句
		String QuerySQL = "SELECT * FROM admin WHERE adminID ='"
				+ userID + "';";
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(QuerySQL);
			
			while(rs.next()){
				setAdminID(userID);
				setName(rs.getString("AdminName"));
				}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}
	
	
	
}
