package persistencia;

import org.bson.BsonDocument;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoBroker {

	private static MongoBroker yo;
	private MongoClientURI mongoClient;
	private MongoClient mc;
	private MongoDatabase db;
	private MongoCollection usuarios;
	
	
	private MongoBroker(){
		 this.mongoClient = new MongoClientURI( "mongodb://disoft:disoft2017@ds135790.mlab.com:35790/usuarios"  );
         mc= new MongoClient(this.mongoClient);
         db=mc.getDatabase(this.mongoClient.getDatabase());
         usuarios=db.getCollection("Usuarios");
         
	}
	
	public static MongoBroker get(){
		if(yo==null){
			yo=new MongoBroker();
		}
		return yo;
	}
	
	public MongoCollection<BsonDocument> getCollection(String collection){
		MongoCollection<BsonDocument> result=db.getCollection(collection, BsonDocument.class);
		if(result==null){
			db.createCollection(collection);
			result = db.getCollection(collection,BsonDocument.class);
		}
		return result;
	}

}
