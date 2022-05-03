package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.User;

public class UserDAO {
	public static void main(String[] args) {
		
	}
	
	public User getUser(String name, String password) {
		User result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?charactorEncoding=UTF-8","root","admin");
			
			String sql = "select * from user where name = ? and password = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = new User();
				result.setName(name);
				result.setId(rs.getInt("id"));
				result.setPassword(password);
			}
			ps.close();
			c.close();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
