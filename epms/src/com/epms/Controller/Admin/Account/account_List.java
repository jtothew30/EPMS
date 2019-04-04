package com.epms.Controller.Admin.Account;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epms.Model.Admin.Account.Account_Bean;
import com.epms.Model.Admin.Account.Account_DAO;

@WebServlet("/accountList.do")
public class account_List extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		if(req.getSession().getAttribute("id") == null) {
	        resp.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
		
		int spage = 1;
		String page = req.getParameter("page");
		if(page !=null && !page.equals("")) {
			spage = Integer.parseInt(page);
		}
		
		String skey = req.getParameter("skey");
		String sval = req.getParameter("sval");
		
		HashMap<String, Object> list = new HashMap<String, Object>();
		list.put("skey", skey);
		list.put("sval", sval);
		
		Account_DAO dao = new Account_DAO();	
		int listCount = dao.getAccountListCount(list);
		
		int maxPage = (int)(listCount/10.0+0.9);
		if(maxPage==0) maxPage = 1;
		if(spage > maxPage) spage = maxPage;
		list.put("start",spage*10-9);
		
		ArrayList<Account_Bean> qlist = dao.getAcountList(list);
		
		int startPage = (int)(spage/5.0 + 0.8) * 5 - 4;
		int endPage = startPage +4;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		req.setAttribute("listCount",listCount);
		req.setAttribute("skey", skey);
		req.setAttribute("sval", sval);
		req.setAttribute("spage", spage);
		req.setAttribute("maxPage", maxPage);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("bean", qlist);
		
		RequestDispatcher dis = req.getRequestDispatcher("/admin/account/accountList.jsp");
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
