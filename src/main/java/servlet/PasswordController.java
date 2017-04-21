package main.java.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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

/**
 * Servlet implementation class PasswordController
 */
@WebServlet("/PasswordController")
public class PasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HttpSession session=null;   
    public PasswordController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("flag");
		if(flag.equals("forgot")){
			String email = request.getParameter("email");
			MongoClient client = Connection.getlocalConnection();
			RegDB db = new RegDB(client);
			List<DBObject> list =db.readForgotPasswordUser(email);
			if(list.size() > 0){
				Iterator<DBObject> itr = list.iterator();
				DBObject obj = (DBObject)itr.next();
				session = request.getSession();
				session.setAttribute("message","Email is sent!");
				sendEmail(obj);
				response.sendRedirect(request.getContextPath()+"/user/forgot.jsp");				
			}else{
				session = request.getSession();
				session.setAttribute("message","Email is not valid!");
				response.sendRedirect(request.getContextPath()+"/user/forgot.jsp");
			}
			
		}
	}
	
	public void sendEmail(DBObject obj){
		java.util.Properties properties = new java.util.Properties();
	     properties.put("mail.smtp.auth", "true");
	     properties.put("mail.smtp.starttls.enable", "true");
	     javax.mail.Session mailSession = javax.mail.Session.getInstance(properties);

	     String emailId =obj.get("email").toString(); //request.getParameter("email");
	     System.out.println("Email sent : "+emailId);
	     try {
	         MimeMessage message = new MimeMessage(mailSession);

	         //message.setContent("<a href='http:\\\\"+request.getRemoteHost()+"\\"+request.getContextPath()+"\\register\\loadRegistrationPage.htm?random="+ id +"'>Please click here to Activate SwapMeTv Account</a> ", "text/html");
	         String content = "Hi "+obj.get("user").toString()+", <br/> Thank you for using ebanking website for online money transactions."+
	        		 			"<br/> Your request for getting password is accepted, below is your Ebanking Account credentials"+
	        		 			"<br/> Your <b>username</b> is : "+obj.get("user").toString()+
	        		 			"<br/> Your <b>password</b> is : "+obj.get("password").toString()+
	        		 			"<br/> If you do have any questions, or query please mentioned it here!"+
	        		 			"<br/><br/> Thanks, <br/>Archit Gajjar";
	         message.setContent( content, "text/html");
	         message.setSubject("EBanking: Reset Password");
	         
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
