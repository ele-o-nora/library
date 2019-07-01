<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Students</title>
<%@ page isELIgnored="false"%>
</head>

<body>

	<h2>Students of class ${classroomName}</h2>
	<table>
		<tr>
			<th>id</th>
			<th>first name</th>
			<th>last name</th>
			<th>action</th>
		</tr>
		<c:forEach var="student" items="${students}">
			<tr>
				<td>${student.id}</td>
				<td>${student.firstName}</td>
				<td>${student.lastName}</td>
				<td>
				<a href="/books/${student.id}">List all checked books</a><br/>
				<a href="/findbook?studentId=${student.id}">Checkout new book</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<a href="/classrooms">Back to classrooms list</a>
</body>
</html>