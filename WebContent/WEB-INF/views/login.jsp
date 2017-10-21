<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel='stylesheet' href='<c:url value="/resources/css/main.css" />' type='text/css'/>
<link rel='stylesheet' href='<c:url value="/resources/css/login.css" />' type='text/css'/>
</head>
<body>
	<div class="container">
		<div class="wrapper">
			<form:form action="/WltMngrWeb/login" method="post" modelAttribute="user" cssClass="form-login">
				<form:input path="username" cssClass="form-control" name="username" placeholder="Username" autofocus=""/>
				<form:input path="password" type="password" name="password" class="form-control"  placeholder="Password" />
				<form:checkbox path="demo"/>Demo Mode
				<input type="submit" value="Login"/>
			</form:form>
		</div>
	</div>
</body>
</html>