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
		
		DBCursor cursor = this.collection.find(new BasicDBObject().append("email", bean.getTransferTo()));
		System.out.println("Receiver is exist ? 0: not exist, 1 : exist => "+cursor.size());
		/*
		 * check the receiver is exist or not ? 
		 * if No: take the sender id, using bean.getFrom(), update the register Account(reduce balanace)
		 * 		  and the update into the transfer collection too. 
		 * else -> remaining portion.
		 * 
		 * */
		
		if(cursor.size() == 0 ){
			//==================== if receiver is not exist.=========================
			
			// update the Sender's Account information according to the flag. 
			Long balance = getBalance(bean,bean.getFlag(),"sender");
			DBObject from = AccountConvertor.updateFromDocument(bean, balance);
			System.out.println("Sender Object id value is : "+bean.getTransferFrom());
			collection.update(new BasicDBObject().append("_id", new ObjectId(bean.getTransferFrom())), from);
			
			// now add into the transfer collection. 
			MongoClient client = Connection.getlocalConnection();
			System.out.println("You are tranfered money, now update the tranfer collection");
			String flag="rec_not_exist";
			new TransferDB(client).updateDocument(bean,flag);		// flag="rec_not_exist"
			// update the Checking and Savings session. 
		
		}
		else{
			// ================= if Receiver is exist!===============================
			
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
			String flag = "rec_exist";
			new TransferDB(client).updateDocument(bean, flag);
		}
			
	}
	
	public long getBalance(AccountBean bean, String flag, String person){
		// if person : sender, check the flag : if(checking) -> return balance, else -> return savings balance. 
		if(person.equals("sender")){
			
			Long balance;
			BasicDBObject obj;
			obj = new BasicDBObject().append("_id", new ObjectId(bean.getTransferFrom()));
			DBCursor cursor = collection.find(obj);
			DBObject doc= (DBObject)cursor.next();
			// System.out.println("ACC object values :: "+doc.toString());
			DBObject acc = (DBObject)doc.get("Account");
			
			// if flag is checking, then update the checking Account else savings account.
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
