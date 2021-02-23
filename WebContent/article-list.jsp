<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Pharmacie Management</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: green">
			<div>
				<a href="#" class="navbar-brand"> Pharmacie </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Articles</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
			
		<div class="container">
		
			
			
			<h1 class="display-4 text-center">Artice List</h1>
			
			<!-- <div class="w-50 container text-left input-group">
			  <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search"
			    aria-describedby="search-addon" />
			  <button type="button" class="btn btn-outline-primary">search</button>
			</div> -->
			
			
			
			<br>
			</div>
			<div class="container text-right">
				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add Article</a>
			</div>
			<table class="table table-dark">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Prix</th>
						<th>Quantité</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					
					<c:forEach var="article" items="${listArticle}">

						<tr>
							<td><c:out value="${article.id}" /></td>
							<td><c:out value="${article.name}" /></td>
							<td><c:out value="${article.prix}" />&nbsp MAD</td>
							<td><c:out value="${article.quantite}" /></td>
							<td><a href="edit?id=<c:out value='${article.id}' />">Edit</a></td>
							<td><a href="delete?id=<c:out value='${article.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>
				

			</table>
			
		
	</div>
</body>
</html>
