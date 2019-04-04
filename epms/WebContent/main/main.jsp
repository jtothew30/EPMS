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
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script  src="./js/top.js"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/mustache.js/0.7.2/mustache.min.js'></script>
<link rel="stylesheet" href="./template/css/style.css">
<link rel="shortcut icon" href="../template/images/favicon.png" />
<link rel="stylesheet" href="./template/node_modules/mdi/css/materialdesignicons.min.css">
<script src="./js/user/login.js"></script>
<link rel="stylesheet" href="./css/slick.css">
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
<script type="text/javascript">
	function changeclear(){
		myform.keyword.value="";
		myform.keyword.focus();
	}//end
</script>
<style>
	.carousel-control-prev-icon {
		background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23000' viewBox='0 0 8 8'%3E%3Cpath d='M4 0l-4 4 4 4 1.5-1.5-2.5-2.5 2.5-2.5-1.5-1.5z'/%3E%3C/svg%3E")
	}
	.carousel-control-next-icon{
		background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23000' viewBox='0 0 8 8'%3E%3Cpath d='M1.5 0l-1.5 1.5 2.5 2.5-2.5 2.5 1.5 1.5 4-4-4-4z'/%3E%3C/svg%3E")
	}
	.carousel-indicators .active {
		background-color: black;
	}
	.carousel-indicators li {
		background-color: #dddddd;
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
					<div
						class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12">
						<div class="mdc-card" style="height: 800px; " >
							<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
							  <ol class="carousel-indicators">
							    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
							    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
							    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
							    <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
							    <li data-target="#carouselExampleIndicators" data-slide-to="4"></li>
							  </ol>
							  <div class="carousel-inner">
							    <div class="carousel-item active">
							      <img class="d-block w-100" src="./img/common/main.jpg" style="height:100%; width:auto;" alt="First slide">
							      <div class="carousel-caption d-none d-md-block" style="bottom:80%;">
									    <font style="color: black; font-weight: bolder; font-size: 30pt;">Welcome!</font><br><br>
									    <font style="color: black; font-weight: bolder; font-size: 20pt;">주차해 Dream 홈페이지에 오신걸 환영합니다.</font>
								  </div>
							    </div>
							    <div class="carousel-item">
							      <img class="d-block w-100" src="./img/common/a1.png" style="height:100%; width:80%;" alt="Second slide">
							    </div>
							    <div class="carousel-item">
							      <img class="d-block w-100" src="./img/common/a2.png" style="height:100%; width:80%;" alt="Third slide">
							    </div>
							    <div class="carousel-item">
							      <img class="d-block w-100" src="./img/common/a3.png" style="height:100%; width:80%;" alt=" slide">
							    </div>
							    <div class="carousel-item">
							      <img class="d-block w-100" src="./img/common/a4.png" style="height:100%; width:80%;" alt=" slide">
							    </div>
							  </div>
							  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
							    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
							    <span class="sr-only">Previous</span>
							  </a>
							  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
							    <span class="carousel-control-next-icon" aria-hidden="true"></span>
							    <span class="sr-only">Next</span>
							  </a>
							</div>
					
							<%-- <h1>주차공간 공유시스템</h1>
							<div class="list">
								<div>
									<img src="./img/common/main.jpg" width="80%" height="80%"><br>
									<h2>주차공유시스템</h2>
								</div>
								<div>
									<img src="./img/common/a1.jpg"  width="80%" height="80%"><br>
									<h3>찾으려는 주차지역을 지도에서 선택하거나, 검색해서 선택하세요</h3>
								</div>
								<div>
									<img src="./img/common/a2.jpg"  width="80%" height="80%"><br>
									<h3>주차지역이 있는 지역을 선택한 후, 상세지역을 선택하세요</h3>
								</div>
								<div>
									<img src="./img/common/a3.jpg"  width="80%" height="80%"><br>
									<h3>지도에 마커를 선택하면 주차가능시간을 조회, 시간예약 선택을 할수 있습니다.</h3>
								</div>
							</div> --%>
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
  <script src="./bootstrap/js/bootstrap.min.js"></script>
  <!-- End custom js for this page-->
  <script type="text/javascript" src="./js/slick.js"></script>
  <%--script type="text/javascript">
  $('.list').slick({
	  arrows : true,
	  autoplay : true,
	  autoplaySpeed: 2000,
	  draggable: true,
	  infinite: true,
	  fade: true,
	  touchMove: true,
	  speed: 300,
	  slidesToShow: 1,
	  adaptiveHeight: true
	});
  </script--%>  
</body>
</html>