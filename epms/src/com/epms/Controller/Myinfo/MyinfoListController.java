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


@WebServlet("/myinfo.do")
public class MyinfoListController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		HttpSession session = request.getSession();
		String type = request.getParameter("type");  // list type (guest/host/past)
		String id = (String)session.getAttribute("id"); // login user id from session
				
		if(id == null) {
	         response.sendRedirect("./template/pages/samples/500.html");
	         return;
	    }
		
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
		
		MyinfoDAO mdao = new MyinfoDAO();
		int total=0;
		switch(type) {
		default:
			break;
		case "host":
			total = mdao.myinfoHostCount(id); //user's id
			break;
		case "past":
			total = mdao.myinfoPastCount(id); //user's id
			break;
		}
		
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
		
		ArrayList<ReservationBean> rblist = new ArrayList<ReservationBean>();
		
		
		switch(type) {
			default:break;
			case "guest":
				rblist = mdao.myinfoGuestList(id); // user's ongoing reservation list as guest
				request.setAttribute("rblist", rblist);
				request.setAttribute("id", id); // user's id
				HttpUtil.forward(request, response, "myinfo/myinfo_glist.jsp");
				break;
			case "host":
				rblist = mdao.myinfoHostList(id, start, end); // user's ongoing reservation list as host	
				
				request.setAttribute("rblist", rblist);
				request.setAttribute("id", id); // user's id
				request.setAttribute("total", total);
				request.setAttribute("startpage", startpage);
				request.setAttribute("endpage", endpage);
				request.setAttribute("pageNUM", pageNUM); 
				request.setAttribute("pagecount", pagecount); 
				HttpUtil.forward(request, response, "myinfo/myinfo_hlist.jsp");
				break;
			case "past":
				rblist = mdao.myinfoPastList(id, start, end); // user's all past reservation list
				System.out.println("size:"+rblist.size());
				request.setAttribute("rblist", rblist);
				request.setAttribute("id", id); // user's id
				request.setAttribute("total", total);
				request.setAttribute("startpage", startpage);
				request.setAttribute("endpage", endpage);
				request.setAttribute("pageNUM", pageNUM); 
				request.setAttribute("pagecount", pagecount); 
				
				System.out.println("s:"+start+"/e:"+end);
					
				HttpUtil.forward(request, response, "myinfo/myinfo_plist.jsp");
				break;
			}
	 }
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}
