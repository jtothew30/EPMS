package com.epms.Controller.Notification;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epms.Http.HttpUtil;
import com.epms.Model.Notification.NotificationDAO;
import com.epms.Model.Notification.NotificationBean;

@WebServlet("/notification_list.do")
public class NotificationListController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int start, end ;  
		int pagecount;  
		int pageNUM ;   
		int startpage, endpage; 
		String pnum;  
		int temp; 
		
		pnum=request.getParameter("pageNum");
		System.out.println("pnum check : "+pnum);
		if(pnum=="" || pnum==null) { pnum="1"; }
		pageNUM=Integer.parseInt(pnum);  
		
		NotificationDAO ndao = new NotificationDAO(); 
		int total = ndao.notificationCount();
		if(total%10==0){ pagecount=total/10; } 
		else { pagecount=(total/10)+1; }
		
		end=total-(pageNUM-1)*10 ; 
		start=end-9;
		
		System.out.println("total:"+total+"/pagecount:"+pagecount);
		System.out.println("s:"+start+"/e:"+end);
		
		temp=(pageNUM-1)%10; 
		startpage=pageNUM-temp; 
		endpage=startpage+9;
		if(endpage>pagecount){endpage=pagecount; }
		
		ArrayList<NotificationBean> nblist =ndao.notificationList(start,end);
		request.setAttribute("nblist", nblist);
		request.setAttribute("total", total);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("pageNUM", pageNUM); 
		request.setAttribute("pagecount", pagecount); 
		
		HttpUtil.forward(request, response, "notification/notification_list.jsp");
	 }
	  
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}
