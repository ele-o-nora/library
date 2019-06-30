<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Classrooms</title>
<%@ page isELIgnored="false"%>
</head>

<body>

	<h2>Classrooms</h2>
	<table>
		<tr>
			<th>id</th>
			<th>name</th>
			<th>action</th>
		</tr>
		<c:forEach var="classroom" items="${classrooms}">
			<tr>
				<td>${classroom.id}</td>
				<td>${classroom.name}</td>
				<td>
				<a href="/classroom/${classroom.name}">List all students</a><br/>
				<a href="/${classroom.name}/add">Add new student</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<a href="/">Back to main</a>
</body>
</html>