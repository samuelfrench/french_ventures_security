<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
	<div id='permissionsInfo' style='display:block;'>
		<security:authorize access="hasRole('ROLE_USER')">
			USER MODE
		</security:authorize>
		<security:authorize access="hasRole('ROLE_ADMIN')">
			ADMIN MODE
		</security:authorize>
		<br />
		
	</div>
<div id="navigation" class="navDiv">
	<a href="/french_ventures_secure/home"
		class="button border-fade navButton"><img
		src="/french_ventures_secure/resources/image/home153.svg"
		class="homeIcon" /></a> <a
		href="/french_ventures_secure/view/product/getList"
		class="button border-fade navButton"><img
		src="/french_ventures_secure/resources/image/productsNavPlaceholder.png"
		class="homeIcon" /></a> <a
		href="/french_ventures_secure/view/product/productFinder"
		class="button border-fade navButton"><img
		src="/french_ventures_secure/resources/image/productsNavPlaceholder.png"
		class="homeIcon" /></a>
		<div id='exchangeRate'>Loading...</div>
		<a
		href="/french_ventures_secure_secure/debug/todo"
		class="navButton right" id="logo"> <img
		src="/french_ventures_secure/resources/image/logo/public/FrenchVenture_Logo.svg"
		id="logo"></a>
</div>
<script src="<c:url value="/resources/js/widget/exchangeRate.js" />" type="text/javascript"></script>