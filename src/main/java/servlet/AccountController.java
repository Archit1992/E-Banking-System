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

import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBObject;


@WebServlet("/AccountController")
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HttpSession session;
	@SuppressWarnings("unused")
	private BasicDBObject checking,savings;
	
	@SuppressWarnings("unused")
	private List<DBObject> list;
	public List<String> acc;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
