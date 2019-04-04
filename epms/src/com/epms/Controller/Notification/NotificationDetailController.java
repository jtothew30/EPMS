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

@WebServlet("/notification_detail.do")
public class NotificationDetailController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("idx"));
		NotificationDAO ndao = new NotificationDAO(); 
		NotificationBean nb = ndao.notificationDetail(no);
		request.setAttribute("nb", nb);
		HttpUtil.forward(request, response, "notification/notification_detail.jsp");
	 }
	  
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}
