package com.epms.Controller.Admin.parking;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epms.Model.Admin.Parking.ParkingP_Bean;
import com.epms.Model.Admin.Parking.Parking_area_DAO;

@WebServlet("/parkingPDetail.do")
public class parkingP_Detail extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		if(req.getSession().getAttribute("id") == null) {
	        resp.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
		
		PrintWriter out = resp.getWriter();
		Parking_area_DAO dao = new Parking_area_DAO();
		String address=req.getParameter("idx");
		System.out.println("parkingP_Detail / " + address );
		ArrayList<ParkingP_Bean> list = dao.getDetailAreaPList(address);	
		
		
		req.setAttribute("address", address);
		req.setAttribute("bean", list);
		HttpSession session = req.getSession();
		System.out.println(list.size());
		RequestDispatcher dis = req.getRequestDispatcher("/admin/parking/parkingDetail.jsp");
		dis.forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doUser(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doUser(req, resp);
	}
}
