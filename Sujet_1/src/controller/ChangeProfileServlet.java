package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.db.DatabaseAccessError;
import model.db.UserDB;

@WebServlet("/ChangeProfileServlet")
public class ChangeProfileServlet extends HttpServlet {

	private static final long serialVersionUID = -1465734615820012050L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Get parameters
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String email = req.getParameter("email");
		String poste = req.getParameter("poste");
		String password = req.getParameter("password");
		String confPassword = req.getParameter("conf_password");
		String biographie = req.getParameter("biographie");
		
		// Get user
		if (req.getSession().getAttribute(SessionAttributes.LOGIN_VALID) == null) {
			resp.sendRedirect("index.jsp");
			return;
		}
		User user = (User) req.getSession().getAttribute(SessionAttributes.LOGIN_VALID);
		
		if (nom == null || prenom == null || email == null || poste == null 
				|| password == null || confPassword == null || biographie == null) {
			req.getSession().setAttribute(SessionAttributes.COMPLETER_ERROR_WARNING, "Netword error, please try again.");
		} else if (!password.equals(confPassword)) {
			req.getSession().setAttribute(SessionAttributes.COMPLETER_ERROR_WARNING, "Mot de passe ne correspond pas.");
		} else {
			boolean isValid = true;
			if (!nom.equals("") && !nom.equals(user.getNom())) {
				if (isUserNameValid(nom)) user.setNom(nom);
				else req.getSession().setAttribute(SessionAttributes.COMPLETER_ERROR_WARNING, "Nom non valide");
				isValid = false;
			}
			if (!prenom.equals("") && !prenom.equals(user.getPrenom())) { 
				if (isUserNameValid(prenom)) user.setPrenom(prenom);
				else req.getSession().setAttribute(SessionAttributes.COMPLETER_ERROR_WARNING, "Prénom non valide");
				isValid = false;
			}
			if (!email.equals("") && !email.equals(user.getEmail())) {
				if (isEmailValid(email)) user.setEmail(email);
				else req.getSession().setAttribute(SessionAttributes.COMPLETER_ERROR_WARNING, "Email non valide");
				isValid = false;
			}
			if (!poste.equals("") && !poste.equals(user.getPoste())) user.setPoste(poste);
			if (!password.equals("") && !password.equals(user.getPassword())) user.setPassword(password);
			if (!biographie.equals("") && !biographie.equals(user.getBiographie())) user.setBiographie(biographie);
			
			try {
				if (isValid)
					if (new UserDB().updateUser(user)) 
						req.getSession().setAttribute(SessionAttributes.COMPLETER_SUCCESS_INFO, "Félicitations! Votre profil a été bien créé.");
			} catch (DatabaseAccessError e) {
				e.printStackTrace();
				req.getSession().setAttribute(SessionAttributes.COMPLETER_ERROR_WARNING, "Network error(update user)");
			}
		}
		
		resp.sendRedirect("modify.jsp");
	}
	
	private boolean isUserNameValid(String name) {
		final String USER_NAME = "^[A-Za-z]+$";
		if (name == null) return false;
		else return name.matches(USER_NAME);
	}
	
	private boolean isEmailValid(String email) {
		final String EMAIL = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		if (email == null) return false;
		else return email.matches(EMAIL);
	}
	
	

}
