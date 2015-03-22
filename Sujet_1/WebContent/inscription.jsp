<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/utils/header.jsp">
	<jsp:param value="TO DO MORE SOCIAL NETWORKING" name="title"/> 
	<jsp:param value="index.jsp" name="page"/>
</jsp:include>

<div class="container">


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
		
		<div class="form-group">
			<label for="email">Addresse Electrique</label> 
			<input type="email" class="form-control" placeholder="Enter email">
		</div>
		<div class="form-group">
			<label for="password">Mot de passe</label> 
			<input type="password" class="form-control" placeholder="Password">
		</div>
		<div class="form-group">
			<label for="password">Configuration du mot de passe</label> 
			<input type="confi_password" class="form-control" placeholder="Password">
		</div>
		<button type="submit" class="btn btn-info pull-right">Inscription</button>
	</form>


</div>

</div>
    
<jsp:include page="/utils/footer.jsp"></jsp:include>