package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Competence;
import model.Force;
import model.User;

public class CompetenceDB extends ForceDB {

	
	@Override
	public List<Force> findForces(User user) throws DatabaseAccessError {
		
		List<Force> competences = new ArrayList<Force>();
		Connection con = DBUtil.getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM competence WHERE user_id = ?");
			stmt.setInt(1, user.getId());
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Competence competence = new Competence(rs.getInt(1), rs.getString(2), rs.getString(3));
				competences.add(competence);
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseAccessError("Database error(table competence).", e);
		} finally {
			DBUtil.dropConnection(con);
		}
		
		return competences;
	}


	@Override
	public boolean dropForce(Force force) throws DatabaseAccessError {
		
		boolean isDropped = false;
		Connection con = DBUtil.getConnection();
		
		try {
			
			PreparedStatement stmt = con.prepareStatement("DELETE FROM competence WHERE user_id = ? AND titre = ? AND description = ?");
			stmt.setInt(1, force.getId());
			stmt.setString(2, force.getTitre());
			stmt.setString(3, force.getDescription());
			
			stmt.executeUpdate();
			
			stmt.close();
			
			isDropped = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseAccessError("Database error(insert into competence)", e);
		} finally {
			DBUtil.dropConnection(con);
		}
		
		return isDropped;
	}
	


	@Override
	public boolean addForce(Force force) throws DatabaseAccessError {
		
		boolean isAdded = false;
		Connection con = DBUtil.getConnection();
		
		try {
			
			PreparedStatement stmt = con.prepareStatement("INSERT INTO competence (user_id, titre, description) VALUES (?,?,?)");
			stmt.setInt(1, force.getId());
			stmt.setString(2, force.getTitre());
			stmt.setString(3, force.getDescription());
			
			stmt.executeUpdate();
			
			stmt.close();
			
			isAdded = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseAccessError("Database error(insert into competence)", e);
		} finally {
			DBUtil.dropConnection(con);
		}
		
		return isAdded;
		
	}
	
}
