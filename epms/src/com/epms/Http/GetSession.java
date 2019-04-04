package com.epms.Http;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.epms.Model.User.Account_DAO;

@WebServlet("/getsession.do")
public class GetSession extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String id = (String)session.getAttribute("id");
		
		Account_DAO dao = new Account_DAO();
		JSONArray array = null;
		if(id != null && !id.equals("")) {
			array = dao.accountInfo(id);
			
		}
		JSONObject json = new JSONObject();
		System.out.println(id);
		json.put("session", id);
		json.put("user", array);
		PrintWriter out = response.getWriter();
		out.print(json);
	 }
	  
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doUser(request,response);}
}
