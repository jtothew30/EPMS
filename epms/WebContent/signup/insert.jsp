<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ACCOUNT INSERT PAGE</title>
<meta http-equiv="CONTENT-TYPE" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- common js & css -->
<script src="./js/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="./bootstrap/css/bootstrap.min.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="./css/top.css">
<script  src="./js/top.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/mustache.js/0.7.2/mustache.min.js'></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<link rel="stylesheet" href="./template/css/style.css">
<link rel="shortcut icon" href="../template/images/favicon.png" />
<link rel="stylesheet" href="./template/node_modules/mdi/css/materialdesignicons.min.css">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="./js/user/InputJS.js"></script>
<script src="./js/user/login.js"></script>
</head>
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
				<div class="mdc-card" align="center"> 
					<div class="panel panel-default">
						<div class="panel-heading" style="background: #C91E1E; color: white; "><h2 style="font-size: 20pt; font-weight: bold; padding-top: 10px; padding-bottom: 10px; color: white; ">Sign up</h2></div>
							<p><br><br>
							<form>
								<div style="float:left; padding-left: 30%">
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputID">아이디</label><br><br>
										<div style="float:left;">
										<input type="text" style="left:0px; width:500px !important; display:inline; " placeholder="Input ID" class="form-control" id="id" name="id" >&nbsp;
										<input type="button" class="btn btn-outline-danger"id="idCheck" onclick="flagS_t();" value="중복확인">
										<span id="maskId"> </span>
										</div>
									</div>
								</div>
								<br>
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputPW1">패스워드</label><br><br>
										<div style="float:left;">
										<input type="password" style="left:0px; width:500px !important; display:inline; " onkeyup="checklen(this,10);" onchange="clean()" placeholder="Input PW" class="form-control" id="pw1" name="pw1" >
										<span id="maskPw1"> </span>
										</div>
									</div>
								</div>
								<br>
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputPW2">패스워드 확인</label><br><br>
										<div style="float:left;">
										<input type="password" style="left:0px; width:500px !important; display:inline; " placeholder="Input RePW" class="form-control" id="pw2" name="pw2" onkeyup="checkPwd();" onchange="clean()" >
										<span id="maskPw2"> </span>
										</div>
									</div>
								</div>	
								<br>
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputName">이름</label><br><br>
										<div style="float:left;">
										<input type="text" style="left:0px; width:500px !important; display:inline; " placeholder="Input Name" class="form-control" id="name" name="name" onchange="clean()" >
										<span id="maskName"> </span>
										</div>
									</div>
								</div>
								<br>
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputPhone">전화번호</label><br><br>
										<div style="float:left;">
										<input type="text" style="left:0px; width:80px !important; display:inline; " size="3" class="form-control" id="num1" name="num1" onchange="num(); clean();" >
										 - <input type="text" style="left:0px; width:80px !important; display:inline; " size="4" class="form-control" id="num2" name="num2" onchange="num(); clean();" >
										 - <input type="text" style="left:0px; width:80px !important; display:inline; " size="4" class="form-control" id="num3" name="num3" onchange="num(); clean();" >
										<span id="maskPhone"> </span>
										</div>
									</div>
								</div>
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputAddress">주소</label><br><br>
										<div style="float:left;">
										<div style="float:left;">
										<input type="text" style="left:0px; width:200px !important; display:inline; " class="form-control" id="code" name="code" readonly="readonly" onchange="clean();" >
										<input type="button" style="left:0px; width:200px !important; display:inline; " class="form-control" onclick="DaumPostcode()" value="우편번호 찾기" onchange="clean()" ><br>
										</div><br><br>
										<input type="text" style="left:0px; width:500px !important; " class="form-control" id="addr1" name="addr1" readonly="readonly" onchange="clean();" ><br>
										<input type="text" style="left:0px; width:500px !important; " placeholder="상세주소를 입력하세요." class="form-control" id="addr2" name="addr2" onchange="clean();" >
										<span id="maskAddr"> </span>
										</div>
									</div>
								</div>
								<br><br><br><br>
								<br>
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputEmail">이메일</label><br><br>
										<div style="float:left;">
										<input type="text" style="left:0px; width:500px !important; display:inline; " class="form-control" id="email" name="email" onblur="emailcheck();" onchange="clean()" >
										<span id="maskEmail"> </span>
										</div>
									</div>
								</div>
								</div>
								<p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br><p><br><br>
								<div class="center-block">
										<input type="button" class="btn btn-outline-success" onclick="check();" value="완료">&nbsp;&nbsp;<input type="button" value="뒤로가기" class="btn btn-outline-secondary" onclick="history.back(1)">&nbsp;&nbsp;<input type="reset" class="btn btn-outline-danger" value="다시쓰기">
								</div>
								<p><br><br>
							</form>
					</div>
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