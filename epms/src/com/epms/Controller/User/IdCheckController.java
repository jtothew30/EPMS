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


@WebServlet("/idCheck.do")
public class IdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    
    protected void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	FlowFilter filter = new FlowFilter();
		
    	response.setContentType("text/html;charset=UTF-8");
		PrintWriter out =  response.getWriter();
		
    	String id = request.getParameter("UID");
   	
    	System.out.println("id : "+id );    	
    	
    	Account_DAO dao = new Account_DAO();
    	int result = dao.epmsIdCheck(id);
    	System.out.println("result : " + result);
    	if(result==0) { // 넘어온 아이디가 디비에 없다
    		out.println("<font size=3 color='green'>ID 사용가능합니다</font>");
    	}else {// 넘어온 아이디가 디비에 있다
    		out.println("<font size=3 color='red'>이미 사용중인 ID입니다</font>");
    	}   	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doUser(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doUser(request, response);
	}

}
