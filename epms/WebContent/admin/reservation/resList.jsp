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
<script type="text/javascript">
	function requestCancel(no) {
		alert(no);
		swal({
			  title: "예약취소",
			  text: "해당 예약요청을 정말로 취소하시겠습니까?" ,
			  icon: "warning",
			  buttons: {
				  cancel: "아니요",
				  catch: "네",
				}
			})
			.then((value) => {
				  switch (value) {
				    case "catch":
				    	reguestCancel_Ajax(no);
				      break;	 
				    case "cancel":
				    	break;
				    default:
				  }
		});		
	}
	
	function reguestCancel_Ajax(no) {
		$.ajax({
			url:'./reservation_cancel.do',
			data:{"no":no,"status":'예약완료'},
			success:function(t){
				location.href='./Reservation.do';
			},
			error:function(){
				swal('예약내역 관리','서버오류입니다. (reguestCancel_Ajax)','error');
			}
		});
	}
</script>
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
						<div class="panel-heading" style="background: #605E5E; color: white; "><h2 style="font-size: 20pt; font-weight: bold; padding-top: 10px; padding-bottom: 10px; color: white; ">전체 예약내역</h2></div>
					<br><br>
					<form action="Reservation.do" method="get">
						<select class="selectpicker" style="height: 23pt;" name="skey">
							<option value="0" <c:if test="${skey == 0}">selected</c:if>>Guest</option>
							<option value="1" <c:if test="${skey == 1}">selected</c:if>>Host</option>
							<option value="2" <c:if test="${skey == 2}">selected</c:if>>Address</option>
						</select> <input type="text" class="form-control" style="width:20%; display:inline-block;" name="sval" value="${sval}" /> <input type="submit" class="btn btn-outline-primary" value="검색">
						<div id="listCount" align="center">총 검색된 자료수:${listCount}</div>
					</form>
					<table class="table table-hover">  
					<thead>
						<tr>
							<th>Guest</th> <th>Host</th> <th>Address</th> <th>Area</th> <th>Content</th> <th>From</th> <th>To</th> <th>Request</th> <th>Agree</th> <th>Status</th> <th>Status Date</th> <th>취소</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="bean" items="${bean}"> 
						<tr>
							<td>${bean.guest}</td>
							<td>${bean.host}</td>
							<td>${bean.address}</td>
							<td>${bean.area}</td>
							<td>${bean.content}</td> 
							<td>${bean.from}</td>
							<td>${bean.to}</td>
							<td>${bean.request}</td>
							<td>${bean.agree}</td>
							<td>
							<c:choose>
								<c:when test="${bean.status == '예약중'}"><strong style="color:balck">${bean.status}</strong></c:when>
								<c:when test="${bean.status == '예약완료'}"><strong style="color:green">${bean.status}</strong></c:when>
								<c:when test="${bean.status == '사용완료'}"><strong style="color:blue">${bean.status}</strong></c:when>
								<c:when test="${bean.status == '예약취소'}"><strong style="color:red">${bean.status}</strong></c:when>
							</c:choose>
							</td>
							<td>${bean.statusdate}</td>
							<td style="width:10%;">
							<c:choose>
							<c:when test="${bean.status == '예약중' || bean.status == '예약완료'}">
								<input type="button" class="btn btn-outline-danger" onclick="requestCancel(${bean.no});" value="예약취소">
							</c:when>
							<c:otherwise>
								&nbsp;
							</c:otherwise>
							</c:choose>
							
							</td>
						</tr>   
						</c:forEach>
					</tbody>	
					</table>
					<br>
				</div>
				<nav aria-label="..." style="background-color:#f0f4ff;">
				<br>
						<ul class="pagination justify-content-center">
							<c:choose>
						 		<c:when test="${startPage != 1}">
									<li class='page-item'><a href='./Reservation.do?page=${startPage-1}' class="page-link" aria-label='Previous'><span aria-hidden='true'>Previous</span></a></li>
								</c:when>
								<c:otherwise>
									<li class='page-item disabled'><a href='#' class="page-link" aria-label='Previous'><span aria-hidden='true'>Previous</span></a></li>
								</c:otherwise>
							</c:choose>
							<c:forEach var="pageNum" begin="${startPage}" end="${endPage}" step="1">
								<c:choose>
									<c:when test="${pageNum == spage }">
										<li class='page-item active'><a href='./Reservation.do?page=${pageNum}' class="page-link">${pageNum}<span class='sr-only'>${pageNum}</span></a></li>
									</c:when>
									<c:when test="${pageNum != page && sval == null}">
										<li class='page-item'><a href='./Reservation.do?page=${pageNum}' class="page-link">${pageNum}<span class='sr-only'>${pageNum}</span></a></li>
									</c:when>
									<c:otherwise>
										<li class='page-item'><a href='./Reservation.do?page=${pageNum}&skey=${skey}&sval=${sval}' class="page-link">${pageNum}<span class='sr-only'>${pageNum}</span></a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:choose>
								<c:when test="${endPage == maxPage }">
									<li class='page-item disabled'><a href='#' class="page-link" aria-label='Next'><span aria-hidden='true'>Next</span></a></li>
								</c:when>
								<c:otherwise>
									<li class='page-item'><a href='./Parking.do?page=${endPage+1 }' class="page-link" aria-label='Next'><span aria-hidden='true'>Next</span></a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</nav>
					<div id="list"></div>
				<div id="input"></div>
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