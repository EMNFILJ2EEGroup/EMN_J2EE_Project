<%@ include file="../jspf/prolog.jspf"%>

<html>

<%@ include file="../jspf/commonHeader.jspf"%>
<script type="text/javascript" src="${baseURL}/lib/js/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${baseURL}/lib/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${baseURL}/css/login.css">
<title>Ev�nement</title>

</head>


<body>
<%@ include file="../jspf/barreHaut.jspf"%>
<div class="container">
	<h3>Nom de l'�v�nement 1</h3>


	<h5>Adresse de l'�v�nement 1</h5>

	<h5>Date et heure de d�but de l'�v�nement 1</h5>

	<h5>Date et heure de fin de l'�v�nement 1</h5>
	
		<div class="row">
			<div class="col-xs-6 col-xs-offset-3">
				<input type="submit" id="inscription" name="button"
					class="frm_btns btn btn-success" value="Inscription" />
			</div>
		</div>
	</div>

</body>
</html>