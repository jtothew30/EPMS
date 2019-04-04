var items =[];
function messageCheck() {
  refreshNotifications(items);
};

function refreshNotifications(items) {
  var cssTransitionEnd = getTransitionEnd();
  var container = $('body');
    
  var template = 
      '<div class="notifications js-notifications">' +
        '<h3>Notifications</h3><div style="overflow-y: scroll; -ms-overflow-style: none; height:300px;"' +
        '<ul class="notifications-list">' +
          '<li class="item no-data">You don\'t have notifications</li>' +
          '{{#items}}' +
            '<li class="item js-item {{#isExpired}}expired{{/isExpired}}" data-id="{{id}}">' +
              '<div class="details">' +
                '<span class="title{{num}}"><a href="javascript:reCheck({{num}});">{{title}}</a></span>' +
                '<span style="display:none;" class="content{{num}}">{{content}}</span>' +
                '<span style="display:none;" class="requestDate{{num}}">{{requestDate}}</span><br>' +
                '<span class="date{{num}}">{{date}}</span>' +
              '</div>' +
              '<button type="button" onclick="readMessage({{num}});" class="autoClick{{num}} button-default button-dismiss js-dismiss">&nbsp;&nbsp;&nbsp;x</button>' +
            '</li>' +
          '{{/items}}' +
        '</ul></div>' +
        '<a href="./myinfo.do?type=past" class="show-all">Show all notifications</a>' +
      '</div>';
  
  container
    .append(Mustache.render(template, { items: items }))
    .find('.js-count').attr('data-count', items.length).html(items.length).end()
    .on('click', '.js-show-notifications', function(event) {
      $(event.currentTarget).closest('.js-show-notifications').toggleClass('active').blur();
      return true;
    })
    .on('click', '.js-dismiss', function(event) {
    	
      var item = $(event.currentTarget).parents('.js-item');
      var removeItem = function() {
        item[0].removeEventListener(cssTransitionEnd, removeItem, false);
        item.remove();
        
        /* update notifications' counter */
        var countElement = container.find('.js-count');
        var prevCount = +countElement.attr('data-count');
        var newCount = prevCount - 1;
        countElement
          .attr('data-count', newCount)
          .html(newCount);
        
        if(newCount === 0) {
          countElement.remove();
          container.find('.js-notifications').addClass('empty');
        }
      };
      
      item[0].addEventListener(cssTransitionEnd, removeItem, false);
      item.addClass('dismissed');
      return true;
    });
}

function strpad(num) {
  if (parseInt(num) < 10) {
    return '0' + parseInt(num);
  } else {
    return parseInt(num);
  }
}

function getTransitionEnd() {
  var supportedStyles = window.document.createElement('fake').style;
  var properties = {
    'webkitTransition': { 'end': 'webkitTransitionEnd' },
    'oTransition': { 'end': 'oTransitionEnd' },
    'msTransition': { 'end': 'msTransitionEnd' },
    'transition': { 'end': 'transitionend' }
  };
  
  var match = null;
  Object.getOwnPropertyNames(properties).forEach(function(name) {
    if (!match && name in supportedStyles) {
      match = name;
      return;
    }
  });
  
  return (properties[match] || {}).end;
}

// 메시지 버튼 클릭시 알림창 출력
function fe_alramSelect(){
	var $container = $(".showMessageButton");
	$container.click(function(){
		if($('.notifications').css("opacity") == 0) {
			$('.notifications').css("opacity", 1.0);
			$('.notifications').show();
		} else {
			$('.notifications').css("opacity", 0.0);
			$('.notifications').hide();
		}
	});
}

// 메시지 읽음 체크
function readMessage(no) {
	var data = {"data" : no};
	 $.ajax({
			type:"GET",
			url:"./reservation_MessageRead.do", 
			data:data,
			success: function(){
			}	
	});
}

