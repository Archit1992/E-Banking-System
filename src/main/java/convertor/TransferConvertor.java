package main.java.convertor;

import java.util.Date;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import main.java.vo.AccountBean;

public class TransferConvertor {
	
	public DBObject readDocument(AccountBean bean) {
		
		BasicDBObjectBuilder builder = new BasicDBObjectBuilder().start()
				.append("p_id", new ObjectId(bean.getTransferFrom())); 	
		return builder.get();
	}

	public DBObject createDocument(AccountBean bean) {
		
		// adding transfer history into DBObject.
		BasicDBObjectBuilder buildDoc = new BasicDBObjectBuilder().start()
				.append("user_id", new ObjectId(bean.getTransferFrom()))
				.append("sent_to", bean.getPersonName())
				.append("Account", bean.getFlag())
				.append("email", bean.getTransferTo())
				.append("Amount", bean.getAmount()).append("Date", new Date());
		
		// adding DBObject into the BasicDBList.
		BasicDBList list = new BasicDBList();
		list.add(buildDoc.get());
		
		// adding BasicDBList into the final document which contains the person Obj_id too.
		BasicDBObjectBuilder builder = new BasicDBObjectBuilder().start()
				.append("p_id", new ObjectId(bean.getTransferFrom()))
				.append("Transfer", list);
		
		return builder.get();
	}

	public DBObject updateDocument(DBObject doc,AccountBean bean) {
		
		// add the document into the list.
		BasicDBObjectBuilder obj = new BasicDBObjectBuilder().start()
				.append("user_id", new ObjectId(bean.getTransferFrom()))
				.append("sent_to", bean.getPersonName())
				.append("Account", bean.getFlag())
				.append("email", bean.getTransferTo())
				.append("Amount", bean.getAmount()).append("Date", new Date());
		
		
		// Query parameter.
		BasicDBObject document = new BasicDBObject();
		document.append("Transfer", obj.get());
		
		// update query
		BasicDBObject object = new BasicDBObject().append("$push",obj);
		object.append("$push", document);
		
		// return document which you want to update.
		return object;
	}

	public DBObject readReceiver(AccountBean bean) {
		
		BasicDBObjectBuilder builder = new BasicDBObjectBuilder().start()
				.append("email", bean.getTransferTo()); 	
		return builder.get();
	}

	public DBObject readReciver(String rec_ids) {
		BasicDBObjectBuilder builder = new BasicDBObjectBuilder().start()
				.append("p_id", new ObjectId(rec_ids));
		
		return builder.get();
	}

	public DBObject updateReciver(String rec_id, AccountBean bean) {
		
		// create new document. 
		BasicDBObjectBuilder builder = new BasicDBObjectBuilder().start()
				.append("From", bean.getTransferFrom())
				.append("Amount", bean.getAmount())
				.append("Date", new Date());
		
		// add into the list.
		BasicDBList list = new BasicDBList();
		list.add(builder.get());
		
		
		// create query.
		BasicDBObject update = new BasicDBObject()
				.append("p_id", new ObjectId(rec_id))
				.append("Receiver", list);
		
		return update;
	}

	public DBObject updateTansferhistory(DBObject rec_doc, AccountBean bean) {
		
		// add the document into the list.
		BasicDBObjectBuilder obj = new BasicDBObjectBuilder().start()
				.append("From", bean.getTransferFrom())
				.append("Amount", bean.getAmount())
				.append("Date", new Date());
		
		// Query parameter.
		BasicDBObject document = new BasicDBObject();
		document.append("Receiver", obj.get());
		
		// update query
		BasicDBObject object = new BasicDBObject().append("$push",obj);
		object.append("$push", document);
		
		// return document which you want to update.
		return object;

	}

	public DBObject updateAccount(AccountBean bean) {
		// add the document into the list.
		BasicDBObjectBuilder obj = new BasicDBObjectBuilder().start()
				.append("user_id", new ObjectId(bean.getTransferFrom()))
				.append("sent_to", bean.getPersonName())
				.append("email", bean.getTransferTo())
				.append("Amount", bean.getAmount())
				.append("Date", new Date());
		
		// Query parameter.
		BasicDBObject document = new BasicDBObject();
		document.append("Transfer", obj.get());
		
		// update query
		BasicDBObject object = new BasicDBObject().append("$push",obj);
		object.append("$push", document);
		
		// return document which you want to update.
		return object;
	}
}
