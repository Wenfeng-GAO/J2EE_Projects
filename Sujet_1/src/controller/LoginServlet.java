package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 4249780684684026453L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Get parameters
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		// Quick test (Connect to database later)
		boolean loginTest = false;
		if (email.equals("john@example.com") && password.equals("123")) {
			loginTest = true;
		}
		
		// Create session attributes
		if (loginTest == true) {
			
			// Connect to database to get name later
			
			// Create user session
			req.getSession().setAttribute("user", new User("email"));
		} else {
			req.getSession().setAttribute("wrongPwd", "wrong");
		}
		
		// Dispatch
		RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);
		
		
	}
	
	

}
