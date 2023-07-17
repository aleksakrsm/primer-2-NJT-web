<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manufactures</title>
</head>
<body>
	This is page for MANUFACTURE list.
	<div>
		<h1>All MANUFACTURES</h1>
		<table>
			<thead>
				<tr>
					<th>Name</th>
					<th>City code</th>
					<th>City name</th>
					<th>Details</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="m" items="${manufactures}">
				<tr>
					<td>${m.name}</td>
					<td>${m.cityDto.number}</td>
					<td>${m.cityDto.name}</td>
					<td><a href="${pageContext.request.contextPath}/manufacturer/details/id/${m.id}"> details</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>