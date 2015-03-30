<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.db.ProjectDB" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Project" %>

<jsp:include page="/utils/header.jsp">
	<jsp:param value="Project Farm" name="title" />
</jsp:include>




<% 
if (session.getAttribute("user") != null) {
	List<Project> projects = ProjectDB.getAllProjects();
	if (projects.size() > 0) {
%>

		<div class="container">
			<div class="panel panel-default">
			
			<div class="panel-heading">All projects</div>
			
			<div class="panel-body">
		
				<table class="table table-hover">
				
					<tr>
					
						<th>#</th>
						<th>Acronym</th>
						<th>Category</th>
						<th># of Incubation Days</th>
						<th>Budget</th>
						<th># of Evaluations</th>
						<th>Action</th>
				
					</tr>
			
<%
		for (int i = 0; i < projects.size(); i++) {
			Project project = projects.get(i);
%>
					<tr class="clickable-row" data-href="#">
						<td><%= i+1 %></td>
						<td><a href="evaluation.jsp?acronym=<%= project.getAcronym() %>"><%= project.getAcronym() %></a></td>
						<td><%= project.getCategory().getDescription() %></td>
						<td><%= project.getFundingDuration() %></td>
						<td><%= project.getBudget() %></td>
						<td><%= project.getEvaluations().size() %></td>
						<td>Evaluate</td>
					</tr>
				
<%
		}
%>
			
				</table>
			
			</div>
			
			</div>
		</div>
	
	
	
	
<%
	} else { 
%>
		<div class="container text-center">
			
			<div class="jumbotron">
			  	
			  	<h1>Oops we haven't project yet!</h1>
			  	<p>...</p>
			  	<p><a class="btn btn-primary btn-lg" href="<%=request.getContextPath() %>/LogoutServlet" role="button">Go on holiday!</a></p>
			
			</div>
		
		</div>
	
<%
	}
}
%>










<jsp:include page="/utils/footer.jsp"></jsp:include>

