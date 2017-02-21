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
			System.out.println("���������ɹ�������");
		} catch (ClassNotFoundException e) {
			System.out.println("��������ʧ�ܣ�");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("���ݿ����ӳɹ�������");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("���ݿ�����ʧ�ܣ�����");
		}

		return conn;
	}

	public static void free(ResultSet rs, Connection conn, Statement stmt) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			System.out.println("�ر�ResultSetʧ�ܣ�����");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("�ر�Connectionʧ�ܣ�����");
				e.printStackTrace();
			} finally {
				try {
					if (stmt != null)
						stmt.close();
				} catch (SQLException e) {
					System.out.println("�ر�Statementʧ�ܣ�����");
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		JDBC_Connection.getConnection();
	}

}
