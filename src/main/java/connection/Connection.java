package main.java.connection;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;

public class Connection {
	// ------------------------------------------------------ get MongoLab Client object.
	public static MongoClient getConnection() {
		try {

			MongoClientURI uri = new MongoClientURI("mongodb://<username>:<lastname>@ds<port>.mlab.com:<port>/<dbname>");
			MongoClient client = new MongoClient(uri); // MongoClient connected with the specified URI.
			return client;

		} catch (MongoException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static MongoClient getlocalConnection() {
		try {
			MongoClient client = new MongoClient("localhost",27017); // MongoClient connected with the specified URI.
			return client;
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//-------------------------------------------------------- close connection
	public static void close(MongoClient client){
		client.close();		// close connection.
	}
	
	
}
