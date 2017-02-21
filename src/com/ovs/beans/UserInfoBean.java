package com.ovs.beans;

import java.sql.*;
import java.util.ArrayList;

import com.ovs.db.JDBC_Connection;
import com.ovs.model.Info;
import com.ovs.model.User;

public class UserInfoBean {
	private String pageName = "UserInfoBean:----------------------------------------------\n";

	public UserInfoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getUserSecurityP(String UserID){
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String SecurityP="";
		
		String querySQL="select * from user where UserID='"+UserID+"';";
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(querySQL);

			rs.next();
			SecurityP=rs.getString("SecurityP");
			return SecurityP;
		}catch(SQLException e){
			e.printStackTrace();
			return SecurityP;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
		
	}
	
	public String getUserPWD(String UserID){
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String PWD="";
		
		String querySQL="select * from login where ID='"+UserID+"';";
		System.out.println(querySQL);
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(querySQL);

			rs.next();
		    PWD=rs.getString("pwd");
			return PWD;
		}catch(SQLException e){
			e.printStackTrace();
			return PWD;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
		
	}
	
	//根据UserID获得某个用户
	public User getCertainUser(String UserID) throws SQLException{
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		String querySQL="select * from user where UserID='"+UserID+"';";
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(querySQL);
			
			if(rs==null)return null;
			else{
				User myUser = new User();
				while(rs.next()){
					myUser.setRegisterDate(rs.getDate("RegisterDate"));
					myUser.setSecurityA(rs.getString("SecurityA"));
					myUser.setSecurityP(rs.getString("SecurityP"));
					myUser.setUserID(rs.getString("UserID"));
					myUser.setUserName(rs.getString("UserName"));
					myUser.setValidIdentity(rs.getString("ValidIdentity"));
				}
				return myUser;	
			}//else			
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}
	
	//获得所有用户
	public ArrayList<User> getUser() throws SQLException{
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<User> al=new ArrayList<User>();
		
		String querySQL="select * from user order by UserID desc;";
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(querySQL);
			
			if(rs==null)return null;
			else{
				while(rs.next()){
					User myUser = new User();
					//System.out.println(rs.getDate("RegisterDate"));
					myUser.setRegisterDate(rs.getDate("RegisterDate"));
					myUser.setSecurityA(rs.getString("SecurityA"));
					myUser.setSecurityP(rs.getString("SecurityP"));
					myUser.setUserID(rs.getString("UserID"));
					myUser.setUserName(rs.getString("UserName"));
					myUser.setValidIdentity(rs.getString("ValidIdentity"));
					
					al.add(myUser);
				}
				return al;	
			}//else			
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}//getUser()
	
	
	public ArrayList<User> findUserByPageUser(int page) throws SQLException{
		ArrayList<User> resultUser = new ArrayList<User>();
		//查询发布的通知  SQL 语句
		String QuerySQLUser = "SELECT * FROM user order by UserID desc limit "
				+ (page-1)*Info.PAGE_SIZE+","+Info.PAGE_SIZE+";";
	
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(QuerySQLUser);
			
			if(rs==null)return null;
			else{
				while(rs.next()){	
					User myUser = new User();
					myUser.setRegisterDate(rs.getDate("RegisterDate"));
					myUser.setSecurityA(rs.getString("SecurityA"));
					myUser.setSecurityP(rs.getString("SecurityP"));
					myUser.setUserID(rs.getString("UserID"));
					myUser.setUserName(rs.getString("UserName"));
					myUser.setValidIdentity(rs.getString("ValidIdentity"));

					resultUser.add(myUser);
				}	
				return resultUser;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}				
	}//findUserByPageUser

}
