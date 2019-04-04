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
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<link rel="shortcut icon" href="../template/images/favicon.png" />
<link rel="stylesheet" href="./template/node_modules/mdi/css/materialdesignicons.min.css">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="./js/user/login.js"></script>
</head>
<script type="text/javascript">
function check(){
	var si = $('#si').val();
	var gu = $('#gu').val();
	var address = $('#address').val();
	var lat = $('#lat').val();
	var lon = $('#lon').val();
	
	if (si==null||si==""){
		swal("등록 실패!","시,도을 입력하세요!","error");
		$('#si').focus();
		return;
	}
	
	
	if (gu==null||gu==""){
		swal("등록 실패!","구,군를 입력하세요!","error");
		$('#gu').focus();
		return;
	}
	
	if (address==null||address==""){
		swal("등록 실패!","주소를 입력하세요!","error");
		$('#address').focus();
		return;
	}
	
	if (lat==null||lat==""){
		swal("등록 실패!","위도를 입력하세요!","error");
		$('#lat').focus();
		return;
	}
	
	if (lon==null||lon==""){
		swal("등록 실패!","경도를 입력하세요!","error");
		$('#lon').focus();
		return;
	}
	
	//위에 해당 되는 거 없으면 널 체크 true
	flag=true;
	
	//위에 다 통과&체크 완료 후 submit
	areaSave();
}//check end

function areaSave() {
	var si = $('#si').val();
	var gu = $('#gu').val();
	var address = $('#address').val();
	var lat = $('#lat').val();
	var lon = $('#lon').val();

var data = {"si": si, "gu": gu, "address":address, "lat":lat, "lon":lon};
$.ajax({
	url:"./parkingInsert.do",
	data: data,
	dataType: "JSON",
	success: function(t) {
		if(t.result == true) {
			swal({
				title:"지역등록 완료",
				text : "주차지역 등록에 성공하였습니다.",
				icon : "success"
			}).then((willDelete) => {
				if(willDelete) {
					location.href = './Parking.do';
				}
			})
		} else {
			swal("주차지역 등록 실패","주차지역 등록 과정에서 오류가 발생했습니다.","error");
		}
	}
});
}
</script>
<body>
<div class="body-wrapper"> 
  <jsp:include page="/common/_sidebar.html"></jsp:include>
  <jsp:include page="/common/_navbar.html"></jsp:include>
  <div class="page-wrapper mdc-toolbar-fixed-adjust">
      <main class="content-wrapper">
        <div class="mdc-layout-grid">
          <div class="mdc-layout-grid__inner">
          	<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-1">
          	</div>
			<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-10">
				<div class="mdc-card" align="center"> 
					<div class="panel panel-default">
						<div class="panel-heading" style="background: #C91E1E; color: white; "><h2 style="font-size: 20pt; font-weight: bold; padding-top: 10px; padding-bottom: 10px; color: white; ">Parking Area 등록</h2></div>
							<p><br><br>
							<form>
								<div style="float:left; padding-left: 30%">
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputID">시,도</label><br><br>
										<div style="float:left;">
										<input type="text" style="left:0px; width:500px !important; display:inline; " class="form-control" id="si" name="si" >&nbsp;
										</div>
									</div>
								</div>
								<br>
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputPW1">구,군</label><br><br>
										<div style="float:left;">
										<input type="text" style="left:0px; width:500px !important; display:inline; " class="form-control" id="gu" name="gu" >
										</div>
									</div>
								</div>
								<br>
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputPW2">전체 주소</label><br><br>
										<div style="float:left;">
										<input type="text" style="left:0px; width:500px !important; display:inline; " class="form-control" id="address" name="address"  >
										</div>
									</div>
								</div>	
								<br>
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputName">LAT</label><br><br>
										<div style="float:left;">
										<input type="text" style="left:0px; width:500px !important; display:inline; " class="form-control" id="lat" name="lat" onchange="numCheck();">
										<span id="latspan"></span>
										</div>
									</div>
								</div>
								<br>
								<div class="mdc-card__primary">
									<div class="form-group">
										<label style="float:left; font-size: 15pt; font-weight: bolder" for="exampleInputEmail">LON</label><br><br>
										<div style="float:left;">
										<input type="text" style="left:0px; width:500px !important; display:inline; " class="form-control" id="lon" name="lon" onchange="numCheck();">
										<span id="lonspan"></span> 
										</div>
									</div>
								</div>
								</div>
								<p><br><br><p><br><br><p><br><p><br><br><p><br><p><br><br><p><br><p><br><br><p><br><p><br><br><p><br><p><br><br><p><br><p><br><br><p><br>
								<div class="center-block">
										<input type="button" class="btn btn-outline-success" onclick="check();" value="등록">&nbsp;&nbsp;<input type="button" value="뒤로가기" class="btn btn-outline-secondary" onclick="history.back(1)">&nbsp;&nbsp;<input type="reset" class="btn btn-outline-danger" value="다시쓰기">
								</div>
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