/*package com.ovs.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import com.ovs.model.MyLog;
*//**
 * 
 *����1��ʾ�ɹ� 0��ʾ����ʧ�� -1��ʾ���ݿ�����ʧ��
 *//*
public class DBDelete {
	 ɾ������Ҫ��ļ�¼��������
	public static int delete(String sql) {
//		Logger log = MyLog.Log("DBDelete","./log/DBDelete%g.log");//����һ��log����		
//        log.info("����DBDelete��־"); 
		Connection conn = JDBC_Connection.getConnection();	// ����Ҫ��ȡ���ӣ������ӵ����ݿ�
		if(conn == null){
//			log.info("���ݿ�����ʧ��" );
			return -1;
		}
		try {
			conn.setAutoCommit(false);//ȡ���Զ��ύ
			Statement stmt = (Statement) conn.createStatement();	// ��������ִ�о�̬sql����Statement����
			try{
				int count = stmt.executeUpdate(sql);	// ִ�в��������sql��䣬�����ز������ݵĸ���			
//				log.info("�����ɾ�� " + count + " ������");	//���ɾ�������Ĵ�����
			    conn.commit();//�ύ
			    stmt.close();
			    conn.close();	//�ر����ݿ�����
			    return count;
			}catch(Exception e){
			    try{
			        conn.rollback();//�ع�
			        return 0;
			    }catch(Exception ex){
//			        log.info("�ع�ʧ��");
			        return 0;
			    }
			}
		} catch (SQLException e) {
//			log.info("ɾ������ʧ��");
			return 0;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
		
	}
	
}
*/