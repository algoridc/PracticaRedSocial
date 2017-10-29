$( document ).ready(function() {
	$(function() {
		
		//CONTROL DE ACCESO//////////////////////////////////////////////////////

		var _name = sessionStorage.getItem("name");
		var _email = sessionStorage.getItem("email");
		var _status = sessionStorage.getItem("status"); 
		//alert("wall.js SESSION ln10--> name: "+_name+" email: "+_email+" status: "+_status);
		if(_status != "ok"){
			//ERROR DE ESTADO
			alert("No ha iniciado sesion");
			location.href ="index.html";
		}
		
		//DATOS DE USUARIO///////////////////////////////////////////////////////
		document.getElementById("menuNombre").innerHTML = _name;
		document.getElementById("infoUser").innerHTML = "Bienvenido "+_name+"!!";
		
		
	});
	$('#menuInicio').click(function(){
		//ACCESO AL PANEL DE INICIO
		$.post( "wall.do",{ value: "i" }, function( data ) {});
		
	});
	$('#menuMensajes').click(function(){
		//ACCESO AL PANEL DE MENSAJES
		$.post( "wall.do",{ value: "m" }, function( data ) {});
		
	});
	$('#menuNombre').click(function(){
		//ACCESO AL PANEL DE USUARIO
		$.post( "wall.do",{ value: "u" }, function( data ) {});
		
		
	});
	$('#menuConfiguracion').click(function(){
		//ACCESO AL PANEL DE CONFIGURACION
		$.post( "wall.do",{ value: "c" }, function( data ) {});
		
		
	});
	$('#logOut').click(function(){
		//borro la sesion
		sessionStorage.clear();
		//vuelvo al inicio
		location.href ="index.html";
		
	});
	
	
});
