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
<script>
	function requestCancel(no, status) {
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
				    	location.href='./myinfo_cancel.do?no='+no+'&status='+status;
				      break;	 
				    case "cancel":
				    	break;
				    default:
				  }
			});		
	}

</script>


</head>
<body>

<div class="body-wrapper"> 
  <jsp:include page="../common/_sidebar.html"></jsp:include>
  <jsp:include page="../common/_navbar.html"></jsp:include>
  <div class="page-wrapper mdc-toolbar-fixed-adjust">
      <main class="content-wrapper">
        <div class="mdc-layout-grid">
          <div class="mdc-layout-grid__inner">
			<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12">
				<div class="mdc-card" align="center" style="background-color: #f0f4ff;"> 
					<div class="panel panel-default">
						<div class="panel-heading" style="background: #605E5E; color: white; "><h2 style="font-size: 20pt; font-weight: bold; padding-top: 10px; padding-bottom: 10px; color: white; ">${id}님의 현재 진행중인 예약 요청 내역</h2></div>
					<br><br>
					<table class="table table-hover" style=width:80%;">  
					<thead>
						<tr>
							<th>No.</th> <th>대여자</th> <th>주소</th> <th>주차공간</th> <th>대여기간</th> <th>상태</th> <th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="rb" items="${rblist}"> 
						<tr>
							<td style="width:5%;"> ${rb.r_rn} </td>
							<td style="width:10%;"> ${rb.r_host} </td>
							<td style="width:20%;"> ${rb.r_address} </td>
							<td style="width:10%;"> ${rb.r_area} </td>
							<td style="width:35%;"> ${rb.r_from} ~ ${rb.r_to} </td>
							<td style="width:10%;"> ${rb.r_status} </td>  
							<td style="width:10%;"> <input type="button" class="btn btn-outline-danger" onclick="requestCancel(${rb.r_no}, '${rb.r_status}')" value="취소"></td>
						</tr>   
						</c:forEach>
					</tbody>	
					</table>
					<br><br>
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