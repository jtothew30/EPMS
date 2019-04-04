package com.epms.Http;

import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

import com.epms.Model.User.Login_DAO;
  
@ServerEndpoint("/websocket")
public class websocket {
    /***
     * 웹 소켓이 연결되면 호출되는 이벤트
     */
	static Date startDate;
	String id = null;
    @OnOpen
    public void handleOpen(){
    	startDate = new Date();
    	System.out.println(startDate);
        System.out.println("client is now connected...");
    }
    /**
     * 웹 소켓으로부터 메시지가 오면 호출되는 이벤트
     * @param message
     * @return
     */
    @OnMessage
    public void handleMessage(String message){
        id = message;
        System.out.println("server : " + id);
    }
    /**
     * 웹 소켓이 닫히면 호출되는 이벤트
     */
    @OnClose
    public void handleClose(){
    	startDate = null;
    	Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					if(startDate == null) {
						Login_DAO dao = new Login_DAO();
						dao.logOut(id);
						System.out.println("logout / " + id);
					}
				} catch(Exception e) {}
			}
		});
    	th.start();
        System.out.println("client is now disconnected...");
    }
    /**
     * 웹 소켓이 에러가 나면 호출되는 이벤트
     * @param t
     */
    @OnError
    public void handleError(Throwable t){
    	System.out.println("error");
    }
}