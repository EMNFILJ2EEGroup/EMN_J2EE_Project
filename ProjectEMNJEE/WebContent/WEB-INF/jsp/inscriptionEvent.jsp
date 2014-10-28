<%@ include file="../jspf/prolog.jspf"%>

<html>

<%@ include file="../jspf/commonHeader.jspf"%>
<script type="text/javascript" src="${baseURL}/lib/js/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${baseURL}/lib/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${baseURL}/css/login.css">
<title>Inscription à un évènement</title>

</head>


<body>
<%@ include file="../jspf/barreHaut.jspf"%>
<div class="container">
	<h3>Inscription à l'évènement 1</h3>


	<h5>Email</h5>
	<div class="form-group input-group">
		<input type="text" class="form-control" name="email" id="emailId">
	</div>

	<h5>Nom</h5>
	<div class="form-group input-group">
		<input type="text" class="form-control" name="nom" id="nomId">
	</div>

	<h5>Prénom</h5>
	<div class="form-group input-group">
		<input type="text" class="form-control" name="prenom" id="prenomId">
	</div>

	<h5>Société</h5>
	<div class="form-group input-group">
		<input type="text" class="form-control" name="societe" id="societeId">
	</div>
		<div class="row">
			<div class="col-xs-6 col-xs-offset-3">
				<input type="submit" id="enregistrer" name="button"
					class="frm_btns btn btn-success" value="Enregistrer" />
			</div>
		</div>
	</div>

</body>
</html>