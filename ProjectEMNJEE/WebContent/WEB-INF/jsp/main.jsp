<%@ include file="../jspf/prolog.jspf"%>

<html>

<%@ include file="../jspf/commonHeader.jspf"%>
<script type="text/javascript" src="${baseURL}/lib/js/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${baseURL}/lib/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${baseURL}/css/login.css">
<title>Main</title>
</head>


<body>
	<%@ include file="../jspf/barreHaut.jspf"%>
	<h1>Liste des events publiés</h1>
	<div class="container">
		<div class="list-group">
			<c:if test="${ ! empty requestScope.eventsList }">
				<c:forEach var="event" items="${requestScope['eventsList']}">
					<a href="${baseURL}/main/info?event=${event.id}"
						class="list-group-item"> ${event.name} </a>
				</c:forEach>
			</c:if>
			<c:if test="${ empty requestScope.eventsList }">
				<p>Aucun evenements publiés</p>
			</c:if>
		</div>
	</div>
</body>
</html>