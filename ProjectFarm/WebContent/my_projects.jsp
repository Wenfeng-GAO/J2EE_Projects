<%@page import="model.Evaluation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Project" %>
<%@ page import="model.db.ProjectDB" %>
<%@ page import ="model.Owner" %>

<jsp:include page="/utils/header.jsp">
	<jsp:param value="Project Farm" name="title" />
</jsp:include>



<% 
if (session.getAttribute("user") != null) {
	List<Project> projects = ProjectDB.getProjectsOfOwner((Owner) session.getAttribute("user"));
	if (projects.size() > 0) {
%>

	
	
		<div class="container">
			<div class="panel panel-default">
			
			<div class="panel-heading">My projects</div>
			
			<div class="panel-body">
		
				<table class="table table-hover">
				
					<tr>
					
						<th>#</th>
						<th>Acronym</th>
						<th>Category</th>
						<th># of Incubation Days</th>
						<th>Budget</th>
						<th>Risk Level</th>
						<th>Attractiveness</th>
						<th># of Evaluations</th>
				
					</tr>
		
			
<%
		for (int i = 0; i < projects.size(); i++) {
			Project project = projects.get(i);
%>
		
		
					<tr class="clickable-row" data-href="#">
						<td><%= i+1 %></td>
						<td><a href="project_details.jsp?acronym=<%= project.getAcronym() %>"><%= project.getAcronym() %></a></td>
						<td><%= project.getCategory().getDescription() %></td>
						<td><%= project.getFundingDuration() %></td>
						<td><%= project.getBudget() %></td>
						
						
						<td><%= project.getRisk() > 0 ? project.getRisk() : "-" %></td>
						<td><%= project.getAttractiveness() > 0 ? project.getAttractiveness() : "-" %></td>
						<td><%= project.getEvaluations().size() %></td>
					</tr>
				
<%
		}
%>
			
				</table>
			
			</div>
			
			<div class="panel-footer">
				<a class="btn btn-info" href="new_project.jsp" role="button">New project</a>
			</div>
			
			</div>
		</div>
	
	
	
	
<%
	} else { 
%>
		<div class="container text-center">
			
			<div class="jumbotron">
			  	
			  	<h1>You haven't project yet!</h1>
			  	<p>...</p>
			  	<p><a class="btn btn-primary btn-lg" href="new_project.jsp" role="button">Create one!</a></p>
			
			</div>
		
		</div>
	
<%
	}
}
%>
	


<jsp:include page="/utils/footer.jsp"></jsp:include>

