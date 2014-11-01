<%@ include file="../jspf/prolog.jspf"%>

<html>

<%@ include file="../jspf/commonHeader.jspf"%>
<link rel="stylesheet" type="text/css" href="${baseURL}/css/login.css">
<link rel="stylesheet" type="text/css" href="${baseURL}/css/event.css">
<link rel="stylesheet" type="text/css" href="${baseURL}/css/blocs.css">
<title>Page de l'evenement</title>
</head>


<body>

	<%@ include file="../jspf/barreHaut.jspf"%>
	<div class="container">
		<div class="row">
			<c:set var="listeEvents" value="${requestScope.eventsList }"></c:set>
			<c:set var="eventLink" value="${baseURL}/main/info?event="></c:set>
			<c:set var="title" value="Evènements publiés"></c:set>
			<%@ include file="../jspf/leftBloc.jspf"%>

			<div class="col-xs-9" id="mainBloc">

				<div class="eventContainer jumbotron">
					<h2>${requestScope.focusedEvent.name}</h2>
					<hr />
					<p>
						<span class="glyphicon glyphicon-home"></span>
						${requestScope.focusedEvent.adresse}
					</p>
					<hr />
					<fmt:formatDate value="${requestScope.focusedEvent.dateDebut}"
						var="dateDebut" type="date" pattern="E MM/dd/yyyy" />
					<fmt:formatDate value="${requestScope.focusedEvent.heureDebut}"
						var="heureDebut" type="date" pattern="HH:mm" />
					<fmt:formatDate value="${requestScope.focusedEvent.dateFin}"
						var="dateFin" type="date" pattern="E MM/dd/yyyy" />
					<fmt:formatDate value="${requestScope.focusedEvent.heureFin}"
						var="heureFin" type="date" pattern="HH:mm" />
					<p id="schedule">
						<span class="glyphicon glyphicon-calendar"></span> ${dateDebut} -
						${heureDebut} <br /> ${dateFin} - ${heureFin}
					</p>
				</div>
				<%@ include file="../jspf/toast.jspf"%>

				<form id="inscriptionEvent_frm"
					action="${baseURL}/main/info?event=${requestScope.focusedEvent.id}"
					method="post" class="jumbotron subscribeForm" role="form">
					<div class="container">
						<div class="row">
							<center>
								<h3>Inscription</h3>
							</center>
						</div>
						<div class="row subscribeFormRow">
							<div class="form-group input-group">
								<span class="input-group-addon">@</span> <input type="email"
									class="form-control" name="email" id="emailId"
									placeholder="name@domain.com">
							</div>
						</div>
						<div class="row subscribeFormRow">
							<div class="col-xs-6 subscribeFormCol">
								<div class="form-group input-group">
									<span class="input-group-addon"> Nom </span> <input type="text"
										class="form-control" name="name" id="nomId">
								</div>
							</div>
							<div class="col-xs-6 subscribeFormCol">
								<div class="form-group input-group">
									<span class="input-group-addon"> Prénom </span> <input
										type="text" class="form-control" name="fname" id="prenomId">
								</div>
							</div>
						</div>
						<div class="row subscribeFormRow">
							<div class="form-group input-group">
								<span class="input-group-addon"> Société </span> <input
									type="text" class="form-control" name="company" id="societeId">
							</div>
							<div class="form-group input-group">
								<input type="hidden" class="form-control" name="relatedEvent"
									id="relatedEventId" value="${requestScope.focusedEvent.id}">
							</div>
						</div>

						<div class="row subscribeFormRow" id="formSubscribeBtn">
							<div class="col-xs-8 col-xs-offset-2">
								<input type="submit" id="enregistrer" name="button"
									class="frm_btns btn btn-success" value="Enregistrer" />
							</div>
						</div>
					</div>

				</form>
			</div>

		</div>
	</div>



</body>
</html>