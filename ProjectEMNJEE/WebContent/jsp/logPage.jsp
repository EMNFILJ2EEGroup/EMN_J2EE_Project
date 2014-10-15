
<%@ include file="../WEB-INF/jspf/prolog.jspf"%>
<html>
<head>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="../js/login.js"></script>

<!--<link href="css/style.css" rel="stylesheet" type="text/css" />-->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
</head>
<body>
	<form name="login_frm" id="login_frm" action="" method="post">
		<div id="login_box">
			<div id="login_header">Login</div>
			<div id="form_val">
				<div>User Id :</div>
				<div>
					<input type="text" name="login_id" id="login_id" /><span
						style="font-size: 10px;">hint : admin</span>
				</div>

				<div>Password:</div>
				<div>
					<input type="password" name="password" id="password" /><span
						style="font-size: 10px;">hint : test</span>
				</div>
				<div style="clear: both; height: 0px;"></div>
				<div id="msgbox"></div>
			</div>
			<div id="login_footer">
				<label> <input type="submit" name="login" id="login"
					value="Login" />
				</label>
			</div>
		</div>
	</form>

</body>
</html>