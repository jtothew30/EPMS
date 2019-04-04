<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리게시판</title>
</head>
<body>
	<h1>회원관리게시판</h1>
	<hr>
	<table id="account" class="account" width="800" border="3" bordercolor="lightgray" >
		<tr height="30">
			<form action="accountList.do" method="get">
			<select name="skey">
				<option value="0" <c:if test="${skey == 0}">selected</c:if>>ID</option>
				<option value="1" <c:if test="${skey == 1}">selected</c:if>>Name</option>
			</select>
			<input type="text" name="sval" value="${sval}" />
			<input type="submit" value="검색" >
			<div id="listCount" align="center">총 검색된 자료수:${listCount}</div>
			</form>
		</tr>
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
	<c:forEach var="bean" items="${bean}">
		<tr>
			<td><a href='accountDetail.do?id=${bean.id}'>${bean.id}</a></td>			
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
	</c:forEach>
	</table>
	<font>${startPage}</font>
	<c:if test="${startPage != 1}">
		<a href='accountList.do?page=${startPage-1}'>[이전]</a>
	</c:if>
	<c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
		<c:if test="${pageNum == page}">
			${pageNum}&nbsp;
		</c:if>
		<c:if test="${pageNum != page && sval == null}">
			<a href='accountList.do?page=${pageNum}'>${pageNum}&nbsp;</a>
		</c:if>
		<c:if test="${pageNum != page && sval != null}">
			<a href='accountList.do?page=${pageNum}&skey=${skey}&sval=${sval}'>${pageNum}&nbsp;</a>
		</c:if>
	</c:forEach>
		
	<c:if test="${endPage != maxPage }">
		<a href='accountList.do?page=${endPage+1}'>[다음]</a>
	</c:if>
	<br>
	<a href="index.jsp">index페이지</a>
	
</body>
</html>