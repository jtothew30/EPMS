package com.epms.Controller.Admin.reservation;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epms.Model.Admin.Reservation.Reservation_Bean;
import com.epms.Model.Admin.Reservation.Reservation_DAO;

@WebServlet("/ReservationEdit.do")
public class reservation_Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		if(req.getSession().getAttribute("id") == null) {
	        resp.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
		Reservation_DAO dao = new Reservation_DAO();
		Reservation_Bean bean = null;
		if (req.getParameter("idx") != null) {
			int idx = Integer.parseInt(req.getParameter("idx"));
			bean = dao.getDetail(idx);

			req.setAttribute("bean", bean);
			RequestDispatcher dis = req.getRequestDispatcher("/reservation/resEdit.jsp");
			dis.forward(req, resp);
		}else {
			bean = new Reservation_Bean();
			bean.setNo(Integer.parseInt(req.getParameter("no")));
			bean.setGuest(req.getParameter("guest"));
			bean.setHost(req.getParameter("host"));
			bean.setAddress(req.getParameter("address"));
			bean.setArea(req.getParameter("area"));
			bean.setContent(req.getParameter("content"));
			bean.setRequest(req.getParameter("request"));
			bean.setTo(req.getParameter("to"));
			bean.setFrom(req.getParameter("from"));
			bean.setAgree(req.getParameter("agree"));
			bean.setStatusdate(req.getParameter("statusdate"));
			bean.setReadcheck(req.getParameter("readcheck"));
			bean.setStatus(req.getParameter("status"));
			
			if(dao.reservationUpdate(bean)) {
				resp.sendRedirect("/EPMS/Reservation.do");
			}else {
				System.out.println("res edit error");
			}
		}
	}
	// 이건 doGet, doPost 나눠서 작성하는게 낫것네
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doUser(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doUser(req, resp);
	}
}
