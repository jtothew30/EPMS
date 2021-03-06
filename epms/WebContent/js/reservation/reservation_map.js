var lat = new Array;
var lon = new Array;
var add = new Array;
var selectNum; // 어떤 마커가 선택되었는지

// 맵에 사용되는 변수
var HOME_PATH = window.HOME_PATH || '.';
var map;
var markers =[];
var infoWindows =[];

// DateTimePicker 셋팅
function setDateTimePicker() {
	  var d = new Date();
	  $('input[name="datetimes"]').daterangepicker({
	    "timePicker": true,
	    "timePicker24Hour": true,
	    "timePickerIncrement": 30,
	    "startDate": moment().startOf('hour'),
	    "minDate": d.getMinutes()%30==0?d:(d.getMinutes()>30?new Date(d.getTime()+(60-d.getMinutes())*60000):new Date(d.getTime()+(30-d.getMinutes())*60000)),
	    locale: {
	      format: 'Y-MM-DD HH:mm'
	    }
	  });
	/*var d = new Date();
	$.fn.datepicker.dates['kr'] = {
   		days: ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"],
   		daysShort: ["일", "월", "화", "수", "목", "금", "토", "일"],
   		daysMin: ["일", "월", "화", "수", "목", "금", "토", "일"],
   		months: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
   		monthsShort: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]
   	};

	$('#reservation_date .time').timepicker({
	    'showDuration': true,
	    'timeFormat': 'H:i',
	    'autoclose': true,
	    'maxTime': '00:01',
	    'minTime':  d.getMinutes()%30==0?d:(d.getMinutes()>30?new Date(d.getTime()+(60-d.getMinutes())*60000):new Date(d.getTime()+(30-d.getMinutes())*60000))
	});

	$('#reservation_date .date').datepicker({
	    'format': 'yyyy-mm-dd',
	    'startDate': new Date(),
	    'language': 'kr',
	    'autoclose': true
	});
	
	var reservation_date = document.getElementById('reservation_date');
    var datepair = new Datepair(reservation_date);
	*/
    // 모달에서 작동하기 위한 기능, 외부클릭하면 피커 close
	$('div').click(function(e){
		if(!$(e.target).hasClass('datetimes') && $(e.target).closest('.daterangepicker').length == 0){
			$('.daterangepicker').hide();
		} else if($(e.target).hasClass('datetimes')) {
			$('.daterangepicker').show();
		}
	});
}

// 검색지도 클릭시
function render_areasearch(area) {
	location.href = "../reservation_searchboard.do?area=" + area;
}

function render_view(seq) {
  location.href = "./store_01_view.html?pg=1&seq="+seq+"&area=경남&ss=";
}

function schecked(frm){
	if (frm.ss.value == ''){
		alert('검색어를 입력해주세요.');
		frm.ss.focus()
    return false;
	}
	else {
    return true;
	}
}


// 모달 내용 생성
function modalContentCreate(i){
	var contentString = [
		'<h1 style="margin-top:10px;"><span class="badge badge-danger" >'+ add[i] +'</span></h1>' ,
		'<table style="width:800px;" >',
		'<tr style="border-top: 1px solid #444444;">',
		'<td style="width:50%; height:auto; vertical-align: top; padding-top: 10px; padding-left:10px;"><h2 style="display:inline-block;"><span class="badge badge-danger" >기간 선택</span></h2><font style="font-size: 12px; font-weight:bold;">&nbsp;&nbsp;① 원하는 날짜와 시간을 선택한 후 검색을 누르세요.</font> <br><input type="text" style="width:70%;  display: inline-block;" class="datetimes form-control" name="datetimes" onchange="resetSearchList();" />&nbsp;&nbsp;<input type="button" class="btn btn-outline-primary" style=" display: inline-block;"value="검색" onclick="searchList();"></td>',
		'<td style="border-left: 1px solid #444444; width:50%; vertical-align: top; padding-top: 10px; padding-left:10px;padding-right:10px;" rowspan="2"><div id="pano" style="width:100%;height:360px;"></div></td>',
		'</tr>',
	    '<tr>',
	    '<td style="border-top: 1px solid #444444; vertical-align: top; padding-top: 10px; padding-left:10px;" height="270" valign="top"><h2 style="display:inline-block;"><span class="badge badge-danger" >자리 선택</span></h2><font style="font-size: 12px; font-weight:bold;">&nbsp;&nbsp;② 희망 자리를 우측 로드뷰에서 확인 후 선택하세요.</font><br><div style="overflow-y:scroll; margin:10px; margin-top:15px; height:200px; width:100%; display:inline-block;" class="reservationList"></div></td>',
	    '</tr>',
	    '</table>'
	].join('');
	return contentString;
}

