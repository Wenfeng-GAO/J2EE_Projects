<%@page import="model.Force"%>
<%@page import="model.Passion"%>
<%@page import="model.User"%>
<%@page import="controller.SessionAttributes"%>

<% 
if (request.getSession().getAttribute(SessionAttributes.PASSION_SUCCESS_INFO) != null) { 
	String message = request.getSession().getAttribute(SessionAttributes.PASSION_SUCCESS_INFO).toString();
	request.getSession().removeAttribute(SessionAttributes.PASSION_SUCCESS_INFO);
%>
	<div class="alert alert-success alert-dismissible" role="alert">
	  	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  	<strong>Bien!</strong> <%= message %>
	</div>
<%
}
%>

<div class="panel panel-warning">
	<div class="panel-heading text-center">
  		<h3 class="panel-title">Passions</h3>
	</div>
	
	<div class="panel-body">
		<table class="table table-striped" id="passion">  
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
					  			<td><%= description %>
					  				<a href="RemovePassionServlet?title=<%= titre %>&description=<%= description %>">
					  				<span class="glyphicon glyphicon-remove pull-right" aria-hidden="true"></span></a></td>
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
		<button class="btn btn-primary pull-right" data-toggle="modal" data-target="#passionModal">Ajouter</button>
		<% 
			if (request.getSession().getAttribute(SessionAttributes.PASSION_ERROR_WARNING) != null) { %>
				<div class="alert alert-danger col-md-9" role="alert"><%= request.getSession().getAttribute(SessionAttributes.PASSION_ERROR_WARNING).toString() %></div>
				<% request.getSession().removeAttribute(SessionAttributes.PASSION_ERROR_WARNING); 
			} 
		%>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="passionModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Nouvelle passion</h4>
			</div>
			<form action="AddPassionServlet">
				<div class="modal-body">
						<div class="form-group">
							<label for="recipient-name" class="control-label">Intitulé</label>
							<input type="text" class="form-control" name="titre">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">Description</label>
							<textarea class="form-control" name="description"></textarea>
						</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Ajouter passion</button>
				</div>
			</form>
		</div>
	</div>
</div>
