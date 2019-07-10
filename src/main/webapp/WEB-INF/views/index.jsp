<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" />
<title>Library</title>
<%@ page isELIgnored="false"%>
</head>

<body class="text-center">

	<h2 class="text-info">Library</h2>
	<a href="/classrooms" class="btn btn-secondary">List all classrooms</a>
	<a href="/findbook" class="btn btn-secondary">Find books</a>
	<sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
		<form action="/logout" method="POST">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" value="Logout" class="btn btn-info m-1"/>
		</form>
	</sec:authorize>
</body>
</html>