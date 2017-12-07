<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="menu-wrapper">
	<h3>Meniu</h3>
	<ul class="main-menu">
		<li>Management Plati</li>
		<li>
			<ul class="sub-menu">
				<li><a href="<c:url value="/payments/custom"/>">Introducere plata noua</a></li>
			</ul>
		</li>
		<li>Sumar Plati</li>
		<li>
			<ul class="sub-menu">
				<li><a href="<c:url value="/payments/custom"/>">Sumar lunar</a></li>
			</ul>
		</li>
	</ul>
	<!--<tiles:importAttribute name="currentLocale"/>${currentLocale}-->
	
</div>