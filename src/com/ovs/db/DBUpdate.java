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
public class DBUpdate {

	/* 更新符合要求的记录，并返回更新的记录数目*/
	public static int update(String sql) {
//		Logger log = MyLog.Log("DBUpdate","./log/DBUpdate%g.log");//创建一个log对象		
//	    log.info("函数DBUpdate日志"); 
		Connection conn = DBConnection.getConnection();	// 首先要获取连接，即连接到数据库
		if(conn == null){
//			log.info("数据库连接失败" );
			return -1;
		}
		try {
			//sql = "INSERT INTO `mytable` VALUES ('刘四', 'n', '2013-07-09', 'USA');";	// 插入数据的sql语句
			conn.setAutoCommit(false);//取消自动提交
			Statement st = (Statement) conn.createStatement();	// 创建用于执行静态sql语句的Statement对象
			try{
				int count = st.executeUpdate(sql);	// 执行插入操作的sql语句，并返回插入数据的个数			
//				log.info("向表中插入 " + count + " 条数据");	//输出插入操作的处理结果
			    conn.commit();//提交
			    st.close();
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
//			log.info("更新数据失败");
			return 0;
		}
	}
}
