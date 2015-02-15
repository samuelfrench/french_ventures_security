<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	<div style="padding-top: 160px;">
		<table id="customerProductTable">
			<thead>
				<tr>
					<th><h2>Photo</h2></th>
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

</body>
</html>