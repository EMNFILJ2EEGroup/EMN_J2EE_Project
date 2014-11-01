<%@ include file="../jspf/prolog.jspf"%>

<html>

<%@ include file="../jspf/commonHeader.jspf"%>
<link rel="stylesheet" type="text/css" href="${baseURL}/css/login.css">
<title>Connexion</title>
</head>


<body>

	<%@ include file="../jspf/barreHaut.jspf"%>

	
	<form id="login_frm" action="${baseURL}/login" method="post"
		class="jumbotron" role="form">

		<h1>Login</h1>

		<div class="form-group input-group">
			<span class="input-group-addon">@</span> <input type="email"
				class="form-control" name="username" id="login_id"
				placeholder="user@emn.fr">
		</div>

		<div class="form-group input-group">
			<span class="input-group-addon">>></span> <input type="password"
				class="form-control" name="passwd" id="login_pwd" placeholder="test">
		</div>
		<%@ include file="../jspf/toast.jspf"%>
		<div class="container">
			<div class="row formRow">
				<div class="col-xs-12">
					<input type="submit" id="login" name="button"
						class="frm_btns btn btn-primary" value="Login" />
				</div>
			</div>
			<div class="row">
				<div class="col-xs-6 col-xs-offset-3">
					<input type="submit" id="subscribe" name="button"
						class="frm_btns btn btn-success" value="Subscribe" />
				</div>
			</div>
		</div>
	</form>

</body>
</html>