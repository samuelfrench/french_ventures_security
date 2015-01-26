<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:include page="config.jsp" />
<title>Advanced Product Search (Customer Facing)</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div style="padding-top: 160px;">
		<table id="customerProductTable">
			<thead>
				<tr>
					<td>Photo</td>
					<td>Price Per Unit</td>
					<td>Length</td>
					<td>Width</td>
					<td>Unit Weight(g)</td>
					<td>Qty Per Unit</td>
					<td>In Stock</td>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		Loading...
	</div>

</body>
</html>