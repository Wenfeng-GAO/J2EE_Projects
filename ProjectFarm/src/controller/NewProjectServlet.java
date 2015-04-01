package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;
import model.Owner;
import model.Project;
import model.db.CategoryDB;
import model.db.ProjectDB;
import model.exception.InvalidDataException;

/**
 * Create a project object, add the project in the database or assign an error 
 * if inconsistencies were detected
 * @author Wenfeng
 *
 */

@WebServlet("/NewProjectServlet")
public class NewProjectServlet extends HttpServlet {

	private static final long serialVersionUID = -8678484981445788014L;
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Get parameters
		Category category = null;
		Owner owner = null;
		Project project = null;
		double budget = 0;
		int fundingDuration = 0;
		
		String acronymPara = req.getParameter("acronym"),
			   descriptionPara = req.getParameter("description"),
			   categoryPara = req.getParameter("category"),
			   budgetPara = req.getParameter("budget"),
			   fundingDurationPara = req.getParameter("funding_duration");
		
		
		// Get Owner from session
		if (req.getSession().getAttribute("user") != null) {
			owner = (Owner) req.getSession().getAttribute("user");
		}
		
		// Delete the FORMAT_ERROR session if exists
		if (req.getSession().getAttribute("FORMAT_ERROR") != null) {
			req.getSession().removeAttribute("FORMAT_ERROR");
		}
		
		// Get Category from parameter
		if (categoryPara != null) {
			category = CategoryDB.getCategory(categoryPara);
		}
		
		// Transform budget to Double
		if (budgetPara != null) {
			
			try {
				budget = Double.parseDouble(budgetPara);
			} catch (NumberFormatException e) {
				budget = 0;
			}
			
		}
		
		// Transform funding duration to Integer
		if (fundingDurationPara != null) {
			
			try {
				fundingDuration = Integer.parseInt(fundingDurationPara);
			} catch (NumberFormatException e) {
				fundingDuration = 0;
			}
		
		}

		// Create project object
		try {
			project = new Project(acronymPara, descriptionPara, fundingDuration, budget, owner, category);
			ProjectDB.saveProject(project);
			// If everything goes well, dispatch to project list page
			resp.sendRedirect("my_projects.jsp");
		} catch (InvalidDataException e) {
			errorFormatFeedback(req, resp, e);
		}
		
	}
	
	
	/**
	 * A method to store exception in the session, then dispatch to the original page
	 * @param req
	 * @param resp
	 * @param e	Exceptions
	 * @throws ServletException
	 * @throws IOException
	 */
	private void errorFormatFeedback(HttpServletRequest req, HttpServletResponse resp, Exception e) throws ServletException, IOException {
		req.getSession().setAttribute("FORMAT_ERROR", e);
		req.getRequestDispatcher("/new_project.jsp").forward(req, resp);
	}

	
	
}
