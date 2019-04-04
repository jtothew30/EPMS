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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.epms.Http.HttpUtil;
import com.epms.Model.Reservation.ReservationBean;
import com.epms.Model.Reservation.ReservationDAO;


@WebServlet("/reservation_check.do")
public class ReservationMessageCheckController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	if(request.getSession().getAttribute("id") == null) {
        response.sendRedirect("./template/pages/samples/500.html");
        return;
    }
	String name = request.getParameter("name"); 
	ReservationDAO rdao=new ReservationDAO();
	JSONObject rblist = rdao.reservationCheck(name);
	PrintWriter out = response.getWriter();
	out.print(rblist);
	// ���߿� �޼����� ����� ������ ����
  }
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}



