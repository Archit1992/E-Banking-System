package main.java.dao;

import java.util.List;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import main.java.convertor.StatementConvertor;
import main.java.vo.RegBean;

public class StatementDB {
	DBCollection collection;
	public StatementDB(MongoClient client){
		this.collection = client.getDB("ebanking").getCollection("transfer"); 
	}
	
	public List readDocument(RegBean bean){
		DBObject obj = StatementConvertor.readStatement(bean);
		System.out.println("Collection name is : "+this.collection.getFullName());
		DBCursor cursor = this.collection.find(obj);
		return cursor.toArray();
	}
	
}
