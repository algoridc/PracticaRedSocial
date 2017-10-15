<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Mens Ubiqua - Red Social</title>
<script src = " https://code.jquery.com/jquery.js "></script> 
 <script src = " https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"> </script> 
 <script type="text/javascript" src="resources/js/wall.js"></script>
 <link rel = "stylesheet" href = " https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> 
</head>
<body>
 <div class="row"  style="background-color:lightblue"> 
   <div class="col-md-12" align="middle"><img src="img/uclm.png" class="imgthumbnail"> </div>  
   <div class="col-md-12" align="middle"><h4>Mens Ubiqua - Red Social</h4> </div>  
 </div>
<div class="row"  style="background-color:lightgray"> 
   <div class="col-md-3 col-md-offset-9">
   
   <!--Menu del usuario  --------------------------------------------------------------------------------------->
   
   <div class="collapse navbar-collapse navbar-ex1-collapse"> 
 <ul class="nav navbar-nav"> 
    <li><a href="#"><span class="glyphicon glyphiconuser"></span> Inicio</a></li> 
    <li><a href="#"><span class="glyphicon glyphiconuser"></span> Mensajes</a></li> 
 <li class="dropdown"> 
   <a href="#" class="dropdown-toggle" data-toggle="dropdown"> Menú <b class="caret"></b></a> 
 <ul class="dropdown-menu"> 
  <li><a href="#" id=menuNombre>Usuario</a></li> 
  <li class="divider"></li> 
  <li><a href="#">Configuración</a></li>
  <li><a href="#">Ayuda</a></li>
  <li><a href="#">Acerca de...</a></li>   
  <li class="divider"></li> 
  <li><a href="#" id="logOut">Salir</a></li> 
 </ul> 
 </li> 

</ul> 
 </div> 
   </div>  
 </div> 
 
 <div class="row" name="mesaggesPanel"> 
   <div class="col-md-3" style="background-color:lightblue" id="infoUser" align="middle">información de usuario</div> 
   <div class="col-md-6" align="middle">mensajes de los contactos</div> 
   <div class="col-md-3" style="background-color:lightgray" align="middle">otras opciones</div> 
 </div> 
 

</body>
</html>