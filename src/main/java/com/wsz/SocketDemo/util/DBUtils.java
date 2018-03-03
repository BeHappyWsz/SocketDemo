package com.wsz.SocketDemo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 数据库连接类
 * @author wsz
 * @date 2018年3月3日
 */
public class DBUtils {

	private static String driverClass = "com.mysql.jdbc.Driver";
	private static String url ="jdbc:mysql://localhost:3306/socketdemo";
	private static String username = "wsz";
	private static String password = "wsz";
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
