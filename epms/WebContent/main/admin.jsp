<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
</head>
<body>
	<h1>환영합니다</h1>
	<hr>
	<li><a href="/EPMS/FAQList.do">FAQ페이지</a></li>
	<li><a href="/EPMS/accountList.do">회원관리페이지</a></li>
	<li><a href="/EPMS/Parking.do">주차관리페이지</a></li>
	<li><a href="/EPMS/Reservation.do">예약로그페이지</a></li>
	<li><a href="/EPMS/Logout.do">로그아웃</a></li>
</body>
</html>
<!--
	session
		id	- id (각각)
			- admin
	
	체크값 - 임시
	db => type값으로 수정될 예정 
							-->