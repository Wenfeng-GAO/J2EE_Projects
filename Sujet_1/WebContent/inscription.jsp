<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/utils/header.jsp">
	<jsp:param value="TO DO MORE SOCIAL NETWORKING" name="title"/> 
	<jsp:param value="index.jsp" name="page"/>
</jsp:include>

<div class="container">

<% if (request.getSession().getAttribute("alert") != null) { %>

	<div class="alert alert-warning alert-danger" role="alert">
	
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		Les deux mots de passes ne concordent pas!
		
	</div>

<% } %>

	<form action="<%= request.getContextPath() %>/InscriptionServlet">
		<div class="form-group">
			<label for="nom">Nom</label>
			<input type="text" class="form-control" placeholder="Dipont" name="nom">
		</div>
		<div class="form-group">
			<label for="prenom">Prenom</label>
			<input type="text" class="form-control" placeholder="Jean" name="prenom">
		</div>


		<label class="radio-inline">
			<input type="radio" name="sex" value="male">
			Masculin
		</label>
		<label class="radio-inline">
			<input type="radio" name="sex" value="female">
			Feminin
		</label>
		<label class="radio-inline">
			<input type="radio" name="sex" value="female">
			Autre
		</label>
		
		<div class="form-group">
			<label for="email">Addresse Electrique</label> 
			<input type="email" class="form-control" placeholder="example@todomore.com" name="email">
		</div>
		<div class="form-group">
			<label for="password">Mot de passe</label> 
			<input type="password" class="form-control" placeholder="Password" name="password">
		</div>
		<div class="form-group">
			<label for="password">Configuration du mot de passe</label> 
			<input type="password" class="form-control" placeholder="Password" name="passwordconfi">
		</div>
		<button type="submit" class="btn btn-info pull-right">Inscription</button>
	</form>


</div>

</div>
    
<jsp:include page="/utils/footer.jsp"></jsp:include>