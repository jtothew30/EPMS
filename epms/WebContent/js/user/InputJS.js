
//====전역변수:각각 체크여부
	var flag = false;//null체크여부
	var flagS = false;//아이디중복체크여부
	var flagP = false;//비번확인체크여부
	var flagE = false;//email체크여부
	var flagN = false;//번호 체크여부
	
	var label = "";
	
//====다 체크하고 submit
	function check(){
		var id = $('#id').val();
		var pw1 = $('#pw1').val();
		var pw2 = $('#pw2').val();
		var name = $('#name').val();
		var num1 = $('#num1').val();
		var num2 = $('#num2').val();
		var num3 = $('#num3').val();
		var code = $('#code').val();
		var addr1 = $('#addr1').val();
		var addr2 = $('#addr2').val();
		var email = $('#email').val();
		
		
		if (id==null||id==""){
			swal("회원가입 실패!","아이디를 입력하세요!","error");
			$('#id').focus();
			return;
		}
		
		
		if (pw1==null||pw1==""){
			swal("회원가입 실패!","패스워드를 입력하세요!","error");
			$('#pw1').focus();
			return;
		}
		
		if (pw2==null||pw2==""){
			swal("회원가입 실패!","패스워드를 입력하세요!","error");
			$('#pw2').focus();
			return;
		}
		
		if (name==null||name==""){
			swal("회원가입 실패!","이름을 입력하세요!","error");
			$('#name').focus();
			return;
		}
		
		if (num1==null||num1==""||num2==null||num2==""||num3==null||num3==""){
			swal("회원가입 실패!","전화번호를 입력하세요!","error");
			$('#num1').focus();
			return;
		}
		
		if (code==null||code==""||addr1==null||addr1==""||addr2==null||addr2==""){
			swal("회원가입 실패!","주소를 입력하세요!","error");
			$('#code').focus();
			return;
		}
		
		if (email==null||email==""){
			swal("회원가입 실패!","이메일을 입력하세요!","error");
			$('#e').focus();
			return;
		}
		
		//위에 해당 되는 거 없으면 널 체크 true
		flag=true;
		
		if(flagS==false){	//아이디중복체크 안됐을때
			swal("회원가입 실패!","아이디 중복 체크를 하세요!","error");
			return;
		}
		
		if(flagP==false){	//비번 확인 틀렸을때
			swal("회원가입 실패!","비밀번호가 일치하지 않습니다!","error");
			$('#pw2').focus();
			return;
		}
		
		if(flagN==false){	//전화번호 올바르지 않을때
			swal("회원가입 실패!","전화번호를 올바르게 입력하세요!","error");
			$('#num1').focus();
			return;
		}
		
		//위에 다 통과&체크 완료 후 submit
		if(flag==true&&flagS==true&&flagP==true&&flagE==true&&flagN==true){
			signupSave();
		} else { return; }
	}//check end
	
	function clean(){
		label.innerHTML = "";
	}
	
function signupSave() {
	var id = $('#id').val();
	var pw1 = $('#pw1').val();
	var pw2 = $('#pw2').val();
	var name = $('#name').val();
	var num1 = $('#num1').val();
	var num2 = $('#num2').val();
	var num3 = $('#num3').val();
	var code = $('#code').val();
	var addr1 = $('#addr1').val();
	var addr2 = $('#addr2').val();
	var email = $('#email').val();
	
	var data = {"id":id, "pw1": pw1, "pw2": pw2, "name": name, "num1": num1, "num2": num2, "num3": num3, "code":code, "addr1":addr1, "addr2":addr2, "email":email};
	$.ajax({
		url:"./accountInsert.do",
		data: data,
		dataType: "JSON",
		success: function(t) {
			if(t.result == true) {
				swal({
					title:"회원가입완료",
					text : "회원가입에 성공하였습니다.",
					icon : "success"
				}).then((willDelete) => {
					if(willDelete) {
						location.href = './user_saveView.do?where=main';
					}
				})
			} else {
				swal("회원가입실패","회원가입 과정에서 오류가 발생했습니다.","error");
			}
		}
	});
}
//====사번 체크
	/*function checkSb(){//사번 입력체크하고 중복확인 팝업창 열어주기
		var id = iform.id.value;
		var label = document.getElementById("maskId");
		var id_reg = /[(0-9)]{4}/;//숫자4자리입력
		if(id==""||id==null){
			label.innerHTML = "<font color='red'>사번을 입력하세요(숫자4자리)</font>";
			iform.id.focus();
			return;
		} else {
			if(id_reg.test(id)==false){
				label.innerHTML = "<font color='red'>4자리숫자만 입력</font>";
				iform.id.value="";
				iform.id.focus();
				return;
			}
		}
		
	}//checkSb end
*/	
	$(function() {
		var id = $('#id').value;
		var label = document.getElementById("maskId");
		var id_reg = /[(0-9)]{4}/;//숫자4자리입력
		if(id==""||id==null){
			label.innerHTML = "<font color='red'>ID를 입력하세요</font>";
			$('#id').focus();
			return;
		} 
		/*else {
			if(id_reg.test(id)==false){
				label.innerHTML = "<font color='red'>4자리숫자만 입력</font>";
				iform.id.value="";
				iform.id.focus();
				return;
			}
		}*/
	});
	
	function flagS_t(){//아이디 플래그를 true로 바꿔주는 메소드(팝업창에서 작동)		
			flagS=true;					
	}
			
	
	
