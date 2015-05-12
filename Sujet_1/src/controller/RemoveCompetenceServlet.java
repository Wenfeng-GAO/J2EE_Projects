package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

import model.Competence;
import model.Force;
import model.User;
import model.db.CompetenceDB;
import model.db.DatabaseAccessError;

@WebServlet("/RemoveCompetenceServlet")
public class RemoveCompetenceServlet extends HttpServlet {

	private static final long serialVersionUID = -7649170146960258769L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Get parameters
		String titre = req.getParameter("title");
		String description = req.getParameter("description");
		
		titre = StringEscapeUtils.escapeHtml3(titre);
		description = StringEscapeUtils.escapeHtml3(description);
		
		// Get user
		if (req.getSession().getAttribute(SessionAttributes.LOGIN_VALID) == null) {
			resp.sendRedirect("index.jsp");
			return;
		}
		User user = (User) req.getSession().getAttribute(SessionAttributes.LOGIN_VALID);
		
		if (titre != null && description != null && (!titre.equals(""))) {
			Force competence = new Competence(user.getId(), titre, description);
			try {
				if (new CompetenceDB().dropForce(competence)) {
					for (int i = 0; i < user.getCompetences().size(); i++) {
						Force force = user.getCompetences().get(i);
						if (force.equals(competence))
							user.getCompetences().remove(force);
					}
					req.getSession().setAttribute(SessionAttributes.COMPETENCE_SUCCESS_INFO, "Félicitation! Réussi à supprimer.");
				}
			} catch (DatabaseAccessError e) {
				e.printStackTrace();
				req.getSession().setAttribute(SessionAttributes.COMPETENCE_ERROR_WARNING, "Database error(delete from competence)");
			}
		} else {
			req.getSession().setAttribute(SessionAttributes.COMPETENCE_ERROR_WARNING, "Network error, try again.");
		}
				
		resp.sendRedirect("modify.jsp#competence");
	}
	
	

}
