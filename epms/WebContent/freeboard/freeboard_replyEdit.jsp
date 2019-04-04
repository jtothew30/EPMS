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
 function writeCheck()
  {
   var form = document.replyform;
  if( !form.rfr_content.value )
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

<form name="replyform" action="freeboard_replyedit.do">
 <table  width=750 border=0 cellspacing=0 >
	댓글쓰기
	<tr>
	   <td>내용:</td>
	   <td><textarea cols=27 rows=3 name="rfr_content">${fbrb.rfr_content}</textarea> 
	   	   <input type="hidden" name="rfr_no" value="${fbrb.rfr_no}">
	   	   <input type="hidden" name="rfr_board_no" value="${fbrb.rfr_board_no}">
	   </td>
	</tr>
	<tr> 
	  <td>&nbsp;</td> 
	  <td colspan="2"><input type="button" value="수정" OnClick="javascript:writeCheck();"> 
	  <input type=button value="취소" OnClick="javascript:history.back(-1)"> 
	  <td>&nbsp;</td> 
	</tr> 
 </table>
</form>
 	
</body>
</html>
