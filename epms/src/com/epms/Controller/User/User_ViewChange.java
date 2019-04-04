package com.epms.Controller.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epms.Http.HttpUtil;
import com.epms.Model.Notification.NotificationDAO;

@WebServlet("/user_saveView.do")
public class User_ViewChange extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String a = request.getParameter("where");
		if(a == null) {
			HttpUtil.forward(request, response, "signup/terms.jsp");
		} else if(a .equals("find")) {
			HttpUtil.forward(request, response, "login/IDPWRefer.jsp");
		} else if(a.equals("main")) {
			HttpUtil.forward(request, response, "main/main.jsp");
		} else {
			HttpUtil.forward(request, response, "signup/insert.jsp");
		}
	 }
	  
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}
