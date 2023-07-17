<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<p>This is page for city view.</p>

<c:if test="${not empty information}">
	<div>${information}</div>
</c:if>
<c:if test="${not empty exception}">
	<div>${exception}</div>
</c:if>


<form action="#" method="post">
	<div>City number:</div>
	<div>
		<input type="text" name="number" value="${city.number}"
			readonly="readonly" />
	</div>

	<div>City name:</div>
	<div>
		<input type="text" name="name" value="${city.name}"
			readonly="readonly" />
	</div>
</form>
