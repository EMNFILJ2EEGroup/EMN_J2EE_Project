<%@ include file="../jspf/prolog.jspf"%>

<html>

<%@ include file="../jspf/commonHeader.jspf"%>
<link rel="stylesheet" type="text/css" href="${baseURL}/css/login.css">
<link rel="stylesheet" type="text/css" href="${baseURL}/css/blocs.css">
<title>Tableau de bord</title>
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
				<center><h1>Tableau de bord</h1></center>
				<hr />
				<%@ include file="../jspf/toast.jspf"%>
			</div>

		</div>
	</div>

</body>