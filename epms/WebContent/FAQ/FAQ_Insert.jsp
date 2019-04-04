<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>FAQ 글 글쓰기</title>
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
<link rel="stylesheet" href="./template/css/style.css">
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<link rel="shortcut icon" href="../template/images/favicon.png" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<link rel="stylesheet" href="./template/node_modules/mdi/css/materialdesignicons.min.css">
<script src="./js/user/login.js"></script>

<script type="text/javascript">
	function check() { 
		if($('#title').val() == "" ) { 
			swal("등록실패","새 글 제목을 적어주세요.","error");
			form.title.focus(); return; 
		}
		if($('#content').val() == "" ) { 
			swal("등록실패","내용을 입력하세요.","error");
			form.memo.focus(); return; 
		}
	
		var data = {"level":$('#level').val(), "parent":$('#parent').val(),"group":$('#group').val(),"re_seq":$('#re_seq').val(), "title":$('#title').val(), "content":$('#content').val()};
		
		$("#content").bind('input propertychange', function () {
		    var maxLength = 200;
		    if ($(this).val().length > maxLength) {
		        $(this).val($(this).val().substring(0, maxLength));
		    }
		});
		
		faqSave(data);
	} 

	function faqSave(data) {
		$.ajax({
			url:'./FAQInsertSave.do',
			type:'GET',
			data: data,
			dataType: "JSON",
			success:function(t){
				if(t.result == "true") {
					swal({
						title:"등록완료",
						text : "게시판 글을 등록하였습니다.",
						icon : "success"
					}).then((willDelete) => {
						if(willDelete) {
							location.href = './FAQList.do';
						}
					})
				} else {
					swal("등록실패","게시판글을 등록하는데 오류가 발생했습니다.","error");
				}
			},
			error:function(jqXHR, textStatus, errorThrown){
				swal("등록실패","게시판글을 등록하는데 오류가 발생했습니다.","error");
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
						<div class="panel-heading" style="background: #605E5E; color: white; "><h2 style="font-size: 20pt; font-weight: bold; padding-top: 10px; padding-bottom: 10px; color: white; ">FAQ 글쓰기</h2></div>
						<div class="panel-body">
							<div class="container">
								<form role="form" style="padding-top: 30px; padding-bottom: 30px;">
									<input type="hidden" id="level" name="level" value="${bean.level}">
									<input type="hidden" id="parent" name="parent" value="${bean.no}">
									<input type="hidden" id="group" name="group" value="${bean.no}">
									<input type="hidden" id="re_seq" name="re_seq" value="${bean.re_seq}">
									<div class="form-group">
										<label class="text-left" for="subject">Title</label>
										<input type="text" class="form-control" name="title" id="title" placeholder="Enter title"> 
									</div>
									<div class ="form-group">
										<label for="content">Comment</label>
										<textarea class="form-control" rows="30" name="content" id="content" placeholder="Enter content" ></textarea> 
									</div>
									<div class="center-block">
										<input type="button"  class="btn btn-outline-success" onclick="javascript:check(); " value="저장하기">&nbsp;&nbsp;<input type="button" value="뒤로가기" class="btn btn-outline-secondary" onclick="history.back(1)">&nbsp;&nbsp;<input type="reset" class="btn btn-outline-danger" value="다시쓰기">
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-1">
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