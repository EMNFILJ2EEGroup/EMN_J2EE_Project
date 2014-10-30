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
	<%@ include file="../jspf/barreHautPanel.jspf"%>
	<center>
		<h1>Page de gestion</h1>
	</center>
	<br />

	
	<h1>Liste de mes évènements</h1>
	<%@ include file="../jspf/toast.jspf"%>
	<div class="container">
		<div class="list-group">
			<c:if test="${ ! empty requestScope.myEventsList }">
				<c:forEach var="event" items="${requestScope['myEventsList']}">
					<a href="${baseURL}/panel/info?event=${event.id}"
						class="list-group-item"> ${event.name} </a>
				</c:forEach>
			</c:if>
			<c:if test="${ empty requestScope.myEventsList }">
				<p>Aucun évènements</p>
			</c:if>
		</div>
	</div>

	<c:if test="${ requestScope.focusedEvent.publication == 0}">
		<%@ include file="../jspf/panelEventNormal.jspf"%>
	</c:if>
	<c:if test="${ requestScope.focusedEvent.publication != 0}">
		<%@ include file="../jspf/panelEventDisabled.jspf"%>
	</c:if>
	
	<h1>Liste des participants</h1>
	<div class="container">
		<div class="list-group">
			<c:if test="${ ! empty requestScope.eventParticipants}">
				<c:forEach var="participant" items="${requestScope.eventParticipants}">
					 <p>${participant.prenom} ${participant.nom} (${participant.email}) - ${participant.societe} </p>
				</c:forEach>
			</c:if>
			<c:if test="${ empty requestScope.eventParticipants }">
				<p>Aucun participants</p>
			</c:if>
		</div>
	</div>
</body>