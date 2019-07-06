<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" />
<title>Books</title>
<%@ page isELIgnored="false"%>
</head>

<body class="text-center">
	<c:if test="${!empty fine}">
	<p class="text-danger">Fine to pay: ${fine} roubles</p>
	</c:if>
	
	<h2 class="text-muted">
		<c:if test="${!empty genre}">${genre} books</c:if>
		<c:if test="${!empty country}">Books from ${country}</c:if>
		<c:if test="${!empty author}">Books by ${author}</c:if>
		<c:if test="${(!empty studentId) and (empty genre) and (empty country) and (empty author)}">Checked books</c:if>
	</h2>
	<div class="row">
	<div class="col-sm-8 offset-sm-2">
	<table class="table table-striped">
		<tr>
			<th>id</th>
			<th>title</th>
			<c:if test="${(!empty genre) or (!empty author) or (!empty country)}">
				<th>available</th>
			</c:if>
			<c:if test="${!empty studentId}">
				<th>action</th>
			</c:if>
		</tr>
		<c:forEach var="book" items="${books}">
		<c:if test="${book.available > 0 or (empty studentId) or ((empty genre) and (empty country) and (empty author))}">
			<tr class="align-middle">
				<td>${book.id}</td>
				<td>${book.title}</td>
				<c:if test="${(!empty genre) or (!empty author) or (!empty country)}">
					<td>${book.available}</td>
				</c:if>
				<c:if test="${(!empty studentId) and (empty genre) and (empty country) and (empty author)}">
				<td>
					<form method="POST" action="/returnbook">
						<input type="hidden" name="studentId" value="${studentId}" />
						<input type="hidden" name="bookId" value="${book.id}" /> 
						<input type="submit" value="Return book" class="btn btn-outline-secondary btn-sm"/>
					</form>
				</td>
				</c:if>
				<c:if test="${(!empty studentId) and ((!empty genre) or (!empty country) or (!empty author))}">
				<td>
					<form method="POST" action="/checkoutbook">
						<input type="hidden" name="studentId" value="${studentId}" />
						<input type="hidden" name="bookId" value="${book.id}" /> 
						<input type="submit" value="Checkout book" class="btn btn-outline-secondary btn-sm"/>
					</form>
				</td>
				</c:if>
			</tr>
			</c:if>
		</c:forEach>
	</table>
	</div>
	</div>
	<c:if test="${empty studentId}">
		<a href="/findbook" class="btn btn-secondary">Back to search</a>
	</c:if>
	<c:if test="${!empty studentId}">
		<a href="/findbook?studentId=${studentId}" class="btn btn-secondary">Checkout another book</a>
	</c:if>
	<a href="/" class="btn btn-secondary">Back to main</a>
	
</body>
</html>