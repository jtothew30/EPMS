<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="./js/user/InputJS.js"></script>
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
						<div class="panel-heading" style="background: #605E5E; color: white; "><h2 style="font-size: 20pt; font-weight: bold; padding-top: 10px; padding-bottom: 10px; color: white; ">"${bean.id}"님의 회원정보</h2></div>
					<br><br>
					<form >
								<div style="float:left; padding-left: 30%">
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputID">아이디</label><br><br>
										<div style="float:left;">
										<input type="text" style="left:0px; width:500px !important; display:inline; " placeholder="Input ID" class="form-control" value="${bean.id}" disabled id="id" name="id" >&nbsp;
										</div>
									</div>
								</div>
								<br>
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputPW1">패스워드</label><br><br>
										<div style="float:left;">
										<input type="text" style="left:0px; width:500px !important; display:inline; " value="${bean.pw}" placeholder="Input PW" class="form-control" id="pw1" name="pw1" >
										</div>
									</div>
								</div>
								<br>
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputPW2">패스워드 확인</label><br><br>
										<div style="float:left;">
										<input type="text" style="left:0px; width:500px !important; display:inline; " placeholder="Input RePW" class="form-control" value="${bean.pw}" id="pw" name="pw">
										</div>
									</div>
								</div>	
								<br>
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputName">이름</label><br><br>
										<div style="float:left;">
										<input type="text" style="left:0px; width:500px !important; display:inline; " value="${bean.name}" placeholder="Input Name" class="form-control" id="name" name="name">
										</div>
									</div>
								</div>
								<br>
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputPhone">전화번호</label><br><br>
										<div style="float:left;">
										<input type="text" style="left:0px; width:80px !important; display:inline; " value="${fn:split(bean.phone,'-')[0]}" size="3" class="form-control" id="num1" name="num1" >
										 - <input type="text" style="left:0px; width:80px !important; display:inline; " value="${fn:split(bean.phone,'-')[1]}"size="4" class="form-control" id="num2" name="num2" >
										 - <input type="text" style="left:0px; width:80px !important; display:inline; " value="${fn:split(bean.phone,'-')[2]}"size="4" class="form-control" id="num3" name="num3" >
										</div>
									</div>
								</div>
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputAddress">주소</label><br><br>
										<div style="float:left;">
										<div style="float:left;">
										<input type="text" style="left:0px; width:200px !important; display:inline; " class="form-control" id="code" name="code" readonly="readonly" >
										<input type="button" style="left:0px; width:200px !important; display:inline; " class="form-control" onclick="DaumPostcode()" value="우편번호 찾기"><br>
										</div><br><br>
										<input type="text" style="left:0px; width:500px !important; " value="${bean.addr1}" class="form-control" id="addr1" name="addr1" readonly="readonly" ><br>
										<input type="text" style="left:0px; width:500px !important; " value="${bean.addr2}" placeholder="상세주소를 입력하세요." class="form-control" id="addr2" name="addr2">
										</div>
									</div>
								</div>
								<br><br><br><br>
								<br>
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputEmail">이메일</label><br><br>
										<div style="float:left;">
										<input type="text" style="left:0px; width:500px !important; display:inline; " value="${bean.email}" class="form-control" id="email" name="email">
										</div>
									</div>
								</div>
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputEmail">Type</label><br><br>
										<div style="float:left;">
										<input type="text" style="left:0px; width:500px !important; display:inline; " value="${bean.type}" class="form-control" id="type" name="type" >
										</div>
									</div>
								</div>
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputEmail">포인트</label><br><br>
										<div style="float:left;">
										<input type="text" style="left:0px; width:500px !important; display:inline; " value="${bean.point}" class="form-control" id="point" name="point">
										</div>
									</div>
								</div>
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputEmail">주차지역</label><br><br>
										<div style="float:left;">
										<input type="text" style="left:0px; width:500px !important; display:inline; " value="${bean.area}" class="form-control" id="area" name="area" >
										</div>
									</div>
								</div>
								</div>
								<p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><br><p><br><br><p><br><br><br><br><p><br><br><p><br><br><p><br><br>
										<input type="button" class="btn btn-outline-success" onclick="adminEdit();" value="수정">&nbsp;&nbsp;<input type="button" value="뒤로가기" class="btn btn-outline-secondary" onclick="history.back(1)">&nbsp;&nbsp;<input type="reset" class="btn btn-outline-danger" value="다시쓰기">
								<p><br><br>
							</form>
								</div>
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