//ajax 폴링
var sess;
function getMessage(){
	$.ajax({
		type:"GET",
		dataType:"JSON",
		url: "./getsession.do",
		success:function(data) {
			sess = data.session;
			if(data.session == null || data.session == "" || data.session == "admin"){
			} else {
				checkMessage();
				setInterval(checkMessage, 5000);
			}
		},
		error: {
			
		}		
	});
}
function checkMessage(){
	var id = sess;
    $.ajax({
		type:"GET",
		url:"./reservation_check.do", 
		dataType: 'json',
		data:{"name":id}, //로그인한 계정의 id를 넘겨줌 (host의 id)
		success: function(data){
			var list = data["list"];
			var temp = [];
			for(var i =0; i<list.length; i++) {
				if(list[i].host==id){
	            	   if(list[i].status=='예약취소'){
	            		   b={
	      	                     id: i+1, 
	      	                     title: list[i].guest + "님의 예약취소",
	      	                     date : list[i].from.substring(0,list[i].from.length-3) + " ~ " + list[i].to.substring(0,list[i].to.length-3) , 
	      	                     content : list[i].content,
	      						 num : list[i].no,
	      						 requestDate : list[i].request
	      	                  };
	            	   }else if(list[i].status=='예약중'){
	            		   b={
	      	                     id: i+1, 
	      	                     title: list[i].guest + "님의 예약요청",
	      	                     date : list[i].from.substring(0,list[i].from.length-3) + " ~ " + list[i].to.substring(0,list[i].to.length-3) , 
	      	                     content : list[i].content,
	      						 num : list[i].no,
	      						 requestDate : list[i].request
	      	                  };
	            	   }
	               }else if(list[i].guest==id){
	            	   if(list[i].status=='예약취소'){
	            		   b={
	      	                     id: i+1, 
	      	                     title: list[i].host + "님이 요청을 거절하였습니다.",
	      	                     date : list[i].from.substring(0,list[i].from.length-3) + " ~ " + list[i].to.substring(0,list[i].to.length-3) , 
	      	                     content : list[i].content,
	      						 num : list[i].no,
	      						 requestDate : list[i].request
	      	                  };
	            	   }else if(list[i].status=='예약완료'){
	            		   b={
	      	                     id: i+1, 
	      	                     title: list[i].host + "님이 요청을 승낙하였습니다.",
	      	                     date : list[i].from.substring(0,list[i].from.length-3) + " ~ " + list[i].to.substring(0,list[i].to.length-3) , 
	      	                     content : list[i].content,
	      						 num : list[i].no,
	      						 requestDate : list[i].request
	      	                  };
	            	   }
	           
	               }
	               temp.push(b);
            }
			
			if(temp.length === items.length) {
				
			} else {
				items = temp;
				$('.notifications').remove();
				messageCheck();
			}
		}
	});
}

// 메시지 버튼이 활성아닐때만 ajax 폴링 실행
function poll() { 
	if(!$('.showMessageButton').hasClass('active')){  
		getMessage();
	}
}

// 승낙, 거절 선택창 진입
function reCheck(no) {
	if($('.title'+no).text().indexOf('예약요청') <0) {
		swal($('.title'+no).text() + "\n\n요청날짜 : " + $('.requestDate'+no).text() + "\n신청날짜 : " + $('.date'+no).text() + "\n메세지 : " + $('.content' + no).text());
		$('.autoClick'+no).trigger('click');
	} else {
		swal({
			  title: "응답확인",
			  text: "요청날짜 : " + $('.requestDate'+no).text() + "\n신청날짜 : " + $('.date'+no).text() + "\n메세지 : " + $('.content' + no).text() ,
			  icon: "success",
			  buttons: {
				  defeat: "거절",
				  catch: "승낙",
				},
			})
			.then((value) => {
				  switch (value) {
				    case "catch":
				      // 요청 승낙시 처리
				      var data = {"data" : no, "agree" : "true"};
				      $.ajax({
							type:"GET",
							url:"./reservation_requestResult.do", 
							data:data,
							success: function(){
								$('.autoClick'+no).trigger('click');
								swal('요청을 수락하였습니다.');
							}	
					  });
				      break;
				 
				    case "defeat":
				      // 요청 취소시 처리
				      var data = {"data" : no, "agree" : "false"};
				      $.ajax({
							type:"GET",
							url:"./reservation_requestResult.do", 
							data:data,
							success: function(){
								$('.autoClick'+no).trigger('click');
								swal('요청을 거절하였습니다.');
							}	
					  });			      
				      break;			 
				    default:
				  }
			});
	}
}

