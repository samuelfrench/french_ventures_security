<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="config.jsp" />
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp" />
<script
		src="<c:url value="/resources/js/pageHelpers/adminConsole.js" />"
		type="text/javascript"></script>

<div id="productTableDiv" style="padding-top: 100px;">
<table id="productTable" class="stripe display"><thead>
<tr>

<th>Product Code</th>
<th>Resource URL</th>
<th>Description</th>
<th>Qty Per Unit</th>
<th>Stock Qty</th>
<th>Weight(g)</th>
<th>Retail Price (USD)</th>
<th>Supply Cost (USD)</th>
</tr>
</thead>
<tbody>
<c:forEach var="product" items="${productListing}" varStatus="varStatus">
<tr>
<td>${product.productCode }</td>
<td>${product.resourceURL }</td>
<td>${product.description }</td>
<td>${product.qtyPerUnit }</td>
<td>${product.unitOnHand }</td>
<td>${product.weightInGrams }</td>
<td>${product.retailPriceUSD }</td>
<td>${product.supplyCostUSD }</td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</body>
</html>