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

@WebServlet("/ReservationInsertSave.do")
public class reservation_InsertSave extends HttpServlet{
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
		Reservation_Bean bean = new Reservation_Bean();
		bean.setNo(0);
		bean.setGuest(req.getParameter("guest"));
		bean.setHost(req.getParameter("host"));
		bean.setAddress(req.getParameter("address"));
		bean.setArea(req.getParameter("area"));
		bean.setContent(req.getParameter("content"));
		bean.setFrom(req.getParameter("from"));
		bean.setTo(req.getParameter("to"));
		bean.setRequest(req.getParameter("request"));
		bean.setAgree(req.getParameter("agree"));
		bean.setStatusdate(req.getParameter("statusdate"));
		bean.setReadcheck(req.getParameter("readcheck"));
		bean.setStatus(req.getParameter("status"));
		
		if(dao.reservationInsert(bean)) {
			resp.sendRedirect("/EPMS/Reservation.do");
		}else {
			System.out.println("reservation insert error");
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
