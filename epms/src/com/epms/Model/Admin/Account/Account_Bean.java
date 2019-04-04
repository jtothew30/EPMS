package com.epms.Model.Admin.Account;

public class Account_Bean {
	/*
	create table account(
	ac_id varchar2(20) not null,
	ac_pw varchar2(20) not null,
	ac_name varchar2(15) not null,
	ac_phone varchar2(20) not null,
	ac_addr1 varchar2(100) not null,
	ac_addr2 varchar2(100) not null,
	ac_email varchar2(30) not null,
	ac_date date not null,
	ac_type varchar2(10) null,
	ac_point number default 0,
	ac_area varchar2(20) null,
	primary key(ac_id)
	);
	*/
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String addr1;
	private String addr2;
	private String email;
	private java.util.Date date;
	private String type = "false";
	private int point = 0;
	private String area = "default";
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	
	

}
