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
</head>
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
<body>

<div class="body-wrapper"> 
  <jsp:include page="../common/_sidebar.html"></jsp:include>
  <jsp:include page="../common/_navbar.html"></jsp:include>
  <div class="page-wrapper mdc-toolbar-fixed-adjust">
      <main class="content-wrapper">
        <div class="mdc-layout-grid">
          <div class="mdc-layout-grid__inner">
          	<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-1">
          	</div>
			<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-10">
				<div class="mdc-card" align="center" style="background-color: #f0f4ff;"> 
					<div class="panel panel-default" style="background-color: #f0f4ff;">
						<div class="panel-heading" style="background: #605E5E; color: white; "><h2 style="font-size: 20pt; font-weight: bold; padding-top: 10px; padding-bottom: 10px; color: white; ">News</h2></div>
					<br><br>
					<table class="table table-hover" style=width:80%;">  
					<thead>
						<tr>
							<th>No.</th> <th>제목</th> <th>작성자</th> <th>작성일</th><th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="nb" items="${nblist}"> 
						<tr>
							<td style="width:10%;"> ${nb.nofi_rn} </td>
							<td style="width:40%;"> 
								<a href="notification_detail.do?idx=${nb.nofi_no}"> ${nb.nofi_title}</a>                           
							</td>
							<td style="width:20%;"> ${nb.nofi_id} </td>
							<td style="width:20%;"> ${nb.nofi_date} </td>
							<td style="width:10%;"> ${nb.nofi_cnt} </td>
						</tr>   
						</c:forEach>
					</tbody>	
					</table>
					<br><br>
					<span id="adminWrite"></span>
					</div>
					<p><br><br>
					<nav aria-label="..." >
						<ul class="pagination justify-content-center">
							<c:choose>
						 		<c:when test="${startpage != 1 }">
									<li class='page-item'><a href='./notification_list.do?pageNum=${startpage-1}' class="page-link" aria-label='Previous'><span aria-hidden='true'>Previous</span></a></li>
								</c:when>
								<c:otherwise>
									<li class='page-item disabled'><a href='#' class="page-link" aria-label='Previous'><span aria-hidden='true'>Previous</span></a></li>
								</c:otherwise>
							</c:choose>
							<c:forEach var="i" begin="${startpage}" end="${endpage}" step="1">
								<c:choose>
									<c:when test="${pageNUM ==i }">
										<li class='page-item active'><a href='./notification_list.do?pageNum=${i}' class="page-link">${i}<span class='sr-only'>${i}</span></a></li>
									</c:when>
									<c:otherwise>
										<li class='page-item'><a href='./notification_list.do?pageNum=${i}' class="page-link">${i}<span class='sr-only'>${i}</span></a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:choose>
								<c:when test="${endpage == pagecount }">
									<li class='page-item disabled'><a href='#' class="page-link" aria-label='Next'><span aria-hidden='true'>Next</span></a></li>
								</c:when>
								<c:otherwise>
									<li class='page-item'><a href='./notification_list.do?pageNum=${endpage+1}' class="page-link" aria-label='Next'><span aria-hidden='true'>Next</span></a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	  </div>
	</main>
	<jsp:include page="../common/_footer.html"></jsp:include>
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