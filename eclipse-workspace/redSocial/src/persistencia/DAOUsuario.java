package persistencia;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Component;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.MongoClientURI;

import auxiliares.Utilidades;
import modelo.Usuario;

@Component
public class DAOUsuario {

    private List lista = new LinkedList();
    
	public static void insert(Usuario usuario) {
	/**	BsonDocument bso=new BsonDocument();
		bso.append("nombre", new BsonString(usuario.getNombre()));
		String pwd1="1234";
		String pwdencriptada=Utilidades.Encriptar(pwd1);
		bso.append("pwd", new BsonString(pwdencriptada));
		
		
		MongoBroker broker= MongoBroker.get();
		MongoCollection<BsonDocument>usuarios=broker.getCollection("Usuarios");
		usuarios.insertOne(bso);
		*/
	}
	
	public static Usuario insertUserConPWD(Usuario usuario, String pwd) {
		
		
		Document bso=new Document();
		
		bso.put("nombre", usuario.getNombre());
		String pwdencriptada=Utilidades.Encriptar(pwd);
		bso.append("pwd", pwdencriptada);
		bso.append("email", usuario.getDireccion());
		
		MongoBroker broker= MongoBroker.get();
		MongoCollection<Document>usuarios=broker.getCollection("Usuarios");
		usuarios.insertOne(bso);
		
		Usuario user=null;
		try {
			user = DAOUsuario.select(usuario.getDireccion(),pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return user;
		
	}
	
	public static void UpdateUserPWD(Usuario usuario, String pwd) throws Exception {
		/*BsonDocument bso1=new BsonDocument();
		bso1.append("nombre", new BsonString(usuario.getNombre()));
		
		MongoBroker broker1= MongoBroker.get();
		MongoCollection<BsonDocument>usuarios=broker1.getCollection("Usuarios");
		usuarios.deleteOne(bso1);
		
		BsonDocument bso2=new BsonDocument();
		bso2.append("nombre", new BsonString(usuario.getNombre()));
		String pwdencriptada=Utilidades.Encriptar(pwd);
		bso2.append("pwd", new BsonString(pwdencriptada));
		
	
		usuarios.insertOne(bso2);
		*/
	}

	public static Usuario select(String email,String pwd) throws Exception {
		Usuario result = null;
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> usuarios=broker.getCollection("Usuarios");
		Document criterio=new Document();
		criterio.append("email", email);
		
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document usuario=resultado.first();
		
		if (usuario!=null) {
			String desEncriptada = Utilidades.Desencriptar(usuario.getString("pwd"));
			if (pwd.equals(desEncriptada)) {
				result = new Usuario (usuario.getString("nombre"), usuario.getString("email"), usuario.getString("pwd"));
			}
		}
		
		return result;
	}
	
	public static Usuario selectSinPWD(String email) throws Exception {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> usuarios=broker.getCollection("Usuarios");
		Document criterio=new Document();
		criterio.append("email", email);
		
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document usuario=resultado.first();
		Usuario result=null;
		if (usuario!=null) {
			result = new Usuario (usuario.getString("nombre"), usuario.getString("email"), usuario.getString("pwd"));
		}
		
		return result;
		
	}
	
	public static String selectEmail(String email) {
		String result = "";
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> usuarios=broker.getCollection("Usuarios");
		Document criterio=new Document();
		criterio.append("email", email);
		
		FindIterable<Document> resultado=usuarios.find(criterio);
		Document usuario=resultado.first();
		
		if (usuario==null) {
			result="ok";
		}else {
			result="ko";
		}
		
		return result;
	}
	
	public static void delete(Usuario usuario) {
		Document bso=new Document();
		bso.append("nombre", usuario.getNombre());
		
		MongoBroker broker= MongoBroker.get();
		MongoCollection<Document>usuarios=broker.getCollection("Usuarios");
		usuarios.deleteOne(bso);
		
	}
	

}
