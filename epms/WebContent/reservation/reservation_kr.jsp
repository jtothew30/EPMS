<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title></title>
<meta http-equiv="CONTENT-TYPE" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css" href="./css/map.css">
<link rel="stylesheet" type="text/css" href="./bootstrap/css/bootstrap.min.css">
<script src="./js/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=hdmif033op&submodules=panorama"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script src="./js/reservation/reservation_map.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="./css/top.css">
<script  src="./js/top.js"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/mustache.js/0.7.2/mustache.min.js'></script>
<link rel="stylesheet" href="./template/css/style.css">
<link rel="shortcut icon" href="../template/images/favicon.png" />
<link rel="stylesheet" href="./template/node_modules/mdi/css/materialdesignicons.min.css">
<script src="./js/user/login.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<style type="text/css">
		.mapSelect {position:relative;left:25px;top:22px;float:none;width:210px;height:280px;background:url(./img/common/map/bg_map.gif) 0 0 no-repeat;}
		
		.mapSelect .imgMap {position:absolute; top:0; left:0; z-index:100;}
		
		.mapSelect ul {position:absolute; top:0; left:0; z-index:0;}
		.mapSelect ul li {display:none; position:absolute;}
		.mapSelect ul li.map1 {top:37px; left:61px;}
		.mapSelect ul li.map2 {top:31px; left:35px;}
		.mapSelect ul li.map3 {top:21px; left:56px;}
		.mapSelect ul li.map4 {top:13px; left:80px;}
		.mapSelect ul li.map5 {top:85px; left:37px;}
		.mapSelect ul li.map6 {top:98px; left:68px;}
		.mapSelect ul li.map7 {top:71px; left:86px;}
		.mapSelect ul li.map8 {top:77px; left:110px;}
		.mapSelect ul li.map9 {top:121px; left:124px;}
		.mapSelect ul li.map10 {top:143px; left:157px;}
		.mapSelect ul li.map11 {top:163px; left:138px;}
		.mapSelect ul li.map12 {top:139px; left:98px;}
		.mapSelect ul li.map13 {top:124px; left:51px;}
		.mapSelect ul li.map14 {top:159px; left:33px;}
		.mapSelect ul li.map15 {top:164px; left:11px;}
		.mapSelect ul li.map16 {top:232px; left:4px;}
