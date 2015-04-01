package model.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import model.Evaluator;
import model.Owner;
import model.User;
import model.db.exception.DatabaseAccessError;

public class UserDB {
	
	public static boolean checkLogin(String login,String password) throws DatabaseAccessError, ClassNotFoundException, SQLException, NamingException{

		boolean isLogin = false;
		
		Connection con = DBUtils.getConnection();
		Statement stmt = con.createStatement();
		
		String sql_owner = "SELECT owner_id FROM owner WHERE email='" + login + "' AND password='" + password + "'";
		ResultSet rs = stmt.executeQuery(sql_owner);
		while (rs.next()) {
			if (rs.getInt("owner_id") > 0) 
				isLogin = true;
		}
		
		String sql_evaluator = "SELECT evaluator_id FROM evaluator WHERE email='" + login + "' AND password='" + password + "'";
		rs = stmt.executeQuery(sql_evaluator);
		while (rs.next()) {
			if (rs.getInt("evaluator_id") > 0)
				isLogin = true;
		}
		
		con.close();
		return isLogin;
	}
	
	public static User getUser(String login) {

		User user = null;
		Connection con = null;
		Statement stmt = null;
		try {
			con = DBUtils.getConnection();
			stmt = con.createStatement();
			String sql_owner = "SELECT * FROM owner WHERE email='" + login + "'";
			ResultSet rs = stmt.executeQuery(sql_owner);
			while (rs.next()) {
				if (rs.getInt("owner_id") > 0) {
					// Retrieve by column name
					String name = rs.getString("username");
					String email = rs.getString("email");
					String password = rs.getString("password");
					int id = rs.getInt("owner_id");
					user = new Owner(email, name, password, id);
				}
			}
			String sql_evaluator = "SELECT * FROM evaluator WHERE email='" + login + "'";
			rs = stmt.executeQuery(sql_evaluator);
			while (rs.next()) {
				if (rs.getInt("evaluator_id") > 0) {
					// Retrieve by column name
					String name = rs.getString("username");
					String email = rs.getString("email");
					String password = rs.getString("password");
					int id = rs.getInt("evaluator_id");
					user = new Evaluator(email, name, password, id);

				}
			}
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return user;
		
	}
	
	
	public static Owner getOwner(int owner_id) {
		
		Owner owner = null;
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = DBUtils.getConnection();
			stmt = con.createStatement();
			String sql_owner = "SELECT * FROM owner WHERE owner_id=" + owner_id;
			ResultSet rs = stmt.executeQuery(sql_owner);
			while (rs.next()) {
				// Retrieve by column name
				String name = rs.getString("username");
				String email = rs.getString("email");
				String password = rs.getString("password");
				int id = rs.getInt("owner_id");
				owner = new Owner(email, name, password, id);
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
		
		return owner;
	}
	
	
	public static Evaluator getEvaluator(int evaluator_id) {
		
		Evaluator evaluator = null;
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = DBUtils.getConnection();
			stmt = con.createStatement();
			String sql_evaluator = "SELECT * FROM evaluator WHERE evaluator_id=" + evaluator_id;
			ResultSet rs = stmt.executeQuery(sql_evaluator);
			while (rs.next()) {
				// Retrieve by column name
				String name = rs.getString("username");
				String email = rs.getString("email");
				String password = rs.getString("password");
				int id = rs.getInt("evaluator_id");
				evaluator = new Evaluator(email, name, password, id);
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
		
		return evaluator;
	}
	
}
