var session;

// facebook 관련 코드
var checkLoginStatus = function(response) {
	FB.getLoginStatus(function(response){
		statusChangeCallback(response);
	});
}

function statusChangeCallback(response){
	if(response.status === "connected"){
		FB.api('/me', function(resp){
			$.ajax({
				"url": "./logPro.do",
				"type": "post",
				"dataType" : "JSON",
				"data": {id:resp.id, pw:resp.name, flag: "1"},
				"success": function(data){
					if(data.result == "error"){
						swal({
							title:"로그인 실패",
							text : "아이디와 비밀번호가 존재하지 않거나 일치하지 않습니다.",
							icon : "error"
						}).then((willDelete) => {
						})
					} else if(data.result == "success"){
						swal({
							title:"로그인 성공",
							text : "환영합니다!",
							icon : "success"
						}).then((willDelete) => {
							session = resp.id;
						});
						$(".logout").show();
		      			$(".login").hide();
		      			$(".admin").hide();
					} else if(data.result == "overlap") {
							swal({
  								title:"로그인 실패",
  								text : "이미 로그인되어있는 계정입니다.",
  								icon : "error"
  							}).then((willDelete) => {
  							})
  						}
				},
				"error": function(data){}
			});	  		 	  		
		});
	} else {
	}
}

window.fbAsyncInit = function() {
	FB.init({
		appId      : '641166546301850',
		cookie     : true,  // enable cookies to allow the server to access 
		// the session
		xfbml      : true,  // parse social plugins on this page
		version    : 'v3.2' // use graph api version 3.2
	});
	
	// Now that we've initialized the JavaScript SDK, we call 
	// FB.getLoginStatus().  This function gets the state of the
	// person visiting this page and can return one of three states to
	// the callback you provide.  They can be:
	//
	// 1. Logged into your app ('connected')
	// 2. Logged into Facebook, but not your app ('not_authorized')
	// 3. Not logged into Facebook and can't tell if they are logged into
	//    your app or not.
	//
	// These three cases are handled in the callback function.
	
	 FB.getLoginStatus(function(response) {
	      statusChangeCallback(response);
	 });
};


(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = 'https://connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v3.2';
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));


function facebookLogin() {
	FB.login(function(response) {
		if(response.status === "connected"){
			FB.api('/me', function(resp){
				$.ajax({
					"url": "./logPro.do",
					"type": "post",
					"dataType" : "JSON",
					"data": {id:resp.id, pw:resp.name, flag: "1"},
					"success": function(data){
						if(data.result == "error"){
							swal({
								title:"로그인 실패",
								text : "아이디와 비밀번호가 존재하지 않거나 일치하지 않습니다.",
								icon : "error"
							}).then((willDelete) => {
							})
						} else if(data.result == "success"){
							swal({
								title:"로그인 성공",
								text : "환영합니다!",
								icon : "success"
							}).then((willDelete) => {
								session = resp.id;
							});
							$(".logout").show();
			      			$(".login").hide();
			      			$(".admin").hide();
						} else if(data.result == "overlap") {
  							swal({
  								title:"로그인 실패",
  								text : "이미 로그인되어있는 계정입니다.",
  								icon : "error"
  							}).then((willDelete) => {
  							})
  						}
					},
					"error": function(data){}
				});	  		 	  		
			});
		} else {
		}
	});
}


// 일반 회원 로그인
//우리 홈페이지 id, password 입력 접속 코드
var label = "";

function login(){
	var id = $('#userid').val();
	var pw = $('#userpw').val();
	if(id==null || id==""){
		swal({
			title:"로그인 실패",
			text : "아이디를 입력해주세요.",
			icon : "error"
		}).then((willDelete) => {
		})
		return;
	}
	
	if(pw==null || pw==""){
		swal({
			title:"로그인 실패",
			text : "비밀번호를 입력해주세요",
			icon : "error"
		}).then((willDelete) => {
		})
		return;
	}		
	loginRequest();
	
}

