<%@page import="controller.SessionAttributes"%>
<%@page import="model.exception.InvalidLoginException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%= request.getParameter("title") %></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/Sujet_1/ext/bootstrap/3.2.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/Sujet_1/ext/bootstrap/3.2.2/css/styles.css">
</head>


<body>

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
    	
    	<div class="navbar-header">
      		<a class="navbar-brand" href="<%= request.getContextPath()%>/index.jsp">TO DO MORE SOCIAL NETWORKING</a>
    	</div>
    
    <div>
    
    	<!-- When the user input the right email and password -->
      	<% if(session.getAttribute(SessionAttributes.LOGIN_VALID) != null) { %>
		
		<ul class="nav navbar-nav pull-right">
        	<li class="dropdown">
          		<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
          			<span class="glyphicon glyphicon-user" aria-hidden="true"></span> 
          			
		<%
			User user = (User) session.getAttribute(SessionAttributes.LOGIN_VALID);
			String nom = user.getNom();
			String prenom = user.getPrenom();
		%>
		
			<%= prenom %>&nbsp&nbsp<%= nom %>
		
          			<span class="caret"></span>
          		</a>
          		
         		<ul class="dropdown-menu" role="menu">
        			<li><a href="<%= request.getContextPath()%>/ModifyServlet">Mon profil</a></li>
            		<li><a href="<%= request.getContextPath()%>/LogoutServlet">Deconnexion</a></li>
        		</ul>
        		
        	</li>
         </ul>

			<form class="navbar-form navbar-left pull-right" role="search" action="SearchServlet">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Recherche" name="search">
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>

			<!-- When the password or the email address is wrong -->
		<% } else if (session.getAttribute(SessionAttributes.LOGIN_FAILED) != null){ 
				Exception e = (InvalidLoginException) session.getAttribute(SessionAttributes.LOGIN_FAILED);
				session.removeAttribute(SessionAttributes.LOGIN_FAILED);
		%>
	
		<form class="navbar-form form-inline pull-right"  action="<%= request.getContextPath()%>/LoginServlet">

			<label class="label label-danger"><%= e.getMessage() %></label> 
			<input type="text" class="form-control" placeholder="john@example.com" name="email">
    		<input type="password" class="form-control" placeholder="Password" name="password">
    		<button type="submit" class="btn btn-default">Sign in</button>
    		
 		</form>
	
	<% } else {%>
		
		<form class="navbar-form form-inline pull-right"  action="<%= request.getContextPath()%>/LoginServlet">
		
    		<input type="text" class="form-control" placeholder="john@example.com" name="email">
    		<input type="password" class="form-control" placeholder="Password" name="password">
    		<!-- <br/>
    		<div class="checkbox">
    			<label>
      				<input type="checkbox">Garder ma session active
    			</label>
  			</div> -->
    		<button type="submit" class="btn btn-primary">Sign in</button>
    		
		</form>
	
	<% } %>
	
	</div>	    
  </div>
</nav>
<!-- container, body and HTML tags are still opened -->