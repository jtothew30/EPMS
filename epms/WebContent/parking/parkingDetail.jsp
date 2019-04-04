<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주차공간정보 자세히보기</title>
</head>
<body>
	<table width="800" border="3" bordercolor="lightgray">
		<tr>
			<th>pap_address</th>
			<th>pap_area</th>
			<th>pap_host</th>
		</tr>
		
		<tr>
			<td>${bean.address}</td>
			<td>${bean.area}</td>
			<td>${bean.host}</td>
		</tr>		
	</table>
	<a href="parkingDelete.do?id=${address}">지역삭제</a>
	<!--a href="parkingPInsert.do?id=${bean.address}">상세입력</a-->
	<a href="#!${address}" onclick="displayHtml1('${address}');">상세입력</a>	
</body>
</html>