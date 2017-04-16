package main.java.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.vo.AccountBean;

/**
 * Servlet implementation class TransferController
 */
@WebServlet("/TransferController")
public class TransferController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private AccountBean bean=null;
    public TransferController() {
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String p_name ="Archit Gajjar";
		String send_id="58f10105e0a19b4730795878";
		String from ="gajjararchit000005@gmail.com";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
