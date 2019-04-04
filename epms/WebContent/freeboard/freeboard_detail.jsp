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
	function replyCheck() { 
		if($('#rfr_content').val() == "" ) { 
			swal("등록실패","내용을 입력하세요.","error");
			return; 
		}
		var data = {"content":$('#rfr_content').val(), "fr_no":$('#freeNum').val()};
		replySave(data);	
	} 
	
	function replySave(data) {
		$.ajax({
			url:'./freeboard_reply.do',
			type:'GET',
			data: data,
			success:function(){
				location.href=location.href='freeboard_detail.do?fr_no='+$('#freeNum').val();
			},
			error:function(jqXHR, textStatus, errorThrown){
				swal("등록실패","댓글이 너무 깁니다!:"+errorThrown,"error");
				$('#rfr_content').val("");
		    }
		});
	}
	
	
	function replyEdit(no,content) { 
		$('#content'+no).html("");
		$('#content'+no).html(
			'<input type="text" class="form-control" id="edit'+no+'" value="'+content+'">'		
		);
		$('#bt'+no).html("");
		$('#bt'+no).html(
			'<input type=button class="btn btn-outline-primary" value="완료" onclick="replyEditSave('+no+');">&nbsp;'+
			'<input type=button class="btn btn-outline-danger" value="취소" onclick="replyEditCancel('+no +',\''+content+'\');">'
		);
	}
	
	function replyEditSave(no) {
		var content = $('#edit'+no).val();
		if(content == "" || content == null) {
			swal('댓글 수정실패','수정할 댓글을 입력하세요.','error');
			return;
		}
		
		$.ajax({
			url:'./freeboard_replyedit.do',
			data: {"no":no, "content" : content},
			success:function(){
				window.location.reload();
			},
			error:function(){
				swal('댓글 수정 오류','댓글을 수정하는데 오류가 발생했습니다. (replyEditSave)','error');
			}
		});
	}
	
	function replyEditCancel(no, content) {
		$('#content'+no).html("");
		$('#content'+no).html(content);
		$('#bt'+no).html("");
		$('#bt'+no).html(
			'<input type=button class="btn btn-outline-primary" value="수정" onclick="replyEdit('+no+',\''+content+'\');">&nbsp;'+
			'<input type=button class="btn btn-outline-danger" value="삭제" onclick="replyDelete('+no+';)">'
		);
	}
	
	function replyDelete(no) {	
		swal({
			  title: "댓글 삭제",
			  text: "댓글을 정말로 삭제하시겠습니까? ",
			  icon: "warning",
			  buttons: {
				  cancel: "취소",
				  catch: "삭제",
				}
			})
			.then((value) => {
				  switch (value) {
				    case "catch":
				     	 // 요청 승낙시 처리
				      	var data = {"rfr_no": no};
						$.ajax({
							url:'./freeboard_replydelete.do?',
							data:data,
							"success": function () {
								swal({
									title:"댓글 삭제",
									text : "댓글이 삭제되었습니다.",
									icon : "success"
								}).then((willDelete) => {
									location.href="./freeboard_detail.do?fr_no="+$('#freeNum').val();
								});
							},
							"error" : function () {
								swal({
									title:"댓글 삭제",
									text : "댓글 삭제에 실패했습니다. 관리자에게 문의하세요. (replyDelete)",
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
					<div class="panel panel-default" style="background-color: #f0f4ff;">
						<div class="panel-heading" style="background: #605E5E; color: white; "><h2 style="font-size: 20pt; font-weight: bold; padding-top: 10px; padding-bottom: 10px; color: white; ">Free Board</h2></div>
					<br><br>	
					<input type="hidden" value="${fbb.fr_no}" id="freeNum">
 						<table width="80%">
							  <tr>
							   <td>
							   <table width="100%">
							     <tr>
							      <td width="0">&nbsp;</td>
							      <th class="td1" width="20%">제목</th>
							      <td width="80%">${fbb.fr_title}</td>
							      <td width="0">&nbsp;</td>
							     </tr>
								 <tr class="tr1"><td colspan="4" width="407"></td></tr>
								 <tr>
							      <td width="0">&nbsp;</td>
							      <th class="td1" width="20%">작성자</th>
							      <td width="80%" id="fr_writer">${fbb.fr_id}</td>
							      <td width="0">&nbsp;</td>
							     </tr>
							     <tr class="tr1"><td colspan="4" width="407"></td></tr>
							    <tr>
							      <td width="0">&nbsp;</td>
							      <th class="td1" width="20%">조회수</th>
							      <td width="80%">${fbb.fr_count}</td>
							      <td width="0">&nbsp;</td>
							     </tr>
								 <tr class="tr1"><td colspan="4" width="407"></td></tr>
							    <tr>
							      <td width="0">&nbsp;</td>
							      <th class="td1" width="20%">날짜</th>
							      <td width="80%">${fbb.fr_date}</td>
							      <td width="0">&nbsp;</td>
							     </tr>
							      <tr class="tr1"><td colspan="4" width="407"></td></tr>
							                <tr>
							      <td width="0">&nbsp;</td>
							                   <td width="399" colspan="2" height="200">${fbb.fr_content}
							                </tr>
							     <tr class="tr1"><td colspan="4" width="407"></td></tr>
							     <tr class="tr1"><td colspan="4" width="407"></td></tr>
							     <tr>
							     	<td width="0">&nbsp;</td>
							     	<td colspan="3" width="407" align="center">
							     		<div class="mdc-card__primar" align="center"> 
							     			<div class="mdc-card" align="center"> 
							     			
							     			
			<div class="panel panel-default" style="background-color: #f0f4ff;">
				<div class="panel-heading" style="background: #605E5E; color: white; "><h2 style="font-size: 20pt; font-weight: bold; padding-top: 10px; padding-bottom: 10px; color: white; ">댓 글</h2></div>
			<br><br>
			<table class="table table-hover" style="width:80%;">  
			<tbody>
				<c:forEach var="fbrb" items="${reply}"> 
				<tr style="text-align: center;">
					<td style="width:10%; text-align: center;">${fbrb.rfr_id}</td>
					<td style="width:40%; text-align: center;" id="content${fbrb.rfr_no}"> ${fbrb.rfr_content} </td>
					<td style="width:30%; text-align: center;"> ${fbrb.rfr_date}</td>
					<td style="width:20%; text-align: center;" id="bt${fbrb.rfr_no}">
						<c:choose>
							<c:when test="${session == fbrb.rfr_id}">
								<input type=button class="btn btn-outline-primary" value="수정" onclick="replyEdit(${fbrb.rfr_no},'${fbrb.rfr_content}');">&nbsp;
								<input type=button class="btn btn-outline-danger" value="삭제" onclick="replyDelete(${fbrb.rfr_no});">
							</c:when>
						</c:choose>
					</td>
				</tr>   
				</c:forEach>
				<tr id="replySave">
					<td colspan="4" width="407">		
					 <table class="table table-hover" style="width:80%;" >
							<tr>
								<th style=" background-color:#f0f4ff; padding-left: 30px; padding-top: 30px; color: #0073e6;">댓글쓰기</th>
							</tr>
						<tr>				   
						   <td style="width:100%; background-color:#f0f4ff;">
						   	   <textarea cols=100 rows=10 id="rfr_content" style="color: #0073e6; border-color: #3cc3ff5e;" name="rfr_content"></textarea> 
						   </td>						   
						</tr>
						<tr align="center">
							<td style="width:100%; background-color:#f0f4ff;" >
								<input type=button class="btn btn-outline-success" value="댓글저장" onclick="replyCheck()">
							</td>
						</tr>
					 </table>
					</td>
				</tr>
			</tbody>	
			</table>
			</div>
												
																								
											</div>
							     		</div>
							     	</td>
							     </tr>
							     <tr align="center">
							      <td width="0">&nbsp;</td>
							      <td colspan="2" width="399">
							      <span id="myfr"></span>
									<input type=button class="btn btn-outline-dark" value="목록으로" onclick="location.href='./freeboard_list.do'">
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