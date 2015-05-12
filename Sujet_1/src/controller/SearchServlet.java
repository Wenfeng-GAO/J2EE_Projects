package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;

import model.User;
import model.db.DatabaseAccessError;
import model.db.UserDB;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = -1953084286713579746L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Get parameter
		String search = req.getParameter("search");
		search = StringEscapeUtils.escapeHtml3(search);
		
		List<User> users = new ArrayList<User>();
		
		if (search != null && !search.equals("")) {
			try {
				users = new UserDB().findUsersBy(search);
				if (!users.isEmpty())
					req.getSession().setAttribute(SessionAttributes.SEARCH_RESULT, users);
			} catch (DatabaseAccessError e) {
				e.printStackTrace();
			}
		}
		
		resp.sendRedirect("search.jsp");
		
	}
	
	
	
	

}
