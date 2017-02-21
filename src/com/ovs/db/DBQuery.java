package com.ovs.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import com.ovs.model.MyLog;


/**
 * 
 * 返回查询结果   rs表示成功 null表示操作失败 
 */
public class DBQuery {

	/* 查询数据库，输出符合要求的记录的情况*/
	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
	public static ResultSet query(String sql ) {
//		Logger log = MyLog.Log("DBQuery","./log/DBQuery%g.log");//创建一个log对象		
//	    log.info("函数DBQuery日志"); 
		conn = DBConnection.getConnection();	//同样先要获取连接，即连接到数据库
		if(conn == null){
//			log.info("数据库连接失败" );
			return null;
		}
//		log.info("查询函数中接收到数据库数据成功"+conn);

		try {
			st = (Statement) conn.createStatement();	//创建用于执行静态sql语句的Statement对象，st属局部变量	
			rs = (ResultSet)st.executeQuery(sql);	//执行sql查询语句，返回查询数据的结果集
			if(!rs.next()){
//				log.info("数据库中未查询到数据");
				return null;
			}		
//			log.info("数据库中已经查询到数据");
			return rs;
		} catch (SQLException e) {
//			log.info("数据库中查数据失败");
			return null;
		}
		
	}
	

	public static void closeDB() throws SQLException{
		if(null != rs)
			rs.close();
//		System.out.println("此次数据库连接resultSet成功关闭！");
		if(null != st)
			st.close();
//		System.out.println("此次数据库连接statement成功关闭！");
		if(null != conn){
			conn.close();	//关闭数据库连接
//		System.out.println("此次数据库连接connection成功关闭！");
		}
	}
}