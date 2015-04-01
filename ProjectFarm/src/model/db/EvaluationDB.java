package model.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

import model.Evaluation;
import model.Evaluator;
import model.Project;
import model.exception.InvalidDataException;

public class EvaluationDB {

	public static void saveEvaluation(Evaluation evaluation) {
		
		// Connection to database
		Connection con = null;
		Statement stmt = null;
		try {
			con = DBUtils.getConnection();
			stmt = con.createStatement();
			// Insert project data to the database
			String sql_evaluation = "INSERT INTO evaluation" 
					+ "(evaluator_id, project_id, attractiveness, risk)"
					+ "VALUES"
					+ "(" + evaluation.getEvaluator().getId() + ", " 
					+ evaluation.getProject().getId() + ", "
					+ evaluation.getAttractiveness() + ", "
					+ evaluation.getRiskLevel() + ")";
			
			System.out.println(sql_evaluation);
			stmt.executeUpdate(sql_evaluation);
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
	
	public static List<Evaluation> getEvaluationsOfProject(Project project) throws InvalidDataException {
		
		List<Evaluation> evaluations = new LinkedList<Evaluation>();
		
		Connection con = null;;
		Statement stmt = null;
		int project_id = project.getId();
		try {
			con = DBUtils.getConnection();
			stmt = con.createStatement();
			// Insert project data to the database
			String sql_evaluation = "SELECT * FROM evaluation WHERE project_id=" + project_id;
			
			ResultSet rs = stmt.executeQuery(sql_evaluation);
			
			while (rs.next()) {
				int evaluator_id = rs.getInt("evaluator_id");
				int attractiveness = rs.getInt("attractiveness");
				int risk = rs.getInt("risk");
				Evaluator evaluator = UserDB.getEvaluator(evaluator_id);
				Evaluation evaluation = new Evaluation(evaluator, attractiveness, risk);
				evaluations.add(evaluation);
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
		
		return evaluations;
		
	}
}


