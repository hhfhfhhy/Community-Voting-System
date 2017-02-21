package com.ovs.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import com.ovs.model.MyLog;
/**
 * 
 *返回1表示成功 0表示操作失败 -1表示数据库连接失败
 */
public class DBInsert {
	/* 插入数据记录，并输出插入的数据记录数*/
	public static int insert(String sql) {
//		Logger log = MyLog.Log("DBInsert","./log/DBInsert%g.log");//创建一个log对象		
//        log.info("函数DBInsert日志"); 
		Connection conn = DBConnection.getConnection();	// 首先要获取连接，即连接到数据库
		if(conn == null){
//			log.info("数据库连接失败" );
			return -1;
		}
		try {
			//sql = "INSERT INTO `mytable` VALUES ('刘四', 'n', '2013-07-09', 'USA');";	// 插入数据的sql语句
			conn.setAutoCommit(false);//取消自动提交
			Statement st = (Statement) conn.createStatement();	// 创建用于执行静态sql语句的Statement对象
//			log.info("在insert函数中创建对象statement成功   " + st );	
			try{
				int count = st.executeUpdate(sql);	// 执行插入操作的sql语句，并返回插入数据的个数			
//				log.info("在insert函数中向表中插入 " + count + " 条数据");	//输出插入操作的处理结果
			    conn.commit();//提交
			    st.close();
			    conn.close();	//关闭数据库连接
			    return count ;
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
//			log.info("插入数据失败" + e.getMessage());
			return 0;
		}
	}
}
