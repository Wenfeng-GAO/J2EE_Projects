<%@page import="controller.SessionAttributes"%>
<% 
if (request.getSession().getAttribute(SessionAttributes.COMPLETER_SUCCESS_INFO) != null) { 
	String message = request.getSession().getAttribute(SessionAttributes.COMPLETER_SUCCESS_INFO).toString();
	request.getSession().removeAttribute(SessionAttributes.COMPLETER_SUCCESS_INFO);
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
    		<h3 class="panel-title">Completer mon profil</h3>
  		</div>
  		<div class="panel-body">
				<form action="ChangeProfileServlet">
					<div class="form-group">
						<label for="nom" class="sr-only">Nom</label> 
						<input type="text" class="form-control" name="nom" placeholder="Nom">
					</div>
					<div class="form-group">
						<label for="prenom" class="sr-only">Prénom</label> 
						<input type="text" class="form-control" name="prenom" placeholder="Prenom">
					</div>
					<div class="form-group">
						<label for="email" class="sr-only">Email</label> 
						<input type="email" class="form-control" name="email" placeholder="jane.doe@example.com">
					</div>
					<div class="form-group">
						<label for="poste" class="sr-only">Poste dans l'entreprise</label> 
						<input type="text" class="form-control" name="poste" placeholder="Poste dans l'entreprise">
					</div>
					<div class="form-group">
						<label for="password" class="sr-only">Mot de passe</label> 
						<input type="password" class="form-control" name="password" placeholder="Mot de passe">
					</div>
					<div class="form-group">
						<label for="password-confirmation" class="sr-only">Confirmation du mot de passe</label> 
						<input type="password" class="form-control" name="conf_password" placeholder="Confirmation du mot de passe">
					</div>
					<div class="form-group">
						<label for="bibliographie" class="sr-only">Biographie</label> 
						<textarea rows="3" cols="" class="form-control" placeholder="Biographie..." name="biographie"></textarea>
					</div>
					<button type="submit" class="btn btn-primary pull-right">Valider</button>
			<% 
			if (request.getSession().getAttribute(SessionAttributes.COMPLETER_ERROR_WARNING) != null) { %>
				<div class="alert alert-danger col-md-9" role="alert"><%= request.getSession().getAttribute(SessionAttributes.COMPLETER_ERROR_WARNING).toString() %></div>
				<% request.getSession().removeAttribute(SessionAttributes.COMPLETER_ERROR_WARNING); 
			} 
			%>
				</form>
  		</div>
	</div>