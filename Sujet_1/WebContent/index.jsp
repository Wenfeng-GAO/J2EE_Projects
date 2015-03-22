<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/utils/header.jsp">
	<jsp:param value="TO DO MORE SOCIAL NETWORKING" name="title"/> 
	<jsp:param value="index.jsp" name="page"/>
</jsp:include>


<!-- Introduction blocks in the index page -->
<div class="container">

	<div class="row index-row">


		<div class="col-md-3">
			<a href="#"> <img src="<%=request.getContextPath() %>/images/index.png" alt="..." class="img-responsive"></a>
		</div>
		
		<div class="col-md-6 jumbotron">
			<p>Le site devra être accessible à partir de n’importe quelle machine de l’Esigelec et depuis n’importe quel navigateur web.
				Le site devra être développé en java JEE avec l’utilisation de HTML 5 et de CSS 3. 
				Il devra s'exécuter sur un serveur tomcat 7 (au mieux tomcat 8) et la base de données sera géré via un serveur mysql sous wamp.
			</p>
		</div>
	
	
	</div>

</div>
    
<jsp:include page="/utils/footer.jsp"></jsp:include>