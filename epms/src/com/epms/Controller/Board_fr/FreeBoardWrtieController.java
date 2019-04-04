package com.epms.Controller.Board_fr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.epms.Model.FreeBoard.FreeBoardBean;
import com.epms.Model.FreeBoard.FreeBoardDAO;

@WebServlet("/freeboard_write.do")
public class FreeBoardWrtieController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  	request.setCharacterEncoding("UTF-8");
	  	HttpSession session = request.getSession();
	  	String id = (String)session.getAttribute("id");
	  	if(id == null) {
	        response.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
	  	String a = request.getParameter("title");
		String b = request.getParameter("content");
		System.out.println(a+"/"+b);
		FreeBoardDAO fbdao = new FreeBoardDAO();
		FreeBoardBean fbb = new FreeBoardBean();
		
			fbb.setFr_title(a);
			fbb.setFr_content(b);
			fbb.setFr_id(id);
			fbb.setFr_name("gg");
			boolean result = fbdao.freeBoardInsert(fbb);
			JSONObject json = new JSONObject();
			json.put("result", result);
			PrintWriter out = response.getWriter();
			out.print(json);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}