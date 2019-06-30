<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Books</title>
<%@ page isELIgnored="false"%>
</head>

<body>
	<h2>
	<c:if test="${!empty genre}">${genre} books</c:if>
	<c:if test="${!empty country}">Books from ${country}</c:if>
	<c:if test="${!empty author}">Books by ${author}</c:if>
	</h2>
	<table>
		<tr>
			<th>id</th>
			<th>title</th>
			<th>available</th>
		</tr>
		<c:forEach var="book" items="${books}">
			<tr>
				<td>${book.id}</td>
				<td>${book.title}</td>
				<td>${book.available}</td>
			</tr>
		</c:forEach>
	</table>
	<a href="/findbook">Back to search</a><br/>
	<a href="/">Back to main</a>
</body>
</html>