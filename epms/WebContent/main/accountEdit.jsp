<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
</head>
<body>
	
 	<h1 align="center">회원정보 수정</h1>
    <form action="accountEditSave.do?id=${bean.id}" method="POST">        
        <table width="700" border="3" bordercolor="lightgray" align="center">	
         		<tr>
                    <td>ID</td>
                    <td><input type="text" name="id" value="${bean.id}" disabled></td>
                </tr>		
                <tr>
                    <td>이름:</td>
                    <td><input type="text" name="name" value="${bean.name}" ></td>
                </tr>
                <tr>
                    <td>비밀번호:</td>
                    <td><input type="password" name="pw" size="70" maxlength="100"
                        value="${bean.pw}"></td>
                </tr>
                <tr>
                    <td>비밀번호확인:</td>
                    <td><input type="password" name="pw" size="70" maxlength="100"
                        value="${bean.pw}"></td>
                </tr>            
                 <tr>
                    <td>전화번호:</td>
                    <td><input type="text" name="phone" value="${bean.phone}" ></td>
                </tr>
                 <tr>
                    <td>주소</td>
                    <td><input type="text" name="addr1" value="${bean.addr1}" ></td>
                </tr>
                 <tr>
                    <td>상세주소</td>
                    <td><input type="text" name="addr2" value="${bean.addr2}" ></td>
                </tr>
                 <tr>
                    <td>E-mail:</td>
                    <td><input type="text" name="email" value="${bean.email}" ></td>
                </tr>
                <c:if test="${sessionScope.id == 'admin' }">
                 <tr>
                    <td>Type:</td>
                    <td><input type="text" name="type" value="${bean.type}" ></td>
                </tr>
                 <tr>
                    <td>point:</td>
                    <td><input type="text" name="point" value="${bean.point}" ></td>
                </tr>
                 <tr>
                    <td>Area:</td>
                    <td><input type="text" name="area" value="${bean.area}" ></td>
                </tr>
                </c:if>
                <tr>
                    <td colspan=2 align="center"><input type="reset" value="취소">
                    <input type="submit" value="확인"></td>
                </tr>
                    
         </table>
    </form>
</body>
</html>