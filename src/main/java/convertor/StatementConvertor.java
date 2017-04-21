package main.java.convertor;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import main.java.vo.RegBean;

public class StatementConvertor {

	public static DBObject readStatement(RegBean bean) {
		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append("p_id", new ObjectId(bean.getId()));
		return builder.get();
	}

}
