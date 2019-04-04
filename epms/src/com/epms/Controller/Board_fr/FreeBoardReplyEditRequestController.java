package com.epms.Controller.Board_fr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epms.Http.HttpUtil;
import com.epms.Model.FreeBoard.FreeBoardReplyBean;
import com.epms.Model.FreeBoard.FreeBoardReplyDAO;

@WebServlet("/freeboard_replyeditrequest.do")
public class FreeBoardReplyEditRequestController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  	response.setContentType("text/html;charset=UTF-8");
	  	request.setCharacterEncoding("UTF-8");   //한글코드 Encoding
	  	if(request.getSession().getAttribute("id") == null) {
	        response.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
	  	int data = Integer.parseInt(request.getParameter("rfr_no"));
	  	System.out.println("data "+ data);
	  	
	  	// 댓글 넘버(rfr_no)에 해당하는 댓글정보 받아오기
	  	FreeBoardReplyDAO fbrdao = new FreeBoardReplyDAO();
	  	FreeBoardReplyBean fbrb = fbrdao.freeBoardReplyEditRequest(data);
	  	System.out.println("rfr_no getRfr_content : "+ fbrb.getRfr_content());
	  	request.setAttribute("fbrb", fbrb);
	  	HttpUtil.forward(request,response,"freeboard/freeboard_replyEdit.jsp");
		
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}