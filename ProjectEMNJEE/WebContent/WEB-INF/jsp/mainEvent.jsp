<%@ include file="../jspf/prolog.jspf"%>

<html>

<%@ include file="../jspf/commonHeader.jspf"%>
<script type="text/javascript"
	src="${baseURL}/lib/js/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${baseURL}/lib/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${baseURL}/css/login.css">
<link rel="stylesheet" type="text/css" href="${baseURL}/css/event.css">
<title>Page de l'evenement</title>
</head>


<body>
	<%@ include file="../jspf/barreHaut.jspf"%>
	<h1>Liste des events publiés</h1>
	<c:if test="${ ! empty sessionScope.toast }">
		<div id="msgbox" class="alert alert-danger" role="alert">${sessionScope.toast}</div>
	</c:if>
	<div class="container">
		<div class="list-group">
			<c:forEach var="event" items="${requestScope['eventsList']}">
				<a href="${baseURL}/main/info?event=${event.id}"
					class="list-group-item"> ${event.name} </a>
			</c:forEach>
		</div>

		<div class="eventContainer">
			<h2>${requestScope.focusedEvent.name}</h2>
			<p>adresse : ${requestScope.focusedEvent.adresse}</p>
			<p id="schedule">${requestScope.focusedEvent.dateDebut}
				${requestScope.focusedEvent.heureDebut} -->
				${requestScope.focusedEvent.dateFin}
				${requestScope.focusedEvent.heureFin}</p>
		</div>
		<form id="inscriptionEvent_frm"
			action="${baseURL}/main/info?event=${requestScope.focusedEvent.id}" method="post"
			class="jumbotron" role="form">
			<div class="container">
				<h3>Inscription à l'évènement</h3>

				<div class="form-group input-group">
					<span class="input-group-addon">@</span> <input type="email"
						class="form-control" name="email" id="emailId"
						placeholder="name@domain.com">
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon"> Nom </span> <input type="text"
						class="form-control" name="name" id="nomId">
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon"> Prénom </span> <input type="text"
						class="form-control" name="fname" id="prenomId">
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon"> Société </span> <input type="text"
						class="form-control" name="company" id="societeId">
				</div>
				<div class="form-group input-group">
					<input type="hidden" class="form-control" name="relatedEvent"
						id="relatedEventId" value="${requestScope.focusedEvent.id}">
				</div>
				
				<div class="row">
					<div class="col-xs-6 col-xs-offset-3">
						<input type="submit" id="enregistrer" name="button"
							class="frm_btns btn btn-success" value="Enregistrer" />
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>