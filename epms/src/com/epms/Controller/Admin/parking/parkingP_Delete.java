package com.epms.Controller.Admin.parking;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epms.Http.HttpUtil;
import com.epms.Model.Admin.Parking.Parking_area_Bean;
import com.epms.Model.Admin.Parking.Parking_area_DAO;

@WebServlet("/parkingP_Delete.do")
public class parkingP_Delete extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("UTF-8");
		
		if(req.getSession().getAttribute("id") == null) {
	        resp.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
		
		PrintWriter out = resp.getWriter();
		
		
		String area =req.getParameter("area");
		String addr = req.getParameter("addr");
		Parking_area_DAO dao = new Parking_area_DAO();
		
		System.out.println(addr + " del");
		if(dao.deleteParkingP(area)) {
			HttpUtil.forward(req, resp, "parkingPDetail.do?idx="+addr);
		}else {
			System.out.println("parking delete ERROR");
		}
		
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
