/**
 * login.js
 */

$(document).ready(function() {
	$("#login_frm").submit(function() {
		//remove previous class and add new "myinfo" class
		$("#msgbox").removeClass().addClass('myinfo').text('Validating Your Login ');
		this.timer = setTimeout(function() {
			$.ajax({
				url : '../WEB-INF/jspf/loginCheck.jspf',
				data : 'un='+ $( '#login_id').val() + '&pw=' + $( '#password').val(),
				type : 'post',
				success : function(msg) {
					console.log("loginSuccessCallback");
					
					if (msg != 'ERROR') // Message Sent, check and redirect
					{ // and direct to the success page
						console.log("different from Error");
						$("#msgbox").html('Login Verified, Logging in.....').fadeTo(900,1, function() {
							document.location = 'loginIn.jspf?user=' + msg;	//redirect to secure page
						});
					} else {
						console.log("different from Error");
						$("#msgbox").fadeTo(200,0.1, function() { //start fading the messagebox
							//add message and change the class of the box and start fading
							$(this).html('Sorry, Wrong Combination Of Username And Password.').fadeTo(900,1);});
					}
				}
			});
		}, 200);
		return false;
	});
});