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
	function areacheck(){
	 	var data = $("#areap").val();
	 	console.log(data);
	 	$.ajax({
	 		"url" : "../areaCheck.do",
	 		"type": "POST",
	 		"data": {area:data},
	 		"success": function(data){
	 			$('#li').html(data);
	 		},
	 		"error": function(data){
	 			$('#li').fadeOut();
	 		}
	 	});
	 }
	
	//======================================
	/* var path = './parkingPDetail.do?idx=';
	var path1 = './parkingPInsert.do?id=';
	var address = null;
	var path2 = './areaCheck.do';
	var loadHtml = function(path, callback) {
		var xhr = new XMLHttpRequest();
		xhr.open('GET', path, true);
		xhr.onreadystatechange = function() {
			if (this.readyState !== 4)
				return;
			if (this.status !== 200)
				return;
			callback(this.responseText);
		};
		xhr.send();
	}
	var displayHtml = function(file) {
		loadHtml(path + file, function(html) {
			document.querySelector("#input").innerHTML = "";
			document.querySelector("#list").innerHTML = html;
		});
		address = file;
		return false;
	}	
	//-----------------------------------------------------
	var loadHtml1 = function(path1, callback) {
		var xhr1 = new XMLHttpRequest();
		xhr1.open('GET', path1, true);
		xhr1.onreadystatechange = function() {
			if (this.readyState !== 4)
				return;
			if (this.status !== 200)
				return;
			callback(this.responseText);
		};
		xhr1.send();
	}
	var displayHtml1 = function(file1) {
		if (file1 === null) {
			file1 = address;
		}
		loadHtml(path1 + file1, function(html1) {
			document.querySelector("#input").innerHTML = html1;
		});
		return false;
	}	 */
	
	function areaDelete(addr) {
		swal({
			  title: "지역삭제",
			  text: "해당 지역을 정말로 삭제하시겠습니까?" ,
			  icon: "warning",
			  buttons: {
				  cancel: "아니요",
				  catch: "네",
				},
			})
			.then((value) => {
				  switch (value) {
				    case "catch":
				    	location.href='./parkingDelete.do?id='+ addr;
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
  <jsp:include page="/common/_sidebar.html"></jsp:include>
  <jsp:include page="/common/_navbar.html"></jsp:include>
  <div class="page-wrapper mdc-toolbar-fixed-adjust">
      <main class="content-wrapper">
        <div class="mdc-layout-grid">
          <div class="mdc-layout-grid__inner">
			<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12">
				<div class="mdc-card" align="center"> 
					<div class="panel panel-default" style="background-color:#f0f4ff;">
						<div class="panel-heading" style="background: #605E5E; color: white; "><h2 style="font-size: 20pt; font-weight: bold; padding-top: 10px; padding-bottom: 10px; color: white; ">Parking Area</h2></div>
					<br><br>
					<form action="Parking.do" method="get">
						<select class="selectpicker" style="height: 23pt;" name="skey">
							<option value="0" <c:if test="${skey == 0}">selected</c:if>>주소</option>
							<option value="1" <c:if test="${skey == 1}">selected</c:if>>구</option>
						</select>
						<input type="text" class="form-control" style="width:20%; display:inline-block;" name="sval" value="${sval}" />
						<input type="submit" class="btn btn-outline-primary" value="검색" >
						<div id="listCount" align="center">총 검색된 자료수:${listCount}</div>
					</form>
					<table class="table table-hover">  
					<thead>
						<tr>
							<th>지역번호</th> <th>시,도</th> <th>구,군</th> <th>주소</th> <th>위도</th> <th>경도</th> <th>삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="bean" items="${bean}"> 
						<tr>
							<td>${bean.no}</td>
							<td>${bean.si}</td>			
							<td>${bean.gu}</td>
							<td><a href="./parkingPDetail.do?idx=${bean.address}">${bean.address}</a></td>
							<td>${bean.lat}</td>
							<td>${bean.lon}</td>
							<td style="width:10%;"><input type="button" class="btn btn-outline-danger" onclick="areaDelete('${bean.address}');" value="지역삭제"></td>
						</tr>   
						</c:forEach>
					</tbody>	
					</table>
				<input type="button" style="width: 100px;" class="btn btn-outline-success" onclick="location.href='./parking_saveView.do'" value="등록">  
					<br>
				</div>
				<nav aria-label="..." style="background-color:#f0f4ff;">
				<br>
						<ul class="pagination justify-content-center">
							<c:choose>
						 		<c:when test="${startPage != 1 && startPage != null }">
									<li class='page-item'><a href='./Parking.do?page=${startPage-1}' class="page-link" aria-label='Previous'><span aria-hidden='true'>Previous</span></a></li>
								</c:when>
								<c:otherwise>
									<li class='page-item disabled'><a href='#' class="page-link" aria-label='Previous'><span aria-hidden='true'>Previous</span></a></li>
								</c:otherwise>
							</c:choose>
							<c:forEach var="pageNum" begin="${startPage}" end="${endPage}" step="1">
								<c:choose>
									<c:when test="${pageNum == spage }">
										<li class='page-item active'><a href='./Parking.do?page=${pageNum}' class="page-link">${pageNum}<span class='sr-only'>${pageNum}</span></a></li>
									</c:when>
									<c:when test="${pageNum != page && sval == null}">
										<li class='page-item'><a href='./Parking.do?page=${pageNum}' class="page-link">${pageNum}<span class='sr-only'>${pageNum}</span></a></li>
									</c:when>
									<c:otherwise>
										<li class='page-item'><a href='./Parking.do?page=${pageNum}&skey=${skey}&sval=${sval}' class="page-link">${pageNum}<span class='sr-only'>${pageNum}</span></a></li>
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