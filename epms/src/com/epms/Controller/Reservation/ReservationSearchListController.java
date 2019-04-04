package com.epms.Controller.Reservation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.epms.Model.Reservation.ReservationDAO;

@WebServlet("/searchlist.do")
public class ReservationSearchListController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	  public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  if(request.getSession().getAttribute("id") == null) {
		        response.sendRedirect("./template/pages/samples/500.html");
		        return;
		  }
		  String from = request.getParameter("from");
		  String to = request.getParameter("to");
		  String address =request.getParameter("address");
		  System.out.println(from + " / " + to + " / " + address);
		   ReservationDAO dao = new ReservationDAO();
		   ArrayList<String> list = dao.reservationList(from, to, address);
		   JSONObject jsonObject = new JSONObject();
		   JSONArray jsonArray = new JSONArray();
		   for(String s : list) {
			   System.out.println(s);
			   jsonArray.add(s);
		   }
		   jsonObject.put("list", jsonArray);
		   PrintWriter out = response.getWriter();
		   out.print(jsonObject);
		   
	  }
	  
	  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
	  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}
