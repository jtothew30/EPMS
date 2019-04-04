package com.epms.Controller.Admin.parking;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.epms.Model.Admin.Parking.Parking_area_Bean;
import com.epms.Model.Admin.Parking.Parking_area_DAO;

@WebServlet("/parkingInsert.do")
public class parking_Insert extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		if(req.getSession().getAttribute("id") == null) {
	        resp.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
		PrintWriter out = resp.getWriter();
		Parking_area_DAO dao = new Parking_area_DAO();
		Parking_area_Bean bean = new Parking_area_Bean();
		bean.setNo(0);
		bean.setSi(req.getParameter("si"));
		bean.setGu(req.getParameter("gu"));
		bean.setAddress(req.getParameter("address"));
		bean.setLat(Double.parseDouble(req.getParameter("lat")));
		bean.setLon(Double.parseDouble(req.getParameter("lon")));			
		
		boolean result = dao.parkingInsert(bean); 
		JSONObject json = new JSONObject();
		json.put("result", result);
		out.print(json);
		
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
