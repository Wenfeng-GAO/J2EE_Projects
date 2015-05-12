package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Competence;
import model.Force;
import model.User;
import model.db.CompetenceDB;
import model.db.DatabaseAccessError;

import org.apache.commons.lang3.StringEscapeUtils;

@WebServlet("/AddCompetenceServlet")
public class AddCompetenceServlet extends HttpServlet {
	
	private static final long serialVersionUID = 8083411140251079606L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Get parameters
		String titre = req.getParameter("titre");
		String description = req.getParameter("description");
		titre = StringEscapeUtils.escapeHtml3(titre);
		description = StringEscapeUtils.escapeHtml3(description);
		
		// Get user
		if (req.getSession().getAttribute(SessionAttributes.LOGIN_VALID) == null) {
			resp.sendRedirect("index.jsp");
			return;
		}
		User user = (User) req.getSession().getAttribute(SessionAttributes.LOGIN_VALID);
		
		if (titre == null || description == null) {
			req.getSession().setAttribute(SessionAttributes.COMPETENCE_ERROR_WARNING, "Internet error, please try again.");
		} else if (titre.equals("")) {
			req.getSession().setAttribute(SessionAttributes.PASSION_ERROR_WARNING, "Title can not be empty.");
		} else if (user.getCompetences().isEmpty()) {
			Force competence = new Competence(user.getId(), titre, description);
			try {
				if (new CompetenceDB().addForce(competence)) {
					user.getCompetences().add(competence);
					req.getSession().setAttribute(SessionAttributes.PASSION_SUCCESS_INFO, "Félicitations nouvelle compétence a été bien créée!");
				}
			} catch (DatabaseAccessError e) {
				e.printStackTrace();
				req.getSession().setAttribute(SessionAttributes.PASSION_ERROR_WARNING, e.getMessage().toString());
			}
		} else {
			int size = user.getCompetences().size();
			for (int i = 0; i < size; i++) {
				if (user.getCompetences().get(i).getTitre().equalsIgnoreCase(titre)) {
					req.getSession().setAttribute(SessionAttributes.PASSION_ERROR_WARNING, "Titre can not be the same");
					break;
				}
				if (i == size - 1) {
					Force competence = new Competence(user.getId(), titre, description);
					try {
						if (new CompetenceDB().addForce(competence)) {
							user.getCompetences().add(competence);
							req.getSession().setAttribute(SessionAttributes.PASSION_SUCCESS_INFO, "Félicitqtion nouvel compétence a été bien créé!");
						}
					} catch (DatabaseAccessError e) {
						e.printStackTrace();
						req.getSession().setAttribute(SessionAttributes.PASSION_ERROR_WARNING, e.getMessage().toString());
					}
				}
			}
			
		}
		resp.sendRedirect("modify.jsp#competence");
	}
	
	

}
