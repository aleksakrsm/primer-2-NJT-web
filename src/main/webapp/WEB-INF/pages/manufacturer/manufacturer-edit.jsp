<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>This is page for MANUFACTURER edit.</p>
	
	<c:if test="${not empty information}">
		<div>${information}</div>
	</c:if>
	<c:if test="${not empty exception}">
		<div>${exception}</div>
	</c:if>
	
	
	<form:form action="${pageContext.request.contextPath}/manufacturer/confirm" 
				modelAttribute="manufacturerDto" method="post">
		<form:hidden path="id"/>
		
		<div>Name:</div>
		<div><form:input type="text" path="name"/></div>
		<div><form:errors path="name" /></div>
		
		<div>City:</div>
		<div>
			<form:select path="cityDto" multiple="false">
				<form:options items="${cities}" itemValue="number" itemLabel="name"/>
			</form:select>
		</div>
		<div><form:errors path="cityDto" /></div>
		
		<button id=save>Update</button>
	</form:form>
</body>
</html>