</style>
<script type="text/javascript">
function render_areasearch(area) {
	location.href = "./reservation_searchboard.do?area=" + area;
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
          	<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-3">
          		<div class="mdc-card__primary">
          			<div class="container" style="max-width: 400px; overflow: hidden;">
                       <img src="img/common/reservation.png" style="width:100%;object-fit:contain;">
                     </div>
          		</div>
              
             </div>
             
          	<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-3">
              <div class="mdc-card" style="background-color:#f0f4ff;">
          		 <section class="mdc-card__primary">
                  <h1 class="mdc-card__title mdc-card__title--large">① 찾으시는 시,도,군,구를 선택하세요.</h1>
                </section>
                <center>
				<div class="mapSelect" style="left:0px;">
					<ul>
						<li class="map1" style="display: none;"><img src="./img/common/map/img_map_01.png" alt="서울특별시"></li>
						<li class="map2" style="display: none;"><img src="./img/common/map/img_map_02.png" alt="인천광역시"></li>
						<li class="map3" style="display: none;"><img src="./img/common/map/img_map_03.png" alt="경기도"></li>
						<li class="map4" style="display: none;"><img src="./img/common/map/img_map_04.png" alt="강원도"></li>
						<li class="map5" style="display: none;"><img src="./img/common/map/img_map_05.png" alt="충청남도"></li>
						<li class="map6" style="display: none;"><img src="./img/common/map/img_map_06.png" alt="대전광역시"></li>
						<li class="map7" style="display: none;"><img src="./img/common/map/img_map_07.png" alt="충청북도"></li>
						<li class="map8" style="display: none;"><img src="./img/common/map/img_map_08.png" alt="경상북도"></li>
						<li class="map9" style="display: none;"><img src="./img/common/map/img_map_09.png" alt="대구광역시"></li>
						<li class="map10" style="display: none;"><img src="./img/common/map/img_map_10.png" alt="울산광역시"></li>
						<li class="map11" style="display: none;"><img src="./img/common/map/img_map_11.png" alt="부산광역시"></li>
						<li class="map12" style="display: none;"><img src="./img/common/map/img_map_12.png" alt="경상남도"></li>
						<li class="map13" style="display: none;"><img src="./img/common/map/img_map_13.png" alt="전라북도"></li>
						<li class="map14" style="display: none;"><img src="./img/common/map/img_map_14.png" alt="전라남도"></li>
						<li class="map15" style="display: none;"><img src="./img/common/map/img_map_15.png" alt="광주광역시"></li>
						<li class="map16" style="display: none;"><img src="./img/common/map/img_map_16.png" alt="제주특별자치도"></li>
					</ul>
					<img src="./img/common/map/img_map_area.gif" alt="" usemap="#Map" class="imgMap" border="0">
					<map name="Map" id="Map">
						<area shape="poly" coords="72,56,76,56,76,54,78,53,80,52,81,49,84,49,86,49,87,51,87,54,90,56,91,59,89,62,86,64,82,65,79,64,76,64,74,63,73,60,72,57" href="javascript:render_areasearch('서울특별시');" title="서울특별시">
						<area shape="poly" coords="61,57,64,56,66,56,69,56,72,57,72,59,72,62,71,64,70,65,66,67,64,68,61,69,57,69,54,69,53,67,53,64,52,61,47,61,45,59,44,55,43,53,44,52,48,54,51,55,53,56,55,56,57,54,57,52,56,50,48,50,45,48,45,45,47,43,51,42,54,41,58,43,59,45,60,47,60,49,60,52,60,54,60,55" href="javascript:render_areasearch('인천');" title="인천">
						<area shape="poly" coords="56,70,60,69,65,67,69,66,71,64,72,62,75,63,78,65,81,65,84,65,87,62,90,60,90,57,88,55,86,52,83,49,80,49,79,51,77,53,75,54,73,57,72,56,67,55,66,55,62,56,61,56,61,53,61,50,62,47,62,45,65,43,67,42,68,39,65,37,66,35,69,35,72,34,74,31,75,30,73,27,70,26,74,23,75,21,77,23,79,25,81,28,82,30,85,31,88,32,90,33,93,35,95,37,95,38,98,40,98,42,98,44,97,47,96,50,96,52,98,54,99,56,101,57,105,58,107,58,108,60,108,62,106,63,107,66,108,68,107,71,106,73,107,76,107,78,105,81,103,83,101,85,98,86,95,87,94,88,92,91,90,92,87,93,85,94,83,92,78,92,73,93,71,94,67,94,65,94,63,92,61,90,59,87,57,85,56,82,56,80,61,79,62,78,60,76,58,74,56,71,55,69" href="javascript:render_areasearch('경기');" title="경기도">
						<area shape="poly" coords="104,84,108,84,110,83,111,82,114,79,116,78,116,81,118,82,122,80,125,79,127,81,128,83,129,84,135,84,138,86,138,87,141,89,144,89,148,89,150,88,154,88,155,88,160,86,162,88,167,87,170,86,173,83,172,79,169,75,167,71,165,67,164,61,161,59,159,55,157,52,152,47,151,46,147,39,144,35,142,32,140,28,138,25,137,23,136,20,135,16,134,15,128,14,126,18,125,21,123,21,120,21,112,21,109,21,106,21,104,21,100,20,97,21,96,22,92,20,90,19,86,21,82,23,78,23,77,25,81,29,81,32,87,34,90,37,92,39,95,41,96,43,96,47,95,51,94,53,95,55,98,57,101,60,104,61,105,63,106,65,106,69,105,72,106,76,105,79,104,81" href="javascript:render_areasearch('강원');" title="강원도">
						<area shape="poly" coords="56,138,60,138,63,137,66,137,69,134,71,132,73,128,75,128,77,130,78,132,81,134,84,132,87,130,91,130,94,133,94,136,95,136,99,137,105,136,105,135,102,130,102,128,100,124,98,123,96,126,93,125,89,123,87,121,87,119,88,114,90,112,92,110,91,107,89,104,87,101,90,97,91,96,92,94,91,89,89,88,81,88,78,88,75,89,72,90,71,93,67,94,64,96,61,93,58,91,57,90,53,88,52,91,50,94,47,90,46,90,46,94,46,96,43,93,41,93,39,94,39,97,39,99,38,99,36,101,37,105,40,107,42,110,42,115,43,116,44,120,45,123,48,122,48,119,46,115,45,112,44,108,44,106,43,101,45,103,48,105,49,107,50,110,52,115,52,117,52,122,52,126,53,129,53,131,54,134,54,137,55,138" href="javascript:render_areasearch('충남');" title="충청남도">
						<area shape="poly" coords="87,114,89,112,91,109,93,109,95,112,97,112,98,114,97,117,96,119,97,121,97,122,95,126,93,126,92,125,89,123,88,122,86,119,86,117,86,114" href="javascript:render_areasearch('대전');" title="대전광역시">
						<area shape="poly" coords="85,101,88,96,89,93,88,89,92,87,94,84,98,82,101,80,105,78,108,77,113,75,115,73,117,72,120,76,124,74,128,73,130,76,135,78,137,78,140,80,140,83,137,87,137,88,137,91,137,94,132,96,131,93,128,92,123,93,123,94,121,97,114,100,113,101,110,104,109,106,112,108,111,112,110,116,108,119,108,121,111,122,114,123,116,125,117,128,113,133,112,135,106,138,105,136,102,135,100,133,99,129,99,128,97,126,96,125,93,122,95,118,96,116,94,114,90,112,87,109,87,106,86,101" href="javascript:render_areasearch('충북');" title="충청북도">
						<area shape="poly" coords="112,143,110,140,110,137,110,133,117,128,114,124,114,121,112,120,108,118,108,115,111,111,112,108,110,104,110,103,113,99,117,97,120,95,123,93,126,91,132,92,133,93,136,91,137,87,139,83,142,81,146,79,150,79,158,78,162,79,170,78,173,75,177,79,176,83,176,89,177,94,178,97,179,99,176,103,175,107,176,109,178,113,176,117,175,121,175,125,175,128,175,132,176,137,176,140,181,139,183,138,183,143,182,146,180,150,177,152,172,152,170,149,165,151,163,153,160,155,155,157,153,159,147,159,144,157,142,156,141,154,140,152,146,150,147,146,149,143,150,141,150,137,148,136,145,135,142,135,137,136,132,139,129,141,131,143,132,145,131,150,131,151,131,153,128,155,122,155,123,150,122,147,119,145,117,145,113,144" href="javascript:render_areasearch('경북');" title="경상북도">
						<area shape="poly" coords="133,152,137,151,139,151,143,150,146,148,148,146,149,143,151,140,152,138,150,135,148,134,145,134,141,134,138,134,136,135,132,137,131,139,131,141,133,143,133,144,133,146,132,149,132,150,132,152" href="javascript:render_areasearch('대구');" title="대구광역시">
						<area shape="poly" coords="162,157,162,153,165,149,167,147,170,147,171,147,173,149,178,149,179,148,182,146,182,148,181,154,181,158,181,160,180,164,180,167,179,169,177,168,175,167,173,164,172,162,168,162,167,161,165,160,163,159,160,158" href="javascript:render_areasearch('울산');" title="울산광역시">
						<area shape="poly" coords="151,171,156,169,159,166,162,163,164,162,165,164,167,166,168,167,169,167,169,172,167,175,165,177,164,178,162,181,160,182,159,184,157,185,153,185,152,189,151,190,150,183,148,182,147,180,146,180,144,180,141,182,139,185,138,186,136,183,135,181,135,178,139,176,142,176,146,177,149,175,151,172" href="javascript:render_areasearch('부산');" title="부산광역시">
						<area shape="poly" coords="109,190,108,185,109,181,108,179,106,177,104,175,102,172,101,170,99,165,98,162,101,158,101,157,100,151,99,149,102,144,103,139,107,136,111,135,113,134,121,136,122,138,124,141,124,143,125,146,128,147,134,147,137,146,140,146,144,148,147,150,150,151,154,151,158,150,161,149,163,150,163,152,164,154,166,156,170,156,172,159,171,161,168,163,165,166,160,168,157,169,157,173,152,174,145,174,143,176,142,178,145,181,145,183,140,183,138,186,134,187,134,190,134,193,133,196,130,198,126,193" href="javascript:render_areasearch('경남');" title="경상남도">
						<area shape="poly" coords="49,159,55,154,59,151,60,148,56,145,54,142,56,140,56,137,58,135,62,136,65,135,69,133,71,131,74,126,75,128,78,131,79,132,84,130,87,129,90,130,92,132,95,135,98,135,101,135,105,134,107,133,111,134,113,135,114,140,110,142,107,144,105,147,104,149,102,154,102,155,102,159,102,162,102,164,100,169,99,171,92,169,86,169,81,171,78,172,76,167,72,164,71,163,68,162,63,162,62,164,61,167,58,168,54,169,54,165,54,162,49,160" href="javascript:render_areasearch('전북');" title="전라북도">
						<area shape="poly" coords="113,194,111,190,112,187,111,184,108,181,106,178,105,176,103,173,103,172,101,168,101,168,92,167,89,167,86,167,83,168,81,168,79,169,77,170,75,172,75,175,75,177,72,180,69,181,66,181,62,180,61,179,59,175,59,173,62,170,66,170,69,170,71,169,74,167,76,165,76,164,74,162,70,161,66,160,64,162,62,164,59,166,55,166,53,165,52,165,49,166,46,167,44,170,43,173,41,176,42,179,45,183,46,186,43,186,41,183,40,183,34,185,32,187,30,191,32,195,38,190,39,190,40,193,39,197,42,201,45,203,47,205,50,207,49,209,46,209,42,207,38,207,38,210,42,212,43,215,49,215,50,218,48,220,47,224,50,228,53,228,56,222,58,219,60,218,61,221,65,222,68,218,70,216,72,213,75,212,79,210,82,207,84,206,84,210,80,213,77,217,81,219,86,220,88,219,90,214,91,212,91,208,91,205,91,202,93,200,95,203,98,205,98,209,100,212,105,209,108,204,104,201,101,197,104,195,108,194,110,194,111,194,112,194" href="javascript:render_areasearch('전남');" title="전라남도">
						<area shape="poly" coords="60,177,58,173,60,170,62,169,66,171,70,170,72,171,75,173,74,176,72,179,69,180,66,181,63,181,61,178" href="javascript:render_areasearch('광주');" title="광주광역시">
						<area shape="poly" coords="33,242,38,238,43,236,49,235,50,234,53,232,57,233,62,233,66,233,67,234,67,237,66,239,63,241,61,243,58,245,58,247,55,248,52,248,48,248,41,249,40,249,36,246,34,245,33,244" href="javascript:render_areasearch('제주');" title="제주특별자치도">
					</map>
				</div>
				</center>
	<script>
		function fe_mapSelect(){
			var $container = $(".mapSelect");
			var $overImgae = $container.find("li");
			var $trigger = $container.find("area");
		
			$trigger.each(function(i){
				$(this).mouseenter(function(){
					$overImgae.hide();
					$overImgae.eq(i).show();
				});
			});
		
			$container.mouseleave(function(){
				$overImgae.hide();
			});
		}
		
		fe_mapSelect();
		
		var recentModalList = [];
		$(document).ready(function () { 
			//모달이 뜰 때 객체를 리스트에 추가 (Add modal to list)
			$('.modal').on('shown.bs.modal', function (e) { 
				recentModalList.push(e.target); }); 
			//모달이 닫힐 때 객체를 리스트에서 삭제. (Remove modal from list) 
			$('.modal').on('hide.bs.modal', function (e) { 
				customModalClosed(e); 
				console.log(recentModalList.length); 
			}); 
		}); 
		
		var customModalClosed = function (e) { 
			//나를 지운다.(Remove me in list) 
			for (var i = recentModalList.length - 1; i >= 0; i--) { 
				if (recentModalList[i] == e.target) { 
					recentModalList.splice(i, 1); }
				} 
			//이전 모달이 있으면 포커싱.(Focus to before modal)
			if (recentModalList.length > 0) { 
				recentModalList[recentModalList.length - 1].focus();
			} 
		}; 
			//동적 모달일 경우, document.ready의 이벤트가 안 먹기 때문에 이걸로 호출. 
			
		var showModalCustom = function (modalObj) { 
			//모달이 뜰 때 객체를 리스트에 추가 (Add modal to list) 
			modalObj.on('shown.bs.modal', function (e) { 
				$(this).off(e); 
				//이벤트 제거
				recentModalList.push(e.target); 
			}); 
			modalObj.on('hide.bs.modal', function (e) { 
				$(this).off(e); 
				//이벤트 제거 
				customModalClosed(e); 
				console.log(recentModalList.length); 
				}); 
			modalObj.modal('show'); 
			
			
		};
		
		// 맵으로 이동
		 function fnMove(){
		        var offset = $("#map").offset();
		        $('html, body').animate({scrollTop : offset.top}, 400);
		    }

	</script>
	
	</div></div>
	<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-6">
              <div class="mdc-card" style="background-color:#f0f4ff;">
                <section class="mdc-card__primary">
                  <h1 class="mdc-card__title mdc-card__title--large">② 상세 주소를 검색하세요.</h1>
                </section>
               <div class="panel panel-default" align="center">
					<form action="./reservation_searchboard.do?">
						<input type="hidden" name="area" value="${param.area}">
						<input type="text" class="form-control" style="width:60%; display: inline-block;" name="search" placeholder="시,도,군,구를 선택한 후에 검색하세요." size="50">&nbsp;&nbsp;<input type="button" class="btn btn-outline-success" value="검색" onclick="submit();">
					</form>
					<table class="table table-hover"  style="width:50%; float:none; text-align: center;">
						<c:if test="${boardlist.size() == 0}">
							<tr>
							<td style="text-align: center;">죄송합니다. 현재 서비스중인 곳이 없습니다.</td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${boardlist}">
							<c:if test="${boardlist.size() != 0}">
								<tr>
									<td style="text-align: center;">
										<script>lat.push(${item.pa_lat});lon.push(${item.pa_lon});add.push('${item.pa_address}');</script>
										<a href="" onclick="modalMap(${item.pa_lat},${item.pa_lon},'${item.pa_address}'); fnMove();" data-toggle="modal" data-target="#exampleModal">${item.pa_address}</a>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
					<nav aria-label="...">
					  <ul class="pagination justify-content-center">
					  		<c:choose>
						  		<c:when test="${startPage != 1 }">
										<li class='page-item'><a href='./reservation_searchboard.do?area=&search=${search}&pageNum=${startPage-1}' class="page-link" aria-label='Previous'><span aria-hidden='true'>Previous</span></a></li>
								</c:when>
								<c:otherwise>
									<li class='page-item disabled'><a href='#' class="page-link" aria-label='Previous'><span aria-hidden='true'>Previous</span></a></li>
								</c:otherwise>
							</c:choose>
					  		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
					  			<c:choose>
									<c:when test="${pageNum ==i }">
										<li class='page-item active'><a href='./reservation_searchboard.do?area=&search=${search}&pageNum=${i}' class="page-link">${i}<span class='sr-only'>${i}</span></a></li>
									</c:when>
									<c:otherwise>
										<li class='page-item'><a href='./reservation_searchboard.do?area=&search=${search}&pageNum=${i}' class="page-link">${i}<span class='sr-only'>${i}</span></a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:choose>
								<c:when test="${endPage == pageCount }">
									<li class='page-item disabled'><a href='#' class="page-link" aria-label='Next'><span aria-hidden='true'>Next</span></a></li>
								</c:when>
								<c:otherwise>
									<li class='page-item'><a href='./reservation_searchboard.do?area=&search=${search}&pageNum=${endPage+1}' class="page-link" aria-label='Next'><span aria-hidden='true'>Next</span></a></li>
								</c:otherwise>
							</c:choose>
					  </ul>
					</nav>
	</div>
	</div>
	</div>
	<!-- ModalMap -->
	<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-12">
              <div class="mdc-card" style="background-color:#f0f4ff;">
              <section class="mdc-card__primary">
                  <h1 class="mdc-card__title mdc-card__title--large">③ 마커를 선택하여 예약을 진행하세요.</h1>
                </section>
	      <!-- Modal Map --> 
	      <center>
			<div id="map" style="width:1000px;height:700px;"></div>
			</center>
	</div>
	</div>

		<!-- ModalRequest -->
		<div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel1" aria-hidden="true">
		  <div class="modal-dialog modal-sm modal-center"  role="document"> 
		    <div class="modal-content"> 
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel1">예약 요청하기</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
			      	요청 시간 : <span class="requestTime"></span><br>
			      	요청 지역 : <span class="requestAddress"></span><br>
			      	주차 구역 : <span class="requestArea"></span><br> 
			      	메시지 : <center> <textarea class="requestMessage" rows="10" cols="40" placeholder="전송하고자하는 메시지를 입력하세요."></textarea><br><br>
			      	<input type="button" class="btn btn-primary" value="예약 요청하기" onclick="requestReservation();"></center>
		      </div>
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
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<script src="./bootstrap/js/bootstrap.min.js"></script>

</body>
</html>