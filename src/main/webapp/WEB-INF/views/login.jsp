<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" />
<title>Sign in</title>
<%@ page isELIgnored="false"%>
</head>

<body class="text-center">
	<c:if test="${!empty param.error}">
	<p class="text-danger">Bad credentials!</p>
	</c:if>
	<h2 class="text-muted">Please sign in</h2>
	<form action="/perform_login" method="POST">
		<div class="form-row">
			<div class="col-sm-4 offset-sm-2">
				<input type="text" class="form-control" name="username"
					placeholder="Username" />
			</div>
			<div class="col-sm-4">
				<input type="password" class="form-control" name="password"
					placeholder="Password" />
			</div>
		</div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" value="Submit"
			class="btn btn-outline-secondary m-1" />
	</form>

</body>
</html>