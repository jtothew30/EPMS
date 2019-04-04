package com.epms.Controller.FAQ;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.epms.Model.FAQ.FAQ_Bean;
import com.epms.Model.FAQ.FAQ_DAO;

@WebServlet("/FAQInsertSave.do")
public class FAQ_Board_InsertSave extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		if(id == null) {
	        resp.sendRedirect("./template/pages/samples/500.html");
	        return;
	    }
		PrintWriter out = resp.getWriter();
		FAQ_DAO dao = new FAQ_DAO();
		FAQ_Bean bean = new FAQ_Bean();
		/*
		private int no;				//글 번호 -pk
		private String id;			//글 작성자 id
		private String name;		//글작성자 이름

		private String title;		//글 제목
		private String content;		//글 내용
		private int count;			//글 조회수
		private java.sql.Date date; //글 작성날짜
		
		private int level;			//답변글 깊이
		private int parent;			//부모글번호
		
		//추가부분
		private int group;			//글 그룹번호
		private int re_seq;			//답변글 순서
		*/
		bean.setNo(dao.getSeq());
		bean.setId(id);
		bean.setName(id.equals("admin")?"admin":dao.getName(id));
		bean.setTitle(req.getParameter("title"));
		bean.setContent(req.getParameter("content"));
		bean.setCount(0);		
		bean.setLevel(Integer.parseInt(req.getParameter("level")));
		
		bean.setParent(Integer.parseInt(req.getParameter("parent")));
		
		bean.setGroup(Integer.parseInt(req.getParameter("group")));
		
		bean.setRe_seq(Integer.parseInt(req.getParameter("re_seq")));
		
		JSONObject json = new JSONObject();
		if(dao.FAQInsert(bean)){
			json.put("result", "true");
			//resp.sendRedirect("FAQList.do");
		}else {
			json.put("result", "false");
			System.out.println("insert error");
		}
		out.print(json);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doUser(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doUser(req, resp);
	}
}
