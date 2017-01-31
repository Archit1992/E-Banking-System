package main.java.dao;


import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import main.java.connection.Connection;
import main.java.convertor.CountryConvertor;
import main.java.vao.CountryVAO;

public class CountryDAO {
	private DBCollection collection = null;
	
	public CountryVAO createCountry(CountryVAO vo) {
		
		DBObject doc = CountryConvertor.toDBObject(vo);
		System.out.println(doc.toString());
		this.collection = Connection.getConnection("country");
		this.collection.insert(doc);
//		ObjectId id = (ObjectId) doc.get("_id");
//		vo.setId(id.toString());
//		System.out.println("Added User id is : " + id.toString());
		return vo;
	}
}
