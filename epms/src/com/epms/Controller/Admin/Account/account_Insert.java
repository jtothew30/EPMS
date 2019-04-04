package com.epms.Controller.Admin.Account;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epms.Filter.FlowFilter;
import com.epms.Model.Admin.Account.Account_Bean;
import com.epms.Model.Admin.Account.Account_DAO;


@WebServlet("/accountInsertaa.do")
public class account_Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    protected void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();    	   	
    	
		Account_DAO dao = new Account_DAO();
    	Account_Bean bean = new Account_Bean();
    	
    		bean.setId(request.getParameter("id"));
    		bean.setPw(request.getParameter("pw1"));
    		bean.setName(request.getParameter("name"));
    		bean.setPhone(request.getParameter("num1")+"-"+request.getParameter("num2")+"-"+request.getParameter("num3"));
    		bean.setAddr1(request.getParameter("addr1"));
    		bean.setAddr2(request.getParameter("addr2"));
    		bean.setType(request.getParameter("type"));
    		bean.setEmail(request.getParameter("email"));
    		bean.setPoint(0);
    		bean.setArea(request.getParameter("area"));
    		    	 	
    	if(dao.accountInsert(bean)) {
    		// 회원가입 완료 후 처리
    		response.sendRedirect("index.jsp");
    	}else {
    		// 회원가입 에러 후 처리    
    		System.out.println("!!!! db insert error !!!!");
    	}    	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doUser(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doUser(request, response);
	}

}
