package modelo;

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

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", pwd=" + pwd + ", direccion=" + direccion + "]";
	}


	
	
	
	
}
