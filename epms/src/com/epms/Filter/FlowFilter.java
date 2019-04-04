package com.epms.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class FlowFilter implements Filter{
	
	public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
	
        System.out.println("init() ȣ�� ......... one");
    }
	
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // place your code here
    	response.setCharacterEncoding("UTF-8");
    	request.setCharacterEncoding("UTF-8");
    	HttpServletResponse httpresponse = (HttpServletResponse) response;
    	httpresponse.setHeader("X-Frame-Options", "ALLOW-FROM http://nid.naver.com/nidlogin.logout");
        System.out.println("doFilter() ȣ��  �� ......... one");
        // pass the request along the filter chain
        chain.doFilter(request, response);
        System.out.println("doFilter() ȣ��  �� ......... one");
    }
    
    public void destroy() {
        // TODO Auto-generated method stub
        System.out.println("destory () ȣ�� ......... one");
    }
}
