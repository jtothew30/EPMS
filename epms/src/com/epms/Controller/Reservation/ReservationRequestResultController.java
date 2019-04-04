package com.epms.Controller.Reservation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epms.Http.HttpUtil;
import com.epms.Model.Reservation.ReservationBean;
import com.epms.Model.Reservation.ReservationDAO;


@WebServlet("/reservation_requestResult.do")
public class ReservationRequestResultController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if(request.getSession().getAttribute("id") == null) {
        response.sendRedirect("./template/pages/samples/500.html");
        return;
    }
	int no = Integer.parseInt(request.getParameter("data"));
	String agree = request.getParameter("agree");
	
	ReservationDAO rdao=new ReservationDAO();
	System.out.println(agree);
	if(agree.equals("false")) { 
		rdao.reservationReject(no);
	}else{
		rdao.reservationAgree(no);
	}
  }
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}



