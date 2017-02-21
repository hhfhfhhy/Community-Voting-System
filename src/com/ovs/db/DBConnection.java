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
 * 返回连接结果   null表示操作失败 
 */
public class DBConnection {
	/* 获取数据库连接的函数*/
	public static Connection getConnection() {
//		Logger log = MyLog.Log("DBConnection","./log/DBConnection%g.log");//创建一个log对象		
//        log.info("函数DBConnection日志"); 
		Connection con = null;	//创建用于连接数据库的Connection对象
		DataSource ds = null;
		try {
//			Class.forName("com.mysql.jdbc.Driver");
			Context initContext = new InitialContext(); 
			Context envContext = (Context)initContext.lookup("java:/comp/env"); 
			ds = (DataSource)envContext.lookup("jdbc/OVS"); 
		} catch (Exception e1) {
//			log.info("加载数据库驱动失败");	
			System.out.println("加载数据池加载失败"+e1);
			return null;
		}// 加载Mysql数据驱动
		try {
//			con = DriverManager.getConnection("jdbc:mysql://49.123.82.11:3306/douqutravel?user=root&password=root");
//			con = DriverManager.getConnection("jdbc:mysql://49.123.80.48:3306/douqutravle?user=jack&password=v73alice");

//			con = DriverManager.getConnection("jdbc:mysql://115.157.253.240:3306/test?user=jack&password=v73alice");
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=v73alice");

//			con = DriverManager.getConnection("jdbc:mysql://192.168.0.1:3306/test?user=jack&password=v73alice");
//			log.info("数据库连接成功了");
//			return con;	//返回所建立的数据库连接
			con = ds.getConnection();// 创建数据连接
			System.out.println("数据库连接成功了");	
			return con;	//返回所建立的数据库连接
		} catch (Exception e) {
//			log.info("数据库连接失败" + e.getMessage());
			System.out.println("数据库连接失败" + e.getMessage());
			return null;
		}
	}
}