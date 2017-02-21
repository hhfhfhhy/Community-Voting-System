/*package com.ovs.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import com.ovs.model.MyLog;
*//**
 * 
 *返回1表示成功 0表示操作失败 -1表示数据库连接失败
 *//*
public class DBDelete {
	 删除符合要求的记录，输出情况
	public static int delete(String sql) {
//		Logger log = MyLog.Log("DBDelete","./log/DBDelete%g.log");//创建一个log对象		
//        log.info("函数DBDelete日志"); 
		Connection conn = JDBC_Connection.getConnection();	// 首先要获取连接，即连接到数据库
		if(conn == null){
//			log.info("数据库连接失败" );
			return -1;
		}
		try {
			conn.setAutoCommit(false);//取消自动提交
			Statement stmt = (Statement) conn.createStatement();	// 创建用于执行静态sql语句的Statement对象
			try{
				int count = stmt.executeUpdate(sql);	// 执行插入操作的sql语句，并返回插入数据的个数			
//				log.info("向表中删除 " + count + " 条数据");	//输出删除操作的处理结果
			    conn.commit();//提交
			    stmt.close();
			    conn.close();	//关闭数据库连接
			    return count;
			}catch(Exception e){
			    try{
			        conn.rollback();//回滚
			        return 0;
			    }catch(Exception ex){
//			        log.info("回滚失败");
			        return 0;
			    }
			}
		} catch (SQLException e) {
//			log.info("删除数据失败");
			return 0;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
		
	}
	
}
*/