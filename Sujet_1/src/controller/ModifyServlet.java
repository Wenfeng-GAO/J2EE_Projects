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

@WebServlet("/ModifyServlet")
public class ModifyServlet extends HttpServlet {

	private static final long serialVersionUID = 1637001860372050917L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// If no user session, redirect to index page
		if (req.getSession().getAttribute(SessionAttributes.LOGIN_VALID) == null) {
			resp.sendRedirect("index.jsp");
			return;
		}
		
		User user = (User) req.getSession().getAttribute(SessionAttributes.LOGIN_VALID);
		try {
			user.setCompetences(new CompetenceDB().findForces(user));
			user.setPassions(new PassionDB().findForces(user));
		} catch (DatabaseAccessError e) {
			e.printStackTrace();
			req.getSession().setAttribute(SessionAttributes.DB_ACCESS_ERROR, e);
		}
		
		resp.sendRedirect("modify.jsp");
		
	}
	
	

}
