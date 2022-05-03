package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Product;

public class ProductDAO {
	public static void main(String[] args) {
		//System.out.println(new ProductDAO().ListProducts().size());
		System.out.println(new ProductDAO().getProduct(1).getName());
	}
	
	public List<Product> ListProducts(){
		List<Product> products = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?charactorEncoding=UTF-8","root","admin");
			String sql = "select * from product order by id desc";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				int id = rs.getInt("id");
				String name = rs.getString("name");
				float price = rs.getFloat("price");
				
				product.setId(id);
				product.setName(name);
				product.setPrice(price);
				products.add(product);
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
		return products;
	}
	
	public Product getProduct(int id) {
		Product result = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?charactorEncoding=UTF-8","root","admin");
			String sql = "select * from product where id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = new Product();
				result.setId(id);
				String name = rs.getString("name");
				float price = rs.getFloat("price");
				result.setName(name);
				result.setPrice(price);
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
