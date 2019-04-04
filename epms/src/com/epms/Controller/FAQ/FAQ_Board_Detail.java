package com.epms.Controller.FAQ;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epms.Model.FAQ.FAQ_Bean;
import com.epms.Model.FAQ.FAQ_DAO;

@WebServlet("/FAQdetail.do")
public class FAQ_Board_Detail extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		FAQ_DAO dao = new FAQ_DAO();
		FAQ_Bean bean = null;
		if(dao.updateCount(Integer.parseInt(req.getParameter("idx")))) {
			 bean = dao.getDetail(Integer.parseInt(req.getParameter("idx")));	
		}		
		req.setAttribute("bean", bean);
		HttpSession session = req.getSession();
		
		RequestDispatcher dis = req.getRequestDispatcher("/FAQ/FAQ_Detail.jsp");
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
