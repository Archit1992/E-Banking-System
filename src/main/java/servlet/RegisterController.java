package main.java.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import main.java.dao.RegDB;
import main.java.vo.RegBean;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private RegBean bean = null;
	private static MongoClient client= null;
	private HttpSession session=null;
	private static final long serialVersionUID = 1L;   
    public RegisterController() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("flag");
		System.out.println("The flag name is : "+flag);
		HttpSession session;
		if(flag.equals("login")){
			RegBean bean = new RegisterController().setLogin(request, response);			
			//client = Connection.getConnection();
			client = Connection.getlocalConnection();
			List<DBObject> list= new RegDB(client).readDocument(bean);
			if(list.size() != 0){
				session = request.getSession();
				session.setAttribute("user",list);
				System.out.println("You got the record");
				//response.sendRedirect(request.getContextPath()+"/user/home.jsp");
				response.sendRedirect(request.getContextPath()+"/AccountController?flag=home");
			}else{
				session = request.getSession();
				session.setAttribute("invalid", "PLease enter valid user credentials!");
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}			
		}
		if(flag.equals("get")){
			List<DBObject> list = (List<DBObject>)request.getSession().getAttribute("user");			
			DBObject obj = (DBObject)list.get(0);
			ObjectId id = new ObjectId(obj.get("_id").toString());
			bean = new RegBean();
			bean.setId(id.toString());
			MongoClient client = Connection.getlocalConnection();
			List<DBObject> reg_update = new RegDB(client).updateDoc(bean); 
			session = request.getSession();
			session.setAttribute("get_user", reg_update);
			response.sendRedirect(request.getContextPath()+"/user/account.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("flag");
		if(flag.equals("insert")){
			RegBean bean = new RegisterController().setValue(request, response);			
			//client = Connection.getConnection();
			client = Connection.getlocalConnection();
			String id= new RegDB(client).createDocument(bean);
			// send an email to the user.
			sendEmail(bean);
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
		if(flag.equals("update")){
			RegBean bean = new RegisterController().setValue(request, response);
			List<DBObject> list = (List<DBObject>)request.getSession().getAttribute("user");
			DBObject obj = (DBObject)list.get(0);
			ObjectId id = new ObjectId(obj.get("_id").toString());
			//client = Connection.getConnection();
			client = Connection.getlocalConnection();
			new RegDB(client).updateAcc(id, bean);	// calling updateAccount method.
			// send an email to the user.
			sendUpdates(bean);
			response.sendRedirect(request.getContextPath()+"/user/home.jsp");
		
		}
	}
	private void sendUpdates(RegBean bean) {
		java.util.Properties properties = new java.util.Properties();
	     properties.put("mail.smtp.auth", "true");
	     properties.put("mail.smtp.starttls.enable", "true");
	     javax.mail.Session mailSession = javax.mail.Session.getInstance(properties);

	     String emailId =bean.getEmail(); //request.getParameter("email");
	     
	     try {
	         MimeMessage message = new MimeMessage(mailSession);

	         //message.setContent("<a href='http:\\\\"+request.getRemoteHost()+"\\"+request.getContextPath()+"\\register\\loadRegistrationPage.htm?random="+ id +"'>Please click here to Activate SwapMeTv Account</a> ", "text/html");
	         String content = "Hi "+bean.getUsername()+", <br/>  Your account has been updated!"+
	        		 			"<br/> Your <b>username</b> is : "+bean.getUsername()+
	        		 			"<br/> Your <b>Email</b> is : "+bean.getEmail()+
	        		 			"<br/> Your <b>password</b> is : "+bean.getPassword()+
	        		 			"<br/> If you do have any questions, or query please mentioned it here!"+
	        		 			"<br/><br/> Thanks, <br/>Archit Gajjar";
	         message.setContent( content, "text/html");
	         message.setSubject("Registered Successfullly");
	         
	         InternetAddress sender = new InternetAddress("<email>", "Activation Link");
	         InternetAddress receiver = new InternetAddress(emailId);
	         message.setFrom(sender);
	         message.setRecipient(Message.RecipientType.TO, receiver);
	         message.saveChanges();
	         
	         javax.mail.Transport transport = mailSession.getTransport("smtp");
	         transport.connect("smtp.gmail.com", 587, "<email address>", "password");
	         transport.sendMessage(message, message.getAllRecipients());
	         transport.close();

	         
	     } catch (Exception e) {
	    	 System.out.println(e.getMessage());
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
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd",Locale.ENGLISH);
		try {
			Date d = format.parse(date);
			return d;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void sendEmail(RegBean bean){
		 java.util.Properties properties = new java.util.Properties();
	     properties.put("mail.smtp.auth", "true");
	     properties.put("mail.smtp.starttls.enable", "true");
	     javax.mail.Session mailSession = javax.mail.Session.getInstance(properties);

	     String emailId =bean.getEmail(); //request.getParameter("email");
	     
	     try {
	         MimeMessage message = new MimeMessage(mailSession);

	         //message.setContent("<a href='http:\\\\"+request.getRemoteHost()+"\\"+request.getContextPath()+"\\register\\loadRegistrationPage.htm?random="+ id +"'>Please click here to Activate SwapMeTv Account</a> ", "text/html");
	         String content = "Hi "+bean.getUsername()+", <br/> Thank you for registered yourself on ebanking website"+
	        		 			"<br/> Your <b>username</b> is : "+bean.getUsername()+
	        		 			"<br/> Your <b>password</b> is : "+bean.getPassword()+
	        		 			"<br/> If you do have any questions, or query please mentioned it here!"+
	        		 			"<br/><br/> Thanks, <br/>Archit Gajjar";
	         message.setContent( content, "text/html");
	         message.setSubject("Registered Successfullly");
	         
	         InternetAddress sender = new InternetAddress("<email>", "Activation Link");
	         InternetAddress receiver = new InternetAddress(emailId);
	         message.setFrom(sender);
	         message.setRecipient(Message.RecipientType.TO, receiver);
	         message.saveChanges();
	         
	         javax.mail.Transport transport = mailSession.getTransport("smtp");
	         transport.connect("smtp.gmail.com", 587, "<email>", "<password>");
	         transport.sendMessage(message, message.getAllRecipients());
	         transport.close();

	         
	     } catch (Exception e) {
	    	 System.out.println(e.getMessage());
	     }
	}
	
	
}
