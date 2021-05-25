<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.js"></script>
<title>Insert title here</title>
</head>
<!-- 주소 API 다음API에서 가져옴  -->
<!-- 20021.05.14 최수훈 -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수
				
                
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('cPostalCode').value = data.zonecode;
                document.getElementById("cAddress1").value = roadAddr;
                

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>

<script type="text/javascript">
	function inputcIdChk() {
		doucument.signupCustomerForm.idDuplication.value ="idUncheck";
	}
	function opencIdChk(cId) {
		var url = 'cIdCheck.jsp?cId='+ cId
		window.open(url, 'chkFrom', 'width=500, height=300, resizable=no, scrollbars=no');
	}

</script>
<!-- Jquery의 Validate를 이용한 정규식 및 로그인 확인 -->
<script>
$(function(){
  $.validator.addMethod("regx",function(value,elemqnt,regexpr){
      return regexpr.test(value);
  });  
  $("form").validate({
      //validation이 끝난 이후의 submit 직전 추가 작업할 부분
      submitHandler: function() {
          var form = document.signupCustomerForm;
          var f = confirm("회원가입을 완료하겠습니까?");
          
	          if(form.idDuplication.value != "idCheck"){
	        	  alert('중복체크를 진행하세요');
	        	  return false;
	          }
	          if(f){
	             alert(form.cName.value + "님의 회원가입이 완료되었습니다!")
	              return true;
	             
	          } else {
	              return false;
	          }
        	  
          
      },
      //규칙
      rules: {
    	  cId: {
              required : true,
              regx : /^[a-z0-9]{6,20}$/
          },
          cPw: {
              required : true,
              regx : /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{6,20}$/
          },
          cPwcheck: {
              required : true,	
              equalTo : '#cPw'
          },
          cName: {
              required : true,
              regx : /^[가-힣a-zA-z]{1,30}$/
          },
          cTel: {
        	  required : true,
              regx : /^[0-9]{11,12}$/
          },
          cEmail: {
              required : true,
              regx : /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
          },
          cPostalCode: {
        	  required : true
          },
          cAddress1: {
        	  required : true
          },
          cAddress2: {
        	  required : true
          },
      },
      
      //규칙체크 실패시 출력될 메시지
      messages : {
          cId: {
                required : "필수입력사항입니다.",
                regx : "아이디는 영문 소문자, 숫자 포함 6~20자입니다"
            },
            cPw: {
                required : "필수입력사항입니다.",
                regx : "비밀번호는 문자,숫자,특수문자 포함 6~20자입니다"
            },
          cPwcheck: {
                required : "필수입력사항입니다.",
                equalTo : "비밀번호가 맞지 않습니다."
            },
          cName: {
                required : "필수입력사항입니다.",
                regx : "이름은 한글,영어 1~30자입니다"
            },
           cTel: {
              required : "필수입력사항입니다.",
                regx : "-없이 숫자11~12자리입니다"
            },
            cEmail: {
              required : "필수입력사항입니다.",
              regx : "이메일 형식을 확인하세요."
            },
            cPostalCode: {
           	  required : "필수입력사항입니다."
            },
            cAddress1: {
              required : "필수입력사항입니다"
            },
            cAddress2: {
              required : "필수입력사항입니다."
            }
        }
  });
})
</script>

<script type="text/javascript">
	function inputcIdChk() {
		document.signupCustomerForm.idDuplication.value ="idUncheck";
	}
	function opencIdChk(cId) {
		var url = 'cIdCheck.jsp?cId='+ cId
		window.open(url, 'chkFrom', 'width=500, height=300, resizable=no, scrollbars=no');
	}
</script>


<body>
<form name="signupCustomerForm" id="signupCustomerForm" action="signupCustomer.do">
	<h3>회원가입</h3>
	<hr style="width: 80%;">
		<table>
			<tr>
				<td> <h4>구매 회원 기본정보</h4> </td>
			</tr>
			<tr>
			
				<td> <input type="text" name="textid" value="아이디*" readonly="readonly" style="text-align: center"></td>
				<td> <input type="text" name="cId"	id="cId" placeholder="아이디를 입력하세요" onkeydown="inputcIdChk()">
				<input type="button" name="Idcheck" value="중복체크" onclick=" opencIdChk(this.form.cId.value);">
				<input type="hidden" name ="idDuplication" id="idDuplication" value="idUncheck" size="1">
				</td>
			</tr>		
			<tr>
				<td> <input type="text" name="textpw" value="비밀번호*" readonly="readonly" style="text-align: center"></td>
				<td> <input type="password" name="cPw" id="cPw" placeholder="비밀번호를 입력하세요"> </td>
			</tr>		
			<tr>
				<td> <input type="text" name="textpwc" value="비밀번호 확인*" readonly="readonly" style="text-align: center"></td>
				<td> <input type="password" name="cPwcheck" id="cPwcheck" placeholder="비밀번호 확인"> </td>
			</tr>		
			<tr>
				<td> <input type="text" name="textname" value="이름*" readonly="readonly" style="text-align: center"></td>
				<td> <input type="text" name="cName" id="cName" placeholder="이름을 입력하세요"> </td>
			</tr>		
			<tr>
				<td> <input type="text" name="texttel" value="전화번호*" readonly="readonly" style="text-align: center"></td>
				<td> <input type="text" name="cTel" id="cTel" placeholder="-를 생략하고 입력"> </td>
			</tr>		
			<tr>
				<td> <input type="text" name="textbirth" value="생년월일*" readonly="readonly" style="text-align: center"></td>
				<td> <select name="cBirthY" id="cBirthY">
					<%for(int i=2021; i>1900; i--){ %>
					<option value="<%=i%>" selected="selected"><%=i%></option>
					<%} %>
					</select>
					년
					<select name="cBirthM" id="cBirthM">
					<%for(int i=1; i<13; i++){ %>
					<option value="<%=i%>" selected="selected"><%=i%></option>
					<%} %>
					</select>
					월
					<select name="cBirthD" id ="cBirthD">
					<%for(int i=1; i<32; i++){ %>
					<option value="<%=i%>" selected="selected"><%=i%></option>
					<%} %>
					</select>
					일
					</td>
			</tr>		
			<tr>
				<td> <input type="text" name="textemail" value="이메일*" readonly="readonly" style="text-align: center"></td>
				<td> <input type="text" name="cEmail" id="cEmail" placeholder="이메일을 입력하세요"></td>
			</tr>		
			<tr>
				<td> <input type="text" name="textnickname" value="주소*" readonly="readonly" style="text-align: center"></td>
				
				<td><input type="text" name ="cPostalCode" id="cPostalCode" placeholder="우편번호" >
					<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
					<input type="text" name="cAddress1" id="cAddress1" placeholder="도로명주소">
					<span id="guide" style="color:#999;display:none"></span>
					<input type="text" name="cAddress2" id="cAddress2" placeholder="상세주소">
				</td>
			</tr>		
			
		</table>
			<input type="submit" value="회원가입">
</form>


</body>
</html>