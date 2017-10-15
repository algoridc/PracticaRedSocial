$( document ).ready(function() {
	$(function() {

	    $('#login-form-link').click(function(e) {
	    	$("#login-form").delay(100).fadeIn(100);
	 		$("#register-form").fadeOut(100);
			$('#register-form-link').removeClass('active');
			$(this).addClass('active');
			e.preventDefault();
		});
		$('#register-form-link').click(function(e) {
			$("#register-form").delay(100).fadeIn(100);
	 		$("#login-form").fadeOut(100);
			$('#login-form-link').removeClass('active');
			$(this).addClass('active');
			e.preventDefault();
		});

	});
	
	$('#register-submit').attr('disabled','disabled');
	
	$( "#confirm-password" ).keyup(function() {
		 
		var password1 = $("#password-register").val();
		var password2 = $("#confirm-password").val();

	    if(password1 === password2 && password2!='') {
	    	$("#status").css("color","green");
	    	$("#password-register").css('border-color', 'green');
	    	$("#confirm-password").css('border-color', 'green');
	    	$("#status").text("Contraseña correcta");
	    	$('#register-submit').removeAttr('disabled');
	    }
	    else {
	    	$("#status").css("color","red");
	    	$('#register-submit').attr('disabled','disabled');
	    	$("#password-register").css('border-color', 'red');
	    	$("#confirm-password").css('border-color', 'red');
	    	$("#status").text("No coinciden las contraseñas");  
	    };
	});
	
	$( "#password-register" ).keyup(function() {
		 
		var password1 = $("#password-register").val();
		var password2 = $("#confirm-password").val();
		
	    if(password1 === password2 && password2!='') {
	    	$("#status").css("color","green");
	    	$("#password-register").css('border-color', 'green');
	    	$("#confirm-password").css('border-color', 'green');
	    	$("#status").text("Contraseña correcta");
	    	$('#register-submit').removeAttr('disabled');
	    }
	    else {
	    	$("#status").css("color","red");
	    	$('#register-submit').attr('disabled','disabled');
	    	$("#password-register").css('border-color', 'red');
	    	$("#confirm-password").css('border-color', 'red');
	    	$("#status").text("No coinciden las contraseñas");  
	    };
	});
	
	$('#register-submit').click(function(){
		
		var email = $('#email-register').val();
		var username = $('#username').val();
		var password = $('#password-register').val();
		var password2 = $('#confirm-password').val();
		$.post( "consultar.do",{ email: email }, function( data ) {
			 if(data=='ok'){
				 $.post( "registro.do",
						 { email: email, password: password , username:username, 'confirm-password': password2}, 
						 
					function( data ) {
						var json = JSON.parse(data);
						if(json.status=='ok'){
							//GUARDAR EL NOMBRE Y EMAIL EN LOCALSTORAGE
							//localStorage.setItem("name", json.name);
							//localStorage.setItem("email", json.mail);
							//localStorage.setItem("status", json.status);
							sessionStorage.setItem("name", json.name);
							sessionStorage.setItem("email", json.mail);
							sessionStorage.setItem("status", json.status);
							// REDIRECCIÓN AL MURO.
							location.href ="wall.jsp";
							
						}else{
							alert(json.message);
						}
					});
			 }else{
				 $("#email-register").css('border-color', 'red');
				 $("#error-email").css("color","red");
				 $("#error-email").text("Correo electrónico en uso");
			 }
		});
	})
	
	$('#login-submit').click(function(){
		var email = $('#email').val();
		var pwd = $('#password').val();
		$.post( "login.do",{ email: email, password: pwd}, function( data ) {
			var json = JSON.parse(data);
			if(json.status=='ok'){
				//GUARDAR EL NOMBRE Y EMAIL EN LOCALSTORAGE
				//localStorage.setItem("name", json.name);
				//localStorage.setItem("email", json.email);
				//localStorage.setItem("status", json.status);
				sessionStorage.setItem("name", json.name);
				sessionStorage.setItem("email", json.email);
				sessionStorage.setItem("status", json.status);
				
				// REDIRECCIÓN AL MURO.
				location.href ="wall.jsp";
			}else{
				alert(json.message);
			}
		});
	})
	
});
