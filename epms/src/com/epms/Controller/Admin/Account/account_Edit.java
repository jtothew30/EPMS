package com.epms.Controller.Admin.Account;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epms.Model.Admin.Account.Account_Bean;
import com.epms.Model.Admin.Account.Account_DAO;

@WebServlet("/accountEdit.do")
public class account_Edit extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		Account_DAO dao = new Account_DAO();
		Account_Bean bean = null;
		String id = req.getParameter("id");
		System.out.println(id);
		//세션값으로 보안처리
		String sessionid = (String) req.getSession().getAttribute("id");
		System.out.println(sessionid);
		if(sessionid.equals(id) || sessionid.equals("admin")) {		
			bean = dao.getDetail(id);			
			req.setAttribute("bean", bean);
			RequestDispatcher dis = req.getRequestDispatcher("/admin/account/accountEdit.jsp");
			dis.forward(req, resp);		
		}else {
			//에러페이지를 만들꺼면 그쪽으로 전송, 아니라면
			resp.sendRedirect("./template/pages/samples/500.html");
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
