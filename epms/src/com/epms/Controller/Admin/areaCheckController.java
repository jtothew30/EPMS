package com.epms.Controller.Admin;

import java.io.IOException;


import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epms.Filter.FlowFilter;
import com.epms.Model.Admin.Parking.Parking_area_DAO;


@WebServlet("/areaCheck.do")
public class areaCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    
    protected void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setCharacterEncoding("UTF-8");		
    	response.setContentType("text/html;charset=UTF-8");
		PrintWriter out =  response.getWriter();
		
    	String area = request.getParameter("area");
    	
    	
   	    System.out.println(area);
   	    
    	Parking_area_DAO dao = new Parking_area_DAO();
    	int result = dao.areaCheck(area);
    	if(result==0) { // 넘어온 area 디비에 없다
    		out.println("<font size=3 color='green'>사용가능한 area입니다.</font>");
    		
    	}else {// 넘어온 area 디비에 있다
    		out.println("<font size=3 color='green'>이미 사용중인 area입니다</font>");
    	}   	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doUser(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doUser(request, response);
	}

}
