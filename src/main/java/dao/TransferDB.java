package main.java.dao;


import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import main.java.convertor.TransferConvertor;
import main.java.vo.AccountBean;

public class TransferDB {
	DBCollection collection;
	String id = null,user=null;
	
	public TransferDB(MongoClient client){
		// look for the collection : transfer, if it doesn't exist, it will create it!
		this.collection = client.getDB("ebanking").getCollection("transfer"); 
	}
	
	public void updateDocument(AccountBean bean) {
		DBObject doc = new TransferConvertor().readDocument(bean);
		DBCursor cursor = this.collection.find(doc);
		
		System.out.println("Cursor size of the Read Transfer document : "+cursor.size());
		if(cursor.size() == 0){
			// insert document.
			System.out.println("first time executing query in Transfer document");
			DBObject document = new TransferConvertor().createDocument(bean);
			this.collection.insert(document);
			System.out.println("Inserted document first time successfully!");
		}else{
			System.out.println("You are in else bloack.");
			DBObject document = new TransferConvertor().updateDocument(doc, bean);
			this.collection.update(new BasicDBObject()
					.append("p_id", new ObjectId(bean.getTransferFrom())), document);			
		}
	}
}
