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
	<c:if test="${ ! empty sessionScope.toast }">
		<div id="msgbox" class="alert alert-danger" role="alert">${sessionScope.toast}</div>
	</c:if>
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

	<form id="creerEvent_frm" action="${baseURL}/panel/new" method="post"
		class="jumbotron" role="form">
		<div class="container">
			<h3>Création d'un évènement</h3>


			<div class="form-group input-group">
				<span class="input-group-addon">Nom</span> <input type="text"
					class="form-control" name="nomEvent" id="nomEventId"
					placeholder="nom">
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Adresse</span> <input type="text"
					class="form-control" name="adresseEvent" id="adresseEventId">
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Date début</span> <input type="date"
					name="dateDebut" id="dateDebutId">
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Heure début</span> <input
					type="time" name="heureDebut" id="heureDebutId">
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Date fin</span> <input type="date"
					name="dateFin" id="dateFinId">
			</div>
			<div class="form-group input-group">
				<span class="input-group-addon">Heure fin</span> <input
					type="time" name="heureFin" id="heureFinId">
			</div>

			<div class="row formRow">
				<div class="col-xs-12">
					<input type="submit" id="creerPublier" name="button"
						class="frm_btns btn btn-primary" value="both" />
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6 col-xs-offset-3">
					<input type="submit" id="creer" name="button"
						class="frm_btns btn btn-success" value="create" />
				</div>
			</div>
		</div>
	</form>
</body>