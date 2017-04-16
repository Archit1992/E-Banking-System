package main.java.dao;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import main.java.connection.Connection;
import main.java.convertor.AccountConvertor;
import main.java.vo.AccountBean;

public class AccountDB {

	DBCollection collection;
	String id = null,user=null;
	public AccountDB(MongoClient client){
		this.collection = client.getDB("ebanking").getCollection("register"); 
	}
	
	public void updateDocument(AccountBean bean) {
		
		// update Receiver Account Checking Balance.
		Long balance = getBalance(bean, "Checking", "receiver");
		DBObject to = AccountConvertor.updateToDocument(bean, balance);
		collection.update(new BasicDBObject().append("email", bean.getTransferTo()), to);
		
		// update Sender Account Balance (According to flag : Checking/Savings).
		System.out.println("The flag information in the AccountDB : "+bean.getFlag());
		Long bal = getBalance(bean, bean.getFlag(),"sender");
		DBObject from = AccountConvertor.updateFromDocument(bean, bal);
		System.out.println("Sender Object id value is : "+bean.getTransferFrom());
		collection.update(new BasicDBObject().append("_id", new ObjectId(bean.getTransferFrom())), from);
		
		MongoClient client = Connection.getlocalConnection();
		System.out.println("You are tranfered money, now update the tranfer collection");
		new TransferDB(client).updateDocument(bean);
		
	}
	
	public long getBalance(AccountBean bean, String flag, String person){
		if(person.equals("sender")){
			
			Long balance;
			BasicDBObject obj;
			obj = new BasicDBObject().append("_id", new ObjectId(bean.getTransferFrom()));
			DBCursor cursor = collection.find(obj);
			DBObject doc= (DBObject)cursor.next();
			// System.out.println("ACC object values :: "+doc.toString());
			DBObject acc = (DBObject)doc.get("Account");
			
			if(flag.equals("Checking")){
				DBObject check = (DBObject)acc.get("Checking");
				System.out.println("SENDER : Checking object value : "+check.toString());
				System.out.println("SENDER : Checking account balance in the getBalance() method is : "+check.get("Balance").toString());
				balance = Long.parseLong(check.get("Balance").toString());
			}else{
				DBObject save = (DBObject)acc.get("Savings");
				balance = Long.parseLong(save.get("Balance").toString());
			}
			return balance;
		
		}else{
			Long balance;
			BasicDBObject obj;
			obj = new BasicDBObject().append("email", bean.getTransferTo());
			DBCursor cursor = collection.find(obj);
			DBObject doc= (DBObject)cursor.next();
			// System.out.println("ACC object values :: "+doc.toString());
			DBObject acc = (DBObject)doc.get("Account");
			
			if(flag.equals("Checking")){
				DBObject check = (DBObject)acc.get("Checking");
				System.out.println("RECEIVER : Checking object value : "+check.toString());
				System.out.println("RECEIVER : Checking account balance in the getBalance() method is : "+check.get("Balance").toString());
				balance = Long.parseLong(check.get("Balance").toString());
			}else{
				DBObject save = (DBObject)acc.get("Savings");
				balance = Long.parseLong(save.get("Balance").toString());
			}
			return balance;
		}
	}
}
