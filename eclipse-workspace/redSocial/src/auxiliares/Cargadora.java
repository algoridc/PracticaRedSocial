package auxiliares;

import java.io.IOException;

public class Cargadora {

	@SuppressWarnings("resource")
	public static void main(String[]args) throws IOException{
		
	/*	 MongoClientURI uri  = new MongoClientURI("mongodb://disoft:disoft2017@ds135790.mlab.com:35790/usuarios"); 
	     MongoClient client = new MongoClient(uri);
	     MongoDatabase db = (MongoDatabase) client.getDB("usuarios");
	     
		
		if(db.getCollection("Usuarios")==null){
			db.createCollection("Usuarios");
		} 
	     
		MongoCollection<BsonDocument>usuarios=db.getCollection("Usuarios", BsonDocument.class);
		BsonDocument lola=new BsonDocument();
		lola.append("nombre", new BsonString("lola"));
		lola.append("pwd", new BsonString("1234"));
		usuarios.insertOne(lola);
		*/
		/*FileInputStream fis=new FileInputStream("/Users/Usuario/Desktop/nombres.txt");
		BufferedReader br=new BufferedReader(new InputStreamReader(fis));
		String nombre;
		while((nombre=(br.readLine()))!=null){
			Usuario usuario=new Usuario(nombre);
			DAOUsuario.insert(usuario);
		}
		fis.close();*/
	}
}
