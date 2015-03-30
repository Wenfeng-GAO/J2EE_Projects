<%@page import="model.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.lang.Exception" %>
<%@ page import="model.db.CategoryDB" %>

<jsp:include page="/utils/header.jsp">
	<jsp:param value="Project Farm" name="title" />
</jsp:include>


<div class="container">

<form  action="<%= request.getContextPath()%>/NewProjectServlet">

	<!-- Show warnings if mistakes were taken -->
	<% 
	if (session.getAttribute("FORMAT_ERROR") != null) {
		Exception e = (Exception) session.getAttribute("FORMAT_ERROR");
	%>
		<div class="container text-center">
			<div class="alert alert-danger alert-dismissible" role="alert">
		  		
		  		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<%= e.getMessage() %>
			
			</div>
		</div>
	
	<%
	}
	%>
	
	
	<!-- Title -->
	<div class="form-group">

		<label for="title" >Title</label>
		<div >
			<input type="text" class="form-control" placeholder="Your idea" name="acronym" 
			value="<%= request.getParameter("acronym") != null ? request.getParameter("acronym") : "" %>">
		</div>
		
	</div>


	<!-- Description -->
	<div class="form-group">

		<label for="description" >Description</label>
		<div >
			<textarea class="form-control" rows="5" name="description"><%= request.getParameter("description") != null ? request.getParameter("description") : ""%></textarea>
		</div>

	</div>


	<!-- Category -->
	<div class="form-group">

		<label for="category" >Category</label>
		<div >
			<select class="form-control" name="category">
				
<%
				for (Category category : CategoryDB.getCategories()) {
%>
					
					<option><%= category.getDescription() %></option>

<%
				}
%>
				
			</select>
		</div>

	</div>
	

	<!-- Budget -->
	<div class="form-group">

		<label for="budget">Budget(EUR)</label>
		<div>
			<input type="text" class="form-control" placeholder="Input numbers only" name="budget"  
			value="<%= request.getParameter("budget") != null ? request.getParameter("budget") : ""%>">
		</div>

	</div>
	

	<!-- Funding duration -->
	<div class="form-group">
		
		<label for="funding_duration">Funding duration</label>
		
		<div>
			<input type="text" class="form-control" placeholder="Input numbers only" name="funding_duration" 
			value="<%= request.getParameter("funding_duration") != null ? request.getParameter("funding_duration") : "" %>">
		</div>
		
	</div>


	<!-- Save and discard button -->
	<div class="form-inline">

		<div class="form-group">
			<button type="button" class="btn btn-default">Discard</button>
			<button type="submit" class="btn btn-info">Save</button>
		</div>

	</div>


</form>


</div>


<jsp:include page="/utils/footer.jsp"></jsp:include>