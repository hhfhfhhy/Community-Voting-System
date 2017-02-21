package com.ovs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ovs.model.MyLog;
/**
 * 
 * �������ӽ��   null��ʾ����ʧ�� 
 */
public class DBConnection {
	/* ��ȡ���ݿ����ӵĺ���*/
	public static Connection getConnection() {
//		Logger log = MyLog.Log("DBConnection","./log/DBConnection%g.log");//����һ��log����		
//        log.info("����DBConnection��־"); 
		Connection con = null;	//���������������ݿ��Connection����
		DataSource ds = null;
		try {
//			Class.forName("com.mysql.jdbc.Driver");
			Context initContext = new InitialContext(); 
			Context envContext = (Context)initContext.lookup("java:/comp/env"); 
			ds = (DataSource)envContext.lookup("jdbc/OVS"); 
		} catch (Exception e1) {
//			log.info("�������ݿ�����ʧ��");	
			System.out.println("�������ݳؼ���ʧ��"+e1);
			return null;
		}// ����Mysql��������
		try {
//			con = DriverManager.getConnection("jdbc:mysql://49.123.82.11:3306/douqutravel?user=root&password=root");
//			con = DriverManager.getConnection("jdbc:mysql://49.123.80.48:3306/douqutravle?user=jack&password=v73alice");

//			con = DriverManager.getConnection("jdbc:mysql://115.157.253.240:3306/test?user=jack&password=v73alice");
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=v73alice");

//			con = DriverManager.getConnection("jdbc:mysql://192.168.0.1:3306/test?user=jack&password=v73alice");
//			log.info("���ݿ����ӳɹ���");
//			return con;	//���������������ݿ�����
			con = ds.getConnection();// ������������
			System.out.println("���ݿ����ӳɹ���");	
			return con;	//���������������ݿ�����
		} catch (Exception e) {
//			log.info("���ݿ�����ʧ��" + e.getMessage());
			System.out.println("���ݿ�����ʧ��" + e.getMessage());
			return null;
		}
	}
}