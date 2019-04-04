<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> [freeboard_reply.jsp]</title>
  <style type="text/css">
    *{font-size:20pt; font-weight:bold;  font-family:  Segoe UI Symbol , comic Sans MS ;  }
    a{font-size:20pt; font-weight:bold; color:blue; text-decoration:none; }
    a:hover{font-size:26pt; font-weight:bold; color:green; text-decoration:underline; }
    #rsave{
      font-size:26pt; font-weight:bold; 
      background: lightblue; height:150px; width:100px;
    }                
  </style>
  
  <script language = "javascript"> // 자바 스크립트 시작
 function ReplyCheck()
  {
   var form = document.replyform;
   
  if( !form.fr_content.value )
   {
    alert( "내용을 적어주세요" );
    form.rfr_content.focus();
    return;
   }
 
  form.submit();
  }
 </script>
  
 </head>
<body>

<form name="replyform" action="freeboard_reply.do">
 <table  width=750 border=0 cellspacing=0 >
 	댓글쓰기
	<tr>
	   <td>내용:</td>
	   <td><textarea cols=27 rows=3 name="rfr_content"></textarea> 
	   	   <input type="hidden" name="fr_no" value="${fbb.fr_no}">
	   </td>
	   <td rowspan=2 align="center"> <input type="submit" value="확인" id="rsave"> </td>
	</tr>
 </table>
</form>

 <table  width=750 border=0 cellspacing=0 >
   <tr bgcolor="lightblue">
     <td colspan="3">댓글</td>
   </tr>
  
   <tr>
    <td> ${fbrb.rfr_id} </td>
    <td> ${fbrb.rfr_content} </td>
    <td align="right">
     <input type="button" value="수정" onclick="location.href='freeboard_editrequest.do?fr_no=${fbb.fr_no}'">
  	 <input type="button" value="삭제" onclick="location.href='freeboard_delete.do?fr_no=${fbb.fr_no}'">
    </td>
   </tr>
 </table>
 	
</body>
</html>
