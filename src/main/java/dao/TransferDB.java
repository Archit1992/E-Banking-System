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
	DBCollection register;
	String id = null,user=null;
	
	public TransferDB(MongoClient client){
		// look for the collection : transfer, if it doesn't exist, it will create it!
		this.collection = client.getDB("ebanking").getCollection("transfer"); 
		this.register = client.getDB("ebanking").getCollection("register"); 
		
	}
	
	public void updateDocument(AccountBean bean, String flag) {
		// read documents from Transfer Collection.
		DBObject doc = new TransferConvertor().readDocument(bean);
		DBCursor cursor = this.collection.find(doc);
		
		// sender transaction added into the transfer collection.
		System.out.println("Cursor size of the Read Transfer document : "+cursor.size());
		if(cursor.size() == 0){
			// insert document.
			System.out.println("first time executing query in Transfer document");
			DBObject document = new TransferConvertor().createDocument(bean);
			this.collection.insert(document);
			System.out.println("Inserted document first time successfully!");
		}else{
			// document is already exist! -> add into transfer block.
			System.out.println("You are in else bloack.");
			DBObject document = new TransferConvertor().updateDocument(doc, bean);
			this.collection.update(new BasicDBObject()
					.append("p_id", new ObjectId(bean.getTransferFrom())), document);			
		}
		
		if(flag.equals("rec_exist")){
			// read receiver id  from register Collection, receiver is exist or not.
			DBObject receiver = new TransferConvertor().readReceiver(bean);
			DBCursor rec_cursor = register.find(receiver);
			
			DBObject receiver_obj = (DBObject)rec_cursor.next();
			String rec_id = receiver_obj.get("_id").toString();
			
			System.out.println("Receiver Object id is : "+rec_id);
			DBObject rec_doc = new TransferConvertor().readReciver(rec_id);
			
			// contain the DBObject which has Receiver document. 
			DBCursor t_cursor = this.collection.find(rec_doc);
			if(t_cursor.size() == 0){
				
				// first time receive, add into the list
				DBObject upd = new TransferConvertor().updateReciver(rec_id, bean);
				
				// add into the collection. 
				this.collection.insert(upd);	
			}else{
				// append into the list.
				System.out.println("you are in else block of : receiver_cursor.size() == 0 ");
				DBObject update_rec = new TransferConvertor().updateTansferhistory(rec_doc, bean);
				this.collection.update(new BasicDBObject()
						.append("p_id", new ObjectId(rec_id)), update_rec);
				System.out.println("Done Updating both the documents.");
			}
		}
	}
}
