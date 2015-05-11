<%@page import="controller.SessionAttributes"%>
<%@page import="model.User"%>
<% 
if (request.getSession().getAttribute(SessionAttributes.LOGIN_VALID) != null) {
	User user = (User) request.getSession().getAttribute(SessionAttributes.SEARCH_PROFILE); 
%>
<div class="panel panel-warning">
 		<div class="panel-heading text-center">
   		<h3 class="panel-title">Profil de <%= user.getNom() %></h3>
 		</div>
 		<div class="panel-body">
 			<div class="row">
			<div class="col-md-1">
				<img alt="..." src="http://placehold.it/140x140" class="img-circle">
			</div>
			<div class="col-md-9 col-md-offset-1">
				
					<p><span class="glyphicon glyphicon-user" aria-hidden="true"></span> <%= user.getNom() %> <%= user.getPrenom() %> </p>
					<p><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> <%= user.getEmail() %></p>
					<p><span class="glyphicon glyphicon-tower" aria-hidden="true"></span> <%= user.getPoste() %></p>
					
					<blockquote>
						<strong>Qui est <%= user.getNom() %> ?</strong>
						
						<% if (!user.getBiographie().equals("")) { %>		
								<small><%= user.getBiographie() %></small>
						<% } else { %>
								<small>Aucunne description pour le moment...</small>
						<% } %>
					</blockquote>
				<%
				} else { 
					response.sendRedirect("index.jsp");
				}
				%>
					
				
			</div>
		</div>
 		</div>
</div>