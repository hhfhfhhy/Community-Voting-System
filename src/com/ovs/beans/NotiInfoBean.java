package com.ovs.beans;

import java.sql.*;
import java.util.ArrayList;


import com.ovs.db.JDBC_Connection;
/*import com.csios.model.CCT;*/
import com.ovs.model.Info;

public class NotiInfoBean {
	
	private String pageName = "NotiInoBean:----------------------------------------------\n";

	public NotiInfoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
/*	
	 * @author：adidas
	 * 得到一个学生的所有通知，老师加管理员
	 
	public ArrayList<Info> getAllNoti(String classID) throws SQLException{
		
		System.out.println(pageName+"getAllNoti要查找的学生班级编号为classID，"+classID);
		
		ArrayList<Info> resultNoti = new ArrayList<Info>();
		
		//查询管理员发布的通知  SQL 语句
		String QuerySQLAdmin = "SELECT * FROM notification WHERE adminID <> '22' order by startDate desc ;";
		System.out.println("getAllNoti要查找的管理员发布的通知的SQL："+QuerySQLAdmin);
				
		// 执行SQL
		ResultSet rs = com.csios.db.DBQuery.query(QuerySQLAdmin);
		if(rs!=null){
		while(!rs.isAfterLast()){
			Info temp = new Info();
			temp.setAdminID(rs.getString("adminID"));
			temp.setAttachmentAddress(rs.getString("attachmentAddress"));
			temp.setContents(rs.getString("contents"));
//			temp.setDeadline(rs.getDate("deadline"));
			temp.setNoticeID(rs.getDouble("noticeID"));
			temp.setStartDate(rs.getDate("startDate"));
			temp.setTitle(rs.getString("title"));
			
			resultNoti.add(temp);
			rs.next();
		}
		}
		com.csios.db.DBQuery.closeDB();

		//查询老师发布的通知      SQL 语句
		String QuerySQLTea = "SELECT * FROM notification WHERE noticeID IN " 
			+ "(select noticeID from teachernotification where classID = '"+classID+"') order by startDate desc;";
		System.out.println("getAllNoti要查找的老师发布的通知的SQL："+QuerySQLTea);
		// 执行SQL
		ResultSet RS = com.csios.db.DBQuery.query(QuerySQLTea);

		if(RS!=null){
		while(!RS.isAfterLast()){
			Info temp = new Info();
			temp.setAdminID(RS.getString("adminID"));
			temp.setAttachmentAddress(RS.getString("attachmentAddress"));
			temp.setContents(RS.getString("contents"));
//			temp.setDeadline(RS.getDate("deadline"));
			temp.setNoticeID(RS.getDouble("noticeID"));
			temp.setStartDate(RS.getDate("startDate"));
			temp.setTitle(RS.getString("title"));
			
			resultNoti.add(temp);
			RS.next();
		}
		}
		com.csios.db.DBQuery.closeDB();
		
		return resultNoti;
	}
	*/
	/**
	 * @author adidas
	 * @param classID
	 * @param page
	 * @return 分页后的通知实例
	 * @throws SQLException
	 */
/*	public ArrayList<Info> findNoticeByPage(String classID,int page) throws SQLException{

		System.out.println(pageName+"要查找的学生班级编号为classID，"+classID);
		
		ArrayList<Info> resultNoti = new ArrayList<Info>();
		
		//查询发布的通知  SQL 语句
		String QuerySQL = "SELECT * FROM notification WHERE adminID <> '22' OR noticeID IN " 
			+ "(select noticeID from teachernotification where classID = '"+classID+"') order by noticeID desc limit "
				+ (page-1)*CCT.PAGE_SIZE+","+CCT.PAGE_SIZE+";";
		System.out.println(pageName+"要查找的发布的通知的SQL："+QuerySQL);
				
		// 执行SQL
		ResultSet rs = com.csios.db.DBQuery.query(QuerySQL);
		
		while(!rs.isAfterLast()){
			Info temp = new Info();
			temp.setAdminID(rs.getString("adminID"));
			temp.setAttachmentAddress(rs.getString("attachmentAddress"));
			temp.setContents(rs.getString("contents"));
//			temp.setDeadline(rs.getDate("deadline"));
			temp.setNoticeID(rs.getDouble("noticeID"));
			temp.setStartDate(rs.getDate("startDate"));
			temp.setTitle(rs.getString("title"));
			
			resultNoti.add(temp);
			rs.next();
		}
		System.out.println("管理员发布的通知条数"+resultNoti.size());
		com.csios.db.DBQuery.closeDB();

		return resultNoti;
	}*/
	
