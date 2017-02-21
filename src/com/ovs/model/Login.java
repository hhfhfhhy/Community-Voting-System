package com.ovs.model;

import java.sql.*;

import com.ovs.db.JDBC_Connection;

public class Login {
	private String id;
	private String pwd;
	private String role;
	
	public Login(){}
	public Login(String id,String role,String pwd) {
		super();
		this.id = id;
		this.role = role;
		this.pwd = pwd;
	}
	
	public String getPWD(){
		return pwd;
	}
	
	public void setPWD(String pwd){
		this.pwd=pwd;
	}
	
	public String getRole(){
		return role;
	}
	
	public void setRole(String role){
		this.role=role;
	}

	public String getID(){
		return id;
	}
	
	public void setID(String id){
		this.id=id;
	}
	
	
	public Login getLoginInfo(String AdminID){
		String QuerySQL="select * from login where ID="+AdminID+";";
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(QuerySQL);
			
			rs.next();
			Login log=new Login();
			log.setID(rs.getString("ID"));
			log.setPWD(rs.getString("pwd"));
			log.setRole(rs.getString("role"));
			return log;
		}catch(SQLException e){
			System.out.println("用户信息错误");
			e.printStackTrace();
			return null;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}

}
