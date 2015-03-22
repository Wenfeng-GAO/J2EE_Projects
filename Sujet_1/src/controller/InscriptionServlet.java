package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {

	private static final long serialVersionUID = 6155152479365720973L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Get parameters
		String nom = req.getParameter("nom"),
			   prenom = req.getParameter("prenom"),
			   sex = req.getParameter("sex"),
			   email = req.getParameter("email"),
			   pwd = req.getParameter("password"),
			   pwdConfi = req.getParameter("passwordconfi");
		
		// Verify if the password is correct
		if (!pwd.equals(pwdConfi)) {
			
			req.getSession().setAttribute("alert", "alert");
			
			// Dispatch
			RequestDispatcher dispatcher = req.getRequestDispatcher("/inscription.jsp");
			dispatcher.forward(req, resp);
		} else {
		
			// Connect to database and store the data later
		
		
			
			// Set session attributes
			User user = new User(email);
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setPassword(pwd);
			user.setSex(sex);
			req.getSession().setAttribute("user", user);
			
			// Dispatch
			RequestDispatcher dispatcher = req.getRequestDispatcher("/modify.jsp");
			dispatcher.forward(req, resp);
			
		}
		
	}

}
