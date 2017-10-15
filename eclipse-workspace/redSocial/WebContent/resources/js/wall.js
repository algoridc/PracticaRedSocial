$( document ).ready(function() {
	$(function() {

		var _name = sessionStorage.getItem("name");
		var _email = sessionStorage.getItem("email");
		var _status = sessionStorage.getItem("status"); 
		//alert("wall.js SESSION ln10--> name: "+_name+" email: "+_email+" status: "+_status);
		if(_status != "ok"){
			//ERROR DE ESTADO
			alert("No ha iniciado sesion");
			location.href ="index.html";
		}
		//$('#menuNombre').val();
		document.getElementById("menuNombre").innerHTML = _name;
		document.getElementById("infoUser").innerHTML = "Bienvenido "+_name+"!!";
		
	});
	$('#logOut').click(function(){
		//borro la sesion
		sessionStorage.clear();
		//vuelvo al inicio
		location.href ="index.html";
		
	});
	
	
});
