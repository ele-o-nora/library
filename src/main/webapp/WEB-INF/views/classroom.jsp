<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" />
<title>Students</title>
<%@ page isELIgnored="false"%>
</head>

<body class="text-center">

	<h2 class="text-muted">Students of class ${classroomName}</h2>
	<table class="table table-striped">
		<tr>
			<th>id</th>
			<th>first name</th>
			<th>last name</th>
			<th>action</th>
		</tr>
		<c:forEach var="student" items="${students}">
			<tr>
				<td class="align-middle">${student.id}</td>
				<td class="align-middle">${student.firstName}</td>
				<td class="align-middle">${student.lastName}</td>
				<td>
				<a href="/books/${student.id}" class="btn btn-outline-secondary btn-sm m-1">List all checked books</a><br/>
				<a href="/findbook?studentId=${student.id}" class="btn btn-outline-secondary btn-sm m-1">Checkout new book</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<a href="/classrooms" class="btn btn-secondary">Back to classrooms list</a>
	
</body>
</html>