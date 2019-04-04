package com.epms.Controller.Reservation;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.epms.Model.Reservation.ReservationDAO;

@WebServlet("/reservation_MessageRead.do")
public class ReservationMessageReadController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	if(request.getSession().getAttribute("id") == null) {
        response.sendRedirect("./template/pages/samples/500.html");
        return;
    }
	String no = request.getParameter("data"); 
	ReservationDAO rdao=new ReservationDAO();
	
	String type = rdao.checkUserType(Integer.valueOf(no), (String)request.getSession().getAttribute("id"));	
	rdao.reservationMessageRead(Integer.valueOf(no), type);
  }
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}

