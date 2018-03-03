package com.wsz.SocketDemo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wsz.SocketDemo.entity.Files;
import com.wsz.SocketDemo.util.DBUtils;
/**
 * 文件操作Service
 * @author wsz
 * @date 2018年3月3日
 */
public class FileService {

	private Connection conn = null;
	
	private PreparedStatement pstmt = null;
	
	private ResultSet rs = null;
	
	public boolean save(Files file) {
		String sql = "insert into t_file(fname,fcontent) values(?,?)";
		try {
			conn = DBUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, file.getFname());
			pstmt.setBytes(2, file.getFcontent());
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
