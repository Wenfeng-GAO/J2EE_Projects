<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/utils/header.jsp">
<jsp:param value="Project Farm" name="title"/>
</jsp:include>



<div class="container text-center">
	
	<div class="jumbotron">
		<h1>Project ideas are seeds to change the world</h1>     
		<a href="<%= request.getContextPath()%>/new_project.jsp"><button type="button" class="btn btn-default">New project idea</button></a>
	</div>

</div>





<jsp:include page="/utils/footer.jsp"></jsp:include>