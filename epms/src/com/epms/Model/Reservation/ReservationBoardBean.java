package com.epms.Model.Reservation;

public class ReservationBoardBean {
	private String pa_si;
	private String pa_gu;
	private String pa_address;
	private Double pa_lat;
	private Double pa_lon;
	private int boardCount;
	

	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	public String getPa_si() {
		return pa_si;
	}
	public void setPa_si(String pa_si) {
		this.pa_si = pa_si;
	}
	public String getPa_gu() {
		return pa_gu;
	}
	public void setPa_gu(String pa_gu) {
		this.pa_gu = pa_gu;
	}
	public String getPa_address() {
		return pa_address;
	}
	public void setPa_address(String pa_address) {
		this.pa_address = pa_address;
	}
	public Double getPa_lat() {
		return pa_lat;
	}
	public void setPa_lat(Double pa_lat) {
		this.pa_lat = pa_lat;
	}
	public Double getPa_lon() {
		return pa_lon;
	}
	public void setPa_lon(Double pa_lon) {
		this.pa_lon = pa_lon;
	}
}
