<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head>
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
<link rel="stylesheet" type="text/css" href="https://jonthornton.github.io/jquery-timepicker/jquery.timepicker.css">
<link rel="stylesheet" type="text/css" href="https://jonthornton.github.io/jquery-timepicker/lib/bootstrap-datepicker.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="./css/top.css">
<script  src="./js/top.js"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/mustache.js/0.7.2/mustache.min.js'></script>
<link rel="stylesheet" href="./template/css/style.css">
<link rel="shortcut icon" href="../template/images/favicon.png" />
<link rel="stylesheet" href="./template/node_modules/mdi/css/materialdesignicons.min.css">
<script src="./js/user/login.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
</head>
<script type="text/javascript">
	function render_areasearch(area) {
		location.href = "./reservation_searchboard.do?area=" + area;
	}
</script>
<style type="text/css">
		.mapSelect {position:relative;left:25px;top:22px;float:none;width:518px;height:398px;background:url(./img/common/map2/bg_seoul.png) 0 0 no-repeat;}
		
		.mapSelect .imgMap {position:absolute; top:0; left:0; z-index:100;}
		
		.mapSelect ul {position:absolute; top:0; left:0; z-index:0;}
		.mapSelect ul li {display:none; position:absolute;}
		.mapSelect ul li.map1 {top:6px; left:334px;}
		.mapSelect ul li.map2 {top:-18px; left:275px;}
		.mapSelect ul li.map3 {top:25px; left:258px;}
		.mapSelect ul li.map4 {top:117px; left:367px;}
		.mapSelect ul li.map5 {top:137px; left:312px;}
		.mapSelect ul li.map6 {top:95px; left:254px;}
		.mapSelect ul li.map7 {top:102px; left:225px;}
		.mapSelect ul li.map8 {top:137px; left:168px;}
		.mapSelect ul li.map9 {top:62px; left:144px;}
		.mapSelect ul li.map10 {top:187px; left:356px;}
		.mapSelect ul li.map11 {top:190px; left:294px;}
		.mapSelect ul li.map12 {top:192px; left:240px;}
		.mapSelect ul li.map13 {top:213px; left:223px;}
		.mapSelect ul li.map14 {top:163px; left:121px;}
		
		.mapSelect ul li.map15 {top:175px; left:422px;}
		.mapSelect ul li.map16 {top:233px; left:365px;}
		.mapSelect ul li.map17 {top:245px; left:303px;}
		.mapSelect ul li.map18 {top:259px; left:262px;}
		.mapSelect ul li.map19 {top:270px; left:172px;}
		.mapSelect ul li.map20 {top:303px; left:168px;}
		.mapSelect ul li.map21 {top:313px; left:132px;}
		.mapSelect ul li.map22 {top:217px; left:140px;}
		.mapSelect ul li.map23 {top:272px; left:60px;}
		.mapSelect ul li.map24 {top:222px; left:70px;}
		.mapSelect ul li.map25 {top:145px; left:2px;}
