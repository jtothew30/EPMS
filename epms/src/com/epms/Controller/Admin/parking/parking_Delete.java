package com.epms.Controller.Admin.parking;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epms.Model.Admin.Parking.Parking_area_Bean;
import com.epms.Model.Admin.Parking.Parking_area_DAO;

@WebServlet("/parkingDelete.do")
public class parking_Delete extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		if(req.getSession().getAttribute("id") == null) {
	        resp.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
		
		Parking_area_DAO dao = new Parking_area_DAO();
		Parking_area_Bean bean = null;
		String id =req.getParameter("id");
		
		if(dao.deleteParking(id)) {
			resp.sendRedirect("Parking.do");
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