//====비밀번호 확인
	function checkPwd(){//비밀번호 확인 체크
		var str = document.getElementById("maskPw2");
		if($('#pw1').val() == $('#pw2').val()) {
			str.innerHTML = "비밀번호가 일치합니다.";
			flagP = true;	//비번일치플래그 true로 해줌
		} else {
			str.innerHTML = "<font color='red'>비밀번호가 일치하지 않습니다.</font>";
		}
	}
		
	
//====각각 데이터 입력 자리수제한(db오류 안나게 바이트로 처리)
	function checklen(obj, maxByte)	{	//db데이터 사이즈에 맞는 길이 체크
		var strValue = obj.value;
		var strLen = strValue.length;
		var totalByte = 0;
		var len = 0;
		var oneChar ="";
		var str2 ="";
		
		for (var i = 0; i < strLen; i++) {	//한글자씩 가져와서
			oneChar = strValue.charAt(i);
			if (escape(oneChar).length > 4)	{	//길이가 4초과=유니코드/한글이면
				totalByte += 2;	//2바이트로 넣어주고
			} else {
				totalByte++;	//아니면 1바이트로 처리
			}
			if(totalByte <= maxByte) {
				len = i+1;	//입력된 데이터의 자리수 기억해주기
			}
		}
		if (totalByte > maxByte) {
			swal("회원가입 실패!","Byte를 초과 입력 불가!","error");
			str2 = strValue.substr(0, len);	//아까 기억한 자리수까지만큼 잘라서 넣어주기
			obj.value = str2;
		}
	}//end

	
//====email 형식체크
	function emailcheck(){
		  var mail=$('#email').val();
		  var mail_reg=/^([\S]{2,16})@([a-zA-Z]{2,10})\.([a-zA-Z]{2,10})$/;
		  if(mail_reg.test(mail)==false){
			 msg="<font color=red>이메일 형식 체크하세요</font>"; 
			 document.getElementById("maskEmail").innerHTML=msg;
			 return;
		  }else{
		  	flagE = true;
		  	document.getElementById("maskEmail").innerHTML="";
		  }
	   }//end
	   
	   
//====우편번호
	function DaumPostcode() {
		new daum.Postcode({
      oncomplete: function(data) {
        // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

        // 각 주소의 노출 규칙에 따라 주소를 조합한다.
        // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
        var fullAddr = ''; // 최종 주소 변수
        var extraAddr = ''; // 조합형 주소 변수

        // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
        if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
            fullAddr = data.roadAddress;

        } else { // 사용자가 지번 주소를 선택했을 경우(J)
            fullAddr = data.jibunAddress;
        }

        // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
        if(data.userSelectedType === 'R'){
            //법정동명이 있을 경우 추가한다.
            if(data.bname !== ''){
                extraAddr += data.bname;
            }
            // 건물명이 있을 경우 추가한다.
            if(data.buildingName !== ''){
                extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
            fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
        }

        // 우편번호와 주소 정보를 해당 필드에 넣는다.
        document.getElementById('code').value = data.zonecode; //5자리 새우편번호 사용
        document.getElementById('addr1').value = fullAddr;

        // 커서를 상세주소 필드로 이동한다.
        document.getElementById('addr2').focus();
      }
		}).open();
	}//end
	
	
	
