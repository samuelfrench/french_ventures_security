<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>Product Image</h3>
${product.largeImageDiv}
<h3>Detailed Information</h3>
Product Description: ${product.description} <br />
Qty Per Unit: ${product.qtyPerUnit} <br />
Retail Price: ${product.retailPriceUSD} <br />