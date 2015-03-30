<%@page import="model.Evaluation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
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
		
			<p>You haven't uploaded any documents yet.</p>
    		
    		
<%
	}
%>

			<button type="button" class="btn btn-default" data-toggle="modal" data-target="#upload">Upload</button>

  			
  		</div>
	
	</div>



	<div class="panel panel-default">
  		
  		<div class="panel-heading">
    		<h3 class="panel-title">Statistics</h3>
  		</div>
  		
  		<div class="panel-body">
    		
  			<ul class="list-group">
  			
			    <li class="list-group-item"><strong>Risk Level: </strong><%= project.getRisk() > 0 ? project.getRisk() : "-" %></li>
			    <li class="list-group-item"><strong>Attractiveness: </strong><%= project.getAttractiveness() > 0 ? project.getAttractiveness() : "-" %></li>
			    <li class="list-group-item"><strong># of Evaluations: </strong><%=  project.getEvaluations().size() %></li>
 			
 			</ul>    		
    		
  		</div>
	
	</div>
	

	<a class="btn btn-default" href="#" role="button">Save</a>
	<a class="btn btn-default" href="#" role="button">Discard</a>
	
</div>



<!-- Upload modal -->
<div class="modal fade" id="upload" tabindex="-1" role="dialog"  aria-hidden="true">
	
	<div class="modal-dialog">
    	<div class="modal-content">
      		
      		<div class="modal-header">
        		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        		<h4 class="modal-title" id="myModalLabel">Upload documents</h4>
      		</div>
      		
      		<form method="post" action="<%= request.getContextPath() %>/ProjectDetailsServlet?project=<%=project.getAcronym() %>" 
      			enctype="multipart/form-data">
	      		
	      		<div class="modal-body">
	        		
	 				<input id="fileupload" name="myfile" type="file" />
	        		
	      		</div>
	      		
	      		<div class="modal-footer">
	        		<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
	        		<button type="Submit" class="btn btn-primary">Upload</button>
	      		</div>
      		
      		</form>
    	
    	</div>
	</div>

</div>



<%
	}
%>



<jsp:include page="/utils/footer.jsp"></jsp:include>


