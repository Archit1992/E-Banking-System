package main.java.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.types.ObjectId;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import main.java.connection.Connection;
import main.java.dao.StatementDB;
import main.java.vo.RegBean;

/**
 * Servlet implementation class StatementController
 */
@WebServlet("/StatementController")
public class StatementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public HttpSession session;   
    public StatementController() {
        super();
        // TODO Auto-generated constructor stub
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
    	List<DBObject> list = (List<DBObject>)request.getSession().getAttribute("user");
		Iterator<DBObject> itr = list.iterator();
		DBObject object = (DBObject)itr.next();
		ObjectId id = (ObjectId)object.get("_id");		// getting ObjectId of the login user.
		
		RegBean bean = new RegBean();
		bean.setId(id.toString()); 		// set ObjectId in String format.
		
		MongoClient client = Connection.getlocalConnection();
		List<DBObject> obj = new StatementDB(client).readDocument(bean);
		
		if(obj.size() != 0){
			Iterator<DBObject> it = obj.iterator();
			
			DBObject document = (DBObject)it.next();
			System.out.println("Docuemnt contains data : "+document);
			List<DBObject> transfer = (List<DBObject>) document.get("Transfer");
			List<DBObject> receiver = (List<DBObject>) document.get("Receiver");
			
			System.out.println("Transfer list : "+transfer);
			System.out.println("Reciver list : "+receiver);
			
			HttpSession session = request.getSession();
			session.setAttribute("transfer", transfer);
			session.setAttribute("receiver", receiver);
		}
		
		response.sendRedirect(request.getContextPath()+"/user/statement.jsp");
	    
		/*List<DBObject> trans = (List<DBObject>)session.getAttribute("transfer");
		System.out.println("Size : "+trans.size());
		System.out.println("start : "+(trans.size()-1));
		
		System.out.println("Email : "+((DBObject)trans.get(trans.size()-1)).get("email").toString());
		System.out.println("Sent : "+((DBObject)trans.get(trans.size()-1)).get("sent_to").toString());
		System.out.println("amount : "+((DBObject)trans.get(trans.size()-1)).get("Amount").toString());
		System.out.println("date : ");
		Date d = new Date(((DBObject)trans.get(trans.size()-1)).get("Date").toString());
		System.out.println("Date : "+d.getDate());
		
		for(int i=( trans.size()-1) ; i > 0  ; i--){
			String email = ((DBObject)trans.get(i)).get("email").toString();
			String sentTo = ((DBObject)trans.get(i)).get("sent_to").toString();
			String amount = ((DBObject)trans.get(i)).get("Amount").toString();
			String date = ((DBObject)trans.get(i)).get("Date").toString();
			System.out.println("Sent to : "+sentTo);
			System.out.println("Sent to : "+email);
			System.out.println("Sent to : "+amount);
			System.out.println("Sent to : "+date);
			
		}	
		*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