</style>
<body>
<div class="body-wrapper"> 
  <jsp:include page="../common/_sidebar.html"></jsp:include>
  <jsp:include page="../common/_navbar.html"></jsp:include>
  <div class="page-wrapper mdc-toolbar-fixed-adjust">
      <main class="content-wrapper">
        <div class="mdc-layout-grid">
          <div class="mdc-layout-grid__inner">
          	<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-6">
              <div class="mdc-card" style="background-color:#f0f4ff;">
          		 <section class="mdc-card__primary">
                  <h1 class="mdc-card__title mdc-card__title--large">① 찾으시는 시,도,군,구를 선택하세요.</h1>
                </section>
                <center>
	<div class="mapSelect">
		<ul> 
			<li class="map1" style="display: none;"><img src="./img/common/map2/img_map_01.png" alt="서울특별시 노원구""></li>
			<li class="map2" style="display: none;"><img src="./img/common/map2/img_map_02.png" alt="서울특별시 도봉구"></li>
			<li class="map3" style="display: none;"><img src="./img/common/map2/img_map_03.png" alt="서울특별시 강북구"></li>
			<li class="map4" style="display: none;"><img src="./img/common/map2/img_map_04.png" alt="서울특별시 중량구"></li>
			<li class="map5" style="display: none;"><img src="./img/common/map2/img_map_05.png" alt="서울특별시 동대문구"></li>
			<li class="map6" style="display: none;"><img src="./img/common/map2/img_map_06.png" alt="서울특별시 성북구"></li>
			<li class="map7" style="display: none;"><img src="./img/common/map2/img_map_07.png" alt="서울특별시 종로구"></li>
			<li class="map8" style="display: none;"><img src="./img/common/map2/img_map_08.png" alt="서울특별시 서대문구"></li>
			<li class="map9" style="display: none;"><img src="./img/common/map2/img_map_09.png" alt="서울특별시 은평구"></li>
			<li class="map10" style="display: none;"><img src="./img/common/map2/img_map_10.png" alt="서울특별시 광진구"></li>
			<li class="map11" style="display: none;"><img src="./img/common/map2/img_map_11.png" alt="서울특별시 성동구"></li>
			<li class="map12" style="display: none;"><img src="./img/common/map2/img_map_12.png" alt="서울특별시 중구"></li>
			<li class="map13" style="display: none;"><img src="./img/common/map2/img_map_13.png" alt="서울특별시 용산구"></li>
			<li class="map14" style="display: none;"><img src="./img/common/map2/img_map_14.png" alt="서울특별시 마포구"></li>
			<li class="map15" style="display: none;"><img src="./img/common/map2/img_map_15.png" alt="서울특별시 강동구"></li>
			<li class="map16" style="display: none;"><img src="./img/common/map2/img_map_16.png" alt="서울특별시 송파구"></li>
			<li class="map17" style="display: none;"><img src="./img/common/map2/img_map_17.png" alt="서울특별시 강남구"></li>
			<li class="map18" style="display: none;"><img src="./img/common/map2/img_map_18.png" alt="서울특별시 서초구"></li>
			<li class="map19" style="display: none;"><img src="./img/common/map2/img_map_19.png" alt="서울특별시 동작구"></li>
			<li class="map20" style="display: none;"><img src="./img/common/map2/img_map_20.png" alt="서울특별시 관악구"></li>
			<li class="map21" style="display: none;"><img src="./img/common/map2/img_map_21.png" alt="서울특별시 금천구"></li>
			<li class="map22" style="display: none;"><img src="./img/common/map2/img_map_22.png" alt="서울특별시 영등포구"></li>
			<li class="map23" style="display: none;"><img src="./img/common/map2/img_map_23.png" alt="서울특별시 구로구"></li>
			<li class="map24" style="display: none;"><img src="./img/common/map2/img_map_24.png" alt="서울특별시 양천구"></li>
			<li class="map25" style="display: none;"><img src="./img/common/map2/img_map_25.png" alt="서울특별시 강서구"></li>
		</ul>
		<img src="./img/common/map2/img_map_area.png" alt="" usemap="#Map" class="imgMap" border="0">
		<map name="Map" id="Map">
			<area shape="poly" coords="357,9,364,9,369,10,373,6,379,5,382,8,384,13,386,15,393,17,398,18,397,24,396,27,391,32,394,37,397,42,396,50,395,56,392,62,394,71,393,77,398,81,407,81,414,84,417,92,416,102,410,108,407,113,400,116,393,120,389,116,378,116,361,126,356,124,344,114,338,105,333,101,340,90,343,85,349,92,350,78,348,75,347,62,345,57,342,46,346,24,346,18,349,17,355,13" href="javascript:render_areasearch('서울특별시 노원구');" title="서울특별시 노원구">
			<area shape="poly" coords="295,6,304,1,311,0,318,7,320,14,325,13,329,12,333,9,339,11,342,18,341,37,338,48,343,61,346,77,347,83,341,81,336,91,332,102,324,91,319,81,311,76,303,74,301,67,303,57,305,47,300,37,296,29,294,28,292,22,292,18,289,15,293,7" href="javascript:render_areasearch('서울특별시 도봉구');" title="서울특별시 도봉구">
			<area shape="poly" coords="285,26,288,24,292,31,297,38,301,46,299,51,297,62,297,71,303,78,311,81,323,95,334,107,340,112,326,128,321,130,314,128,309,129,301,125,293,119,294,112,282,108,277,102,271,98,264,93,266,87,263,83,265,79,261,70,257,66,266,62,269,54,274,53,275,47,277,41,277,41,273,32,278,27,282,25" href="javascript:render_areasearch('서울특별시 강북구');" title="서울특별시 강북구">
			<area shape="poly" coords="368,127,379,120,388,119,393,125,401,119,417,116,423,123,423,135,425,142,420,149,423,151,421,157,417,161,414,172,408,175,403,186,388,188,378,188,377,179,372,167,369,159,369,147,371,147,371,137,370,132" href="javascript:render_areasearch('서울특별시 중량구');" title="서울특별시 중량구">
			<area shape="poly" coords="312,181,328,164,334,153,339,156,344,148,356,146,360,141,367,137,364,152,366,164,375,181,374,194,368,204,360,202,345,194,340,188,332,186,320,187,318,191,312,189,311,184" href="javascript:render_areasearch('서울특별시 동대문구');" title="서울특별시 동대문구">
			<area shape="poly" coords="254,100,261,96,271,103,282,114,291,115,291,124,303,130,317,134,320,136,343,116,354,127,364,129,364,129,366,135,356,139,351,145,342,144,338,151,331,151,329,159,325,159,324,166,306,180,304,177,299,172,294,174,290,167,286,161,285,157,274,159,269,157,266,154,257,151,260,147,268,143,268,138,266,134,267,129,263,123,263,118,256,107,252,104" href="javascript:render_areasearch('서울특별시 성북구');" title="서울특별시 성북구">
			<area shape="poly" coords="227,113,234,112,237,107,245,106,249,102,255,113,261,122,264,129,264,142,255,146,254,152,260,156,266,158,268,162,281,162,289,170,290,177,300,177,305,183,309,182,309,189,306,190,294,189,284,191,280,188,273,192,252,191,246,192,235,186,231,181,235,177,234,170,237,168,235,162,236,152,229,146,231,141,228,135,228,127,226,125,226,116" href="javascript:render_areasearch('서울특별시 종로구');" title="서울특별시 종로구">
			<area shape="poly" coords="170,183,181,170,181,170,185,175,185,175,192,175,194,168,194,168,198,167,201,158,201,158,216,149,216,149,216,142,216,142,225,138,225,138,226,142,226,142,228,150,228,150,232,153,232,153,232,164,234,169,230,178,228,181,228,181,238,192,244,195,241,204,237,208,214,210,208,212,197,206,201,201,193,197,193,197,183,192,174,187" href="javascript:render_areasearch('서울특별시 서대문구');" title="서울특별시 서대문구">
			<area shape="poly" coords="173,78,171,76,182,82,194,79,201,73,209,72,209,72,219,62,219,62,227,67,236,78,241,86,247,95,247,95,249,100,237,106,232,109,225,111,223,114,225,134,219,137,211,144,212,150,203,153,195,161,196,166,193,165,188,172,184,172,185,168,180,165,165,182,165,179,154,170,144,160,145,159,148,155,149,157,148,160,153,165,157,164,164,164,164,157,168,155,167,149,165,145,168,139,167,136,166,128,170,118,173,116,172,111,175,109,174,104,172,103,173,99,176,97,177,94,177,89" href="javascript:render_areasearch('서울특별시 은평구');" title="서울특별시 은평구">
			<area shape="poly" coords="378,192,398,192,405,188,409,191,406,200,406,206,410,210,417,205,421,206,417,216,414,225,409,230,400,240,392,245,386,252,378,252,355,244,358,238,373,208,371,203" href="javascript:render_areasearch('서울특별시 광진구');" title="서울특별시 광진구">
			<area shape="poly" coords="312,193,320,194,331,189,342,193,351,202,363,206,369,207,369,211,353,243,317,230,307,230,303,236,295,236,294,230,305,217,305,213,310,211,313,205,316,202,315,199,313,197" href="javascript:render_areasearch('서울특별시 성동구');" title="서울특별시 성동구">
			<area shape="poly" coords="250,195,271,196,282,191,286,193,301,192,309,192,311,200,310,206,304,210,301,213,301,218,292,226,289,221,285,218,277,223,273,217,269,214,257,212,248,213,247,214,242,209,243,205,246,201" href="javascript:render_areasearch('서울특별시 중구');" title="서울특별시 중구">
			<area shape="poly" coords="242,214,248,219,253,215,267,217,274,225,278,226,285,221,288,225,292,231,293,237,300,239,298,244,291,251,282,260,263,270,231,258,228,251,224,243,231,239,238,226,242,223,241,216" href="javascript:render_areasearch('서울특별시 용산구');" title="서울특별시 용산구">
			<area shape="poly" coords="142,163,183,195,197,202,197,202,196,209,209,215,238,210,240,212,240,212,238,214,238,214,239,220,235,227,224,242,216,240,210,230,210,230,205,232,197,227,197,227,183,229,123,183,137,177,137,170,138,168" href="javascript:render_areasearch('서울특별시 마포구');" title="서울특별시 마포구">
			<area shape="poly" coords="461,197,474,188,481,184,486,177,489,177,493,176,492,181,498,194,498,198,501,203,501,211,502,215,506,218,502,221,503,226,497,223,494,225,493,226,481,228,480,225,471,236,471,239,471,242,470,244,467,249,467,251,462,256,461,263,457,266,457,267,453,263,447,262,426,252,428,249,431,244,432,238,432,236,423,231,427,223,434,208,439,201,448,197,450,196" href="javascript:render_areasearch('서울특별시 강동구');" title="서울특별시 강동구">
			<area shape="poly" coords="367,270,370,267,375,270,384,270,391,268,400,264,403,261,406,260,411,256,415,246,421,239,422,234,425,236,429,240,426,246,424,250,424,256,433,259,450,266,454,270,454,274,451,279,454,285,460,288,465,286,466,284,470,289,473,288,475,295,476,302,473,306,464,313,460,323,453,329,449,330,442,327,440,330,442,335,447,338,445,339,437,339,433,337,424,324,415,309,408,301,399,296,388,293,376,290,371,288,369,283,368,276" href="javascript:render_areasearch('서울특별시 송파구');" title="서울특별시 송파구">
			<area shape="poly" coords="316,247,329,250,344,254,352,260,363,268,365,281,368,291,375,295,389,298,404,303,412,311,420,325,432,342,432,345,426,347,424,352,421,350,413,347,405,347,398,349,398,350,393,341,389,335,386,332,389,326,382,327,377,328,375,327,372,331,366,334,357,335,351,336,346,336,341,328,338,323,334,311,325,314,316,289,310,272,307,262,306,259,304,258,309,253" href="javascript:render_areasearch('서울특별시 강남구');" title="서울특별시 강남구">
			<area shape="poly" coords="262,286,269,285,274,284,281,278,285,277,290,275,293,269,296,266,301,262,303,260,304,267,308,279,312,289,315,298,321,308,323,317,324,319,332,319,334,319,336,330,344,340,354,342,366,340,373,337,378,333,384,333,383,335,388,340,392,348,396,355,397,358,391,362,389,372,385,375,380,378,372,378,371,384,369,388,346,387,340,387,336,386,331,382,327,381,326,377,330,374,329,371,330,367,324,359,329,358,327,353,324,350,326,345,320,342,318,348,316,353,312,356,308,357,305,356,303,359,300,358,292,352,289,344,287,340,280,341,280,346,279,348,275,350,271,353,271,340,267,338,264,333,263,326,265,313,265,301,266,297,268,295,268,292,262,288" href="javascript:render_areasearch('서울특별시 서초구');" title="서울특별시 서초구">
			<area shape="poly" coords="198,275,205,274,219,272,221,271,249,286,254,288,259,290,264,295,260,299,261,303,260,315,260,324,260,325,254,325,251,326,241,317,244,317,242,313,239,306,240,302,236,300,233,302,231,304,227,304,219,301,216,303,209,304,205,304,200,298,198,299,195,305,189,307,184,308,180,311,176,313,172,314,182,301,187,300,190,299,194,290,195,283" href="javascript:render_areasearch('서울특별시 동작구');" title="서울특별시 동작구">
			<area shape="poly" coords="200,306,207,308,218,308,221,306,232,308,235,308,236,305,238,308,238,315,240,318,239,321,249,330,260,330,262,337,267,344,268,356,263,356,261,361,258,364,255,368,251,371,244,372,242,376,241,379,236,380,234,382,230,381,225,380,220,384,215,384,214,386,211,386,211,381,208,376,204,373,201,369,201,366,197,359,192,353,187,355,184,354,186,351,185,348,184,345,184,342,182,340,179,336,181,333,182,330,182,327,180,325,179,325,178,322,177,321,174,320,169,321,170,319,175,318,184,313,192,310,197,308,198,307" href="javascript:render_areasearch('서울특별시 관악구');" title="서울특별시 관악구">
			<area shape="poly" coords="132,315,135,315,138,315,140,315,147,321,152,325,157,328,162,327,165,326,169,324,173,323,175,324,176,327,178,330,178,331,176,332,175,333,176,339,181,345,182,348,182,354,182,356,185,358,190,358,191,358,196,364,199,369,197,371,193,373,191,377,189,381,186,382,183,381,180,383,180,387,177,388,173,387,163,387,165,385,164,381,163,375,158,371,160,368,157,366,154,362,150,360,148,355,148,351,151,350,149,348,147,348,145,345,147,343,144,339,139,333,136,329,134,324,134,321" href="javascript:render_areasearch('서울특별시 금천구');" title="서울특별시 금천구">
			<area shape="poly" coords="140,219,145,223,146,222,159,232,170,242,173,247,178,246,181,245,189,246,194,249,200,250,206,256,214,265,210,266,202,268,197,268,195,272,190,288,190,294,185,297,180,298,176,303,173,308,170,314,167,314,164,308,159,297,159,289,159,281,154,276,150,273,146,272,143,270,144,262,143,260,143,256,152,252,153,247,154,244,148,230" href="javascript:render_areasearch('서울특별시 영등포구');" title="서울특별시 영등포구">
			<area shape="poly" coords="73,284,79,282,80,284,82,289,88,293,96,289,99,284,105,283,110,282,115,284,120,288,124,290,129,289,135,285,143,274,155,282,156,286,155,296,159,308,165,317,167,318,163,323,156,324,151,320,140,311,133,310,128,304,126,304,121,307,118,311,113,315,110,319,107,321,103,320,101,324,102,328,99,329,102,333,99,333,95,330,92,328,88,330,86,330,83,327,79,330,77,330,69,330,67,333,65,332,63,331,65,326,70,323,69,317,73,313,73,309,68,307,66,308,63,306,62,303,60,301,61,297,65,297,70,292,73,289" href="javascript:render_areasearch('서울특별시 구로구');" title="서울특별시 구로구">
			<area shape="poly" coords="93,257,123,252,124,245,123,240,125,234,122,229,123,222,128,226,133,228,139,227,142,227,149,239,151,246,148,248,141,254,137,257,140,273,134,278,130,283,126,286,122,285,124,280,121,280,116,281,115,278,110,278,106,278,102,279,96,282,89,289,85,287,84,284,83,281,79,280,77,279,74,280,73,279,76,276,73,271,75,270,75,263,77,257,79,255,79,251,75,250,75,247,73,244,71,243,76,236,78,231,79,227,79,233,85,236,85,240,87,247,89,252" href="javascript:render_areasearch('서울특별시 양천구');" title="서울특별시 양천구">
			<area shape="poly" coords="44,147,73,176,93,187,119,204,137,219,138,224,133,225,127,221,122,217,121,222,119,227,118,231,119,236,120,249,112,251,105,252,97,252,94,253,87,240,88,235,84,231,82,227,80,223,78,224,76,228,74,232,72,235,59,235,55,233,52,231,45,238,38,243,37,239,38,235,35,234,33,229,20,227,16,223,11,223,4,216,2,213,7,211,13,210,15,206,19,205,19,202,17,196,21,196,23,194,23,190,30,184,37,180,40,174,42,167,45,167,45,157,43,155,42,150" href="javascript:render_areasearch('서울특별시 강서구');" title="서울특별시 강서구">
		</map>
	</div>
	</center>
	<p><br>
	<form action="./reservation_searchboard.do?">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="btn btn-outline-success" value="처음으로 돌아가기" onclick="submit();">
	</form>
	</div></div>
	<script>
		var curArea = '${param.area}';
		var tem = "img[alt='"+curArea+"']";
		var curImage = $(tem);
		curImage.parents("li").show();
		function fe_mapSelect(){
			var $container = $(".mapSelect");
			var $overImage = $container.find("li");
			var $trigger = $container.find("area");
			
			$trigger.each(function(i){
				$(this).mouseenter(function(){
					$overImage.hide();
					$overImage.eq(i).show();
					curImage.parents("li").show();
				});
			});
		
			$container.mouseleave(function(){
				$overImage.hide();
				curImage.parents("li").show();
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
	<div class="mdc-layout-grid__cell stretch-card mdc-layout-grid__cell--span-6">
              <div class="mdc-card" style="background-color:#f0f4ff;">
                <section class="mdc-card__primary">
                  <h1 class="mdc-card__title mdc-card__title--large">② 상세 주소를 검색하세요.</h1>
                </section>
                <div class="panel panel-default" align="center">
    <br><br>
	<form action="./reservation_searchboard.do?">
		<input type="hidden" name="area" value="${param.area}">
		<input type="text" name="search" class="form-control" style="width:60%; display: inline-block;" placeholder="시,도,군,구를 선택한 후에 검색하세요." size="50">&nbsp;&nbsp;<input type="button" class="btn btn-outline-success" value="검색" onclick="submit();">
	</form>
	<br>
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
					<a href="" onclick="modalMap(${item.pa_lat},${item.pa_lon},'${item.pa_address}'); fnMove();"data-toggle="modal" data-target="#exampleModal">${item.pa_address}</a>
				</td>
			</tr>
			</c:if>
		</c:forEach>
	</table>
	<p><br>
	<nav aria-label="...">
	  <ul class="pagination justify-content-center">
	  		<c:choose>
		  		<c:when test="${startPage != 1 }">
						<li class='page-item'><a href='./reservation_searchboard.do?area=${area}&search=${search}&pageNum=${startPage-1}' class="page-link" aria-label='Previous'><span aria-hidden='true'>Previous</span></a></li>
				</c:when>
				<c:otherwise>
					<li class='page-item disabled'><a href='#' class="page-link" aria-label='Previous'><span aria-hidden='true'>Previous</span></a></li>
				</c:otherwise>
			</c:choose>
	  		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	  			<c:choose>
					<c:when test="${pageNum ==i }">
						<li class='page-item active'><a href='./reservation_searchboard.do?area=${area}&search=${search}&pageNum=${i}' class="page-link">${i}<span class='sr-only'>${i}</span></a></li>
					</c:when>
					<c:otherwise>
						<li class='page-item'><a href='./reservation_searchboard.do?area=${area}&search=${search}&pageNum=${i}' class="page-link">${i}<span class='sr-only'>${i}</span></a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${endPage == pageCount }">
					<li class='page-item disabled'><a href='#' class="page-link" aria-label='Next'><span aria-hidden='true'>Next</span></a></li>
				</c:when>
				<c:otherwise>
					<li class='page-item'><a href='./reservation_searchboard.do?area=${area}&search=${search}&pageNum=${endPage+1}' class="page-link" aria-label='Next'><span aria-hidden='true'>Next</span></a></li>
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
		  <div class="modal-dialog modal-sm"  role="document"> 
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
 <script type="text/javascript" src="https://jonthornton.github.io/jquery-timepicker/jquery.timepicker.js"></script>
<script type="text/javascript" src="https://jonthornton.github.io/jquery-timepicker/lib/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="./js/datetimepicker/jquery.datepair.js"></script>
<script type="text/javascript" src="./js/datetimepicker/datepair.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<script src="./bootstrap/js/bootstrap.min.js"></script>
</body>
</html>