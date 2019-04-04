package com.epms.Controller.Board_fr;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.epms.Model.FreeBoard.FreeBoardReplyDAO;

@WebServlet("/freeboard_replyedit.do")
public class FreeBoardReplyEditController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  	response.setContentType("text/html;charset=UTF-8");
	  	request.setCharacterEncoding("UTF-8");   //한글코드 Encoding
	  	if(request.getSession().getAttribute("id") == null) {
	        response.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
	  	int rfrno = Integer.parseInt(request.getParameter("no"));
	  	String content = request.getParameter("content");
	  	
	  	
	  	// 댓글 번호와 댓글 변경 내용을 db에 update 시키는 작업
	  	FreeBoardReplyDAO fbrdao = new FreeBoardReplyDAO();
	    fbrdao.freeBoardReplyEditUpdate(rfrno, content);
		
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}