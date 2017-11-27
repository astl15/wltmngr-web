<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div>
	<h3>Insertie rapida a unei plati</h3>
	<p>Introduceti rapid datele unei plati efectuate astazi<p>
	<form:form modelAttribute="payment" action="/WltMngrWeb/" method="POST">
		<form:label path="description">Descriere</form:label>
		<form:input path="description"/>
		<form:label path="amount">Suma</form:label>
		<form:input path="amount"/>
		<form:label path="category">Categorie</form:label>
			<form:select path="category">
				<c:forEach items="${categories}" var="category">
					<c:if test="${category.locale eq currentLocale}">
						<form:option value="${category.id}" label="${category.label}"/>
					</c:if>	
				</c:forEach>
			</form:select>
		<form:button>Submit</form:button>
	</form:form>
	<h3>Sumar plati efectuate recent</h3>
	<p>In aceasta sectiune sunt afisate cele mai recente 10 plati efectuate</p>
	<c:if test="${fn:length(lastPaymentsByPrincipal) gt 0}">
		<table>
			<tr>
				<th>Descriere</th>
				<th>Categorie</th>
				<th>Suma</th>
				<th>Data</th>
			</tr>
		<c:forEach items = "${lastPaymentsByPrincipal}" var="payment">
			<tr>
				<td>${payment.description}</td>
				<td>${payment.category.label}</td>
				<td>${payment.amount}</td>
				<td>${payment.date}</td>
			</tr>			
		</c:forEach>
		</table>
	</c:if>
	<h3>Sumar plati luna curenta</h3>
	<c:if test="${fn:length(paymentsThisMonth) gt 0}">
		<table>
			<tr>
				<th>Descriere</th>
				<th>Categorie</th>
				<th>Suma</th>
				<th>Data</th>
			</tr>
		<c:forEach items = "${paymentsThisMonth}" var="paymentTM">
			<tr>
				<td>${paymentTM.description}</td>
				<td>${paymentTM.category.label}</td>
				<td>${paymentTM.amount}</td>
				<td>${paymentTM.date}</td>
			</tr>			
		</c:forEach>
		</table>
	</c:if>
</div>
