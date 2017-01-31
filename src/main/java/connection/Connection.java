package main.java.connection;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;

public class Connection {
	

	// ------------------------------------------------------ getCollection from Mongo.
	public static DBCollection getConnection(String collection) {
		// TODO Auto-generated method stub
		try {
			
			// get connection from Mongo.
			MongoClientURI uri = new MongoClientURI("mongodb://archit:gajjar@ds135519.mlab.com:35519/ebanking");
			MongoClient client = new MongoClient(uri); // MongoClient connected with the
											// specified URI.
			@SuppressWarnings("deprecation")
			DB db = client.getDB(uri.getDatabase());
			DBCollection  getCollection= db.getCollection(collection);
			return getCollection;
			
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		DBCollection collection= Connection.getConnection("country");
		System.out.println(collection.getName());
	}
}
