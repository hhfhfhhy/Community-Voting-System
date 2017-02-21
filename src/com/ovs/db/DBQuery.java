package com.ovs.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import com.ovs.model.MyLog;


/**
 * 
 * ���ز�ѯ���   rs��ʾ�ɹ� null��ʾ����ʧ�� 
 */
public class DBQuery {

	/* ��ѯ���ݿ⣬�������Ҫ��ļ�¼�����*/
	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
	public static ResultSet query(String sql ) {
//		Logger log = MyLog.Log("DBQuery","./log/DBQuery%g.log");//����һ��log����		
//	    log.info("����DBQuery��־"); 
		conn = DBConnection.getConnection();	//ͬ����Ҫ��ȡ���ӣ������ӵ����ݿ�
		if(conn == null){
//			log.info("���ݿ�����ʧ��" );
			return null;
		}
//		log.info("��ѯ�����н��յ����ݿ����ݳɹ�"+conn);

		try {
			st = (Statement) conn.createStatement();	//��������ִ�о�̬sql����Statement����st���ֲ�����	
			rs = (ResultSet)st.executeQuery(sql);	//ִ��sql��ѯ��䣬���ز�ѯ���ݵĽ����
			if(!rs.next()){
//				log.info("���ݿ���δ��ѯ������");
				return null;
			}		
//			log.info("���ݿ����Ѿ���ѯ������");
			return rs;
		} catch (SQLException e) {
//			log.info("���ݿ��в�����ʧ��");
			return null;
		}
		
	}
	

	public static void closeDB() throws SQLException{
		if(null != rs)
			rs.close();
//		System.out.println("�˴����ݿ�����resultSet�ɹ��رգ�");
		if(null != st)
			st.close();
//		System.out.println("�˴����ݿ�����statement�ɹ��رգ�");
		if(null != conn){
			conn.close();	//�ر����ݿ�����
//		System.out.println("�˴����ݿ�����connection�ɹ��رգ�");
		}
	}
}