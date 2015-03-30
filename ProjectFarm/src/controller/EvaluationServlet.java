package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Evaluation;
import model.Evaluator;
import model.Project;
import model.db.ProjectDB;
import model.db.exception.DatabaseAccessError;
import model.exception.InvalidDataException;

@WebServlet("/EvaluationServlet")
public class EvaluationServlet extends HttpServlet {

	private static final long serialVersionUID = 6920566078735730785L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Get parameters
		int attractiveness = 0, risk = 0;
		Evaluator evaluator = null;
		Project project = null;
		String attraPara = req.getParameter("attractiveness"),
			   riskPara = req.getParameter("risk");

		
		try {
			attractiveness = Integer.parseInt(attraPara);
			risk = Integer.parseInt(riskPara);
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}
		
	
		try {
			project = ProjectDB.getProject(req.getParameter("project"));
		} catch (DatabaseAccessError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		evaluator = (Evaluator) req.getSession().getAttribute("user");
		
		
		// Create Evaluation instance and add it to the project instance
		try {
			Evaluation evaluation = new Evaluation(evaluator, attractiveness, risk);
			project.addEvaluation(evaluation);
			
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Dispatch
		req.getRequestDispatcher("/evaluation_list_projects.jsp").forward(req, resp);
		
	}
	

}
