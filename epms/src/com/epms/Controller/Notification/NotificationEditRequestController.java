package com.epms.Controller.Notification;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epms.Http.HttpUtil;
import com.epms.Model.Notification.NotificationBean;
import com.epms.Model.Notification.NotificationDAO;

@WebServlet("/notification_edit_request.do")
public class NotificationEditRequestController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("id") == null) {
	        response.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
		int no = Integer.parseInt(request.getParameter("idx"));
		NotificationDAO ndao = new NotificationDAO(); 
		NotificationBean nb = ndao.notificationSelct(no);
		request.setAttribute("nb", nb);
		HttpUtil.forward(request, response, "notification/notification_edit.jsp");
	 }
	  
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}
