package controlador;

import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import auxiliares.SendMail;
import auxiliares.Utilidades;
import modelo.Usuario;
import modelo.WallSupport;
import persistencia.DAOUsuario;

//Es un servlet controlado por el Spring MVC, sustituto por ejemplo de Struts
@Controller
public class UsuarioServlet {//eo

 // Aquí está la magia !! Con poner @autowired, spring inyecta automáticamente aquí nuestro bean con el servicio !!
 // No hay que preocuparse de inicializarlo ni gestionarlo ni nada parecido !!
 @Autowired
 private DAOUsuario servicioDAOUsuario;
 
 @RequestMapping("registro.do")
 public void registro(HttpServletRequest request,HttpServletResponse response) throws IOException {
	
	 String name = request.getParameter("username");
	 String email = request.getParameter("email");
	 String password = request.getParameter("password");
	 JSONObject json = new JSONObject();
	 
	 
	 Usuario usuario = DAOUsuario.insertUserConPWD(new Usuario(name, email, password), password);
	 
	 if (usuario!=null) {
		 WallSupport.getWallSupport().setUser(usuario);
		 json.put("status", "ok");
		 json.put("name", usuario.getNombre());
		 json.put("email", usuario.getDireccion());
	 }else {
		 json.put("status", "ko");
		 json.put("message", "No se ha podido loguear");
	 }
	
	response.getWriter().print(json);
	 
 }
 
 @RequestMapping("edit.do")
 public void edit(HttpServletRequest request,HttpServletResponse response) throws IOException {
	
	 JSONObject json = new JSONObject();
	 WallSupport soporte = WallSupport.getWallSupport();
	 String name = request.getParameter("username");
	 String email = request.getParameter("email");
	 String oldpassword = request.getParameter("oldpassword");
	 String password = request.getParameter("password");
	 
	// control de errores
	 boolean flag = true;
	 String msj =" Modificación correcta";
	 
	 //encripto la contraseña antigua
	 String pwdencriptada=Utilidades.Encriptar(oldpassword);
	 
	 //saco el usuario del soporte
	 Usuario user = soporte.getUser();
	 
	 //control de erroes de coincidencia entre passwords
	 if(!user.getPwd().equals(pwdencriptada)) {
		 flag = false;
		 msj ="Error en la contraseña antigua";
	 }
	 
	 
	//si la antigua contraseña encriptada coincide con la que habia...
	 if(flag) {
		 //actualizo la informacion
		 user.setNombre(name);
		 user.setDireccion(email);
		 user.setPwd( Utilidades.Encriptar(password) );
		 //acualizo el usuario del soporte
		 soporte.setUser(user);
		 
		//actualizo la bbdd
		 if(!soporte.updateUser()) {
			 flag = false;
			 msj ="Error al actualizar la base de datos";
		 }
	 }
		 
	 //si no ha habido errores
	 if (flag) {
			 json.put("status", "ok");
			 json.put("name", soporte.getUser().getNombre());
			 json.put("email", soporte.getUser().getDireccion());
		 
	 }else {
		 json.put("status", "ko");
		 json.put("message", msj);
	 }
	
	response.getWriter().print(json);
	 
 }
 
 @RequestMapping("consultar.do")
 public void consultar(HttpServletRequest request,HttpServletResponse response) throws IOException {
	
	 String email = request.getParameter("email");
	 String result ="";
	 result=DAOUsuario.selectEmail(email);
	 response.getWriter().write(result); 

 }
 
 @RequestMapping("login.do")
 public void login(HttpServletRequest request,HttpServletResponse response) throws Exception {
	
	 String email = request.getParameter("email");
	 String pwd = request.getParameter("password");
	 JSONObject json = new JSONObject();
	 
	 //busco al usuario en la bbdd
	 Usuario result=DAOUsuario.select(email,pwd); 
	 
	 
	 if (result!=null) {
		 WallSupport.getWallSupport().setUser(result);
		 json.put("status", "ok");
		 json.put("name", result.getNombre());
		 json.put("email", result.getDireccion());

	 }else {
		 json.put("status", "ko");
		 json.put("message", "No se ha podido loguear");
	 }
	
	response.getWriter().print(json);
 }
 
 @RequestMapping("delete.do")
 public void delete(HttpServletRequest request,HttpServletResponse response) throws Exception {
	
	 String email = request.getParameter("email");
	 String pwd = request.getParameter("password");
	 JSONObject json = new JSONObject();

	 //busco al usuario en la bbdd
	 Usuario result=DAOUsuario.select(email,pwd); 
	 
	 json.put("status", "ko");
	 json.put("message", "Usuario o contraseña erroneas");
	 
	 if (result!=null) {
		 if (WallSupport.getWallSupport().deleteUser()) {
			 json.put("status", "ok");
			 json.put("message", "Eliminado correctamente");
			 }
		 else
			 json.put("message", "No se ha podido eliminar de la base de datos");
		 
	 }
	
	response.getWriter().print(json);
 }
 
 @RequestMapping("forgotpassword.do")
 public void forgotpassword(HttpServletRequest request,HttpServletResponse response) throws JSONException, Exception {
	
	 String email = request.getParameter("email");
	 String result ="";
	 result=DAOUsuario.selectEmail(email);
	 
	 if (result=="ko") {
		
			Usuario user = DAOUsuario.selectSinPWD(email);
			SendMail send = new SendMail();
			send.sendMail(user.getDireccion(), Utilidades.Desencriptar(user.getPwd()));
			response.sendRedirect("index.html");
	 }

 }
 
 @RequestMapping("wall.do")
 public void wall(HttpServletRequest request,HttpServletResponse response) throws JSONException, Exception {
	 
	 final WallSupport soporte = WallSupport.getWallSupport();
	 if(!soporte.isUser()) {
		 //CARGAR EL  USUARIO
		 
	 }
	 /**
	 char value = request.getParameter("value").charAt(0);
	 switch(value){
	 case 'i':
		 soporte.getUser();
		 soporte.getPublicMesseges();
		 break;
	 case 'm':
		 soporte.getPrivateMessages();
		 break;
	 case 'u':
		 soporte.getUser();
		 break;
	 case 'c':
		 soporte.getUser();
		 break;
		 
	 }
	 */
	 

 }
}
