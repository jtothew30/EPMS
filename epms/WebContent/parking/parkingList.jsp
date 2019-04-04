<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Parking</title>
<script src="/EPMS/js/jquery-3.3.1.min.js">
 function areacheck(){
 	var data = $("#areap").val();
 	console.log(data);
 	$.ajax({
 		"url" : "../areaCheck.do",
 		"type": "POST",
 		"data": {area:data},
 		"success": function(data){
 			$('#li').html(data);
 		},
 		"error": function(data){
 			$('#li').fadeOut();
 		}
 	});
 }
</script>
<script type="text/javascript"> 
	var path = 'parkingPDetail.do?idx=';
	var path1 = 'parkingPInsert.do?id=';
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
	}
	var displayHtml = function(file) {
		loadHtml(path + file, function(html) {
			document.querySelector("#input").innerHTML = "";
			document.querySelector("#list").innerHTML = html;
		});
		address = file;
		return false;
	}	
	//-----------------------------------------------------
	var loadHtml1 = function(path1, callback) {
		var xhr1 = new XMLHttpRequest();
		xhr1.open('GET', path1, true);
		xhr1.onreadystatechange = function() {
			if (this.readyState !== 4)
				return;
			if (this.status !== 200)
				return;
			callback(this.responseText);
		};
		xhr1.send();
	}
	var displayHtml1 = function(file1) {
		if (file1 === null) {
			file1 = address;
		}
		loadHtml(path1 + file1, function(html1) {
			document.querySelector("#input").innerHTML = html1;
		});
		return false;
	}	

</script>
</head>
<body>
	<h1>Parking</h1>
	<hr>
	<table id="faq" class="faq" width="800" border="3" bordercolor="lightgray" >
		<tr height="30">
			<form action="Parking.do" method="get">
			<select name="skey">
				<option value="0" <c:if test="${skey == 0}">selected</c:if>>주소</option>
				<option value="1" <c:if test="${skey == 1}">selected</c:if>>구</option>
			</select>
			<input type="text" name="sval" value="${sval}" />
			<input type="submit" value="검색" >
			<div id="listCount" align="center">총 검색된 자료수:${listCount}</div>
			</form>
		</tr>
		<tr>
			<th>Pa_no</th>
			<th>Pa_si</th>
			<th>Pa_gu</th>
			<th>Pa_address</th>
			<th>Pa_lat</th>
			<th>Pa_lon</th>
		</tr>
	<c:forEach var="bean" items="${bean}">
		<tr>
			<td>${bean.no}</td>
			<td>${bean.si}</td>			
			<td>${bean.gu}</td>
			<td id="ad" onclick="displayHtml('${bean.address}');"><a href="#!${bean.address}">${bean.address}</a></td>
			<td>${bean.lat}</td>
			<td>${bean.lon}</td>
		</tr>
	</c:forEach>
	</table>
	 				
	<div id="pageidx" class="pageidx">	
		<a href='Parking.do'>[처음]</a>&nbsp;&nbsp;
		<c:if test="${startPage != 1 && startPage !=null}">
			<a href='Parking.do?page=${startPage-1}'>[이전]</a>
		</c:if>
		<c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
			<c:if test="${pageNum == spage}">
				${pageNum}&nbsp;
			</c:if>
			<c:if test="${pageNum != spage && sval == null}">
				<a href='Parking.do?page=${pageNum}'>${pageNum}&nbsp;</a>
			</c:if>
			<c:if test="${pageNum != spage && sval != null}">
				<a href='Parking.do?page=${pageNum}&skey=${skey}&sval=${sval}'>${pageNum}&nbsp;</a>
			</c:if>
		</c:forEach>
			
		<c:if test="${endPage != maxPage }">
			<a href='Parking.do?page=${endPage+1 }'>[다음]</a>
		</c:if>
		&nbsp;&nbsp;<a href='Parking.do?page=${maxPage}'>[끝]</a>
	</div>
	
	<br>
	<!-- 새글쓰기 조건 -->	 
	<c:if test="${sessionScope.id !=null }">
		<a href="FAQInsert.do">새글쓰기</a>
	</c:if>
		
	<br>	
	<a href="index.jsp">index페이지</a>
	<a href="/EPMS/parking/parkingInsert.jsp">새위치 입력</a>
	<p>
	<div id="list"></div>
	<div id="input"></div>
</body>
</html>