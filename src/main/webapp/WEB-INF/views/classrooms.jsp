<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" />
<title>Classrooms</title>
<%@ page isELIgnored="false"%>
</head>

<body class="text-center">

	<h2 class="text-muted">Classrooms</h2>
	<table class="table table-striped">
		<tr>
			<th>id</th>
			<th>name</th>
			<th>action</th>
		</tr>
		<c:forEach var="classroom" items="${classrooms}">
			<tr class="align-middle">
				<td class="align-middle">${classroom.id}</td>
				<td class="align-middle">${classroom.name}</td>
				<td>
				<a href="/classroom/${classroom.name}" class="btn btn-outline-secondary btn-sm m-1">List all students</a><br/>
				<a href="/${classroom.name}/add" class="btn btn-outline-secondary btn-sm m-1">Add new student</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<a href="/" class="btn btn-secondary">Back to main</a>
	
</body>
</html>