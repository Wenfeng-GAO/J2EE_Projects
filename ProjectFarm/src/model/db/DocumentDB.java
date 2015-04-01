package model.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingException;

import model.Document;
import model.Project;
import model.exception.InvalidDataException;

public class DocumentDB {
	
	public static void saveDocument(Document document) {
		
		// Connection to database
		Connection con = null;
		Statement stmt = null;
		try {
			con = DBUtils.getConnection();
			stmt = con.createStatement();
			// Insert project data to the database
			String sql_document = "INSERT INTO document" 
					+ "(project_id, doc_path, created_date)"
					+ "VALUES"
					+ "(" + document.getProject().getId() + ", " 
					+ "'" + document.getDocumentPath() + "', "
					+ "now() )";
			
			System.out.println(sql_document);
			stmt.executeUpdate(sql_document);
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
	
	public static List<Document> getDocumentsOfProject(Project project) throws InvalidDataException {
		
		List<Document> documents = new LinkedList<Document>();
		
		Connection con = null;;
		Statement stmt = null;
		int project_id = project.getId();
		try {
			con = DBUtils.getConnection();
			stmt = con.createStatement();
			// Insert project data to the database
			String sql_evaluation = "SELECT * FROM document WHERE project_id=" + project_id;
			
			ResultSet rs = stmt.executeQuery(sql_evaluation);
			
			while (rs.next()) {
				String doc_path = rs.getString("doc_path");
				Date created_date = rs.getDate("created_date");
				Document doc = new Document(doc_path);
				doc.setAdded(created_date);
				documents.add(doc);
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
		
		return documents;
		
	}

}
