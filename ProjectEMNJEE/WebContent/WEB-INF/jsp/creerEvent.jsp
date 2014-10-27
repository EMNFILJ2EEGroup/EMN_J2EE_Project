<%@ include file="../jspf/prolog.jspf"%>

<html>

<%@ include file="../jspf/commonHeader.jspf"%>
<script type="text/javascript" src="${baseURL}/lib/js/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${baseURL}/lib/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${baseURL}/css/login.css">
<title>Créer un évènement</title>
</head>


<body>

<%@ include file="../jspf/barreHaut.jspf"%>
<div class="container">
	<h3>Description de l'évènement</h3>


	<h5>Nom de l'évènement</h5>
	<div class="form-group input-group">
		<input type="text" class="form-control" name="nomEvent" id="nomEventId">
	</div>

	<h5>Adresse</h5>
	<div class="form-group input-group">
		<input type="text" class="form-control" name="adresseEvent" id="adresseEventId">
	</div>

	<h5>Date et heure de début</h5>
	<div class="form-group input-group">
		<input type="text" class="form-control" name="dateHeureDebut" id="dateHeureDebutId">
	</div>

	<h5>Date et heure de fin</h5>
	<div class="form-group input-group">
		<input type="text" class="form-control" name="dateHeureFin" id="dateHeureFinId">
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

</body>
</html>