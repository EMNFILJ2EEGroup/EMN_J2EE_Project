<%@ include file="../jspf/prolog.jspf"%>

<html>

<%@ include file="../jspf/commonHeader.jspf"%>
<script type="text/javascript"
	src="${baseURL}/lib/js/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${baseURL}/lib/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${baseURL}/css/login.css">
<title>Page principale</title>
</head>


<body>
	<%@ include file="../jspf/barreHaut.jspf"%>
	<h1>Page de gestion</h1> <br/>
	
	<h1>Liste de mes évènements</h1>
	<c:if test="${ ! empty sessionScope.toast }">
		<div id="msgbox" class="alert alert-danger" role="alert">${sessionScope.toast}</div>
	</c:if>
	<div class="container">
		<div class="list-group">
			<c:if test="${ ! empty requestScope.eventsList }">
				<c:forEach var="event" items="${requestScope['eventsList']}">
					<a href="${baseURL}/panel/info?event=${event.id}"
						class="list-group-item"> ${event.name} </a>
				</c:forEach>
			</c:if>
			<c:if test="${ empty requestScope.eventsList }">
				<p>Aucun évènements</p>
			</c:if>
		</div>
	</div>
</body>