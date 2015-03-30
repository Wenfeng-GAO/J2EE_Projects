<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Project" %>
<%@ page import="model.db.ProjectDB" %>
<%@ page import="model.Document" %>
<jsp:include page="/utils/header.jsp">
	<jsp:param value="Project Farm" name="title" />
</jsp:include>






<div class="container">


<%
	if (request.getParameter("acronym") != null) {
		Project project = ProjectDB.getProject(request.getParameter("acronym"));
%>

	<div class="panel panel-default">
  		
  		<div class="panel-heading">
    		<h3 class="panel-title">Project Evaluation</h3>
  		</div>
  		
  		<div class="panel-body">
  		
  			<ul class="list-group">
			    
			    <li class="list-group-item"><strong>Acronym: </strong><%=  project.getAcronym()%></li>
			    <li class="list-group-item"><strong>Created: </strong><%=  project.getCreated()%></li>
			    <li class="list-group-item"><strong>Category: </strong><%=  project.getCategory().getDescription()%></li>
			    <li class="list-group-item"><strong>Budget(EUR): </strong><%=  project.getBudget()%></li>
			    <li class="list-group-item"><strong>Description: </strong><%=  project.getDescription()%></li>
 			
 			</ul>
  		
  		</div>
	
	</div>



	<div class="panel panel-default">
  		
  		<div class="panel-heading">
    		<h3 class="panel-title">Documents</h3>
  		</div>
  		
  		<div class="panel-body">
    		
    		
<%
		if (project.getDocuments().size() > 0) {
			for (Document doc : project.getDocuments()) {
%>
	
				<a  class="list-group-item" 
					href="<%= request.getContextPath() %>/DownloadServlet?doc_path=<%= doc.getDocumentPath() %>&doc_name=<%= doc.docName() %>"><%=  doc.docName()%>
				</a>
					
<%
			}
		} else {
%>
			
				<p>The owner haven't uploaded any document yet.</p>
	    		
<%
		}
%>

  			
  		</div>
	
	</div>


	<form action="<%=request.getContextPath() %>/EvaluationServlet" >
	<div class="panel panel-default">
  		
  		<div class="panel-heading">
    		<h3 class="panel-title">Your Evaluation</h3>
  		</div>
  		
  		<div class="panel-body">
    		
    		<label>Attractiveness</label>
  			<select class="form-control" name="attractiveness">
				  <option>1</option>
				  <option>2</option>
				  <option>3</option>
				  <option>4</option>
				  <option>5</option>
			</select> 		
    		
    		<label>Risk Level</label>
  			<select class="form-control" name="risk">
				  <option>1</option>
				  <option>2</option>
				  <option>3</option>
				  <option>4</option>
				  <option>5</option>
			</select> 		
    		
 
  		</div>
	
	</div>
	

	<input class="btn btn-default" type="submit" >
	<a class="btn btn-default" href="evaluation_list_projects.jsp" role="button">Discard</a>
	<input type="hidden" name="project" value="<%= project.getAcronym() %>">
	
	</form>
</div>





<%
	}
%>



<jsp:include page="/utils/footer.jsp"></jsp:include>


