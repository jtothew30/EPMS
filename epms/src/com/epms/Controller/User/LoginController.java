package com.epms.Controller.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.ant.SessionsTask;
import org.json.simple.JSONObject;

import com.epms.Filter.FlowFilter;
import com.epms.Model.User.Account_Bean;
import com.epms.Model.User.Account_DAO;
import com.epms.Model.User.Admin_DAO;
import com.epms.Model.User.Login_DAO;


@WebServlet("/logPro.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
   
	//로그아웃시 GET 으로 받아
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
		PrintWriter out =  response.getWriter();
		System.out.println("test");
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("id") != null) {
			
			String id = (String)session.getAttribute("id");
			Login_DAO dao = new Login_DAO();
			boolean result = dao.logOut(id);
			session.invalidate();
			JSONObject json = new JSONObject();
			System.out.println(true);
			json.put("result", result);
			out.print(json);
		}else {
			JSONObject json = new JSONObject();
			json.put("result", "error");
			out.print(json);
		}
		out.close();
	}

	//로그인시 POST로 받아
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
		PrintWriter out =  response.getWriter();
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String admin = request.getParameter("admin");
		
		String flag = request.getParameter("flag");
		
		System.out.println("id : "+id+" /pw: "+ pw + " /admin: "+admin+" /flag: "+ flag);
		
		Admin_DAO adao = new Admin_DAO();
		Login_DAO ldao = new Login_DAO();
		Account_DAO dao = new Account_DAO();
		
		// check에는 id를 가진 사람의 이름이 들어있어
		/*
		 * 1. admin으로 관리자 구분
		 * 2. admin이 없으면 account 있는지 확인
		 * 3. account 있으면 세션 생성 저장 -> 로그인
		 * 4. account 없으면 회원설정이 안되어 있다는 거니까
		 * 5. flag==1이면 sns 정보가 넘어온 사람 -> login 디비 저장
		 * 6. flag!=1이면 회원가입하지 않은 사람 ->
		 */
		String check = "";
		int count = 0;
		if(admin==null || admin=="") {
			
			check = ldao.accountCheck(id); // id에 해당하는 회원 이름
			count = ldao.acCount(id,pw); // id에 해당하는 회원이 있으면 count==1, 없으면 count==0			
			System.out.println(count);
			if(count == 1) {
				HttpSession session = request.getSession();
				
				if(session.isNew() || session.getAttribute("id")==null) {
					session.setAttribute("id", id);	
					if(ldao.login(id, check)) {
						JSONObject json = new JSONObject();
						json.put("result", "success");
						out.print(json);
					} else {
						JSONObject json = new JSONObject();
						json.put("result", "overlap");
						out.print(json);
					}
					
				}else {// 관리자 x, 회원등록 o, session 이미 생성
					// response.sendRedirect("main.jsp");
					//out.println("현재 로그인 상태입니다<p>");
					//out.println("<a href='../logPro.do'>로그아웃</a>");
				}			
			}else if(count == 0){				
				if(flag==null) {// 관리자 x, 회원등록 x, session 생성 x
					JSONObject json = new JSONObject();
					json.put("result", "error");
					out.print(json);
				
					//out.println("회원이 아니세요.");
					//out.println("<a href='login/login.jsp'>로그인</a>");
				}else if(flag.equals("1")){// 관리자 x, 회원등록 x, session 생성 x, sns 로 로그인한 사람
					Date date = new Date();
					Account_Bean bean = new Account_Bean();
					bean.setId(id);
					bean.setPw(pw);
					bean.setName(pw);
					bean.setPhone("010");
					bean.setAddr1("sns");
					bean.setAddr2("sns");
					bean.setEmail("sns");
					bean.setDate(date);
					
					dao.epmsInsert(bean);
					
					HttpSession session = request.getSession();
					if(session.isNew() || session.getAttribute("id")==null) {
						session.setAttribute("id", id);	
						if(ldao.login(id, pw)) {
							JSONObject json = new JSONObject();
							json.put("result", "success");
							out.print(json);
						} else {
							JSONObject json = new JSONObject();
							json.put("result", "overlap");
							out.print(json);
						}
					}else {// sns 로 로그인 했지만 이미 session 생성 되어 있어서 로그인 상태
						response.sendRedirect("main.jsp");
						JSONObject json = new JSONObject();
						json.put("result", "error");
						out.print(json);
					}
				}
			}
		}else {	// 디비 admin에 관리자 id==good, pw==1234 미리 저장 시킨 후 확인 	
			int chk = adao.adminCheck(id, pw);
			System.out.println("관리자 id:"+id+"/관리자 비밀번호:"+pw);
			System.out.println("chk 관리자 체크: " + chk);
			if(chk != 0) {
				HttpSession session = request.getSession();
				
				if(session.isNew() || session.getAttribute("id")==null) {
					session.setAttribute("id", "admin");	
					if(ldao.login(id, check)) {
						JSONObject json = new JSONObject();
						json.put("result", "success");
						out.print(json);
					} else {
						JSONObject json = new JSONObject();
						json.put("result", "overlap");
						out.print(json);
					}
				}else {
					out.println("현재 관리자 로그인 상태입니다");
				}			
			}else {
				JSONObject json = new JSONObject();
				json.put("result", "error");
				out.print(json);
			}
		}
	}
}
