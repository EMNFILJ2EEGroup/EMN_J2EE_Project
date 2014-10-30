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
			<div class="col-xs-3" id="leftBloc">
				<center><h2>Evènements publiés</h2></center>
				<div class="list-group">
					<c:if test="${ ! empty requestScope.eventsList }">
						<c:forEach var="event" items="${requestScope['eventsList']}">
							<a href="${baseURL}/main/info?event=${event.id}"
								class="list-group-item"> ${event.name} </a>
						</c:forEach>
					</c:if>
					<c:if test="${ empty requestScope.eventsList }">
						<p>Auccun évènements publiés</p>
					</c:if>
				</div>
			</div>

			<div class="col-xs-9" id="mainBloc">

				
				<%@ include file="../jspf/toast.jspf"%>
			</div>

		</div>
	</div>
</body>
</html>