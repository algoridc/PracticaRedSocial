package persistencia;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoBroker {

	private static MongoBroker yo;
	private MongoClientURI uri;
	private MongoClient mongoClient;
	private MongoDatabase db;
	
	private MongoBroker(){
		this.uri = new MongoClientURI("mongodb://disoft:disoft2017@ds135790.mlab.com:35790/usuarios");
		this.mongoClient= new MongoClient(uri);
        this.db= mongoClient.getDatabase("usuarios");
	}
	
	public static MongoBroker get(){
		if(yo==null){
			yo=new MongoBroker();
		}
		return yo;
	}
	
	public MongoCollection<Document> getCollection(String collection){
		MongoCollection<Document> result=db.getCollection(collection, Document.class);
		if(result==null){
			db.createCollection(collection);
			result = db.getCollection(collection,Document.class);
		}
		return result;
	}

}
