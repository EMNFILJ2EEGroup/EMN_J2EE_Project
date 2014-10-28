<%@ include file="../jspf/prolog.jspf"%>

<html>

<%@ include file="../jspf/commonHeader.jspf"%>
<script type="text/javascript" src="${baseURL}/lib/js/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${baseURL}/lib/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${baseURL}/css/login.css">
<title>Cr�er un �v�nement</title>
</head>


<body>
<%@ include file="../jspf/barreHaut.jspf"%>
<form id="creerEvent_frm" action="${baseURL}/creerEvent" method="post"
		class="jumbotron" role="form">
		


<div class="container">
	<h3>Cr�ation de l'�v�nement</h3>


	<h5>Nom de l'�v�nement</h5>
	<div class="input-group form-group">
		<input type="text" class="form-control" name="nomEvent" id="nomEventId">
	</div>

	<h5>Adresse</h5>
	<div class="input-group form-group">
		<input type="text" class="form-control" name="adresseEvent" id="adresseEventId">
	</div>

	<h5>Date de d�but</h5>
	<div class="input-group form-group">
  		<input type="date" id="dateDebutId" name="dateDebut"><br>
	</div>

	<h5>Heure de d�but</h5>
	<div class="input-group form-group">
  		<input type="time" id="heureDebutId" name="heureDebut"><br>
	</div>

  	<h5>Date de fin</h5>
	<div class="input-group form-group">
  		<input type="date" id="dateFinId" name="dateFin"><br>
	</div>

<h5>Heure de fin</h5>
	<div class="input-group form-group">
  		<input type="time" id="heureFinId" name="heureFin"><br>
	</div>

		<div class="row formRow">
			<div class="col-xs-12">
				<input type="submit" id="creerPublier" name="button"
					class="frm_btns btn btn-primary" value="Cr�er et publier" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6 col-xs-offset-3">
				<input type="submit" id="creer" name="button"
					class="frm_btns btn btn-success" value="Cr�er" />
			</div>
		</div>
	</div>
</form>
</body>
</html>