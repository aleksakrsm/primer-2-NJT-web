<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Student Service - Login</title>
	<link rel='stylesheet' href='${pageContext.request.contextPath}/webjars/bootstrap/4.4.1/css/bootstrap.min.css'>
	<link rel='stylesheet' href='${pageContext.request.contextPath}/webjars/font-awesome/5.6.3/css/all.min.css'>
</head>
<body>
	<div class="container-fluid">
		<div class="row justify-content-center m-5">
			<div class="card p-4">
				<div class="card-body">
					<h4 class="card-title text-center">Sign in to Student Service</h4>
					<form:form method="post" modelAttribute="authenticationRequestDto">
						<c:if test="${not empty invalid}">
						   <div class="alert alert-danger mb-1 mt-4" role="alert">${invalid}</div>
						</c:if>
						<div class="form-label-group mt-3 mb-4">
							<label for="username">Username</label>
							<form:input type="text" path="username" id="username" class="form-control" 
								placeholder="Username" autofocus="autofocus" />
							<div class="text-danger">
								<form:errors path="username" cssClass="error" class="text-danger" /> 
							</div>	
						</div>
						<div class="form-label-group mt-3 mb-4">
							<label for="password">Password</label>
							<form:input type="password" path="password" id="password" class="form-control"
								 placeholder="Password" />
							<div class="text-danger">
								<form:errors path="password" cssClass="error" />
							</div>
						</div>
						<button class="btn btn-success w-100" type="submit">Sign in</button>
					</form:form>
				</div>
			</div>	
		</div>
	</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>