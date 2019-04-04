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
	function deleteAreaP(area,addr) {
		swal({
			  title: "주차공간 삭제",
			  text: "해당 주차공간을 정말로 삭제하시겠습니까?" ,
			  icon: "warning",
			  buttons: {
				  cancel: "아니요",
				  catch: "네",
				}
			})
			.then((value) => {
				  switch (value) {
				    case "catch":
				    	location.href='./parkingP_Delete.do?area='+area+'&addr='+addr;
				      break;	 
				    case "cancel":
				    	break;
				    default:
				  }
			});		
	}
	
	function areaP_Save(addr) {
		var area = $('#areaSave').val();
		var host = $('#host_ID').val();
		
		if(area == "" || area == null) {
			swal('주차지역 등록','주차지역 번호를 입력하세요.','error');
			return;
		}
		if(host == "" || host == null) {
			swal('주차지역 등록','Host ID를 입력하세요.','error');
			return;
		}
		
		swal({
			  title: "주차지역 등록",
			  text: area+'지역에 '+ host +' 을/를 등록하시겠습니까?',
			  icon: "warning",
			  buttons: {
				  cancel: "아니요",
				  catch: "네",
				}
			})
			.then((value) => {
				  switch (value) {
				    case "catch":
				    	var data = {"area":area,"host":host,"addr":addr};
				    	areaP_SaveAjax(data);
				      break;	 
				    case "cancel":
				    	break;
				    default:
				  }
		});		
		
	}
	
	function areaP_SaveAjax(data) {
		$.ajax({
			url:'./parkingPInsertSave.do',
			data:data,
			dataType:"JSON",
			success:function(t){
				if(t.result == true) {
					location.href='./parkingPDetail.do?idx='+data.addr;
				} else {
					swal('주차지역 등록','이미 등록된 주차구역이거나 존재하지 않는 ID입니다.','error');
				}
			},
			error:function(){
				swal('주차지역 등록','서버오류입니다. (areaP_SaveAjax)','error');
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
          	<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-4">
          		<div class="mdc-layout-grid__inner">
          		<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-11">
          		<div class="mdc-card" align="center" style="background-color:#f0f4ff;"> 
					<div class="panel panel-default" style="background-color:#f0f4ff;">
						<div class="panel-heading" style="background: #605E5E; color: white; "><h2 style="font-size: 20pt; font-weight: bold; padding-top: 10px; padding-bottom: 10px; color: white; ">추가등록</h2></div>
					<br><br>
						<div class="mdc-layout-grid__inner">
							<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-2">
							</div> 
							<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-8">
								<div class="mdc-card">
								<p><p>
									<div style="text-align: left; padding-left:10%;">
										<font style="font-weight: bolder;">주차구역 : </font>&nbsp;&nbsp;<input type="text" id="areaSave" class="form-control" style="width:60%; display: inline-block;">
									</div>	
									<p>
									<div style="text-align: left; padding-left:10%;">
										<font style="font-weight: bolder;">&nbsp;&nbsp;Host ID &nbsp;:</font> &nbsp;&nbsp;<input type="text" class="form-control" id="host_ID" style="width:60%; display: inline-block;">
										<p><p>
									</div>
								</div>
							</div>
						</div>
					<br><br>
				</div>
				<input type="button" style="width:20%;" class="btn btn-outline-success" onclick="areaP_Save('${address}')" value="등록">
				<br><Br>
			</div>
			
			</div>
			<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12"></div><div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12"></div><div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12"></div>
			<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12"></div><div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12"></div><div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12"></div>
			</div>
          	</div>
			<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-6">
				<div class="mdc-card" align="center" style="background-color:#f0f4ff;"> 
					<div class="panel panel-default" style="background-color:#f0f4ff;">
						<div class="panel-heading" style="background: #605E5E; color: white; "><h2 style="font-size: 20pt; font-weight: bold; padding-top: 10px; padding-bottom: 10px; color: white; ">${address}의 주차구역</h2></div>
					<br><br>
					<table class="table table-hover" style=width:80%;">  
					<thead>
						<tr>
							<th>주차구역</th> <th>Host ID</th> <th>삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="bean" items="${bean}"> 
							<tr>
								<td > ${bean.area} </td>
								<td > ${bean.host} </td> 
								<td style="width:10%"><input type="button" class="btn btn-outline-danger" onclick="deleteAreaP('${bean.area}','${address}')" value="삭제"></td>
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