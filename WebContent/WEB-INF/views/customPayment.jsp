<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="category-container">
	<h3>Insertie a unei plati noi</h3>
	<p>Introduceti datele unei plati</p>
	<form:form modelAttribute="customPayment" action="/WltMngrWeb/payments/custom" method="POST">
	<form:label path="date">Data</form:label>
	<form:input path="date" id="datepicker" />
	<form:label path="description">Descriere</form:label>
	<form:input path="description"/>
	<form:label path="amount">Suma</form:label>
	<form:input path="amount"/>
	<form:label path="category">Categorie</form:label>
		<form:select path="category">
			<c:forEach items="${categories}" var="category">
				<c:if test="${currentLocale eq 'ro-ro'}">
					<form:option value="${category.id}" label="${category.label}"/>
				</c:if>	
			</c:forEach>
		</form:select>
	<form:button>Submit</form:button>
	</form:form>
</div>

