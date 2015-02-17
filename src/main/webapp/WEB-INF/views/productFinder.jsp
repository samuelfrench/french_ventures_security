<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:include page="config.jsp" />

<script
		src="<c:url value="/resources/js/pageHelpers/productFinder.js" />"
		type="text/javascript"></script>
<title>Advanced Product Search (Customer Facing)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="statusMessage"></div>
	<!-- TODO - add a cool search tab -->
	
	<security:authorize access="hasRole('ROLE_ADMIN')">
	<div id="addButton" style='padding-top: 20px; float:left; display:block; '><input type="button" id='adminAddButton' value='Add Product (Administrator Only)'></div>
	</security:authorize>

	<div style="padding-top: 160px;">
		<table id="customerProductTable">
			<thead>
				<tr>
					<th><h2>Photo</h2></th>
					<th><h2>Product Code</h2></th>
					<th><h2>Price Per Unit</h2></th>
					<!--  TODO <th><h2>Length</h2></th>
					<th><h2>Width</h2></th> -->
					<th><h2>Unit Weight(g)</h2></th>
					<th><h2>Qty Per Unit</h2></th>
					<th><h2>Units In Stock</h2></th>
					<th><h2>In Stock</h2></th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		
	</div>
<div id='addProductModal' class='bigModal'>
<span id='formStatusMessage'></span>
<span id='formErrors'></span>
<form:form method="POST" commandName="addProductModel" action="/french_ventures_secure/view/product/addNewProduct">
 
    
      <label class='formLabel' for="productCode">Product Code (Must be Unique)</label>
      <form:input type="text" path="productCode" id="productCode" value="" class="text ui-widget-content ui-corner-all required" />

      <label class='formLabel'for="weightInGrams">Weight/1000 (grams)</label>
      			<form:input type="text" path="weightInGrams" id="weightInGrams" value="" class="text ui-widget-content ui-corner-all"/>
    <label class='formLabel'  path="retailPriceUSD">Retail Price (USD) $</label>
     		 <form:input type="text" path="retailPriceUSD" id="retailPriceUSD" value="" class="text ui-widget-content ui-corner-all"/>
     <label class='formLabel' path="length">Length (cm)</label>
      		<form:input type="text" path="length" id="length" value="" class="text ui-widget-content ui-corner-all"/>
     <label class='formLabel' path="width">Width (cm)</label>
      		<form:input type="text" path="width" id="width" value="" class="text ui-widget-content ui-corner-all"/>
       <label class='formLabel' path="thickness">Thickness (mm)</label>
      			<form:input type="text" path="thickness" id="thickness" value="2" class="text ui-widget-content ui-corner-all"/>
       <label class='formLabel' path="qtyPerUnit">Quantity per Unit</label>
      			<form:input type="text" path="qtyPerUnit" id="qtyPerUnit" value="200" class="text ui-widget-content ui-corner-all"/>
      <label class='formLabel'  path="description">Description</label><br>
      			<form:textarea path="description" id="description" class="text ui-widget-content ui-corner-all" />

      <!-- Allow form submission with keyboard without duplicating the dialog button -->
      <input type="submit" tabindex="1" value="Add/Update Product" />

  </form:form></div>
</body>
</html>