<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@page import="controller.SessionAttributes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/utils/header.jsp">
	<jsp:param value="Resultat des recherches" name="title"/> 
</jsp:include>


<!-- Searching result page context -->
<div class="container">
	<div>
		<h1>Resultat de recherches</h1>
	</div>
	<div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Photo</th>
					<th>Nom utilisateur</th>
					<th>Addresse Mail</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (request.getSession().getAttribute(SessionAttributes.SEARCH_RESULT) != null) {
					List<User> users = (ArrayList<User>) request.getSession().getAttribute(SessionAttributes.SEARCH_RESULT);
					request.getSession().removeAttribute(SessionAttributes.SEARCH_RESULT);
					for (User user : users) { %>
						
						<tr onclick="location.href='SearchResultProfileServlet?email=<%= user.getEmail() %>'">
							<td><img alt="photo" src="http://placehold.it/80x80" class="img-circle"></td>
							<td><%= user.getNom() %> <%= user.getPrenom() %></td>
							<td><%= user.getEmail() %></td>
						</tr>
				<% 
					}
				}
				%>
				
			</tbody>
		</table>
	</div>
</div>


<jsp:include page="/utils/footer.jsp"></jsp:include>