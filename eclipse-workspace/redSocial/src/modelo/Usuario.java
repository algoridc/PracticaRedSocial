package modelo;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import persistencia.DAOUsuario;
import persistencia.MongoBroker;

public class Usuario {
	
	private String nombre;
	private String pwd;
	private String direccion;
	
	
	public Usuario() {
		
	}

	public Usuario(String nombre, String direccion, String pwd) {
		setNombre(nombre);
		setDireccion(direccion);
		setPwd(pwd);
	}

	public Usuario(String nombre) {
		setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	//Metodos DAO//////////////////////////////////////////////////////////
	public Usuario select() {
		return DAOUsuario.select(this);
	}
	public Usuario insert() {
		return DAOUsuario.insert(this);
		
	}
	public boolean update() {
		return DAOUsuario.update(this);
	}
	public boolean delete() {
		return DAOUsuario.delete(this);
		
	}
	
	

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", pwd=" + pwd + ", direccion=" + direccion + "]";
	}


	
	
	
	
}
