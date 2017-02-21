package com.ovs.beans;

import java.sql.*;

import com.ovs.db.JDBC_Connection;
import com.ovs.model.Admin;

public class AdminInfoBean {
	private String pageName = "AdminInfoBean:----------------------------------------------\n";

	public AdminInfoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//根据AdminID获得AdminName
	public Admin getAdmin(String AdminID) throws SQLException{
		System.out.println(pageName+"getNotice要查询的通知编号为noticeID："+AdminID);
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		Admin myAdmin = new Admin();
		
		String querySQL="select * from admin where AdminID='"+AdminID+"';";
		System.out.println(querySQL);
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(querySQL);
			
			if(rs!=null){
				rs.next();
				myAdmin.setAdminID(rs.getString("AdminID"));
				myAdmin.setName(rs.getString("AdminName"));
			}
			System.out.println(pageName+"getNotice查找通知的结果myNoti为"+myAdmin);
			return myAdmin;
			
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}

}