	public Info getNotice(String InfoID) throws SQLException{
		
		System.out.println(pageName+"getNotice要查询的通知编号为noticeID："+InfoID);
		
		Info myNoti = new Info();
		
		//查询通知  SQL 语句
		String QuerySQL = "SELECT * FROM info WHERE InfoID = '"+InfoID+"' ;";
		System.out.println(pageName+"getNotice查找通知的查询语句QuerySQL为"+QuerySQL);
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(QuerySQL);
			
			if(rs!=null){
				rs.next();
				myNoti.setAdminID(rs.getString("AdminID"));
				myNoti.setAttachmentAddress(rs.getString("attachmentAddress"));
				myNoti.setInfoContent(rs.getString("InfoContent"));
				myNoti.setInfoID(rs.getDouble("InfoID"));
				myNoti.setInfoTime(rs.getDate("InfoTime"));
				myNoti.setInfoTitle(rs.getString("InfoTitle"));
			}
			
			//System.out.println(pageName+"getNotice查找通知的结果myNoti为"+myNoti);
			return myNoti;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}

	//新建一个方法直接显示所有公告(包括不是该管理员发布的)，是用在管理员公告展示界面的
	public ArrayList<Info> getTheNoti() throws SQLException{
		
		ArrayList<Info> resultNoti = new ArrayList<Info>();
		
		//查询发布的通知  SQL 语句
		String QuerySQLAdmin = "SELECT * FROM info order by InfoTime desc;";
		System.out.println(pageName+"getTheNoti要查找的发布的通知的SQL："+QuerySQLAdmin);
				
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(QuerySQLAdmin);
			
			if(rs==null)return null;
			else{
				rs.next();
				while(!rs.isAfterLast()){
					Info temp = new Info();
					temp.setAdminID(rs.getString("AdminID"));
					temp.setAttachmentAddress(rs.getString("attachmentAddress"));
					temp.setInfoContent(rs.getString("InfoContent"));
					temp.setInfoID(rs.getDouble("InfoID"));
					temp.setInfoTime(rs.getDate("InfoTime"));
					temp.setInfoTitle(rs.getString("InfoTitle"));
					
					resultNoti.add(temp);
					rs.next();
				}
			}

			System.out.println(pageName+"getTheNoti发布的通知条数"+resultNoti.size());			
			return resultNoti;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}

	/**
	 * @author adidas
	 * @return 查找到的 分页通知
	 * @throws SQLException
	 */
	public ArrayList<Info> findNoticeByPageAdmin(int page) throws SQLException{

		ArrayList<Info> resultNoti = new ArrayList<Info>();
		
		//查询发布的通知  SQL 语句
		/*String QuerySQLAdmin = "SELECT * FROM notification order by startDate desc limit "
				+ (page-1)*CCT.PAGE_SIZE+","+CCT.PAGE_SIZE+";";*/
		String QuerySQLAdmin = "SELECT * FROM info order by InfoTime desc limit "
				+ (page-1)*Info.PAGE_SIZE+","+Info.PAGE_SIZE+";";
		System.out.println(pageName+"findNoticeByPageAdmin分页查找的通知的SQL："+QuerySQLAdmin);
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(QuerySQLAdmin);
			
			if(rs==null)return null;
			else{
				rs.next();
	//			rs.next();
				while(!rs.isAfterLast()){
					Info temp = new Info();
					temp.setAdminID(rs.getString("AdminID"));
					temp.setAttachmentAddress(rs.getString("attachmentAddress"));
					temp.setInfoContent(rs.getString("InfoContent"));
					temp.setInfoID(rs.getDouble("InfoID"));
					temp.setInfoTime(rs.getDate("InfoTime"));
					temp.setInfoTitle(rs.getString("InfoTitle"));
					resultNoti.add(temp);
					rs.next();
				}
				System.out.println("findNoticeByPageAdmin分页查找通知条数"+resultNoti.size());		
				return resultNoti;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}				
	}//findNoticeByPageAdmin
}