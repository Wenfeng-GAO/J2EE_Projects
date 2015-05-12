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

@WebServlet("/AddPassionServlet")
public class AddPassionServlet extends HttpServlet {

	private static final long serialVersionUID = 5763995847641571902L;

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
			req.getSession().setAttribute(SessionAttributes.PASSION_ERROR_WARNING, "Internet error, please try again.");
		} else if (titre.equals("")) {
			req.getSession().setAttribute(SessionAttributes.PASSION_ERROR_WARNING, "Title can not be empty.");
		} else if (user.getPassions().isEmpty()) {
			Force passion = new Passion(user.getId(), titre, description);
			try {
				if (new PassionDB().addForce(passion)) {
					user.getPassions().add(passion);
					req.getSession().setAttribute(SessionAttributes.PASSION_SUCCESS_INFO, "Félicitations nouvelle passion a été bien créée!");
				}
			} catch (DatabaseAccessError e) {
				e.printStackTrace();
				req.getSession().setAttribute(SessionAttributes.PASSION_ERROR_WARNING, e.getMessage().toString());
			}
		} else {
			int size = user.getPassions().size();
			for (int i = 0; i < size; i++) {
				if (user.getPassions().get(i).getTitre().equalsIgnoreCase(titre)) {
					req.getSession().setAttribute(SessionAttributes.PASSION_ERROR_WARNING, "Titre can not be the same");
					break;
				}
				if (i == size - 1) {
					Force passion = new Passion(user.getId(), titre, description);
					try {
						if (new PassionDB().addForce(passion)) {
							user.getPassions().add(passion);
							req.getSession().setAttribute(SessionAttributes.PASSION_SUCCESS_INFO, "Félicitqtion nouvelle passion a été bien créé!");
						}
					} catch (DatabaseAccessError e) {
						e.printStackTrace();
						req.getSession().setAttribute(SessionAttributes.PASSION_ERROR_WARNING, e.getMessage().toString());
					}
				}
			}
			
		}
		
	   	resp.sendRedirect("modify.jsp#passion");
	}
}
