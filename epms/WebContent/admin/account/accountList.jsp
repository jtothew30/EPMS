<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title></title>
<meta http-equiv="CONTENT-TYPE" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- common js & css -->
<script src="./js/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="./bootstrap/css/bootstrap.min.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="./css/top.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script  src="./js/top.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/mustache.js/0.7.2/mustache.min.js'></script>
<link rel="stylesheet" href="./template/css/style.css">
<link rel="shortcut icon" href="../template/images/favicon.png" />
<link rel="stylesheet" href="./template/node_modules/mdi/css/materialdesignicons.min.css">
<script src="./js/user/login.js"></script>
<style>
	th {
		font-weight: bold !important; 
		font-size:15pt !important; 
		text-align: center !important;
	}
	td {
		font-size: 11pt !important;
		text-align: center !important;
	}
</style>
</head>
<body>

<div class="body-wrapper"> 
  <jsp:include page="/common/_sidebar.html"></jsp:include>
  <jsp:include page="/common/_navbar.html"></jsp:include>
  <div class="page-wrapper mdc-toolbar-fixed-adjust">
      <main class="content-wrapper">
        <div class="mdc-layout-grid">
          <div class="mdc-layout-grid__inner">
			<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12">
				<div class="mdc-card" align="center"> 
					<div class="panel panel-default" style="background-color:#f0f4ff;">
						<div class="panel-heading" style="background: #605E5E; color: white; "><h2 style="font-size: 20pt; font-weight: bold; padding-top: 10px; padding-bottom: 10px; color: white; ">회원관리</h2></div>
					<br><br>
					<form action="accountList.do" method="get">
						<select class="selectpicker" style="height: 23pt;" name="skey">
							<option value="0" <c:if test="${skey == 0}">selected</c:if>>ID</option>
							<option value="1" <c:if test="${skey == 1}">selected</c:if>>Name</option>
						</select>
						<input type="text"class="form-control" style="width:20%; display:inline-block;"  name="sval" value="${sval}" />
						<input type="submit" class="btn btn-outline-primary" value="검색" >
						<div id="listCount" align="center">총 검색된 자료수:${listCount}</div>
					</form>
					<table class="table table-hover">  
					<thead>
						<tr>
							<th>ID</th> <th>PW</th> <th>NAME</th> <th>PHONE</th> <th>주소</th> <th>상세주소</th> <th>email</th> <th>가입일</th> <th>타입</th> <th>포인트</th> <th>주차지역</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="bean" items="${bean}"> 
						<tr>
							<td><a href='./accountEdit.do?id=${bean.id}'>${bean.id}</a></td>			
							<td>${bean.pw}</td>
							<td>${bean.name}</td>
							<td>${bean.phone}</td>
							<td>${bean.addr1}</td>
							
							<td>${bean.addr2}</td>
							<td>${bean.email}</td>
							<td>${bean.date}</td>
							<td>${bean.type}</td>
							<td>${bean.point}</td>
							
							<td>${bean.area}</td>
						</tr>   
						</c:forEach>
					</tbody>	
					</table>
					<br><br>
				</div>
				<nav aria-label="..." style="background-color:#f0f4ff;">
						<ul class="pagination justify-content-center">
							<c:choose>
						 		<c:when test="${startPage != 1 }">
									<li class='page-item'><a href='./accountList.do?page=${startPage-1}' class="page-link" aria-label='Previous'><span aria-hidden='true'>Previous</span></a></li>
								</c:when>
								<c:otherwise>
									<li class='page-item disabled'><a href='#' class="page-link" aria-label='Previous'><span aria-hidden='true'>Previous</span></a></li>
								</c:otherwise>
							</c:choose>
							<c:forEach var="pageNum" begin="${startPage}" end="${endPage}" step="1">
								<c:choose>
									<c:when test="${pageNum == spage }">
										<li class='page-item active'><a href='./accountList.do?page=${pageNum}' class="page-link">${pageNum}<span class='sr-only'>${pageNum}</span></a></li>
									</c:when>
									<c:when test="${pageNum != page && sval == null}">
										<li class='page-item'><a href='./accountList.do?page=${pageNum}' class="page-link">${pageNum}<span class='sr-only'>${pageNum}</span></a></li>
									</c:when>
									<c:otherwise>
										<li class='page-item'><a href='./accountList.do?page=${pageNum}&skey=${skey}&sval=${sval}' class="page-link">${pageNum}<span class='sr-only'>${pageNum}</span></a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:choose>
								<c:when test="${endPage == maxPage }">
									<li class='page-item disabled'><a href='#' class="page-link" aria-label='Next'><span aria-hidden='true'>Next</span></a></li>
								</c:when>
								<c:otherwise>
									<li class='page-item'><a href='./accountList.do?page=${endPage+1}' class="page-link" aria-label='Next'><span aria-hidden='true'>Next</span></a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	  </div>
	</main>
	<jsp:include page="/common/_footer.html"></jsp:include>
  </div>
</div>
  
  <!-- body wrapper -->
  <!-- plugins:js -->
  <script src="./template/node_modules/material-components-web/dist/material-components-web.min.js"></script>
  <script src="./template/node_modules/jquery/dist/jquery.min.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page-->
  <script src="./template/node_modules/chart.js/dist/Chart.min.js"></script>
  <script src="./template/node_modules/progressbar.js/dist/progressbar.min.js"></script>
  <!-- End plugin js for this page-->
  <!-- inject:js -->
  <script src="./template/js/misc.js"></script>
  <script src="./template/js/material.js"></script>
  <!-- endinject -->
  <!-- Custom js for this page-->
  <script src="./template/js/dashboard.js"></script>
  <!-- End custom js for this page-->
<script src="./bootstrap/js/bootstrap.min.js"></script>
</body>
</html>