<%@page import="model.Force"%>
<%@page import="model.User"%>
<%@page import="controller.SessionAttributes"%>
<div class="panel panel-warning">
  		<div class="panel-heading text-center">
    		<h3 class="panel-title">Compétences</h3>
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
			  		   	
				  		User user = (User) request.getSession().getAttribute(SessionAttributes.SEARCH_PROFILE);
				  			
				  		if (user.getCompetences() != null && user.getCompetences().size() > 0) {
				  			
					  		for (Force competence : user.getCompetences()) {
					  			String titre = competence.getTitre();
					  			String description = competence.getDescription();
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
					} %>
					  	
			  	</tbody>
			</table>
		</div>
	</div>
