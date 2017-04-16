package main.java.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import main.java.connection.Connection;
import main.java.dao.AccountDB;
import main.java.dao.RegDB;
import main.java.vo.AccountBean;
import main.java.vo.RegBean;


@WebServlet("/AccountController")
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HttpSession session;
	@SuppressWarnings("unused")
	private BasicDBObject checking,savings;
	
	@SuppressWarnings("unused")
	private List<DBObject> list;
	public List<String> acc;
	private AccountBean bean; 
	private MongoClient client;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String flag = request.getParameter("flag");
		if(flag.equals("home")){
			BasicDBObject obj,account;
			list = (List<DBObject>)request.getSession().getAttribute("user");
			System.out.println("List value is : "+list.toString());
			Iterator<DBObject> itr = list.iterator();
			while(itr.hasNext()){
				obj = (BasicDBObject)itr.next();
				account = (BasicDBObject)obj.get("Account");
				checking = (BasicDBObject)account.get("Checking");
				savings = (BasicDBObject)account.get("Savings");
			}
			acc = new ArrayList<String>();
			acc.add(checking.getString("Account_No"));
			acc.add(checking.getString("Balance"));
			acc.add(savings.getString("Account_No"));
			acc.add(savings.getString("Balance"));
			session = request.getSession();
			session.setAttribute("account", acc);
			
			response.sendRedirect(request.getContextPath()+"/user/home.jsp");
		
		}
		if(flag.equals("update")){
		
			list = (List<DBObject>)request.getSession().getAttribute("user");
			Iterator<DBObject> itr = list.iterator();
			DBObject obj = (DBObject)itr.next();
			String objId = obj.get("_id").toString();
			MongoClient client = Connection.getlocalConnection();
			RegBean bean = new RegBean();
			bean.setId(objId);
			List<DBObject> list= (List<DBObject>)new RegDB(client).readDocumentByID(bean);
			
			Iterator<DBObject> it = list.iterator();
			BasicDBObject doc,account;
			System.out.println("UPDATE : list data after transfer : "+list.toString());
			while(it.hasNext()){
				doc = (BasicDBObject)it.next();
				account = (BasicDBObject)doc.get("Account");
				checking = (BasicDBObject)account.get("Checking");
				savings = (BasicDBObject)account.get("Savings");
			}
			acc = new ArrayList<String>();
			acc.add(checking.getString("Account_No"));
			acc.add(checking.getString("Balance"));
			acc.add(savings.getString("Account_No"));
			acc.add(savings.getString("Balance"));
			session = request.getSession();
			session.setAttribute("account", acc);
			
			response.sendRedirect(request.getContextPath()+"/user/home.jsp");
		
		}
		
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag=request.getParameter("flag");
		System.out.println("The flag value is :: "+flag);
		if(flag.equals("transfer")){
			String email = request.getParameter("email");
			String p_name = request.getParameter("p_name");
			int amount = Integer.parseInt(request.getParameter("amount"));
			String type = request.getParameter("bal");
			String userId = getID();
			System.out.println("User selected type: "+type);
			bean = getBean(email,p_name, amount, type, userId);
			client = Connection.getlocalConnection();
			new AccountDB(client).updateDocument(bean);
			
			/*String para= "?send_id="+bean.getTransferFrom()+"&p_name="+bean.getPersonName()+"&from="+bean.getPersonName();
			response.sendRedirect(request.getContextPath()+"/TransferController"+para);
 			*/
			response.sendRedirect(request.getContextPath()+"/AccountController?flag=update");
			// if email is available then update the amount, otherwise only update the document.
		}
	}

	private String getID() {
		List<DBObject> list = (List<DBObject>)session.getAttribute("user");
		Iterator<DBObject> itr = list.iterator();
		ObjectId id = null;
		while(itr.hasNext()){
			BasicDBObject obj = (BasicDBObject)itr.next();
			id = obj.getObjectId("_id");
		}
		return id.toString();
	}
	
	private AccountBean getBean(String email,String p_name, int amount, String flag, String userId){
		bean = new AccountBean();
		bean.setAmount(amount);
		bean.setFlag(flag);
		bean.setTransferFrom(userId);
		bean.setTransferTo(email);
		bean.setPersonName(p_name);
		return bean;	// return bean object.
	}
}
