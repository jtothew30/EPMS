package com.epms.Controller.User;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.json.simple.JSONObject;

import com.epms.Filter.FlowFilter;
import com.epms.Model.User.Account_Bean;
import com.epms.Model.User.Account_DAO;


@WebServlet("/accountInsert.do")
public class InserrtController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    
    protected void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String id = request.getParameter("id");
    	String pw = request.getParameter("pw1");
    	String name = request.getParameter("name");
    	String num1 = request.getParameter("num1");
    	String num2 = request.getParameter("num2");
    	String num3 = request.getParameter("num3");
    	String phone = num1+"-"+num2+"-"+num3;
    	String code = request.getParameter("code");
    	String addr1 = request.getParameter("addr1");
    	String addr2 = request.getParameter("addr2");
    	String email = request.getParameter("email");
    	
    	//System.out.println("id: "+id+" /pw:"+pw+" /phone: "+phone+" /addr1: "+addr1+" /addr2: "+addr2+" /email: "+email);
    	
    	Account_Bean bean = new Account_Bean();
    	Account_DAO dao = new Account_DAO();
    	
    	bean.setId(id);
    	bean.setPw(pw);
    	bean.setName(name);
    	bean.setPhone(phone);
    	bean.setCode(code);
    	bean.setAddr1(addr1);
    	bean.setAddr2(addr2);
    	bean.setEmail(email);
    	
    	boolean result =  dao.epmsInsert(bean);
    	
    	PrintWriter out = response.getWriter();
    	JSONObject json = new JSONObject();
    	json.put("result", result);
    	out.print(json);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doUser(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doUser(request, response);
	}

}