function loginRequest() {
	$.ajax({
		url : "./logPro.do",
		type : "POST",
		data: {"id": $('#userid').val(), "pw": $('#userpw').val(), "admin": $('input:checkbox[id="admin"]').is(":checked") ? "admin" : null},
		dataType: "JSON",
		"success" : function(data){
			if(data.result == "error"){
				swal({
					title:"로그인 실패",
					text : "아이디와 비밀번호가 존재하지 않거나 일치하지 않습니다.",
					icon : "error"
				}).then((willDelete) => {
				})
			} else if(data.result == "success"){
				swal({
					title:"로그인 성공",
					text : "환영합니다!",
					icon : "success"
				}).then((willDelete) => {
					session = $('#userid').val();
					if(!(location.pathname.substring(6,location.pathname.length) == "reservation_searchboard.do")) {
							location.reload();
					}
				});
				$(".logout").show();
      			$(".login").hide();
      			$(".admin").hide();
			} else if(data.result == "overlap") {
				swal({
					title:"로그인 실패",
					text : "이미 로그인되어있는 계정입니다.",
					icon : "error"
				}).then((willDelete) => {
				})
			}
		},
		"error": function(data){
			swal({
				title:"서버문제",
				text : "관리자에게 문의하세요. (loginRequest)",
				icon : "error"
			}).then((willDelete) => {
			})
		}
	});
}

function clean() {
	label.innerHTML = "";
}		

// 카카오톡
Kakao.init('2899ad92021b65bed011b6501b479356');
function kakaoLogin() {
	Kakao.Auth.loginForm({
        success: function(authObj) {
           Kakao.API.request({
    		  url: '/v2/user/me',
    		  success: function(res) {
	    			 $.ajax({
	  					"url": "./logPro.do",
	  					"type": "post",
	  					"dataType" : "JSON",
	  					"data": {id:res.id, pw:res.properties.name, flag: "1"},
	  					"success": function(data){
	  						if(data.result == "error"){
	  							swal({
	  								title:"로그인 실패",
	  								text : "아이디와 비밀번호가 존재하지 않거나 일치하지 않습니다.",
	  								icon : "error"
	  							}).then((willDelete) => {
	  							})
	  						} else if(data.result == "success"){
	  							swal({
	  								title:"로그인 성공",
	  								text : "환영합니다!",
	  								icon : "success"
	  							}).then((willDelete) => {
	  								session = id;
	  							});
	  							$(".logout").show();
	  			      			$(".login").hide();
	  			      			$(".admin").hide();
	  						} else if(data.result == "overlap") {
	  							swal({
	  								title:"로그인 실패",
	  								text : "이미 로그인되어있는 계정입니다.",
	  								icon : "error"
	  							}).then((willDelete) => {
	  							})
	  						}
	  					},
	  					"error": function(data){}
	  				});	  
			  },
			  fail: function(error) {
				alert(JSON.stringify(error))
			}
    	  })
        },
        fail: function(err) {
          alert(JSON.stringify(err));
        }
	});
}

// 세션 호출

