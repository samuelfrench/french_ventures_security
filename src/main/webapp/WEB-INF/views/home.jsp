<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Samuel French Ventures</title>
<jsp:include page="config.jsp" />

<!--
		Used for including CSRF token in JSON requests
		Also see bottom of this file for adding CSRF token to JQuery AJAX requests
	-->
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

</head>
<body>
	<!-- buttons are button class plus border fade class -->
	<jsp:include page="header.jsp" />
	
	Home page. Click the buttons above to navigate throughout the site.
	
</body>
</html>