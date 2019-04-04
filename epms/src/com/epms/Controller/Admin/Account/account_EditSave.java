package com.epms.Controller.Admin.Account;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.epms.Model.Admin.Account.Account_Bean;
import com.epms.Model.Admin.Account.Account_DAO;

@WebServlet("/accountEditSave.do")
public class account_EditSave extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		
		if(req.getSession().getAttribute("id") == null) {
	        resp.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
		
		Account_DAO dao = new Account_DAO();
		Account_Bean bean = null;
		String id = req.getParameter("id");
		bean = dao.getDetail(id);
			bean.setPw(req.getParameter("pw"));
			bean.setName(req.getParameter("name"));
			bean.setPhone(req.getParameter("phone"));
			bean.setAddr1(req.getParameter("addr1"));
			bean.setAddr2(req.getParameter("addr2"));
			bean.setEmail(req.getParameter("email"));
			if(req.getParameter("type")!=null)
			bean.setType(req.getParameter("type"));
			if(req.getParameter("area")!=null)
			bean.setArea(req.getParameter("area"));
			if(req.getParameter("point")!=null)
			bean.setPoint(Integer.parseInt(req.getParameter("point")));		
		
		boolean result = dao.accountEdit(bean);			
		
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
