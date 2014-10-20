<%@ include file="../jspf/prolog.jspf"%>

<html>
<head>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Créer un évènement</title>


<%@ include file="../jspf/barreHaut.jspf"%>

</head>
<body>

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