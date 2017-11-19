<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel='stylesheet' href='<c:url value="/resources/css/main.css" />' type='text/css'/>
<link rel='stylesheet' href='<c:url value="/resources/css/login.css" />' type='text/css'/>
</head>
<body>
	<spring:message code="register.form.username" var="usernamePlaceholder"/>
	<spring:message code="register.form.password" var="passwordPlaceholder"/>
	<spring:message code="register.form.repass" var="repassPlaceholder"/>
	<spring:message code="register.form.button" var="buttonLabelPlaceholder"/>
	<div class="container">
		<div class="wrapper">
			<form:form cssClass="form-login" modelAttribute="register" action="WltMngrWeb/processRegistration" method="POST">
				<form:input cssClass="form-control" path="username" placeholder="${usernamePlaceholder}" />
				<form:password cssClass="form-control" path="password" placeholder="${passwordPlaceholder}" />
				<form:password cssClass="form-control" path="repass" placeholder="${repassPlaceholder}" />
				<input type="submit" value="${buttonLabelPlaceholder}"/>
			</form:form>
		</div>
	</div>
</body>
</html>