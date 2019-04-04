<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계정정보 상세보기</title>
</head>
<body>
	<h1>계정정보 상세보기</h1>
	<hr>		
	<table border=1>
		<tr>
			<th>ID</th>
			<th>PW</th>
			<th>NAME</th>
			<th>PHONE</th>
			<th>addr1</th>
			<th>addr2</th>
			<th>email</th>
			<th>가입날짜</th>
			<th>타입</th>
			<th>포인트</th>
			<th>주차지역</th>
		</tr>
		<tr>
			<td>${bean.id}</td>
			<td>${bean.pw}</td>
			<td>${bean.name}</td>
			<td>${bean.phone}</td>
			<td>${bean.addr1}</td>
			<td>${bean.addr2}</td>
			<td>${bean.email}</td>
			<td>${bean.date}</td>
			<td>${bean.type}</td>
			<td>${bean.point}</td>
			<td>${bean.area}</td>
		</tr>		
	</table>
	
	<!-- 관리자 혹은 본인조건 -->
	<c:if test="${sessionScope.id == 'admin' || sessionScope.id == bean.id}">
	<a href="accountEdit.do?id=${bean.id}">회원정보수정</a>
	</c:if>
	
	<!-- 관리자 조건 -->
	<c:if test="${sessionScope.id == 'admin'}">
		<a href="accountDelete.do?id=${bean.id}">회원삭제</a>
		<a href="accountList.do">회원목록보기</a>		
	</c:if>
</body>
</html>