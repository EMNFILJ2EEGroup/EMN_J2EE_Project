<%@ include file="../jspf/prolog.jspf"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="baseURL"
	value="${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, pageContext.request.contextPath)}" />
<html>
<%@ include file="../jspf/head.jspf"%>
<body>
	<center>
		<h1>/main -> index.jsp</h1>
		<h2>You are successfully logged in</h2>
		<h3>
			<a href="/ProjectEMNJEE/logout">Logout</a>
		</h3>
		<p>TODO LOGOUT</p>

		<p>${requestScope.path}</p>
		<p>${requestScope.requestURI}</p>
		<p>${requestScope.method}</p>
		<p>${request.getServerPort()}</p>


	</center>
</body>
</html>