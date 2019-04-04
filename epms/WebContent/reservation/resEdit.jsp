<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 로그 수정</title>
<script type="text/javascript" src="/EPMS/js/jquery-3.3.1.min.js">
	$("#content").bind('input propertychange', function() {
		var maxLength = 300;
		if ($(this).val().length > maxLength) {
			$(this).val($(this).val().substring(0, maxLength));
		}
	});
</script>
</head>

<body>
	<form action="ReservationEdit.do">
		<input type="hidden" name="no" value="${bean.no}">
		<table id="reservation" class="table" width="800" border="3"
			bordercolor="lightgray">
			<tr>
				<th>Guest</th>
				<th>Host</th>
				<th>Address</th>
				<th>Area</th>
			</tr>
			<tr>

				<td><input type="text" name="guest" value="${bean.guest}"></td>
				<td><input type="text" name="host" value="${bean.host}"></td>
				<td><input type="text" name="address" value="${bean.address}"></td>
				<td><input type="text" name="area" value="${bean.area}"></td>
			</tr>
			<tr>
				<th>From</th>
				<th>To</th>
				<th>Request</th>
				<th>Agree</th>

			</tr>
			<tr>
				<td><input type="text" name="from" value="${bean.from}"></td>
				<td><input type="text" name="to" value="${bean.to}"></td>
				<td><input type="text" name="request" value="${bean.request}"></td>
				<td><input type="text" name="agree" value="${bean.agree}"></td>

			</tr>
			<tr>
				<th>StatusDate</th>
				<th>ReadCheck</th>
				<th>Status</th>
				<th>Content</th>
			</tr>
			<tr>
				<td><input type="text" name="statusdate"
					value="${bean.statusdate}"></td>
				<td><input type="text" name="readcheck"
					value="${bean.readcheck}"></td>
				<td><input type="text" name="status" value="${bean.status}"></td>
				<td><textarea name="content" id="content" rows="2" cols="30">${bean.content}</textarea></td>
			</tr>
			<tr>
				<td colspan=5 align="center"><input type="reset" value="취소">
					<input type="submit" value="수정"></td>
			</tr>
		</table>
	</form>
</body>
</html>