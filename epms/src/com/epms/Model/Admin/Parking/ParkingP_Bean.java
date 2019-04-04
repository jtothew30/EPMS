package com.epms.Model.Admin.Parking;

import java.io.Serializable;

public class ParkingP_Bean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String address;
	private String area;
	private String host;
	
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
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
}
