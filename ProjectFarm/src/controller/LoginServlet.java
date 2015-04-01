package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Owner;
import model.User;
import model.db.UserDB;
import model.db.exception.DatabaseAccessError;

/**
 * Check credentials, add user in the session or assign an error.
 * @author Wenfeng
 *
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 4249780684684026453L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Get parameters 
		String login = req.getParameter("email");
		String password = req.getParameter("password");
		
		
		// Check Login
		try {
			
			if (UserDB.checkLogin(login, password)) {
				
				User user = UserDB.getUser(login);
				req.getSession().setAttribute("user", user);
				
				if (user instanceof Owner) {
					resp.sendRedirect("owner.jsp");
				} else {
					resp.sendRedirect("evaluation_list_projects.jsp");
				}
				
				
			} else {
				
				req.getSession().setAttribute("WRONG_PWD", "wrong");
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
				
			}
		
		} catch (DatabaseAccessError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
