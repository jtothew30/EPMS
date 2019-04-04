package com.epms.Controller.Board_fr;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epms.Http.HttpUtil;
import com.epms.Model.FreeBoard.FreeBoardBean;
import com.epms.Model.FreeBoard.FreeBoardDAO;

@WebServlet("/freeboard_edit.do")
public class FreeBoardEditController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  	if(request.getSession().getAttribute("id") == null) {
	        response.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
	  	int data = Integer.parseInt(request.getParameter("idx"));
	  	String title = request.getParameter("fr_title");
	  	String content = request.getParameter("fr_content");
	  	
	  	FreeBoardDAO fbdao = new FreeBoardDAO();
	    fbdao.freeBoardEditUpdate(data, title, content);
	  	response.sendRedirect("freeboard_detail.do?fr_no="+data);
		
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}