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
import com.epms.Model.FreeBoard.FreeBoardReplyDAO;

@WebServlet("/freeboard_replydelete.do")
public class FreeBoardReplyDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  
	public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");   //한글코드 Encoding
	    if(request.getSession().getAttribute("id") == null) {
	        response.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
		int rfrBoardNo=Integer.parseInt(request.getParameter("rfr_no"));
		System.out.println("reply no : " + rfrBoardNo);  
		FreeBoardReplyDAO dao = new FreeBoardReplyDAO();
		dao.freeBoardReplyDelete(rfrBoardNo);
			
	
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}
