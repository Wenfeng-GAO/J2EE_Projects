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
			if (!nom.equals("") && !nom.equals(user.getNom())) user.setNom(nom);
			if (!prenom.equals("") && !prenom.equals(user.getPrenom())) user.setPrenom(prenom);
			if (!email.equals("") && !email.equals(user.getEmail())) user.setEmail(email);
			if (!poste.equals("") && !poste.equals(user.getPoste())) user.setPoste(poste);
			if (!password.equals("") && !password.equals(user.getPassword())) user.setPassword(password);
			if (!biographie.equals("") && !biographie.equals(user.getBiographie())) user.setBiographie(biographie);
			
			try {
				if (new UserDB().updateUser(user)) 
					req.getSession().setAttribute(SessionAttributes.COMPLETER_SUCCESS_INFO, "Félicitation! Votre profile a été bien créé.");
			} catch (DatabaseAccessError e) {
				e.printStackTrace();
				req.getSession().setAttribute(SessionAttributes.COMPLETER_ERROR_WARNING, "Network error(update user)");
			}
		}
		
		resp.sendRedirect("modify.jsp");
	}

}
