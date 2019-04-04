package com.epms.Model.Admin.Reservation;

import java.io.Serializable;
public class Reservation_Bean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int no;
	private String guest;
	private String host;
	private String address;
	private String area;
	private String content;
	private String from;
	private String to;
	private String request;
	private String agree;
	private String statusdate;
	private String readcheck;
	private String status;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getAgree() {
		return agree;
	}
	public void setAgree(String agree) {
		this.agree = agree;
	}
	public String getStatusdate() {
		return statusdate;
	}
	public void setStatusdate(String statusdate) {
		this.statusdate = statusdate;
	}
	public String getReadcheck() {
		return readcheck;
	}
	public void setReadcheck(String readcheck) {
		this.readcheck = readcheck;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
		
}
