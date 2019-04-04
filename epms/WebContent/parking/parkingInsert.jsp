<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>parking INSERT PAGE</title>
</head>
<body>
<div id="main" align="center">	
	<form name="parkingInsert.do" class="insert" action="/EPMS/parkingInsert.do" method="post">
		<input type="hidden" name="no" value="0">
	<table width="800" border="3" bordercolor="lightgray">
		<tr>
		  <th>pap_address</th>
		  <td><input type="text" id="si" name="si" value="" ></td>
		</tr>
		<tr>
		  <th>구</th>
		  <td><input type="text" id="gu" name="gu" value="" ></td>
		</tr>
		<tr>
		  <th>주소</th>
		  <td><input type="text" id="address" name="address" value="" ></td>
		</tr>
		<tr>
		  <th>LAT</th>
		  <td><input type="text" id="lat" name="lat" onchange="numCheck();">
		  <span id="latspan"></span>
		  </td>
		  
		</tr>
		<tr>
		  <th>LON</th>
		  <td><input type="text" id="lon" name="lon" onchange="numCheck();" >
		  <span id="lonspan"></span>   
		  </td>
		  
		<tr>
		  <td colspan=2 align="center">
		  <input type="submit"  value="지역입력" >
		  <input type="reset" value="입력취소">
		  </td>
		</tr>	
	</table>
	</form>
</div>
</body>
</html>