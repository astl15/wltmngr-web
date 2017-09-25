<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<form action="" class="form-login">
				<input type="text" class="form-control" name="username" placeholder="Username" autofocus="">
				<input type="password" name="password" class="form-control"  placeholder="Password" >
				<input type="checkbox" name="mode" value="isDemoMode">Demo Mode
				<input type="submit" value="Login"></button>
			</form>
		</div>
	</div>
</body>
</html>