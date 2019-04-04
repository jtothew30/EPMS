package com.epms.Controller.Notification;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.epms.Model.Notification.NotificationDAO;

@WebServlet("/notification_request.do")
public class NotificationRequestController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id == null) {
	        response.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		NotificationDAO ndao = new NotificationDAO(); 
		boolean result = ndao.notificationInsert(id, title, content);
		System.out.println("notification request : " + (result?"success":"fail"));
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject();
		json.put("result", result);
		out.print(json);
	 }
	  
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}
