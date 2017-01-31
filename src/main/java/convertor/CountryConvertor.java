package main.java.convertor;


import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import main.java.vao.CountryVAO;

public class CountryConvertor {
	
	public static DBObject toDBObject(CountryVAO vo){
		System.out.println("VO name is : "+vo.getName());
		try{
			BasicDBObjectBuilder builder = BasicDBObjectBuilder.start().append("name", vo.getName());
			return builder.get();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		CountryVAO vo = new CountryVAO();
		vo.setName("Archit");
		DBObject dbobj = CountryConvertor.toDBObject(vo);
		System.out.println(dbobj.toString());
	}
}
