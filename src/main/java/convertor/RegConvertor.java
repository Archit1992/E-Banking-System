package main.java.convertor;

import java.lang.reflect.Array;
import java.util.Date;

import org.bson.BsonArray;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import main.java.vo.RegBean;

public class RegConvertor {
	public static long checking_no=475989777;
	public static long savings_no=478671990;
	
	public RegConvertor(){
		System.out.println("=====================RegController called====================");
		checking_no++;
		savings_no++;
	}
	public DBObject toDBObject(RegBean bean) {
		Document account,checking,savings;
		checking = new Document().append("Account_No",checking_no).append("Balance",25);
		savings = new Document().append("Account_No",savings_no).append("Balance",25);
		
		account= new Document().append("Checking",checking).append("Savings", savings);
		
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.add("user", bean.getUsername())
				.add("email", bean.getEmail())
				.add("password", bean.getPassword())
				.add("date", bean.getBirthdate())
				.add("regDate",new Date())
				.add("gender",bean.getGender())
				.add("Account", account);	// account: DBDocument.
		
		return builder.get();	
	}

	public static DBObject toLoginDBObj(RegBean bean) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start().add("email", bean.getEmail())
				.add("password", bean.getPassword());
		
		return builder.get();
	}
	public static DBObject toFindByID(RegBean bean) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.add("_id", new ObjectId(bean.getId()));
		
		return builder.get();
	}
	public static DBObject getAccountInfo(RegBean bean) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(bean.getId()));
		
		return builder.get();
	}
	public static DBObject getAccountUpdate(RegBean bean) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.add("user", bean.getUsername())
				.add("email", bean.getEmail())
				.add("password", bean.getPassword())
				.add("date", bean.getBirthdate())
				.add("gender",bean.getGender());
		BasicDBObject obj = new BasicDBObject().append("$set", builder.get());  
		
		return obj;
	}
}
