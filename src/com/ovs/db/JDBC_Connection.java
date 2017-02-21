package com.ovs.db;

import java.sql.*;

public class JDBC_Connection {
	static String drivername = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/ovs";
	static String username = "root";
	static String password = "root";

	static {
		try {
			Class.forName(drivername);
			System.out.println("驱动创建成功！！！");
		} catch (ClassNotFoundException e) {
			System.out.println("创建驱动失败！");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("数据库连接成功！！！");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败！！！");
		}

		return conn;
	}

	public static void free(ResultSet rs, Connection conn, Statement stmt) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			System.out.println("关闭ResultSet失败！！！");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("关闭Connection失败！！！");
				e.printStackTrace();
			} finally {
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException e) {
					System.out.println("关闭Statement失败！！！");
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		JDBC_Connection.getConnection();
	}

}
