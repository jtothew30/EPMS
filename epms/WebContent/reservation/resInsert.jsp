<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 글 글쓰기</title>
<script type="text/javascript"  src="/EPMS/js/jquery-3.3.1.min.js">

$("#content").bind('input propertychange', function () {
    var maxLength = 300;
    if ($(this).val().length > maxLength) {
        $(this).val($(this).val().substring(0, maxLength));
    }
});

</script>
</head>
<body>
	<h1 align="center">FAQ 글쓰기</h1>
	
	<form action="/EPMS/ReservationInsertSave.do" name="ResInsert" method="post">				
		<table width="700" border="3" bordercolor="lightgray" align="center">	
			<tr>
				<th>Guest</th>
				<td><input type="text" name="guest"></td>
			</tr>
			<tr>
				<th>Host</th>
				<td><input type="text" name="host"></td>
			</tr>
			<tr>
				<th>Address</th>
				<td><input type="text" name="address"></td>
			</tr>
			<tr>
				<th>Area</th>
				<td><input type="text" name="area"></td>
			</tr>
			<tr>
				<th>Content</th>
				<td><textarea name="content" id="content" rows="6" cols="70" placeholder="내용입력" ></textarea></td>				
			</tr>
			<tr>
				<th>From</th>
				<td><input type="date" name="from" id=""></td>
			</tr>
			<tr>
				<th>To</th>
				<td><input type="date" name="to" id=""></td>
			</tr>
			<tr>
				<th>Request</th>
				<td><input type="date" name="request" id=""></td>				
			</tr>
			<tr>
				<th>Agree</th>
				<td><input type="date" name="agree" id=""></td>
			</tr>
			<tr>
				<th>StatusDate</th>
				<td><input type="date" name="statusdate" id=""></td>
			</tr>
			<tr>
				<th>ReadCheck</th>
				<td><input type="text" name="readcheck" id=""></td>
			</tr>
			<tr>
				<th>Status</th>
				<td><input type="text" name="status" id=""></td>
			</tr>
			<tr></tr>
			
			<tr>
				<td colspan=2 align="center"><input type="reset" value="취소">
				<input type="submit" value="입력"></td>
			</tr>
		</table>
	</form>
</body>
</html>