<%@ include file="../jspf/prolog.jspf"%>

<html>

<%@ include file="../jspf/commonHeader.jspf"%>
<title>Main Event </title>
</head>


<body>
	<center>
		<div>ICI bandeau du haut</div>
		
		<h1>/main/event -> mainEvent.jsp</h1>
		<h3>
			<a href="${baseURL}/logout">Logout</a>
		</h3>

	<h1>Liste des events publi�s</h1>
		<c:if test="${ ! empty requestScope.eventsList }">
			<c:forEach var="item" items="${requestScope['eventsList']}">
				<p>${item.name}></p>
			</c:forEach>
		</c:if>
		<c:if test="${ empty requestScope.eventsList }">
			<p>Aucun evenements publi�s</p>
		</c:if>
			<h1>Evenement trouv� !!! </h1>
			<p> (sinon automatiquement redirig� vers 404)</p>
			<h1>Affichage evenement </h1>

			<h1>Formulaire inscription evenement</h1>
	</center>
</body>
</html>