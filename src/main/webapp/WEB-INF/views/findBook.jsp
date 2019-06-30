<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Find book</title>
<%@ page isELIgnored="false"%>
</head>

<body>

	<h2>Find book</h2>
	<form action="/bygenre" method="GET">
		<select name="genre">
		<c:forEach var="genre" items="${genres}">
			<option value = "${genre.name}">${genre.name}</option>
		</c:forEach>
		</select>
		<input type="submit" value="Search by genre"/>
	</form>
	
		<form action="/bycountry" method="GET">
		<select name="country">
		<c:forEach var="country" items="${countries}">
			<option value = "${country.name}">${country.name}</option>
		</c:forEach>
		</select>
		<input type="submit" value="Search by country"/>
	</form>
	
		<form action="/byauthor" method="GET">
		<select name="authorName">
		<c:forEach var="author" items="${authors}">
			<option value = "${author.firstName} ${author.lastName}">${author.firstName} ${author.lastName}</option>
		</c:forEach>
		</select>
		<input type="submit" value="Search by author"/>
	</form>

</body>
</html>