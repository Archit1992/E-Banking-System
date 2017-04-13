package main.java.dao;

import java.util.List;
import org.bson.types.ObjectId;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import main.java.convertor.RegConvertor;
import main.java.vo.RegBean;

public class AccountDB {

	DBCollection collection;
	String id = null,user=null;
	public AccountDB(MongoClient client){
		this.collection = client.getDB("ebanking").getCollection("register"); 
	}
	
	public void updateDocument(RegBean bean) {
		DBObject doc = RegConvertor.toLoginDBObj(bean);
		DBCursor cursor = this.collection.find(doc);

	}

}
