package model.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

import model.Category;
import model.Owner;
import model.Project;
import model.exception.InvalidDataException;

public class ProjectDB {

	public static void saveProject(Project project) {
		
		// Connection to database
		Connection con = null;
		Statement stmt = null;
		try {
			con = DBUtils.getConnection();
			stmt = con.createStatement();
			// Insert project data to the database
			String sql_project = "INSERT INTO project" 
					+ "(owner_id, category_id, acronym, description, funding_duration, budget, created_date)"
					+ "VALUES"
					+ "(" + project.getOwner().getId() + ", " 
					+ project.getCategory().getId() + ", "
					+ "'" + project.getAcronym() + "'" + ", "
					+ "'" + project.getDescription() + "'" + ", "
					+ project.getFundingDuration() + ", "
					+ project.getBudget() + ", " 
					+ "now()" + ")";
			
			System.out.println(sql_project);
			stmt.executeUpdate(sql_project);
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static Project getProject(String acronym) throws InvalidDataException {
		// Connection to database
		Connection con = null;;
		Statement stmt = null;
		Project project = null;
		try {
			con = DBUtils.getConnection();
			stmt = con.createStatement();
			// Insert project data to the database
			String sql_project = "SELECT * FROM project WHERE acronym='" + acronym + "'";
			
			System.out.println(sql_project);
			ResultSet rs = stmt.executeQuery(sql_project);
			
			while (rs.next()) {
				int project_id = rs.getInt("project_id");
				int owner_id = rs.getInt("owner_id");
				int category_id = rs.getInt("category_id");
				String description = rs.getString("description");
				int funding_duration = rs.getInt("funding_duration");
				double budget = rs.getDouble("budget");
				Date created_date = rs.getDate("created_date");
				Owner owner = UserDB.getOwner(owner_id);
				Category category = CategoryDB.getCategory(category_id);
				project = new Project(acronym, description, funding_duration, budget, owner, category);
				project.setCreated(created_date);
				project.setId(project_id);
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
		
		return project;
	}

	public static List<Project> getProjectsOfOwner(Owner owner) throws InvalidDataException {

		List<Project> projectsOfOwner = new LinkedList<Project>();

		Connection con = null;;
		Statement stmt = null;
		Project project = null;
		int owner_id = owner.getId();
		try {
			con = DBUtils.getConnection();
			stmt = con.createStatement();
			// Insert project data to the database
			String sql_project = "SELECT * FROM project WHERE owner_id=" + owner_id;
			
			ResultSet rs = stmt.executeQuery(sql_project);
			
			while (rs.next()) {
				int category_id = rs.getInt("category_id");
				int project_id = rs.getInt("project_id");
				String acronym = rs.getString("acronym");
				String description = rs.getString("description");
				int funding_duration = rs.getInt("funding_duration");
				double budget = rs.getDouble("budget");
				Date created_date = rs.getDate("created_date");
				Category category = CategoryDB.getCategory(category_id);
				project = new Project(acronym, description, funding_duration, budget, owner, category);
				project.setCreated(created_date);
				project.setId(project_id);
				projectsOfOwner.add(project);
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
		
		return projectsOfOwner;
	
	}
	
	public static List<Project> getAllProjects() throws InvalidDataException {
		// Connection to database
		Connection con = null;;
		Statement stmt = null;
		Project project = null;
		List<Project> projects = new LinkedList<Project>();
		try {
			con = DBUtils.getConnection();
			stmt = con.createStatement();
			// Insert project data to the database
			String sql_project = "SELECT * FROM project";
			
			ResultSet rs = stmt.executeQuery(sql_project);
			
			while (rs.next()) {
				int project_id = rs.getInt("project_id");
				int owner_id = rs.getInt("owner_id");
				int category_id = rs.getInt("category_id");
				String acronym = rs.getString("acronym");
				String description = rs.getString("description");
				int funding_duration = rs.getInt("funding_duration");
				double budget = rs.getDouble("budget");
				Date created_date = rs.getDate("created_date");
				Owner owner = UserDB.getOwner(owner_id);
				Category category = CategoryDB.getCategory(category_id);
				project = new Project(acronym, description, funding_duration, budget, owner, category);
				project.setCreated(created_date);
				project.setId(project_id);
				projects.add(project);
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
		
		return projects;
	
	}
	
}
