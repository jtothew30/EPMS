package com.epms.Controller.Myinfo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.epms.Http.HttpUtil;
import com.epms.Model.Notification.NotificationDAO;
import com.epms.Model.Reservation.ReservationBean;
import com.epms.Model.Myinfo.MyinfoDAO;
import com.epms.Model.Notification.NotificationBean;

@WebServlet("/myinfo_cancel.do")
public class MyinfoCancelController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		if(request.getSession().getAttribute("id") == null) {
	        response.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
		int no = Integer.parseInt(request.getParameter("no"));		
		String status = request.getParameter("status");
		MyinfoDAO mdao = new MyinfoDAO();
		mdao.myinfoCancelOngoing(no, status);		
		response.sendRedirect("myinfo.do?type=guest");
	 }
	  
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}
