package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

import model.Force;
import model.Passion;
import model.User;
import model.db.DatabaseAccessError;
import model.db.PassionDB;

@WebServlet("/RemovePassionServlet")
public class RemovePassionServet extends HttpServlet {

	private static final long serialVersionUID = -9155343760353509497L;

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
			Force passion = new Passion(user.getId(), titre, description);
			try {
				if (new PassionDB().dropForce(passion)) {
					for (int i = 0; i < user.getPassions().size(); i++) {
						Force force = user.getPassions().get(i);
						if (force.equals(passion))
							user.getPassions().remove(force);
					}
					req.getSession().setAttribute(SessionAttributes.PASSION_SUCCESS_INFO, "Félicitation! Réussi à supprimer.");
				}
			} catch (DatabaseAccessError e) {
				e.printStackTrace();
				req.getSession().setAttribute(SessionAttributes.PASSION_ERROR_WARNING, "Database error(delete from passion)");
			}
		} else {
			req.getSession().setAttribute(SessionAttributes.PASSION_ERROR_WARNING, "Network error, try again.");
		}
				
		resp.sendRedirect("modify.jsp#passion");
	}
	
}