function getSession() {
	$.ajax({
		url:"./getsession.do",
		dataType: "JSON",
		"success": function(data){
			session = data.session;
			if(data.session == null || data.session == "") {
				$('#replySave').hide();
      			$(".login").show();
      			$(".logout").hide();
      			$(".admin").hide();
      		}  else {
      			webSocketConn(session);
  				if(data.session == "admin") {
  					$('#adminWrite').html('<input type="button" class="btn btn-outline-success" onclick="location.href=\'notification_saveView.do\';" value="공지쓰기">');
  					$('#adminNewsEdit').html(
  						'<input type=button class="btn btn-outline-primary" value="수정" onclick="newsEdit();">&nbsp;' +
  						'<input type=button class="btn btn-outline-danger" value="삭제" onclick="newsDelete();">'
  					);
  					$('#faqEdit').html(
	                     '<input type=button class="btn btn-outline-success" value="답글쓰기" onclick="faqReply();">&nbsp;' +
	                     '<input type=button class="btn btn-outline-primary" value="수정" onclick="faqEdit();">&nbsp;' +
	                     '<input type=button class="btn btn-outline-danger" value="삭제" onclick="faqDelete();">'
  		            );
  					$('.adminOnly').show();
  					
  					$(".logout").hide();
  	      			$(".login").hide();
  	      			$(".admin").show();
  	      			
  				} else {
  					$('#side_point').html(data.user[9]);
  					$('#side_userID').html(data.user[0]);
  					$(".logout").show();
  	      			$(".login").hide();
  	      			$(".admin").hide();
  				}
  				if(data.session == $('#fr_writer').html() ){
  					$('#myfr').html(
  							'<input type=button class="btn btn-outline-primary" value="수정" onclick="newsEdit();">&nbsp;' + 
  				      	'<input type=button class="btn btn-outline-danger" value="삭제" onclick="newsDelete();">'
  					);
  					
  				}
  				if(data.session == $('#faq_writer').html()){
                    $('#faqEdit').html(
                          '<input type=button class="btn btn-outline-primary" value="수정" onclick="faqEdit();">&nbsp;' + 
                          '<input type=button class="btn btn-outline-danger" value="삭제" onclick="faqDelete();">'
                    );             
                 }
  				$('.loginOnly').show();
  				$('#free_write_bt').html(
  						'<input type="button" class="btn btn-outline-success" onclick="location.href=\'./freeboard_saveView.do\';" value="글쓰기">'
	            );
	              
  				$('#faq_write_bt').html(
  						'<input type="button" class="btn btn-outline-success" onclick="location.href=\'./FAQInsert.do\';" value="글쓰기">'
  				);
      			
      		}
		},
		"error": function(data){
			swal({
				title:"서버오루",
				text : "관리자에게 문의하세요. (getSession)",
				icon : "error"
			}).then((willDelete) => {
			})
		}
	});
}


function logout() {
	$.ajax({
		url:"./logPro.do",
		type: "GET",
		dataType: "JSON",
		"success": function(data){
			if(data.result == true) {
				FB.logout(function(response){
				});
				Kakao.Auth.logout(function(){
				  });
				document.getElementById("test").src = 'http://nid.naver.com/nidlogin.logout';
				swal({
					title:"로그아웃",
					text : "로그아웃되었습니다.",
					icon : "success"
				}).then((willDelete) => {
					location.href="./user_saveView.do?where=main";
				});
				$(".login").show();
      			$(".logout").hide();
      			$(".admin").hide();
			} else {
				swal({
					title:"로그아웃",
					text : "로그아웃에 실패했습니다. 관리자에게 문의하세요. (logout)",
					icon : "error"
				}).then((willDelete) => {
				});
			}
		},
		"error": function(data){
			swal({
				title:"서버오류",
				text : "로그아웃에 실패했습니다. 관리자에게 문의하세요. (logout)",
				icon : "error"
			}).then((willDelete) => {
			})
		}
	});
}


//네이버 로그인
function loginNaver(id, name) {
	$.ajax({
		url: "./logPro.do",
		type: "post",
		dataType : "JSON",
		data: {"id":id, "pw":name, "flag": "1"},
		"success": function(data){
			if(data.result == "error"){
				swal({
					title:"로그인 실패",
					text : "아이디와 비밀번호가 존재하지 않거나 일치하지 않습니다.",
					icon : "error"
				}).then((willDelete) => {
				})
			} else if(data.result == "success"){
				swal({
					title:"로그인 성공",
					text : "환영합니다!",
					icon : "success"
				}).then((willDelete) => {
					
				});
				$(".logout").show();
	  			$(".login").hide();
	  			$(".admin").hide();
			}
		},
		"error": function(data){}
	});	  
}

function webSocketConn(id) {
	
    //WebSocketEx는 프로젝트 이름
    //websocket 클래스 이름
    var webSocket = new WebSocket("ws://52.78.140.117:8081/websocket");
    webSocket.onopen = function(message){
        console.log("Server connect...\n");
    };
    //웹 소켓이 닫혔을 때 호출되는 이벤트
    webSocket.onclose = function(message){
    	webSocket.close();
        console.log("Server Disconnect...\n");
    };
    //웹 소켓이 에러가 났을 때 호출되는 이벤트
    webSocket.onerror = function(message){
    	webSocket.close();
        console.log("error...\n");
    };
    //웹 소켓에서 메시지가 날라왔을 때 호출되는 이벤트
    webSocket.onmessage = function(message){
        console.log("Recieve From Server => "+message.data+"\n");
    };
    setTimeout(function() { webSocket.send(id);}, 50);


}