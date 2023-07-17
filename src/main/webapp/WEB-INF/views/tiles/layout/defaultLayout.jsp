<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">	
	<link rel='stylesheet' href='${pageContext.request.contextPath}/webjars/bootstrap/4.4.1/css/bootstrap.min.css'>
	<link rel='stylesheet' href='${pageContext.request.contextPath}/webjars/bootstrap-table/1.16.0/dist/bootstrap-table.min.css'>
	<link rel='stylesheet' href='${pageContext.request.contextPath}/webjars/font-awesome/5.6.3/css/all.min.css'>
	<link href='${pageContext.request.contextPath}/resources/css/styles.css' rel="stylesheet"/>
	
	<title>
		<tiles:getAsString name="title"></tiles:getAsString>
	</title>
</head>
<body>
	<header id ="header">
		<tiles:insertAttribute name="header"/>
	</header>
	
	<section id="sidemenu">
		<tiles:insertAttribute name="menu"/>
	</section>
	
	<section id="site-content">
		<tiles:insertAttribute name="body"/>
	</section>

	<footer id ="footer">
		<tiles:insertAttribute name="footer"/>
	</footer>
</body>

	<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.1.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	
</html>