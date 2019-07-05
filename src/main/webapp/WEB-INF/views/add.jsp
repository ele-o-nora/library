<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" />
<title>Add student</title>
<%@ page isELIgnored="false"%>
</head>

<body class="text-center">

	<h2 class="text-muted">Add new student</h2>
	<form action="/add" method="POST">
		<input type="hidden" name="classroomName" value="${classroomName}" />
		<div class="form-row">
			<div class="col">
				<input type="text" class="form-control" name="firstName"
					id="firstName" placeholder="First name" />
			</div>
			<div class="col">
				<input type="text" class="form-control" name="lastName"
					id="lastName" placeholder="Last name" />
			</div>
		</div>
		<input type="submit" value="Add student"
			class="btn btn-outline-secondary m-1" />
	</form>

</body>
</html>