<%@ include file="../jspf/prolog.jspf"%>

<html>

<%@ include file="../jspf/commonHeader.jspf"%>
<script type="text/javascript"
	src="${baseURL}/lib/js/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${baseURL}/lib/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${baseURL}/css/login.css">
<link rel="stylesheet" type="text/css" href="${baseURL}/css/blocs.css">
<title>Nouvel évènement</title>
</head>


<body>
	<%@ include file="../jspf/barreHautPanel.jspf"%>
	<div class="container">
		<div class="row">
			<c:set var="listeEvents" value="${requestScope.myEventsList }"></c:set>
			<c:set var="eventLink" value="${baseURL}/panel/info?event="></c:set>
			<c:set var="title" value="Mes évènements"></c:set>
			<%@ include file="../jspf/leftBloc.jspf"%>
			<div class="col-xs-8" id="mainBloc">
				<center>
					<h1>Tableau de bord</h1>
				</center>
				<hr />
				<%@ include file="../jspf/toast.jspf"%>
				<form id="creerEvent_frm" action="${baseURL}/panel/new"
					method="post" class="jumbotron" role="form">
					<div class="container">
						<h3>Création d'un évènement</h3>


						<div class="form-group input-group">
							<span class="input-group-addon">Nom</span> <input type="text"
								class="form-control" name="nomEvent" id="nomEventId"
								placeholder="mon évènement">
						</div>
						<div class="form-group input-group">
							<span class="input-group-addon">Adresse</span> <input type="text"
								class="form-control" name="adresseEvent" id="adresseEventId">
						</div>
						<div class="form-group input-group">
							<span class="input-group-addon">Date début</span> <input
								type="date" name="dateDebut" id="dateDebutId">
						</div>
						<div class="form-group input-group">
							<span class="input-group-addon">Heure début</span> <input
								type="time" name="heureDebut" id="heureDebutId">
						</div>
						<div class="form-group input-group">
							<span class="input-group-addon">Date fin</span> <input
								type="date" name="dateFin" id="dateFinId">
						</div>
						<div class="form-group input-group">
							<span class="input-group-addon">Heure fin</span> <input
								type="time" name="heureFin" id="heureFinId">
						</div>

						<div class="row formRow">
							<div class="col-xs-12">
								<input type="submit" id="creerPublier" name="button"
									class="frm_btns btn btn-primary" value="Créer et publier" />
							</div>
						</div>
						<div class="row">
							<div class="col-xs-6 col-xs-offset-3">
								<input type="submit" id="creer" name="button"
									class="frm_btns btn btn-success" value="Créer" />
							</div>
						</div>
					</div>
				</form>
			</div>

		</div>
	</div>


</body>