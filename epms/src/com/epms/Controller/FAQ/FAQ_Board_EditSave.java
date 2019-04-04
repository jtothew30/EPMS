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

@WebServlet("/FAQEditSave.do")
public class FAQ_Board_EditSave extends HttpServlet{
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
		int id = Integer.parseInt(req.getParameter("idx"));
		bean = dao.getDetail(id);		
		bean.setTitle(req.getParameter("title"));
		bean.setContent(req.getParameter("content"));
		//test용
		if(req.getParameter("group")!=null) {
			bean.setGroup(Integer.parseInt(req.getParameter("group")));		
		}
		if(dao.updateFAQ(bean)) {			
			resp.sendRedirect("FAQList.do");
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
