package com.wsz.SocketDemo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wsz.SocketDemo.entity.User;
import com.wsz.SocketDemo.util.DBUtils;
/**
 * 用户操作类
 * @author wsz
 * @date 2018年3月3日
 */
public class UserService {

	private Connection conn = null;
	
	private PreparedStatement pstmt = null;
	
	private ResultSet rs = null;
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public boolean login(User user) {
		String sql = "select * from t_user where username =? and password = ?";
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeAll(rs,pstmt,conn);
		}
		return false;
	}
	
	/**
	 * 注册
	 * @param user
	 */
	public boolean register(User user) {
		String sql ="insert into t_user(username,password) values(?,?)";
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			DBUtils.closeAll(rs,pstmt,conn);
		}
		return true;
	}
}
