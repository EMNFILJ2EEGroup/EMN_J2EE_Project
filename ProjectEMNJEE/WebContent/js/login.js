/**
 * login.js
 */

$(document).ready( function() {
	$("#login_frm").submit(function() {

		// remove previous class and add new
		// "myinfo" class
		$("#msgbox").removeClass().addClass('myinfo').text('Validating Your Login ').fadeIn(1000);

		this.timer = setTimeout(function() {
			$.ajax({
				url : 'checkLogin.jsp',
				data : 'un='+ $('#login_id').val()+ '&pw='+ $('#password').val(),
				type : 'post',
				success : function(msg) {
					if (msg != 'ERROR') // Message Sent, check and redirect
					{ // and direct to the success page
						$("#msgbox").html('Login Verified, Logging in.....').addClass('myinfo').fadeTo(900,1,
								function() { //redirect to secure page
							document.location = 'logPage.jsp?user=' + msg;
						});

					} else {
						$("#msgbox").fadeTo(200,0.1,
								function() // start fading the messagebox
								{
							// add message and change the class of the box and start fading
							$(this).html('Sorry, Wrong Combination Of Username And Password.')
							.removeClass().addClass('myerror').fadeTo(900,1);});
					}
				}
			});
		}, 200);
		return false;
	});
});