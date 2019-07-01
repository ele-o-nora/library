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
	<c:if test="${!empty fine}">
	<p>Fine to pay: ${fine} roubles</p>
	</c:if>
	
	<h2>
		<c:if test="${!empty genre}">${genre} books</c:if>
		<c:if test="${!empty country}">Books from ${country}</c:if>
		<c:if test="${!empty author}">Books by ${author}</c:if>
		<c:if test="${(!empty studentId) and (empty genre) and (empty country) and (empty author)}">Checked books</c:if>
	</h2>
	<table>
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
			<tr>
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
						<input type="submit" value="Return book" />
					</form>
				</td>
				</c:if>
				<c:if test="${(!empty studentId) and ((!empty genre) or (!empty country) or (!empty author))}">
				<td>
					<form method="POST" action="/checkoutbook">
						<input type="hidden" name="studentId" value="${studentId}" />
						<input type="hidden" name="bookId" value="${book.id}" /> 
						<input type="submit" value="Checkout book" />
					</form>
				</td>
				</c:if>
			</tr>
			</c:if>
		</c:forEach>
	</table>
	<c:if test="${empty studentId}">
		<a href="/findbook">Back to search</a>
		<br />
	</c:if>
	<c:if test="${!empty studentId}">
		<a href="/findbook?studentId=${studentId}">Checkout another book</a>
		<br />
	</c:if>
	<a href="/">Back to main</a>
</body>
</html>