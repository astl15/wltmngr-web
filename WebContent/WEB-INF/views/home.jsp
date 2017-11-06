<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<h3>Insertie rapida</h3>
	<ul>
		<c:forEach items="${categories}" var="category">
			<li>${category.label}</li>
		</c:forEach>
	</ul>
</div>
