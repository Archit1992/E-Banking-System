package main.java.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import main.java.connection.Connection;
import main.java.dao.RegDB;
import main.java.vo.RegBean;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private RegBean bean = null;
	private static MongoClient client= null;
	
	private static final long serialVersionUID = 1L;   
    public RegisterController() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("flag");
		HttpSession session;
		if(flag.equals("login")){
			RegBean bean = new RegisterController().setLogin(request, response);			
			//client = Connection.getConnection();
			client = Connection.getlocalConnection();
			List<DBObject> list= new RegDB(client).readDocument(bean);
			if(list.size() != 0){
				session = request.getSession();
				session.setAttribute("user",list);
				//response.sendRedirect(request.getContextPath()+"/user/home.jsp");
				response.sendRedirect(request.getContextPath()+"/AccountController");
			}else{
				session = request.getSession();
				session.setAttribute("invalid", "PLease enter valid user credentials!");
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("flag");
		if(flag.equals("insert")){
			RegBean bean = new RegisterController().setValue(request, response);			
			//client = Connection.getConnection();
			client = Connection.getlocalConnection();
			String id= new RegDB(client).createDocument(bean);
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
	}
	public RegBean setValue(HttpServletRequest request, HttpServletResponse response){
		bean = new RegBean();
		bean.setEmail(request.getParameter("email"));
		bean.setUsername(request.getParameter("userName"));
		bean.setPassword(request.getParameter("password"));
		bean.setBirthdate(getDate(request.getParameter("dob")));
		bean.setGender(request.getParameter("gender"));
		bean.setRegDate();
		return bean;
	}
	
	public RegBean setLogin(HttpServletRequest request, HttpServletResponse response){
		bean = new RegBean();
		bean.setEmail(request.getParameter("email"));
		bean.setPassword(request.getParameter("password"));
		return bean;
	}
	
	public Date getDate(String date){
		DateFormat format = new SimpleDateFormat("mm/dd/yyyy",Locale.ENGLISH);
		try {
			Date d = format.parse(date);
			return d;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}	
}
