package com.epms.Controller.FAQ;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epms.Model.FAQ.FAQ_Bean;
import com.epms.Model.FAQ.FAQ_DAO;

@WebServlet("/FAQInsert.do")
public class FAQ_Board_Insert extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		if(req.getSession().getAttribute("id") == null) {
	        resp.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
		PrintWriter out = resp.getWriter();
		FAQ_DAO dao = new FAQ_DAO();
		FAQ_Bean bean = null;
		String no = req.getParameter("idx");
		if(no == null) {
			// 새글쓰기 -> 입력페이지
			System.out.println("새글쓰기");			
			//resp.sendRedirect("FAQ/FAQ_InsertNew.jsp");
			bean = new FAQ_Bean();
			req.setAttribute("bean", bean);
			RequestDispatcher dis = req.getRequestDispatcher("/FAQ/FAQ_Insert.jsp");
			dis.forward(req, resp);				
		}else {
			// 답글쓰기 -> 입력페이지
			System.out.println("답글쓰기");
			int num = Integer.parseInt(no);
			bean = dao.getDetail(num);
			bean.setLevel(bean.getLevel()+1);
			req.setAttribute("bean", bean);
			RequestDispatcher dis = req.getRequestDispatcher("/FAQ/FAQ_Insert.jsp");
			dis.forward(req, resp);			
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
