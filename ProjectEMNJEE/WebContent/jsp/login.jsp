<%@ include file="../WEB-INF/jspf/prolog.jspf"%>

<html>
<head>

<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/login.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire de login</title>

</head>
<body>
	<form id="login_frm" action="/ProjectEMNJEE/login" method="post">
		<div id="login_box">
			<div id="login_header">Login</div>
			<div id="form_val">
				<div>User Id :</div>
				<div>
					<input type="text" name="username" id="login_id" /><span> hint : admin</span>
				</div>
				
				<div>Password:</div>
				<div>
					<input type="password" name="passwd" id="login_pwd" /><span> hint : test</span>
				</div>
				<div style="clear: both; height: 0px;"></div>

				<div id="msgbox"> ${sessionScope.toast} </div>
			</div>
			<div id="login_footer">
				<label> <input type="submit" id="login" 	name="button"   value="Login" /></label>
				<label> <input type="submit" id="subscribe" name="button"	value="Subscribe" /></label>
			</div>
		</div>
	</form>

</body>
</html>