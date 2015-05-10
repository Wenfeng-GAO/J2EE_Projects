<%@page import="model.User"%>
<%@page import="controller.SessionAttributes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(request.getSession().getAttribute(SessionAttributes.SEARCH_PROFILE) != null) {
		User user = (User) request.getSession().getAttribute(SessionAttributes.SEARCH_PROFILE);
		String nom = user.getNom();
%>
<jsp:include page="/utils/header.jsp">
	<jsp:param value="Profile" name="title"/> 
	<jsp:param value="<%= nom %>" name="nom"/>
</jsp:include>
<% } %>

<div class="container">
	<jsp:include page="includes/profile.profile.jsp"></jsp:include>
	
	<jsp:include page="includes/profile.competence.jsp"></jsp:include>
	
	<jsp:include page="includes/profile.passion.jsp"></jsp:include> 
	
</div>


<jsp:include page="/utils/footer.jsp"></jsp:include>