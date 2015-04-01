package model.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

import model.Category;

public class CategoryDB {
	
	public static Category getCategory(String description) {
		Connection con = null;
		Statement stmt = null;
		Category category = null;
		
		try {
			con = DBUtils.getConnection();
			stmt = con.createStatement();
			
			String sql_category = "SELECT * FROM category WHERE description=" + "'" + description + "'";
			ResultSet rs = stmt.executeQuery(sql_category);
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("category_id");
				category = new Category(description, id);
			}
		
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return category;

	}
	
	public static Category getCategory(int category_id) {
		Connection con = null;
		Statement stmt = null;
		Category category = null;
		
		try {
			con = DBUtils.getConnection();
			stmt = con.createStatement();
			
			String sql_category = "SELECT * FROM category WHERE category_id=" + category_id;
			ResultSet rs = stmt.executeQuery(sql_category);
			while (rs.next()) {
				// Retrieve by column name
				String description = rs.getString("description");
				int id = rs.getInt("category_id");
				category = new Category(description, id);
			}
		
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return category;
	}

	public static List<Category> getCategories() {
		
		Connection con = null;
		Statement stmt = null;
		List<Category> categories = new LinkedList<Category>();
		
		try {
			con = DBUtils.getConnection();
			stmt = con.createStatement();
			
			String sql_category = "SELECT * FROM category";
			ResultSet rs = stmt.executeQuery(sql_category);
			while (rs.next()) {
				// Retrieve by column name
				String description = rs.getString("description");
				int id = rs.getInt("category_id");
				categories.add(new Category(description, id));
			}
		
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return categories;
	}

}
