package com.epms.Controller.Admin.reservation;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epms.Model.Admin.Reservation.Reservation_Bean;
import com.epms.Model.Admin.Reservation.Reservation_DAO;

@WebServlet("/ReservationDelete.do")
public class reservation_Delete extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		if(req.getSession().getAttribute("id") == null) {
	        resp.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
		PrintWriter out = resp.getWriter();
		
		Reservation_DAO dao = new Reservation_DAO();
		Reservation_Bean bean = null;
		int idx=Integer.parseInt(req.getParameter("idx"));
		String page = req.getParameter("page");
		if(dao.reservationDelete(idx)) {
			resp.sendRedirect("Reservation.do?page="+Integer.parseInt(page));
		}else {
			System.out.println("reservation delete ERROR");
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
