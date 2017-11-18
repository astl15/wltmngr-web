<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="login.form.login"></spring:message></title>
<link rel='stylesheet' href='<c:url value="/resources/css/main.css" />' type='text/css'/>
<link rel='stylesheet' href='<c:url value="/resources/css/login.css" />' type='text/css'/>
</head>
<body>
	<div class="container">
		<div class="wrapper">
			<form action="/WltMngrWeb/login" method="post" class="form-login">
				<input type="text" class="form-control" name="username" placeholder="<spring:message code="login.form.username"></spring:message>"/>
				<input type="password" class="form-control" name="password"  placeholder="<spring:message code="login.form.password"></spring:message>" />
				<input type="checkbox" name="demo"/><spring:message code="login.form.demodescription"></spring:message>
				<input type="submit" value="<spring:message code="login.form.login"></spring:message>"/>
			</form>
		</div>
	</div>
	<p><spring:message code="login.description"></spring:message></p>
	<p><spring:message code="login.regoffer"></spring:message></p>
</body>
</html>