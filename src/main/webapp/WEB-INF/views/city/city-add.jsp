<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<p>This is page for city add.</p>

<c:if test="${not empty information}">
	<div>${information}</div>
</c:if>
<c:if test="${not empty exception}">
	<div>${exception}</div>
</c:if>

<div class="container">
	<form:form action="${pageContext.request.contextPath}/city/save" modelAttribute="cityDto" method="post">
	
	<div class="row">
			<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
				<div class="card card-signin my-5">
					<div class="card-body">
						<h5 class="card-title text-center">Add new city</h5>
						<form class="form-signin">
							<div class="form-label-group">
								<form:input type="input" path="number"  id="number" class="form-control" placeholder="City number"></form:input>
								<label for="inputEmail">City number</label>
							</div>
							<div>
								<form:errors path="number" />
							</div>
							<div class="form-label-group">
								<form:input type="input" id="name" class="form-control" path="name" placeholder="Password"></form:input>
								<label for="name">Password</label>
							</div>


							<button class="btn btn-lg btn-primary btn-block text-uppercase"
								type="submit">Save</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>
