<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="category-container">
	<h3>Insertie rapida a unei plati</h3>
	<p>Introduceti rapid datele unei plati efectuate astazi</p>
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
</div>
<div class="category-container">
	<h3>Costuri totale lunare</h3>
	<p>Sumarul lunii curente</p>
	<p class="p-summary">Suma totala: ${monthlySum}</p>
	<p class="p-summary">Media zilnica: ${monthlyAvg}</p>		
		
	<h3>Costuri totale per categorie</h3>
	<p>In aceasta sectiune sunt afisate costurile totale per catergorie pentru luna curenta</p>
	<c:if test="${fn:length(amountsPerCategory) gt 0}">
		<table>
			<tr>
				<th>Categorie</th>
				<th>Suma</th>
			</tr>
		<c:forEach items = "${amountsPerCategory}" var="amount">
			<tr>
				<td>${amount.category}</td>
				<td>${amount.amount}</td>
			</tr>			
		</c:forEach>
		</table>
	</c:if>
</div>
<div class="category-container">	
	<h3>Costuri totale plati zilnice in luna curenta</h3>
	<p>In aceasta sectiune sunt afisate costurile totale zilnice pentru luna curenta</p>
	<c:if test="${fn:length(amountsPerDate) gt 0}">
		<table>
			<tr>				
				<th>Data</th>
				<th>Suma</th>
			</tr>
		<c:forEach items = "${amountsPerDate}" var="amountPerDate">
			<tr>
				<td>${amountPerDate.date}</td>
				<td>${amountPerDate.amount}</td>
			</tr>			
		</c:forEach>
		</table>
	</c:if>
</div>
<div class="category-container">	
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
<div class="category-container">	
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
</div>
