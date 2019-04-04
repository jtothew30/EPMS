<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<title>FAQ 글 상세보기</title>
<meta http-equiv="CONTENT-TYPE" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- common js & css -->
<script src="./js/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="./bootstrap/css/bootstrap.min.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="./css/top.css">
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script  src="./js/top.js"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/mustache.js/0.7.2/mustache.min.js'></script>
<link rel="stylesheet" href="./template/css/style.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<link rel="shortcut icon" href="../template/images/favicon.png" />
<link rel="stylesheet" href="./template/node_modules/mdi/css/materialdesignicons.min.css">
<script src="./js/user/login.js"></script>
</head>
<!-- 밖으로 빼낼 것들 / 다른 스타일로 변경 -->
<style>
	tr .tr1{heigth:1px; background-color:#dddddd; }
	tr .tr2{height:1px; background-color:#82B5DF; }
	th {
		font-weight: bold !important; 
		font-size:15pt !important; 
		padding: 10pt;
	}
	td {
		font-size: 11pt !important;
		padding: 10pt;
	}
	.tr1 td{
		padding: 1px !important;
	}
	.tr2 td{
		padding: 1px !important;
	}
</style>
<script>
	function faqReply() {
		location.href=location.href='FAQInsert.do?idx='+$('#faqNum').val();
	}
	function faqEdit() {
		location.href=location.href='FAQEdit.do?idx='+$('#faqNum').val();
	}
	function faqDelete() {
		swal({
			  title: "FAQ 게시글 삭제",
			  text: "게시글을 정말로 삭제하시겠습니까?" ,
			  icon: "warning",
			  buttons: {
				  cancel: "취소",
				  catch: "삭제",
				},
			})
			.then((value) => {
				  switch (value) {
				    case "catch":
				     	 // 요청 승낙시 처리
				      	var data = {"idx":$('#faqNum').val()};
						$.ajax({
							url:'./FAQDelete.do?',
							data:data,
							"success": function () {
								swal({
									title:"작성글 삭제",
									text : "작성글이 삭제되었습니다.",
									icon : "success"
								}).then((willDelete) => {
									location.href="./FAQList.do";
								});
							},
							"error" : function () {
								swal({
									title:"작성글 삭제",
									text : "작성글 삭제에 실패했습니다. 관리자에게 문의하세요. (faqDelete)",
									icon : "error"
								}).then((willDelete) => {
								});
							}
						})
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
          	<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-1">
          	</div>
			<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-10">
				<div class="mdc-card" align="center"> 
					<div class="panel panel-default" style="background-color:#f0f4ff;">
						<div class="panel-heading" style="background: #605E5E; color: white; "><h2 style="font-size: 20pt; font-weight: bold; padding-top: 10px; padding-bottom: 10px; color: white; ">FAQ 글 상세보기</h2></div>
					<br><br>	
					<input type="hidden" value="${bean.no}" id="faqNum">
 						<table width="80%">
							  <tr>
							   <td>
							   <table width="100%">
							     <tr>
							      <td width="0">&nbsp;</td>
							      <th class="td1" width="20%">제목</th>
							      <td width="80%">${bean.title}</td>
							      <td width="0">&nbsp;</td>
							     </tr>
								 <tr class="tr1"><td colspan="4" width="407"></td></tr>
								 <tr>
							      <td width="0">&nbsp;</td>
							      <th class="td1" width="20%">작성자</th>
							      <td width="80%" id="faq_writer">${bean.id}</td>
							      <td width="0">&nbsp;</td>
							     </tr>
							     <tr class="tr1"><td colspan="4" width="407"></td></tr>
							    <tr>
							      <td width="0">&nbsp;</td>
							      <th class="td1" width="20%">조회수</th>
							      <td width="80%">${bean.count}</td>
							      <td width="0">&nbsp;</td>
							     </tr>
								 <tr class="tr1"><td colspan="4" width="407"></td></tr>
							    <tr>
							      <td width="0">&nbsp;</td>
							      <th class="td1" width="20%">날짜</th>
							      <td width="80%">${bean.date}</td>
							      <td width="0">&nbsp;</td>
							     </tr>
							      <tr class="tr1"><td colspan="4" width="407"></td></tr>
							                <tr>
							      <td width="0">&nbsp;</td>
							                   <td width="399" colspan="2" height="200">${bean.content}
							                </tr>
							     <tr class="tr1"><td colspan="4" width="407"></td></tr>
							     <tr class="tr1"><td colspan="4" width="407"></td></tr>
							     <tr align="center">
							      <td width="0">&nbsp;</td>
							      <td colspan="2" width="399">
							      <span id="faqEdit"></span>
									<input type=button class="btn btn-outline-dark" value="목록으로" onclick="location.href='./FAQList.do'">
							      <td width="0">&nbsp;</td>
							     </tr>
							    </table>
							   </td>
							  </tr>
							 </table>
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