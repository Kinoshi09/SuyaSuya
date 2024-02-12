package com.suyasuya.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.suyasuya.model.User;

public class UserDao {
	public User findOne(String id) throws ClassNotFoundException, SQLException {
		User user = null;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "SELECT * FROM user WHERE user_id = ?";
		
		try {
			Class.forName(JDBCInfo.DRIVER);
			con = DriverManager.getConnection(JDBCInfo.URL, JDBCInfo.ID, JDBCInfo.PASSWORD);
			
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setId(rs.getString("user_id"));
				user.setPassword(rs.getBytes("password_hash"));
				user.setSalt(rs.getBytes("salt"));
			}
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}
		
		return user;
	}
}
