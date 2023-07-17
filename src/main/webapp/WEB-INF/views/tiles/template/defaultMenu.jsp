<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<c:url value="/city/add" var="cityAdd"></c:url>
	<a href="<c:out value="${cityAdd}"/>">Add city</a>
</div>
<div>
	<c:url value="/city/list" var="cityList"></c:url>
	<a href="<c:out value="${cityList}"/>">List of cities</a>
</div>
<div>
	<c:url value="/manufacturer/add" var="manufacturerAdd"></c:url>
	<a href="<c:out value="${manufacturerAdd}"/>">Manufacturer add</a>
</div>

<div>
	<c:url value="/manufacturer/list" var="manufacturerList"></c:url>
	<a href="<c:out value="${manufacturerList}"/>">Manufacturer list</a>
</div>
