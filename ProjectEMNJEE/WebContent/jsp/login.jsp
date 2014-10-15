<%@ include file="../WEB-INF/jspf/prolog.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire de login</title>

</head>
<body>
	<form id="login_frm" action="/ProjectEMNJEE/login" method="post"
		class="jumbotron" role="form">

		<h1>Login</h1>

		<div class="form-group input-group">
			<span class="input-group-addon">@</span> <input type="email"
				class="form-control" name="username" id="login_id"
				placeholder="Enter email [admin@admin]">
		</div>

		<div class="form-group input-group">
			<span class="input-group-addon">>></span> <input
				type="password" class="form-control" name="passwd" id="login_pwd"
				placeholder="Password [test]">
		</div>
		<c:if test="${ ! empty sessionScope.toast }" >
		<div id="msgbox" class="alert alert-danger" role="alert">${sessionScope.toast}</div>
		</c:if>
		<div class="loginBtns">
			<label> <input type="submit" id="login" name="button"
				class="btn btn-primary" value="Login" />
			</label> <label> <input type="submit" id="subscribe" name="button"
				class="btn btn-success" value="Subscribe" /></label>
		</div>
	</form>

</body>
</html>