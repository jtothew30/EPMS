package com.epms.Controller.Reservation;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.epms.Http.HttpUtil;
import com.epms.Model.Reservation.ReservationBean;
import com.epms.Model.Reservation.ReservationDAO;


@WebServlet("/reservation_request.do")
public class ReservationRequestController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	if(request.getSession().getAttribute("id") == null) {
	      response.sendRedirect("./template/pages/samples/500.html");
	      return;
	}
	String from = request.getParameter("from"); // ��������
	String to = request.getParameter("to"); // ��������
	String address = request.getParameter("address"); // �ּ�
	String area = request.getParameter("area"); // ���ּ�
	String message = request.getParameter("message"); // �޼���
	HttpSession session = request.getSession();
	String guest = (String)session.getAttribute("id");
	
	
	System.out.println(from + "/ " + to + " / " + address + " / " + area + " / " + message);
	ReservationDAO rdao=new ReservationDAO(); 
	String host = rdao.reservationSearchHost(area, address);
	System.out.println(guest + " / " + host);
	
	boolean result = rdao.reservationInsert(guest, host, from, to, address, area, message); // �뿩 ���� insert
	JSONObject json = new JSONObject();
	json.put("result", result);
	PrintWriter out = response.getWriter();
	out.print(json);
  }
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}



