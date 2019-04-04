
package com.epms.Controller.Board_fr;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.epms.Http.HttpUtil;
import com.epms.Model.FreeBoard.FreeBoardBean;
import com.epms.Model.FreeBoard.FreeBoardDAO;

@WebServlet("/freeboard_list.do")
public class FreeBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 int start, end ;
		 int pagecount;
		 int pageNUM ;  
		 int startpage, endpage;
		 String pnum;
		 int temp;
		 int num;
		 
		 String skey, sval;
		 String returnpage;
		 String AA="",BB="";
		 
		  skey=request.getParameter("keyfield"); //�˻��ʵ�
		  sval=request.getParameter("keyword"); //�˻�Ű����
		  if(skey==null || skey=="" || sval==null || sval==""){
			  skey="fr_title"; sval="";
		  }
		  
		  if(skey.equals("fr_name")) { AA=skey; }
		  else if(skey.equals("fr_title")) { BB=skey; }
		   
		  returnpage="&keyfield="+skey+"&keyword="+sval ;
		  
		   pnum=request.getParameter("pageNum");
		   if(pnum==null || pnum==""){ pnum="1"; }
		   pageNUM=Integer.parseInt(pnum); 
		   
		    
		     
		     FreeBoardDAO fbdao = new FreeBoardDAO();
		     
		     int Gtotal=fbdao.freeBoardCount();
		     int GSearchTotal = fbdao.freeBoardCountSearch(skey, sval);
		     if(GSearchTotal%10==0){ pagecount=GSearchTotal/10; } 
		 	 else { pagecount=(GSearchTotal/10)+1; }
		     end=GSearchTotal-(pageNUM-1)*10 ; 
		     start=end-9;
		   
		     temp=(pageNUM-1)%10; 
		     startpage=pageNUM-temp;
		     endpage=startpage+9; 
		     if(endpage>pagecount){ endpage=pagecount;}
		     
		     ArrayList<FreeBoardBean> LG = fbdao.freeBoardSearch(start, end, skey, sval);
		    
		     request.setAttribute("naver", LG);
		 	 request.setAttribute("Gtotal", Gtotal);
		 	 request.setAttribute("GSearchTotal", GSearchTotal);
		  	 request.setAttribute("startpage", startpage);
		 	 request.setAttribute("endpage", endpage);
		 	 request.setAttribute("pageNUM", pageNUM);
		 	 request.setAttribute("pagecount", pagecount);
		 	 request.setAttribute("returnpage", returnpage);
		 	 request.setAttribute("AA", AA);
		 	 request.setAttribute("BB", BB);
		 	 request.setAttribute("skey", skey);
		 	 request.setAttribute("sval", sval);
		 	 
		 	 HttpUtil.forward(request,response,"freeboard/freeboard_list.jsp");
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
	
}
