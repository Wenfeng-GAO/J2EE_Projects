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
		<strong>Warning!</strong> Better check yourself, you're not looking
		too good.
	</div>

<% } %>

	<form>
		<div class="form-group">
			<label for="nom">Nom</label>
			<input type="text" class="form-control" placeholder="Dipont">
		</div>
		<div class="form-group">
			<label for="prenom">Prenom</label>
			<input type="text" class="form-control" placeholder="Jean">
		</div>


		<label class="radio-inline">
			<input type="radio" name="male" value="male">
			Masculin
		</label>
		<label class="radio-inline">
			<input type="radio" name="female" value="female">
			Feminin
		</label>
		<label class="radio-inline">
			<input type="radio" name="female" value="female">
			Autre
		</label>
		
		<div class="form-group">
			<label for="email">Addresse Electrique</label> 
			<input type="email" class="form-control" placeholder="example@todomore.com">
		</div>
		<div class="form-group">
			<label for="password">Mot de passe</label> 
			<input type="password" class="form-control" placeholder="Password">
		</div>
		<div class="form-group">
			<label for="password">Configuration du mot de passe</label> 
			<input type="confi_password" class="form-control" placeholder="Password">
		</div>
		<button type="submit" class="btn btn-infoq pull-right">Inscription</button>
	</form>


</div>

</div>
    
<jsp:include page="/utils/footer.jsp"></jsp:include>