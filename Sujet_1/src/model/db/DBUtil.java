package model.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
	
	public static final String TB_BIOGRAPHIE = "biographie";
	public static final String TB_COMPETENCE_DESX = "competence_desc";
	public static final String TB_PASSION_DESC = "passion_desc";
	
	private static DataSource ds;

	private static DataSource getDataSource() throws DatabaseAccessError{

		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Sujet_1");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new DatabaseAccessError("Database connection failed.", e);
		}
		return ds;

	}

	public static Connection getConnection() throws DatabaseAccessError {
		try {
			return getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DatabaseAccessError("Database connection failed.", e);
		}
	}

	public static void dropConnection(Connection con) throws DatabaseAccessError {

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DatabaseAccessError("Database close error.", e);
			}
		}
	}
}
