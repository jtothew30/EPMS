package com.epms.Controller.User;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.catalina.connector.Request;

import com.epms.Filter.FlowFilter;
import com.epms.Model.User.Account_DAO;


@WebServlet("/passRefer.do")
public class PasswordReferController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    
    protected void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	FlowFilter filter = new FlowFilter();
		
    	response.setContentType("text/html;charset=UTF-8");
		PrintWriter out =  response.getWriter();
		
    	String id = request.getParameter("id");
    	String email = request.getParameter("email");
    	
    	Account_DAO dao = new Account_DAO();
    	
    	String resultPassword = dao.passRefer(id, email);
    	System.out.println(resultPassword);
    	out.print(resultPassword);
    	 	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doUser(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doUser(request, response);
	}

}
