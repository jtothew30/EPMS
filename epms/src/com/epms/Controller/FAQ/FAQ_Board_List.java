package com.epms.Controller.FAQ;

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

import com.epms.Model.FAQ.FAQ_Bean;
import com.epms.Model.FAQ.FAQ_DAO;

@WebServlet("/FAQList.do")
public class FAQ_Board_List extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		int spage = 1;
		String page = req.getParameter("page");
		if(page !=null && !page.equals("")) {
			spage = Integer.parseInt(page);
		}
		
		String skey = req.getParameter("skey");
		String sval = req.getParameter("sval");
		if(skey==null || skey=="" || sval==null || sval==""){
			  skey="0"; sval="";
		  }
		
		HashMap<String, Object> list = new HashMap<String, Object>();
		list.put("skey", skey);
		list.put("sval", sval);
		
		
		
		FAQ_DAO dao = new FAQ_DAO();	
		int listCount = dao.getFAQListCount(list);
		
		int maxPage = (int)(listCount/10.0+0.9);
		if(maxPage==0) maxPage = 1;
		if(spage > maxPage) spage = maxPage;
		list.put("start",spage*10-9);
		
		ArrayList<FAQ_Bean> qlist = dao.getFAQList(list);
		ArrayList<Integer> rlist = new ArrayList<Integer>();
		for (FAQ_Bean bean : qlist) {
			if(bean.getParent() == 0) rlist.add(dao.getRe(bean.getGroup()));
			else rlist.add(0);
		}
		
		int startPage = (int)(spage/5.0 + 0.8) * 5 - 4;
		int endPage = startPage +4;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		int e=listCount-(spage-1)*10;
		int s=(e-9<=0)?1:(e-9);
		ArrayList<Integer> status = new ArrayList<Integer>();
		for (int i = e; i >= s; i--) {
			status.add(i);
		}
		req.setAttribute("arr",status);
		req.setAttribute("listCount",listCount);
		req.setAttribute("skey", skey);
		req.setAttribute("sval", sval);
		req.setAttribute("spage", spage);
		req.setAttribute("maxPage", maxPage);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		
		req.setAttribute("rlist", rlist);
		req.setAttribute("bean", qlist);
		
		RequestDispatcher dis = req.getRequestDispatcher("/FAQ/FAQ_List.jsp");
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
