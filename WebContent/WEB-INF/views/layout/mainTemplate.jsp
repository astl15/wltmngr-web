<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<link rel="stylesheet" href='<c:url value="/resources/css/main.css" />' type="text/css"/>
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  		<script src="<c:url value="/resources/js/datepicker.js" />" type="text/javascript"></script>
		<title><tiles:getAsString name="title" /></title>
	</head>
	<body>
		<header>
            <tiles:insertAttribute name="header" />
        </header>
     	<div class="page-container">
     		<div class="side-menu">
     			<tiles:insertAttribute name="menu" />
     		</div>
     		<div class="site-content">
     			<div class="body-container">
     				<tiles:insertAttribute name="body" />
     			</div>
     		</div>
     	</div>
        <footer id="footer">
            <tiles:insertAttribute name="footer" />
        </footer>
	</body>
</html>