<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
<span id='formErrors'></span>
<form>
    <fieldset>
    
      <label class='formLabel' for="productCode">Product Code (Must be Unique)</label>
      <input type="text" name="productCode" id="productCode" value="" class="text ui-widget-content ui-corner-all required">

      <label class='formLabel'for="weightInGrams">Weight/1000 (grams)</label>
      <input type="text" name="weightInGrams" id="weightInGrams" value="" class="text ui-widget-content ui-corner-all">
     <label class='formLabel'for="length">Length (cm)</label>
      <input type="text" name="length" id="length" value="" class="text ui-widget-content ui-corner-all">
     <label class='formLabel'for="width">Width (cm)</label>
      <input type="text" name="width" id="width" value="" class="text ui-widget-content ui-corner-all">
       <label class='formLabel'for="thickness">Thickness (mm)</label>
      <input type="text" name="thickness" id="thickness" value="2" class="text ui-widget-content ui-corner-all">
       <label class='formLabel'for="qtyPerUnit">Quantity per Unit</label>
      <input type="text" name="qtyPerUnit" id="qtyPerUnit" value="200" class="text ui-widget-content ui-corner-all">
      <label class='formLabel' for="description">Description</label><br>
      <textarea name="description" id="description" class="text ui-widget-content ui-corner-all"></textarea>

      <!-- Allow form submission with keyboard without duplicating the dialog button -->
      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
    </fieldset>
  </form></div>
</body>
</html>