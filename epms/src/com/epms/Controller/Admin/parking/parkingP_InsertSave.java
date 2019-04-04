package com.epms.Controller.Admin.parking;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.epms.Http.HttpUtil;
import com.epms.Model.Admin.Parking.ParkingP_Bean;
import com.epms.Model.Admin.Parking.Parking_area_DAO;

@WebServlet("/parkingPInsertSave.do")
public class parkingP_InsertSave extends HttpServlet{
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
		Parking_area_DAO dao = new Parking_area_DAO();
		ParkingP_Bean bean = new ParkingP_Bean();
		
		bean.setAddress(req.getParameter("addr"));
		bean.setArea(req.getParameter("area"));
		bean.setHost(req.getParameter("host"));
		
		
		boolean result = dao.parkingPInsert(bean);
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