//====전화번호 입력확인
	function num(){
		var str1=$('#num1').val();
		var str2=$('#num2').val();
		var str3=$('#num3').val();
		var num_reg=/[(0-9)]{3,4}/;
		var label=document.getElementById("maskPhone");
		var test1=num_reg.test(str1);
		var test2=num_reg.test(str2);
		var test3=num_reg.test(str3);
		if(test1==false||test2==false||test3==false){
			label.innerHTML = "&nbsp;&nbsp;숫자3~4자리 입력하세요";
		} else {
			label.innerHTML = "";
			flagN=true;
		}
	}//end
	
//===아이디 중복확인
$(function(){
	$('#idCheck').click(function(){
		var data = $('#id').val();
		if(data==""){
			$('#maskId').html("ID 입력 후 중복버튼 누르세요");
			return;
		}
		$.ajax({
			"url" : "./idCheck.do",  
			"type" : "post",
			"data" : {UID: data},
			
			"success": function(data){											
					$('#maskId').html(data);					
			},
			"error": function(data){$('#maskId').fadeOut();}
		});
	});
});	

// admin 수정 체크
function adminEdit(){
	var id = $('#id').val();
	var pw = $('#pw').val();
	var name = $('#name').val();
	var num1 = $('#num1').val();
	var num2 = $('#num2').val();
	var num3 = $('#num3').val();
	var code = $('#code').val();
	var addr1 = $('#addr1').val();
	var addr2 = $('#addr2').val();
	var email = $('#email').val();
	
	
	if (id==null||id==""){
		swal("수정 실패!","아이디를 입력하세요!","error");
		$('#id').focus();
		return;
	}
	
	
	if (pw==null||pw==""){
		swal("수정 실패!","패스워드를 입력하세요!","error");
		$('#pw').focus();
		return;
	}
	
	/*if (pw2==null||pw2==""){
		swal("수정 실패!","패스워드를 입력하세요!","error");
		$('#pw2').focus();
		return;
	}*/
	
	if (name==null||name==""){
		swal("수정 실패!","이름을 입력하세요!","error");
		$('#name').focus();
		return;
	}
	
	if (num1==null||num1==""||num2==null||num2==""||num3==null||num3==""){
		swal("수정 실패!","전화번호를 입력하세요!","error");
		$('#num1').focus();
		return;
	}
	
	if (addr1==null||addr1==""||addr2==null||addr2==""){
		swal("수정","주소를 입력하세요!","error");
		$('#code').focus();
		return;
	}
	
	if (email==null||email==""){
		swal("수정 실패!","이메일을 입력하세요!","error");
		$('#e').focus();
		return;
	}
	
	//위에 해당 되는 거 없으면 널 체크 true
	flag=true;
	
	/*if(flagS==false){	//아이디중복체크 안됐을때
		swal("수정 실패!","아이디 중복 체크를 하세요!","error");
		return;
	}
	
	if(flagP==false){	//비번 확인 틀렸을때
		swal("수정 실패!","비밀번호가 일치하지 않습니다!","error");
		$('#pw2').focus();
		return;
	}
	
	if(flagN==false){	//전화번호 올바르지 않을때
		swal("수정 실패!","전화번호를 올바르게 입력하세요!","error");
		$('#num1').focus();
		return;
	}*/
	
	//위에 다 통과&체크 완료 후 submit
	adminEditSave();
}//check end


function adminEditSave() {
var id = $('#id').val();
var pw = $('#pw').val();
var name = $('#name').val();
var num1 = $('#num1').val();
var num2 = $('#num2').val();
var num3 = $('#num3').val();
var phone = num1 + "-" + num2 + "-" + num3;
var code = $('#code').val();
var addr1 = $('#addr1').val();
var addr2 = $('#addr2').val();
var email = $('#email').val();
var type = $('#type').val();
var point = $('#point').val();
var area = $('#area').val();

var data = {"id":id, "pw": pw, "name": name, "phone": phone, "code":code, "addr1":addr1, "addr2":addr2, "email":email, "type": type, "point":point, "area":area};
$.ajax({
	url:"./accountEditSave.do",
	data: data,
	dataType: "JSON",
	success: function(t) {
		if(t.result == true) {
			swal({
				title:"회원정보 수정완료",
				text : "회원정보 수정에 성공하였습니다.",
				icon : "success"
			}).then((willDelete) => {
				if(willDelete) {
					location.href = './accountList.do';
				}
			})
		} else {
			swal("회원정보 수정실패","회원정보 수정 과정에서 오류가 발생했습니다.","error");
		}
	}
});
}

