package com.epms.Controller.Notification;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epms.Http.HttpUtil;
import com.epms.Model.Notification.NotificationBean;
import com.epms.Model.Notification.NotificationDAO;

@WebServlet("/notification_edit.do")
public class NotificationEditController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("id") == null) {
	        response.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
		int no = Integer.parseInt(request.getParameter("idx"));
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		NotificationDAO ndao = new NotificationDAO(); 
		ndao.notificationEdit(no, id, title, content);
		System.out.println(no+ " / " + id + " / " + title + " / " + content);
	 }
	  
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}
