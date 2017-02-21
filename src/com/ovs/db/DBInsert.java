package com.ovs.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import com.ovs.model.MyLog;
/**
 * 
 *����1��ʾ�ɹ� 0��ʾ����ʧ�� -1��ʾ���ݿ�����ʧ��
 */
public class DBInsert {
	/* �������ݼ�¼���������������ݼ�¼��*/
	public static int insert(String sql) {
//		Logger log = MyLog.Log("DBInsert","./log/DBInsert%g.log");//����һ��log����		
//        log.info("����DBInsert��־"); 
		Connection conn = DBConnection.getConnection();	// ����Ҫ��ȡ���ӣ������ӵ����ݿ�
		if(conn == null){
//			log.info("���ݿ�����ʧ��" );
			return -1;
		}
		try {
			//sql = "INSERT INTO `mytable` VALUES ('����', 'n', '2013-07-09', 'USA');";	// �������ݵ�sql���
			conn.setAutoCommit(false);//ȡ���Զ��ύ
			Statement st = (Statement) conn.createStatement();	// ��������ִ�о�̬sql����Statement����
//			log.info("��insert�����д�������statement�ɹ�   " + st );	
			try{
				int count = st.executeUpdate(sql);	// ִ�в��������sql��䣬�����ز������ݵĸ���			
//				log.info("��insert����������в��� " + count + " ������");	//�����������Ĵ�����
			    conn.commit();//�ύ
			    st.close();
			    conn.close();	//�ر����ݿ�����
			    return count ;
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
//			log.info("��������ʧ��" + e.getMessage());
			return 0;
		}
	}
}