function resetSearchList(){
	$(".reservationList").html("");
}
// 날짜, 시간 선택 후 예약 가능한 목록 출력
function searchList() {
	var from = document.getElementsByName('datetimes')[0].value.split(' - ')[0].trim();
	var to = document.getElementsByName('datetimes')[0].value.split(' - ')[1].trim();
	var data = {"from" : from, "to" : to, "address" : add[selectNum]};
	$.ajax({
		url:'./searchlist.do',
		type:'GET',
		data: data,
		dataType: "JSON",
		success:function(data){
			var list = data["list"];
			if(list.length == 0) {
				$(".reservationList").html('예약할 수 있는 자리가 없습니다.');
			} else {
				var s = "";
				for(var i = 0; i<list.length;i++){
					  var b = "<input type='button'  class='btn btn-success' onclick='modalRequestOpen(this);' data-toggle='modal' data-target='#exampleModal1' value='" + list[i] +"'>&nbsp;&nbsp;";
					  if((i+1)%4 == 0) {
						  b += "<br><br>";
					  }
					  s += b;
				}
				$(".reservationList").html(s);
			}
		},
		error:function(jqXHR, textStatus, errorThrown){
			alert("에러 발생~~ \n" + textStatus + " : " + errorThrown);
	    }
	});
	
}

// 요청 모달 오픈
function modalRequestOpen(t) {
	var s = t.value;
	var from = document.getElementsByName('datetimes')[0].value.split(' - ')[0].trim();
	var to = document.getElementsByName('datetimes')[0].value.split(' - ')[1].trim();
	var date = from + " ~ " + to;
	$('#exampleModal1').modal('show');
	$('.requestArea').html(s);
	$('.requestAddress').html(add[selectNum]);
	$('.requestTime').html(date);
}

// 로드뷰 호출
function roadView(seq) {	
	var pano = null;
    pano = new naver.maps.Panorama("pano", {
        position: new naver.maps.LatLng(lat[seq], lon[seq]),
        pov: {
            pan: -135,
            tilt: -20,
            fov: 100
        }
    });
}

// 네이버 맵 호출
function modalMap(lat,lon,ad) {
	$('#map').empty();
	var mapOptions = {
	    center: new naver.maps.LatLng(lat, lon),
	    zoom: 10
	};
	map = new naver.maps.Map('map', mapOptions);
	inputMarker(ad);
	for (var i=0, ii=markers.length; i<ii; i++) {
	    naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i));
	}
}

// 마커 입력
function inputMarker(ad) {

	this.infoWindows= [];
	this.markers= [];
	for(var i=0;i<lat.length;i++){
		var icon = {};
		if(add[i] == ad) {
			icon
		}
		var marker = new naver.maps.Marker({
		    position: new naver.maps.LatLng(lat[i], lon[i])
		});
		if(add[i] == ad) {
			marker.setIcon({
				url : HOME_PATH +'/img/common/ico_pin.jpg'
			})
		}
		markers.push(marker);
		marker.setMap(map);
		var infoWindow = new naver.maps.InfoWindow({
			content: modalContentCreate(i),
		    maxWidth:900,
		    backgroundColor: '#ddd ',
		    borderColor: '#190707',
		    anchorColor: '#333',
		    borderWidth: 3
		});
		this.infoWindows.push(infoWindow);
	}
}

// 마커 이벤트 핸들러
function getClickHandler(seq) {
    return function(e) {
        var marker = markers[seq],
            infoWindow = infoWindows[seq];
        if (infoWindow.getMap()) {
            infoWindow.close();
        } else {
        	if(session == null || session == "") {
        		swal('예약오류','로그인을 먼저 진행해주세요.','error');
        	} else {
	            infoWindow.open(map, marker);
	            roadView(seq);
	        	selectNum = seq;
	        	setDateTimePicker();
        	}
        }
    }
}

// 예약신청
function requestReservation() {
	var from = document.getElementsByName('datetimes')[0].value.split(' - ')[0].trim();
	var to = document.getElementsByName('datetimes')[0].value.split(' - ')[1].trim();
	
	var area = $('.requestArea').html();
	var address = $('.requestAddress').html();
	var message = $('.requestMessage').val();
	
	var data = {"from" : from, "to" : to, "address" : address, "area" : area, "message" :message};
	
	$.ajax({
		url:'./reservation_request.do',
		type:'GET',
		data: data,
		dataType: "JSON",
		success:function(t){
			if(t.result == true) {
				swal({
					title : "예약신청 완료",
					text : '이용시간 : ' + from + " ~ " + to +'\n요청지역 : ' + address + '\n주차구역 : ' + area,
					icon : "success"
				}).then((willDelete) => {
					if(willDelete) {
						location.href = './reservation_searchboard.do?';
					}
				})
			//	location.href = './reservation_searchboard.do?';	// 이후에 이동할 페이지 수정
			} else {
				swal("예약 실패",'이미 예약된 구역입니다. 다른 구역을 검색하세요.','error');
			}
		},
		error:function(jqXHR, textStatus, errorThrown){
			swal("에러 발생~~ \n" , textStatus + " : " + errorThrown, "error");
	    }
	});
}
