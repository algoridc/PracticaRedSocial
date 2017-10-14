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
	 
	 Usuario user = new Usuario(name, email, password);
	 Usuario usuario = DAOUsuario.insertUserConPWD(user, password);
	 
	 JSONObject json = new JSONObject();
	 if (usuario!=null) {
		 json.put("status", "ok");
		 json.put("name", usuario.getNombre());
		 json.put("email", usuario.getDireccion());
	 }else {
		 json.put("status", "ko");
		 json.put("message", "No se ha podido loguear");
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
	 Usuario result=DAOUsuario.select(email,pwd); 
	 
	 
	JSONObject json = new JSONObject();
	 if (result!=null) {
		 json.put("status", "ok");
		 json.put("name", result.getNombre());
		 json.put("email", result.getDireccion());
	 }else {
		 json.put("status", "ko");
		 json.put("message", "No se ha podido loguear");
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
}
