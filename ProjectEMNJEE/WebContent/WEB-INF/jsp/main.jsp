<%@ include file="../jspf/prolog.jspf"%>

<html>

<%@ include file="../jspf/commonHeader.jspf"%>
<script type="text/javascript"
	src="${baseURL}/lib/js/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${baseURL}/lib/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${baseURL}/css/login.css">
<link rel="stylesheet" type="text/css" href="${baseURL}/css/blocs.css">
<title>Page principale</title>
</head>


<body>
	<%@ include file="../jspf/barreHaut.jspf"%>
	<div class="container">
		<div class="row">
			<c:set var="listeEvents" value="${requestScope.eventsList }"></c:set>
			<c:set var="eventLink" value="${baseURL}/main/info?event="></c:set>
			<c:set var="title" value="Evènements publiés"></c:set>
			<%@ include file="../jspf/leftBloc.jspf"%>
			<div class="col-xs-8" id="mainBloc">
				<%@ include file="../jspf/toast.jspf"%>
			</div>

		</div>
	</div>
</body>
</html>