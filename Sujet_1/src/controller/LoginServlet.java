package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.db.DatabaseAccessError;
import model.db.UserDB;
import model.exception.InvalidLoginException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 4249780684684026453L;
	
	private UserDB userDB = new UserDB();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Session timeout
		req.getSession().setMaxInactiveInterval(240*60);
		
		// Get parameters
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try {
			if (userDB.isValidUser(email, password)) {
				req.getSession().setAttribute(SessionAttributes.LOGIN_VALID, userDB.getUserByEmail(email));
			} else {
				req.getSession().setAttribute(SessionAttributes.LOGIN_FAILED, new InvalidLoginException("Email or password invalid."));
			}
		} catch (DatabaseAccessError e) {
			e.printStackTrace();
			req.getSession().setAttribute(SessionAttributes.LOGIN_FAILED, new DatabaseAccessError("Database connection error."));
		}
		
		// Redirect
		resp.sendRedirect("index.jsp");
		
		
	}
	
	

}
