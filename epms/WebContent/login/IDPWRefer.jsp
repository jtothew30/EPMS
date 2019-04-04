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
<script src='http://cdnjs.cloudflare.com/ajax/libs/mustache.js/0.7.2/mustache.min.js'></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<link rel="stylesheet" href="./template/css/style.css">
<link rel="shortcut icon" href="../template/images/favicon.png" />
<link rel="stylesheet" href="./template/node_modules/mdi/css/materialdesignicons.min.css">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="./js/user/InputJS.js"></script>
<script src="./js/user/login.js"></script>
</head>
<script type="text/javascript">
function idFind() {
	$.ajax({
		url: "./accountIdRefer.do",
		type: "post",
		data: {"name": $('#name').val(), phone: $('#phone1').val() + '-' + $('#phone2').val() + '-' + $('#phone3').val()},
		success: function(t){
			
			if(t != "null") {
				swal('아이디 찾기','해당하는 아이디는 '+t+' 입니다.','success');
			} else {
				swal('아이디 찾기','입력하신 정보에 대한 아이디가 없습니다.\n다시 한번 입력해주세요.','error');
			}
		},
		error: function(data){
			swal('서버오류','관리자에게 문의하세요.(idFind)','error');
		}		
	});
}
function pwFind() {
	$.ajax({
		url : "./passRefer.do",
		type : "post",
		data : {id: $('#id').val(), email: $('#email').val()},
		success: function(t){
			if(t != "null") {
				var a ="";
				for(var i =4;i<t.length;i++){
					a += "*";
				}
				swal('비밀번호 찾기','해당하는 아이디의 비밀번호는 '+t.substring(0,4) + a +' 입니다.','success');
			} else {
				swal('비밀번호 찾기','입력하신 정보가 잘못되었습니다.\n다시 한번 입력해주세요.','error');
			}
		},
		error: function(data){
			swal('서버오류','관리자에게 문의하세요.(pwFind)','error');
		}		
	});	
}
</script>
<body>
<div class="body-wrapper"> 
  <jsp:include page="../common/_sidebar.html"></jsp:include>
  <jsp:include page="../common/_navbar.html"></jsp:include>
  <div class="page-wrapper mdc-toolbar-fixed-adjust">
      <main class="content-wrapper">
        <div class="mdc-layout-grid">
          <div class="mdc-layout-grid__inner">
          	<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-2">
          	</div>
			<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-8">
				<div class="mdc-card" align="center"> 
					<div class="panel panel-default">
						<div class="panel-heading" style="background: #C91E1E; color: white; "><h2 style="font-size: 20pt; font-weight: bold; padding-top: 10px; padding-bottom: 10px; color: white; ">ID 찾기</h2></div>
						<p><p>
						<div class="mdc-layout-grid__inner">
							<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-2">
							</div> 
							<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-8">
								<div class="mdc-card">
								<p><p>
									<div style="text-align: left; padding-left:20%;">
										<font style="font-weight: bolder;">&nbsp;&nbsp;이&nbsp;&nbsp;&nbsp;&nbsp;름&nbsp;&nbsp; : </font>&nbsp;&nbsp;<input type="text" id="name" class="form-control" style="width:30%; display: inline-block;">
									</div>	
									<p>
									<div style="text-align: left; padding-left:20%;">
										<font style="font-weight: bolder;">전화번호 :</font> &nbsp;&nbsp;<input type="text" class="form-control" id="phone1" style="width:20%; display: inline-block;"> - <input type="text" class="form-control" id="phone2" style="width:20%; display: inline-block;"> - <input type="text" class="form-control" id="phone3" style="width:20%; display: inline-block;">
										<p><p>
									</div>
								</div>
							</div>
						</div>
						<div class="mdc-card__primary">
		         				<h1 class="mdc-card__title mdc-card__title--large">* 아이디에 등록된 이름과 전화번호를 알맞게 모두 입력해주세요.</h1>
			         	</div> 
			         	<p><p>
			         	<input type="button" class="btn btn-outline-success" onclick="idFind();" value="아이디 찾기">
			         	<input type=button class="btn btn-outline-dark" value="취소" onclick="history.back(-1);">
					</div>
					</div>	
				</div>
				<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-2">
          		</div>
          		<p><p>
				<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-8">
					<div class="mdc-card" align="center"> 
						<div class="panel panel-default">
							<div class="panel-heading" style="background: #C91E1E; color: white; "><h2 style="font-size: 20pt; font-weight: bold; padding-top: 10px; padding-bottom: 10px; color: white; ">PW 찾기</h2></div>
							<p><p>
							<div class="mdc-layout-grid__inner">
								<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-2">
								</div> 
								<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-8">
									<div class="mdc-card">
									<p><p>
										<div style="text-align: left; padding-left:20%;">
											<font style="font-weight: bolder;">&nbsp;&nbsp;아&nbsp;&nbsp;이&nbsp;&nbsp;디&nbsp;&nbsp; : </font>&nbsp;&nbsp;<input type="text" id="id" class="form-control" style="width:30%; display: inline-block;">
										</div>	
										<p>
										<div style="text-align: left; padding-left:20%;">
											<font style="font-weight: bolder;">&nbsp;&nbsp;이&nbsp;&nbsp;메&nbsp;&nbsp;일&nbsp;&nbsp; : </font> &nbsp;&nbsp;<input type="text" id="email" class="form-control" style="width:50%; display: inline-block;">
											<p><p>
										</div>
									</div>
								</div>
							</div>
							<div class="mdc-card__primary">
			         				<h1 class="mdc-card__title mdc-card__title--large">* 아이디에 등록된 이름과 이메일을 알맞게 모두 입력해주세요.</h1>
				         	</div> 
				         	<p><p>
				         	<input type="button" class="btn btn-outline-success" onclick="pwFind();" value="비밀번호 찾기">
				         	<input type=button class="btn btn-outline-dark" value="취소" onclick="history.back(-1);">
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