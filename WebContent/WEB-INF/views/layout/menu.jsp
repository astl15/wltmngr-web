<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<ul>
		<li>Management Plati</li>
			<ul>
				<li><a href="<c:url value="/payments/custom"/>">Introducere plata noua</a></li>
			</ul>
		<li>Sumar Plati</li>
			<ul>
				<li><a href="<c:url value="/payments/custom"/>">Sumar lunar</a></li>
			</ul>
	</ul>
	<!--<tiles:importAttribute name="currentLocale"/>${currentLocale}-->
	
</div>