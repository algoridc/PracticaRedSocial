package controlador;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.bson.BsonDocument;

import org.bson.BsonString;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import modelo.Usuario;
import persistencia.DAOUsuario;

public class Cargadora {

	@SuppressWarnings("resource")
	public static void main(String[]args) throws IOException{
		 MongoClientURI mongoClient = new MongoClientURI( "mongodb://disoft:disoft2017@ds135790.mlab.com:35790/usuarios"  );
         MongoClient mc= new MongoClient(mongoClient);
         MongoDatabase db=mc.getDatabase(mongoClient.getDatabase());
		
		if(db.getCollection("Usuarios")==null){
			db.createCollection("Usuarios");
		}
		MongoCollection<BsonDocument>usuarios=db.getCollection("Usuarios", BsonDocument.class);
		BsonDocument lola=new BsonDocument();
		lola.append("nombre", new BsonString("lola"));
		lola.append("pwd", new BsonString("1234"));
		usuarios.insertOne(lola);
		
		FileInputStream fis=new FileInputStream("/Users/Usuario/Desktop/nombres.txt");
		BufferedReader br=new BufferedReader(new InputStreamReader(fis));
		String nombre;
		while((nombre=(br.readLine()))!=null){
			Usuario usuario=new Usuario(nombre);
			DAOUsuario.insert(usuario);
		}
		fis.close();
	}
}
