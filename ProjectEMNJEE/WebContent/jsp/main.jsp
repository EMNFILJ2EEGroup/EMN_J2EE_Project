<%@ include file="../WEB-INF/jspf/prolog.jspf"%>
<%@page session="true"%>
<%
	if (session.getAttribute("user") == null) {
		response.sendRedirect("index.jsp");
	}
%>

<html>
<%@ include file="../WEB-INF/jspf/head.jspf"%>
<body>
	<center>
		<h1>You are successfully logged in.</h1>
		<p> TODO : checker si la session est correcte.</p>
		<h3>
			<a href="logMeOut.jsp">Logout</a>
		</h3>
	</center>
</body>
</html>	