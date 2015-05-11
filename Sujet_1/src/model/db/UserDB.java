package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;
import model.dao.UserDAO;


public class UserDB implements UserDAO {
	
	/**
	 * Method to check if the login is valid.
	 * @param email
	 * @param password
	 * @return true if the login is valid
	 * @throws DatabaseAccessError
	 */
	public boolean isValidUser(String email, String password) throws DatabaseAccessError {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT user_id FROM user WHERE email='" + email + "' AND password='" + password + "'");
			while(rs.next()) {
				if(rs.getInt("user_id") > 0) {
					return true;
				}
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseAccessError("Database connection failed.", e);
		} finally {
			DBUtil.dropConnection(con);
		}

	}


	@Override
	public boolean updateUser(User user) throws DatabaseAccessError {
		
		boolean isUpdated = false;
		Connection con = DBUtil.getConnection();
		
		try {
			
			PreparedStatement stmt = con.prepareStatement("UPDATE user SET nom = ?, prenom = ?, email = ?, "
					+ "password = ?, poste = ?, biographie = ? WHERE user_id = ?");
			stmt.setString(1, user.getNom());
			stmt.setString(2, user.getPrenom());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPassword());
			stmt.setString(5, user.getPoste());
			stmt.setString(6, user.getBiographie());
			stmt.setInt(7, user.getId());
			
			stmt.executeUpdate();
			
			stmt.close();
			
			isUpdated = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseAccessError("Database error(update user)", e);
		} finally {
			DBUtil.dropConnection(con);
		}
		
		return isUpdated;
	}

	@Override
	public User getUserByEmail(String email) throws DatabaseAccessError {
		
		User user = null;
		Connection con = null;
		
		try {
			
			con = DBUtil.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM user WHERE email = ?");
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseAccessError("Failed to get the User by the email", e);
		} finally {
			DBUtil.dropConnection(con);;
		}
		
		return user;
		
	}


	@Override
	public List<User> findUsersBy(String s) throws DatabaseAccessError {
		
		String[] userSqls = {"nom", "prenom", "email", "poste"};
		List<User> users = new ArrayList<User>();
		
		for (int i = 0; i < userSqls.length; i++) {
			users = combine(users, findUsersBy(userSqls[i], s));
		}
		
		String[] forceSqls = {"competence", "passion"};
		for (int i = 0; i < forceSqls.length; i++) {
			users = combine(users, findUsersByForces(forceSqls[i], s));
		}
		
		return users;
	}
	
	private List<User> findUsersBy(String element, String search) throws DatabaseAccessError {
		
		List<User> users = new ArrayList<User>();
		Connection con = DBUtil.getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM user WHERE " + element + " LIKE ?");
			stmt.setString(1, "%" + search + "%");
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				users.add(user);
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseAccessError("Database error(query user).", e);
		} finally {
			DBUtil.dropConnection(con);
		}
		
		return users;
	}
	
	private List<User> findUsersByForces(String force, String search) throws DatabaseAccessError {
		
		List<User> users = new ArrayList<User>();
		Connection con = DBUtil.getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT user.* FROM user INNER JOIN " 
					+ force + " ON " + force + "." + "titre LIKE ?" + " AND user.user_id = "
					+ force + ".user_id");
			stmt.setString(1, "%" + search + "%");
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				users.add(user);
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseAccessError("Database error(query user).", e);
		} finally {
			DBUtil.dropConnection(con);
		}
		
		return users;
	} 
	
	private List<User> combine(List<User>a, List<User>b) {
		List<User> users = a;
		for (User userB : b) {
			for (User userA : a) {
				if (userB.getId() == userA.getId())
					b.remove(userB);
			}
		}
		for (User userB : b) {
			users.add(userB);
		}
		return users;
	}


}
