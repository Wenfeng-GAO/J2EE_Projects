<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/utils/header.jsp">
	<jsp:param value="TO DO MORE SOCIAL NETWORKING" name="title"/> 
</jsp:include>


<!-- Introduction blocks in the index page -->
<div class="container">

	<div class="row index-row">


		<div class="col-md-3">
			<a href="#"> <img src="<%=request.getContextPath() %>/images/index.png" alt="..." class="img-responsive"></a>
		</div>
		
		<div class="col-md-6 jumbotron">
			<p>Ce site est un reseau social d'entreprise. Il permet a un membre de 
			l'entreprise d'en retrouver un autre sur la base de son nom, de ses competences, de ses passions...
			</p>
		</div>
	
	
	</div>

</div>
    
<jsp:include page="/utils/footer.jsp"></jsp:include>