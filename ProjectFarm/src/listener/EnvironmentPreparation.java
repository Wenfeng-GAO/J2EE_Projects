package listener;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import model.db.DBUtils;

/**
 * Prepare the environment
 * Create the tables in database ProjectFarm
 * @author Wenfeng
 *
 */
public class EnvironmentPreparation implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent evt) {
		
		try {

			evt.getServletContext().log("Project Farm - Preparing environment");
			
			Connection con = DBUtils.getConnection();

			Statement stmt = con.createStatement();

//			stmt.executeUpdate("CREATE TABLE user "
//					+ "(id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," 
//					+ "email VARCHAR(30),"
//					+ "username VARCHAR(30)," 
//					+ "password VARCHAR(30))"
//					);

			con.close();

			evt.getServletContext().log("Project Farm - Environment is OK");

		} catch (SQLException e) {
			// ignore. table already exists for MYSQL
			if (e.getErrorCode() == 1050 && e.getSQLState().equals("42S01")) {
				return;
			}

			System.out.println(e.getErrorCode());
			System.out.println(e.getSQLState());

			throw new RuntimeException("Error executing listener", e);
		} catch (ClassNotFoundException | NamingException e) {
			throw new RuntimeException("Error executing listener", e);
		}		
	}

}
