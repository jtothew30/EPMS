<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ACCOUNT INSERT PAGE</title>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#idCheck').click(function(){
			var data = $('#id').val();
			if(data==""){
				$('#maskId').html("ID 입력 후 중복버튼 누르세요");
				return;
			}
			$.ajax({
				"url" : "../idCheck.do",
				"type" : "post",
				"data" : {UID: data},
				
				"success": function(data){											
						$('#maskId').html(data);					
				},
				"error": function(data){$('#maskId').fadeOut();}
			});
		});
	});		
</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="../js/jquery-3.3.1.min.js"></script>
<script src="../js/InputJS.js"></script>
</head>
<body>
<div id="main" align="center">	
	<form name="accountInsert" class="insert" action="/EPMS/accountInsert.do" method="post">
	<table width="800" border="3" bordercolor="lightgray">
		<tr>
		  <th>아 이 디</th>
		  <td><input type="text" id="id" name="id" value="${id}" >
			  <input type="button"  id="idCheck" onclick="flagS_t();" value="중복확인">
			  <span id="maskId"> </span><br><br></td>
		</tr>
		<tr>
		  <th>패스워드</th>
		  <td><input type="text" id="pw1" name="pw1" value="" onkeyup="checklen(this,10);" onchange="clean()" >
			  <span id="maskPw1"> </span><br><br></td>
		</tr>
		<tr>
		  <th>패스워드확인</th>
		  <td><input type="text" id="pw2" name="pw2" value="" onkeyup="checkPwd();" onchange="clean()" >
			  <span id="maskPw2"> </span><br><br></td>
		</tr>
		<tr>
		  <th>이   름</th>
		  <td><input type="text" name="name" value="" onchange="clean()" >
		      <span id="maskName"> </span><br><br></td>
		</tr>
		<tr>
		  <th>전화번호</th>
		  <td><input type="text" name="num1" value="" onchange="num(); clean();">-
		      <input type="text" name="num2" value="" onchange="num(); clean();">-
			  <input type="text" name="num3" value="" onchange="num(); clean();">
			  <span id="maskPhone"> </span><br><br></td>
		<tr>		
		  <th>주 소</th>
		  <td><input type="text" size="10" id="code" name="code" readonly onchange="clean()" > 
		      <input type="button" onclick="DaumPostcode()" value="우편번호" onchange="clean()" ><p>
		      <input type="text" name="addr1" id="addr1" value="" onchange="clean()" ><br><br>
			  <input type="text" name="addr2" id="addr2" value="" onchange="clean()" >
			  <span id="maskAddr"> </span><br><br><td>
		</tr>
		<tr>
		  <th>e-mail</th>
		  <td><input type="text" name="email" id="email" value="" onblur="emailcheck( );" onchange="clean()" >
		      <span id="maskEmail"> </span><br><br><td>
		</tr>
		<tr>
		  <td colspan=2 align="center">
		  <input type="submit"  value="회원가입" onclick="check();" >
		  <input type="reset" value="입력취소">
		  </td>
		</tr>	
	</table>
	</form>
</div>
</body>
</html>