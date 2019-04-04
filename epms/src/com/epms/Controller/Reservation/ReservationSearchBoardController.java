package com.epms.Controller.Reservation;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epms.Http.HttpUtil;
import com.epms.Model.Reservation.ReservationBoardBean;
import com.epms.Model.Reservation.ReservationBoardDAO;

@WebServlet("/reservation_searchboard.do")
public class ReservationSearchBoardController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String area = request.getParameter("area");
	if(area == null) {
		area = "";
	}
	String search = request.getParameter("search");
	if(search == null) {
		search = "";
	} 
	
	// 페이징
	String num = request.getParameter("pageNum");
	if(num == null) {
		num = "1";
	}
	int pageNum = Integer.valueOf(num);
	int startPage = (pageNum -1)/5 * 5 +1;
	int endPage = startPage +4;
	int start = (pageNum-1) * 5 +1;
	int end = start + 4;
	
	ReservationBoardDAO dao = new ReservationBoardDAO();
	ArrayList<ReservationBoardBean> list = dao.reservationBoard_Select(area,search,start,end); // 대여 정보 insert
	System.out.println(list.size());
	
	int total = list.size() ==0 ? 0 : list.get(0).getBoardCount();
	int pageCount = total%5 == 0? total/5 : total/5 +1;
	if(endPage>pageCount) {
		endPage = pageCount;
	}
	request.setAttribute("search", search);
	request.setAttribute("area", area);
	request.setAttribute("boardlist", list);
	request.setAttribute("pageNum", pageNum);
	request.setAttribute("startPage", startPage);
	request.setAttribute("endPage", endPage);
	request.setAttribute("pageCount", pageCount);
	System.out.println(pageNum + " / " + startPage + " / " + endPage + " / " + pageCount);
	if(area.equals("")) area = "kr"; else if(area.contains("서울특별시")) area = "seoul"; else if(area.contains("강원도")) area = "gangwon"; else area = "kr";
	System.out.println(area);
    HttpUtil.forward(request,response,"reservation/reservation_"+area+".jsp");//예약 완료 안내 페이지로
  }
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}
