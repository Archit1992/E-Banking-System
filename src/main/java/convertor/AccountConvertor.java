package main.java.convertor;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import main.java.connection.Connection;
import main.java.dao.AccountDB;
import main.java.vo.AccountBean;

public class AccountConvertor {
	
	public static DBObject updateFromDocument(AccountBean bean, Long balance) {
		String objId = bean.getTransferFrom();
		System.out.println("AccountController: ObjID : "+objId);
		BasicDBObject update = new BasicDBObject();
		System.out.println("Sender's Checking/Savings account Balance : "+balance);
		System.out.println("Sender's Checking transfer balance : "+bean.getAmount());
		Long value = balance - bean.getAmount();
		System.out.println("Available balance : "+value);
		System.out.println("Balance now in Sender Account : "+(balance - bean.getAmount()));
		
		if((bean.getFlag()).equals("Checking")){
			update.append("Account.Checking.Balance",(balance - bean.getAmount()));			
		}else{
			update.append("Account.Savings.Balance",(balance - bean.getAmount()));
		}
		
		BasicDBObject document = new BasicDBObject();
		document.append("$set", update);
		
		return document;
	}

	public static DBObject updateToDocument(AccountBean bean, Long balance) {
		System.out.println("AccountController: ToEmail : "+bean.getTransferTo());
		BasicDBObject update = new BasicDBObject();
		update.append("Account.Checking.Balance", balance + bean.getAmount());;
		
		BasicDBObject document = new BasicDBObject();
		document.append("$set", update);
		
		return document;
	}
}
