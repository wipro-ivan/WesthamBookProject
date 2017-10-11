package servlet;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.User;
import dao.LoginDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set response content type
	     response.setContentType("text/html");
	   
	     //printwriter to print onto page
		PrintWriter out=response.getWriter();
		
		// created a new user using the bean
		User newuser = new User();
		//set username  from HTML page
		newuser.setEmailAddr(request.getParameter("email"));
		
		//check if email address exists in table 
		if(LoginDAO.checkUser(newuser.getEmailAddr())){
			//set password
			newuser.setPassword(request.getParameter("password"));
			//authentice password
			if(LoginDAO.checkPW(newuser.getEmailAddr(), newuser.getPassword())){
				//welcome user
				out.println("Welcome " + LoginDAO.getName(newuser.getEmailAddr() + "!"));
			}
			else{
				out.println("This password is incorrect."); //indicate that this is the wrong password
			}
		}
		else{
			out.println("This account does not exist."); //indicate that this email address isn't present in DB
		}
		
	}

	/**
	* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
	}

}



