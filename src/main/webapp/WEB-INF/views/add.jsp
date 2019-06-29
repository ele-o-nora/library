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

	<h2>Add new student</h2>
	<form action="/add" method="POST">
		<input type="hidden" name = "classroomName" value="${classroomName}"/>
		<label for="firstName">First name</label>
		<input type="text" name="firstName" id="firstName"/>
		<br/>
		<label for="firstName">Last name</label>
		<input type="text" name="lastName" id="lastName"/>
		<br/>
		<input type="submit" value="Add student"/>
	</form>

</body>
</html>