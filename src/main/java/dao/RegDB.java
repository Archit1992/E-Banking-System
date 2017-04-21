package main.java.dao;


import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

import main.java.convertor.RegConvertor;
import main.java.vo.RegBean;

public class RegDB {
	DBCollection collection;
	String id = null,user=null;
	public RegDB(MongoClient client){
		this.collection = client.getDB("ebanking").getCollection("register"); 
	}
	
	public String createDocument(RegBean bean) {
		DBObject doc = new RegConvertor().toDBObject(bean);
		this.collection.insert(doc);
		ObjectId id = (ObjectId)doc.get("_id");
		return id.toString();
	}

	public List<DBObject> readDocument(RegBean bean) {
		DBObject doc = RegConvertor.toLoginDBObj(bean);
		DBCursor cursor = this.collection.find(doc);
		return cursor.toArray();
	}

	public List<DBObject> readDocumentByID(RegBean bean) {
		DBObject doc = RegConvertor.toFindByID(bean);
		DBCursor cursor = this.collection.find(doc);
		return cursor.toArray();
	}
	public List<DBObject> readForgotPasswordUser(String email){
		DBCursor cursor = this.collection.find(new BasicDBObject().append("email", email));
		return cursor.toArray();
	}

	public List<DBObject> updateDoc(RegBean bean) {
		DBObject doc = RegConvertor.getAccountInfo(bean);
		DBCursor cursor = this.collection.find(doc);
		return cursor.toArray();
	}
	
	public void updateAcc(ObjectId id, RegBean bean) {
		DBObject doc = RegConvertor.getAccountUpdate(bean);
		this.collection.update(new BasicDBObject().append("_id",id),doc);
	}
		
}

//while(cursor.hasNext()){
//			id = cursor.next().get("_id").toString();
//		}
//		return id;

