package persistencia;

import org.bson.BsonDocument;
import org.bson.BsonString;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import modelo.Usuario;

public class DAOUsuario {

	public static void insert(Usuario usuario) {
		BsonDocument bso=new BsonDocument();
		bso.append("nombre", new BsonString(usuario.getNombre()));
		bso.append("pwd", new BsonString("1234"));
		
		
		MongoBroker broker= MongoBroker.get();
		MongoCollection<BsonDocument>usuarios=broker.getCollection("Usuarios");
		usuarios.insertOne(bso);
		
	}
	
	public static void insertUserConPWD(Usuario usuario, String pwd) {
		BsonDocument bso=new BsonDocument();
		bso.append("nombre", new BsonString(usuario.getNombre()));
		bso.append("pwd", new BsonString(pwd));
		
		
		MongoBroker broker= MongoBroker.get();
		MongoCollection<BsonDocument>usuarios=broker.getCollection("Usuarios");
		usuarios.insertOne(bso);
		
	}
	
	public static void UpdateUserPWD(Usuario usuario, String pwd) throws Exception {
		BsonDocument bso1=new BsonDocument();
		bso1.append("nombre", new BsonString(usuario.getNombre()));
		
		MongoBroker broker1= MongoBroker.get();
		MongoCollection<BsonDocument>usuarios=broker1.getCollection("Usuarios");
		usuarios.deleteOne(bso1);
		
		BsonDocument bso2=new BsonDocument();
		bso2.append("nombre", new BsonString(usuario.getNombre()));
		bso2.append("pwd", new BsonString(pwd));
		
	
		usuarios.insertOne(bso2);
		
	}

	public static Usuario select(String nombre, String pwd) throws Exception {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios=broker.getCollection("Usuarios");
		BsonDocument criterio=new BsonDocument();
		criterio.append("nombre", new BsonString(nombre));
		criterio.append("pwd", new BsonString(pwd));
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario=resultado.first();
		if(usuario==null){
			throw new Exception("credenciales incorrectas");
		}
		Usuario result= new Usuario(nombre);
		return result;
	}
	
	public static Usuario selectSinPWD(String nombre) throws Exception {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<BsonDocument> usuarios=broker.getCollection("Usuarios");
		BsonDocument criterio=new BsonDocument();
		criterio.append("nombre", new BsonString(nombre));
		
		FindIterable<BsonDocument> resultado=usuarios.find(criterio);
		BsonDocument usuario=resultado.first();
		Usuario result= new Usuario(nombre);
		return result;
	}
	
	public static void delete(Usuario usuario) {
		BsonDocument bso=new BsonDocument();
		bso.append("nombre", new BsonString(usuario.getNombre()));
		
		MongoBroker broker= MongoBroker.get();
		MongoCollection<BsonDocument>usuarios=broker.getCollection("Usuarios");
		usuarios.deleteOne(bso);
		
	}
	

}
