package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.db.CompetenceDB;
import model.db.DatabaseAccessError;
import model.db.PassionDB;
import model.db.UserDB;

@WebServlet("/SearchResultProfileServlet")
public class SearchResultProfileServlet extends HttpServlet {

	private static final long serialVersionUID = -2424070158268576280L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String email = req.getParameter("email");
		User user = null;
		
		if (email != null && !email.equals("")) {
			try {
				user = new UserDB().getUserByEmail(email);
				user.setCompetences(new CompetenceDB().findForces(user));
				user.setPassions(new PassionDB().findForces(user));
				req.getSession().setAttribute(SessionAttributes.SEARCH_PROFILE, user);
			} catch (DatabaseAccessError e) {
				e.printStackTrace();
			}
		}
		
		resp.sendRedirect("profile.jsp");
		
	}
	
	

}
