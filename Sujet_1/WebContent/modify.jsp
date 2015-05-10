<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/utils/header.jsp">
	<jsp:param value="MON PROFILE" name="title"/> 
</jsp:include>


<!-- Mon profile page main context -->
<div class="container" >
	
	<jsp:include page="/includes/modify.mon.profile.jsp"></jsp:include>
	
	<jsp:include page="/includes/modify.completer.profile.jsp"></jsp:include>
	
	<jsp:include page="/includes/modify.competences.jsp"></jsp:include>
	
	<jsp:include page="/includes/modify.passions.jsp"></jsp:include>
	
</div>


<jsp:include page="/utils/footer.jsp"></jsp:include>