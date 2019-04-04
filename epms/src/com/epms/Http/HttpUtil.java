package com.epms.Http;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtil {
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) {
		try {
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println("RequestDispatcher 오류");
		}
	}
	// HttpUtil.forward(request, response, path) 로 바로 호출해서 사용하면 됨. RequestDispatcher 객체 생성 필요없음.
}
