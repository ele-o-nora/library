<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" />
<title>Find book</title>
<%@ page isELIgnored="false"%>
</head>

<body class="text-center">
	
	<h2 class="text-muted">Find book</h2>
	<div class="row">
	<div class="col-sm-6 offset-sm-3">
	<form action="/bygenre" method="GET">
		<c:if test="${!empty studentId}"><input type="hidden" name="studentId" value="${studentId}"></c:if>
		<select name="genre" class="custom-select">
		<c:forEach var="genre" items="${genres}">
			<option value = "${genre.name}">${genre.name}</option>
		</c:forEach>
		</select>
		<input type="submit" value="Search by genre" class="btn btn-outline-secondary btn-sm m-1"/>
	</form>
	
		<form action="/bycountry" method="GET">
		<c:if test="${!empty studentId}"><input type="hidden" name="studentId" value="${studentId}"></c:if>
		<select name="country" class="custom-select">
		<c:forEach var="country" items="${countries}">
			<option value = "${country.name}">${country.name}</option>
		</c:forEach>
		</select>
		<input type="submit" value="Search by country" class="btn btn-outline-secondary btn-sm m-1"/>
	</form>
	
		<form action="/byauthor" method="GET">
		<c:if test="${!empty studentId}"><input type="hidden" name="studentId" value="${studentId}"></c:if>
		<select name="authorName" class="custom-select">
		<c:forEach var="author" items="${authors}">
			<option value = "${author.firstName} ${author.lastName}">${author.firstName} ${author.lastName}</option>
		</c:forEach>
		</select>
		<input type="submit" value="Search by author" class="btn btn-outline-secondary btn-sm m-1"/>
	</form>
	</div></div>
</body>
</html>