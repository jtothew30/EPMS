<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/EPMS/js/jquery-3.3.1.min.js">
 $("#arr").click(function(){
	 $.ajax({
		type:"POST",
		url:"../areaCheck.do",
		data:{area:$("#areap").val()},
		dataType: "xml",
		success:function(data){
			$("#lis").html(data);
		},
		error:function(data){
			$("#lis")
			alert(data);
		}
	 });
 });

</script>
</head>
<body>
	<form class="insert" action="parkingPInsertSave.do" method="POST">	
	<table width="800" border="3" bordercolor="lightgray">
		<tr>
		  <th>pap_address</th>
		  <th>pap_area</th>
		  <th>pap_host</th>
		 
		</tr>
		<tr>
		  <td><input type="text" id="address" name="address" value="${address}"></td>
		  <td><input type="text" id="areap" name="area" value="${bean.area}" 
		  <c:if test="${bean.area!=null }">disabled </c:if> >
		  <input type="button"  id="arr" value="중복확인"><br>
		  <span id="lis"></span>
		  </td>
		  <td><input type="text" id="host" name="host" value="${bean.host}" ></td>
		</tr>		
		<tr>
		  <td colspan=3 align="center">
		  <input type="submit"  value="입력" >
		  <input type="reset" value="취소">
		  </td>
		</tr>	
	</table>
	</form>
</body>
</html>