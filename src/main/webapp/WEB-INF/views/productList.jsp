<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:include page="config.jsp" />
<title>Product Index</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<script
		src="<c:url value="/resources/js/pageHelpers/productList.js" />"
		type="text/javascript"></script>
	<div id='mainProductContainer'>
		<h2>Product Index</h2>
		<div id='productsSmall'>
			<c:forEach var="product" items="${productListing}"
				varStatus="varStatus">
				<div id="${product.productCode}" class="productListingContainer">
					<a class='thumbLink'> <span class="thumb">
							<img
							src="/french_ventures_secure/resources/image/product/thumb/${product.resourceURL}"
							class="productImage image small ">
					</span>
					</a> <span class="details">Test - ${product.productCode}</span>
				</div>
			</c:forEach>
		</div>
		<div id="selectedProduct"></div>
	</div>
</body>
</html>