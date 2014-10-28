<%@ include file="../jspf/prolog.jspf"%>

<html>

<%@ include file="../jspf/commonHeader.jspf"%>
<title>Main</title>
</head>


<body>
	<center>
		<div>ICI bandeau du haut</div>
		
		<h1>/main -> main.jsp</h1>
		<h3>
			<a href="${baseURL}/logout">Logout</a>
		</h3>

	<h1>Liste des events publiés</h1>
		<c:if test="${ ! empty requestScope.eventsList }">
			<c:forEach var="item" items="${requestScope['eventsList']}">
				<p>${item.name}></p>
			</c:forEach>
		</c:if>
		<c:if test="${ empty requestScope.eventsList }">
			<p>Aucun evenements publiés</p>
		</c:if>

	</center>
</body>
</html>