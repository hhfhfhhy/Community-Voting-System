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
	 * @author��adidas
	 * �õ�һ��ѧ��������֪ͨ����ʦ�ӹ���Ա
	 
	public ArrayList<Info> getAllNoti(String classID) throws SQLException{
		
		System.out.println(pageName+"getAllNotiҪ���ҵ�ѧ���༶���ΪclassID��"+classID);
		
		ArrayList<Info> resultNoti = new ArrayList<Info>();
		
		//��ѯ����Ա������֪ͨ  SQL ���
		String QuerySQLAdmin = "SELECT * FROM notification WHERE adminID <> '22' order by startDate desc ;";
		System.out.println("getAllNotiҪ���ҵĹ���Ա������֪ͨ��SQL��"+QuerySQLAdmin);
				
		// ִ��SQL
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

		//��ѯ��ʦ������֪ͨ      SQL ���
		String QuerySQLTea = "SELECT * FROM notification WHERE noticeID IN " 
			+ "(select noticeID from teachernotification where classID = '"+classID+"') order by startDate desc;";
		System.out.println("getAllNotiҪ���ҵ���ʦ������֪ͨ��SQL��"+QuerySQLTea);
		// ִ��SQL
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
	 * @return ��ҳ���֪ͨʵ��
	 * @throws SQLException
	 */
/*	public ArrayList<Info> findNoticeByPage(String classID,int page) throws SQLException{

		System.out.println(pageName+"Ҫ���ҵ�ѧ���༶���ΪclassID��"+classID);
		
		ArrayList<Info> resultNoti = new ArrayList<Info>();
		
		//��ѯ������֪ͨ  SQL ���
		String QuerySQL = "SELECT * FROM notification WHERE adminID <> '22' OR noticeID IN " 
			+ "(select noticeID from teachernotification where classID = '"+classID+"') order by noticeID desc limit "
				+ (page-1)*CCT.PAGE_SIZE+","+CCT.PAGE_SIZE+";";
		System.out.println(pageName+"Ҫ���ҵķ�����֪ͨ��SQL��"+QuerySQL);
				
		// ִ��SQL
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
		System.out.println("����Ա������֪ͨ����"+resultNoti.size());
		com.csios.db.DBQuery.closeDB();

		return resultNoti;
	}*/
	
	public Info getNotice(String InfoID) throws SQLException{
		
		System.out.println(pageName+"getNoticeҪ��ѯ��֪ͨ���ΪnoticeID��"+InfoID);
		
		Info myNoti = new Info();
		
		//��ѯ֪ͨ  SQL ���
		String QuerySQL = "SELECT * FROM info WHERE InfoID = '"+InfoID+"' ;";
		System.out.println(pageName+"getNotice����֪ͨ�Ĳ�ѯ���QuerySQLΪ"+QuerySQL);
		
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
			
			//System.out.println(pageName+"getNotice����֪ͨ�Ľ��myNotiΪ"+myNoti);
			return myNoti;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}

	//�½�һ������ֱ����ʾ���й���(�������Ǹù���Ա������)�������ڹ���Ա����չʾ�����
	public ArrayList<Info> getTheNoti() throws SQLException{
		
		ArrayList<Info> resultNoti = new ArrayList<Info>();
		
		//��ѯ������֪ͨ  SQL ���
		String QuerySQLAdmin = "SELECT * FROM info order by InfoTime desc;";
		System.out.println(pageName+"getTheNotiҪ���ҵķ�����֪ͨ��SQL��"+QuerySQLAdmin);
				
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

			System.out.println(pageName+"getTheNoti������֪ͨ����"+resultNoti.size());			
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
	 * @return ���ҵ��� ��ҳ֪ͨ
	 * @throws SQLException
	 */
	public ArrayList<Info> findNoticeByPageAdmin(int page) throws SQLException{

		ArrayList<Info> resultNoti = new ArrayList<Info>();
		
		//��ѯ������֪ͨ  SQL ���
		/*String QuerySQLAdmin = "SELECT * FROM notification order by startDate desc limit "
				+ (page-1)*CCT.PAGE_SIZE+","+CCT.PAGE_SIZE+";";*/
		String QuerySQLAdmin = "SELECT * FROM info order by InfoTime desc limit "
				+ (page-1)*Info.PAGE_SIZE+","+Info.PAGE_SIZE+";";
		System.out.println(pageName+"findNoticeByPageAdmin��ҳ���ҵ�֪ͨ��SQL��"+QuerySQLAdmin);
		
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
				System.out.println("findNoticeByPageAdmin��ҳ����֪ͨ����"+resultNoti.size());		
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