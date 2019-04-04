package com.epms.Controller.Board_fr;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epms.Http.HttpUtil;
import com.epms.Model.FreeBoard.FreeBoardReplyBean;
import com.epms.Model.FreeBoard.FreeBoardReplyDAO;

@WebServlet("/freeboard_reply.do")
public class FreeBoardReplyController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	  	response.setContentType("text/html;charset=UTF-8");
	  	request.setCharacterEncoding("UTF-8");   //한글코드 Encoding
	  	HttpSession session = request.getSession();
	  	String id = (String)session.getAttribute("id");
	  	if(id == null) {
	        response.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
	  	String content = request.getParameter("content");
	  	int fr_no = Integer.parseInt(request.getParameter("fr_no"));
	  	
	  	System.out.println("rdata-"+fr_no);
	  	
	    FreeBoardReplyDAO fbrdao=new FreeBoardReplyDAO();
	    FreeBoardReplyBean fbrb = new FreeBoardReplyBean();
	    
	    fbrb.setRfr_id(id);
	    fbrb.setRfr_content(content);
	    fbrb.setRfr_board_no(fr_no);
	    fbrdao.freeBoardReplyInsert(fbrb);
	    
	    //response.sendRedirect("freeboard_detail.do?fr_no="+fr_no);
	  }
  	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}