<%@page import="model.Force"%>
<%@page import="controller.SessionAttributes"%>
<%@page import="model.User"%>
<div class="panel panel-warning">
  		<div class="panel-heading text-center">
    		<h3 class="panel-title">Passions</h3>
  		</div>
		
		<div class="panel-body">
			<table class="table table-striped">  
			  	<thead>
			  		<tr>
			  			<th>Intitulé</th>
			  			<th>Description</th>
			  		</tr>
			  	</thead>
			  	<tbody>
			  		<%
		  		if (request.getSession().getAttribute(SessionAttributes.LOGIN_VALID) != null) {
		  		
			  		User user = (User) request.getSession().getAttribute(SessionAttributes.LOGIN_VALID);
			  		
			  		if (user.getPassions() != null && user.getPassions().size() > 0) { 
			  		
				  		for (Force passion : user.getPassions()) {
				  			String titre = passion.getTitre();
				  			String description = passion.getDescription();
				  		%>
					  		<tr>
					  			<td><%= titre %></td>
					  			<td><%= description %></td>
					  		</tr>
					  	<% 
					  	} 
					} 
				} else { 
					response.sendRedirect("index.jsp");
				} 
				%>
			  	
			  	</tbody>
			</table>
		</div>
	</div>