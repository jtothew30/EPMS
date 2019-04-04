<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 로그 페이지</title>
<script type="text/javascript" src="/EPMS/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
var path1 = 'ReservationEdit.do?idx=';
var address = null;
var path2 = 'areaCheck.do';
var loadHtml = function(path, callback) {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', path, true);
	xhr.onreadystatechange = function() {
		if (this.readyState !== 4)
			return;
		if (this.status !== 200)
			return;
		callback(this.responseText);
	};
	xhr.send();
	console.log("1");
}
var displayHtml = function(file) {
	loadHtml(path1 + file, function(html) {
		document.querySelector("#list").innerHTML = html;
	});
	address = file;
	return false;
}	

</script>
</head>
<body>
	<table id="reservation" class="table" width="1200" border="3" bordercolor="lightgray">
		<tr height="30">
			<form action="Reservation.do" method="get">
				<select name="skey">
					<option value="0" <c:if test="${skey == 0}">selected</c:if>>Guest</option>
					<option value="1" <c:if test="${skey == 1}">selected</c:if>>Host</option>
					<option value="2" <c:if test="${skey == 2}">selected</c:if>>Address</option>
				</select> <input type="text" name="sval" value="${sval}" /> <input
					type="submit" value="검색">
				<div id="listCount" align="center">총 검색된 자료수:${listCount}</div>
			</form>
		</tr>
		<tr>
			<th>No</th>
			<th>Guest</th>
			<th>Host</th>
			<th>Address</th>
			<th>Area</th>
			<th>Content</th>
			<th>From</th>
			<th>To</th>
			<th>Request</th>
			<th>Agree</th>
			<th>StatusDate</th>
			<th>ReadCheck</th>
			<th>Status</th>
			<th>edit</th>
			<th>delete</th>
		</tr>
		<c:forEach var="bean" items="${bean}">
			<tr>
				<td>${bean.no}</td>
				<td>${bean.guest}</td>
				<td>${bean.host}</td>
				<td>${bean.address}</td>
				<td>${bean.area}</td>
				<td>${bean.content}</td> 
				<td>${bean.from}</td>
				<td>${bean.to}</td>
				<td>${bean.request}</td>
				<td>${bean.agree}</td>
				<td>${bean.statusdate}</td>
				<td>${bean.readcheck}</td>
				<td>${bean.status}</td>
				<td><a href="#!${bean.no}" onclick="displayHtml('${bean.no}');">edit</a></td>				
				<td><a href="ReservationDelete.do?idx=${bean.no}&page=${spage}">del</a></td>
			</tr>
		</c:forEach>
	</table>
	
	<c:if test="${startPage != 1}">
		<a href='Reservation.do?page=${startPage-1}'>[이전]</a>
	</c:if>
	<c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
		<c:if test="${pageNum == spage}">
			${pageNum}&nbsp;
		</c:if>
		<c:if test="${pageNum != spage && sval == null}">
			<a href='Reservation.do?page=${pageNum}'>${pageNum}&nbsp;</a>
		</c:if>
		<c:if test="${pageNum != spage && sval != null}">
			<a href='Reservation.do?page=${pageNum}&skey=${skey}&sval=${sval}'>${pageNum}&nbsp;</a>
		</c:if>
	</c:forEach>

	<c:if test="${endPage != maxPage }">
		<a href='Reservation.do?page=${endPage+1}'>[다음]</a>
	</c:if>
	<br>
	<a href="reservation/resInsert.jsp">새 예약 등록</a>
	<br>
	<a href="/EPMS/index.jsp">index페이지</a>
	<div id="list"></div>
</body>
</html>