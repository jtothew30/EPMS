package com.epms.Controller.Board_fr;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epms.Http.HttpUtil;
import com.epms.Model.FreeBoard.FreeBoardBean;
import com.epms.Model.FreeBoard.FreeBoardDAO;
import com.epms.Model.FreeBoard.FreeBoardReplyBean;
import com.epms.Model.FreeBoard.FreeBoardReplyDAO;

import oracle.net.aso.f;

@WebServlet("/freeboard_detail.do")
public class FreeBoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		int data=Integer.parseInt(request.getParameter("fr_no"));
		
		FreeBoardDAO fbdao = new FreeBoardDAO();
		FreeBoardBean fbb = fbdao.freeBoardDetail(data);
		
		FreeBoardReplyDAO fbrdao=new FreeBoardReplyDAO();
		ArrayList<FreeBoardReplyBean> reply = fbrdao.freeBoardReplyList(data);
		
		fbdao.freeBoardCountUpdate(data);
		request.setAttribute("fbb", fbb);
		request.setAttribute("reply", reply);
		request.setAttribute("session", id);
		HttpUtil.forward(request,response,"freeboard/freeboard_detail.jsp");
	
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}
