<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>Insert title here</title>
<style type="text/css">
label.error{
	   display: inline-block;
	   color: red;
	   font-size: 3pt;
	   margin-top: 6px;
	   margin-bottom: 5px;
	   margin-left: 3px;
	   margin-right: 3px;
}
#sId-error {
	position: absolute;
	margin-left: 70px;
}
#sPostalCode-error {
	position: absolute;
	margin-left: 100px;
}
#sAddress1-error {
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Noto Sans KR", sans-serif;
}
table{
	height: 500px;
	width: 100%;
	margin: auto;
}

table tr td:nth-child(1) {
	width: 30%;
}
table tr td:nth-child(2) {
	width: 70%;
}
/*table tr:nth-child(2) td:nth-child(2) input:nth-child(2) {
	position: fixed;
	
}*/

hr {
	heignt: 1px;
	background: #bbb;
	background: -webkit-linear-gradient(left,#eee,#777, #eee);
	background: -moz-linear-gradient(left,#eee,#777, #eee);
	background: -ms-linear-gradient(left,#eee,#777, #eee);
	background: -o-linear-gradient(left,#eee,#777, #eee);
    margin: auto;
 	margin-top: 20px;
 	margin-bottom: 20px;
    text-align: center;
    font-weight: 300;
    font-size: 28px;
}
/*td{
text-align: left;
font-size: 15pt;
tex
}*/
.wrap {
  width: 100%;
  height: 150vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(0, 0, 0, 0.1); 
}
.signup {
  width: 820px;
  height: 800px;
  background: white;
  border-radius: 20px;
  display: block;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  text-align: center;
}
.signup form{
	width: 600px;
	height: 800px;
	margin: auto;
	margin-top: 70px;
	margin-bottom: 100px;
	align-items: center;
}
.sellerTb tr{
	text-align: legt;
	
}
.sellerTb{
	width: 500px;
	heigt: 600px;
	margin: auto;
	
}
tbody{
widows: 480;
}
.infoTitle{
	height: 31px;
	margin-Top: 0px;
	marhin-bottom:20px;
	
}
.sId{
margin-top: 3px;
}
input[type=text]{
  width: 60%;
  height: 22px;
  border-radius: 30px;
  margin-top: 10px;
  padding: 0px 20px;
  border: 1px solid lightgray;
  outline: none;
}
input[type=password]{
  width: 50%;
  height: 22px;
  border-radius: 30px;
  margin-top: 10px;
  padding: 0px 20px;
  border: 1px solid lightgray;
  outline: none;
}
#sNumber{
width: 320px;
}
#sId-error.error{
margin-top:15px;	
}
#sPostalCode-error{
margin-top:15px;	
}
.signUpBtn {
	margin-top: 50px;
	padding: 6px 10px;
	background-color: gray;
	color: #fff;
	border: none;
	border-radius: 10px;
}

</style>
</head>

<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.js"></script>
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

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sPostalCode').value = data.zonecode;
                document.getElementById("sAddress1").value = roadAddr;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.

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

<!-- Jquery의 Validate를 이용한 정규식 및 로그인 확인 -->
<script>
$(function(){
  $.validator.addMethod("regx",function(value,elemqnt,regexpr){
      return regexpr.test(value);
  });  
  $("form").validate({
      //validation이 끝난 이후의 submit 직전 추가 작업할 부분
      submitHandler: function() {
          var form = document.signupSellerForm;
          var f = confirm("회원가입을 완료하겠습니까?");
          
	          if(form.idDuplication.value != "idCheck"){
	        	  alert('중복체크를 진행하세요');
	        	  return false;
	          }
	          if(f){
	             alert(form.sName.value + "님의 회원가입이 완료되었습니다!")
	              return true;
	          } else {
	              return false;
	          }
      },
      //규칙
      rules: {
    	  sId: {
              required : true,
              regx : /^[a-z0-9]{6,20}$/
          },
          sPw: {
              required : true,
              regx : /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{6,20}$/
          },
          sPwcheck: {
              required : true,	
              equalTo : '#sPw'
          },
          sName: {
              required : true,
              regx : /^[가-힣a-zA-z]{1,30}$/
          },
          sTel: {
        	  required : true,
              regx : /^[0-9]{11,12}$/
          },
          sEmail: {
              required : true,
              regx : /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i
          },
          sPostalCode: {
        	  required : true
          },
          sAddress1: {
        	  required : true
          },
          sAddress2: {
        	  required : true
          },
          sShopName: {
        	  required : true
          },
          sNumber: {
        	  required : true
          },
	      pName :{
	    	  required : false
          }
      },
      
      //규칙체크 실패시 출력될 메시지
      messages : {
          sId: {
                required : "필수입력사항입니다.",
                regx : "아이디는 영문 소문자, 숫자 포함 6~20자입니다"
            },
            sPw: {
                required : "필수입력사항입니다.",
                regx : "비밀번호는 문자,숫자,특수문자 포함 6~20자입니다"
            },
          sPwcheck: {
                required : "필수입력사항입니다.",
                equalTo : "비밀번호가 맞지 않습니다."
            },
          sName: {
                required : "필수입력사항입니다.",
                regx : "이름은 한글,영어 1~30자입니다"
            },
           sTel: {
              required : "필수입력사항입니다.",
                regx : "-없이 숫자11~12자리입니다"
            },
            sEmail: {
              required : "필수입력사항입니다.",
              regx : "이메일 형식을 확인하세요."
            },
            sPostalCode: {
           	  required : "필수입력사항입니다."
            },
            sAddress1: {
              required : "필수입력사항입니다"
            },
            sAddress2: {
              required : "필수입력사항입니다."
            },
            sShopName: {
              required : "필수입력사항입니다."
            },
            sNumber: {
              required : "필수입력사항입니다."
            }
        }
  });
})
</script>

<script type="text/javascript">
	function inputsIdChk() {
		document.signupSellerForm.idDuplication.value ="idUncheck";
	}
	function opensIdChk(sId) {
		var url = 'sIdCheck.jsp?sId='+ sId
		window.open(url, 'chkFrom', 'width=500, height=300, resizable=no, scrollbars=no');
	}
</script>

<body>
<jsp:include page="headerFormDelete.jsp"></jsp:include>
<div class="wrap">
	<div class="signup">
		<form name ="signupSellerForm" id="signupSellerForm" action="signupSeller.do">
			<h3>회원가입</h3>
			<hr>	
				<div class="sellerTb">
					<table>
					<tr>
						<h4>판매 회원 기본정보</h4>
					</div>
					</tr>
					<tr>
						<td  style="font-size: 10pt; size: 15;">아이디*</td>
							<td > 
								<input type="text" name="sId" id="sId" placeholder="아이디를 입력하세요" onkeydown="inputsIdChk()">
								<input type="button" name="Idcheck" value="중복체크" onclick="opensIdChk(this.form.sId.value)">
								<input type="hidden" name ="idDuplication"id="idDuplication" value="idUncheck" size="1">
							</td>
						</div>
					</tr>
					<tr>
						<td></td>
						<td></td>
					
					</tr>
					<tr>
						<td style="font-size: 10pt;">비밀번호*</td>
						<td> <input type="password" name="sPw" id="sPw" placeholder="비밀번호"> </td>
					</tr>		
					<tr>
						<td style="font-size: 10pt;">비밀번호 확인*</td>
						<td> <input type="password" name="sPwcheck" id="sPwcheck" placeholder="비밀번호 확인"> </td>
					</tr>		
					<tr>
						<td style="font-size: 10pt;">이름*</td>
						<td> <input type="text" name="sName" id="sName" placeholder="이름을 입력하세요"> </td>
						
					</tr>		
					<tr>
						<td style="font-size: 10pt;">전화번호*</td>
						<td> <input type="text" name="sTel" id="sTel" placeholder="-를 생략하고 입력"> </td>
					</tr>		
					<tr>
						<td style="font-size: 10pt;">생년월일*</td>
						<td> <select name="sBirthY">
							<%for(int i=2021; i>1900; i--){ %>
							<option value="<%=i%>" selected="selected"><%=i%></option>
							<%} %>
							</select>
							년
							<select name="sBirthM">
							<%for(int i=1; i<13; i++){ %>
							<option value="<%=i%>" selected="selected"><%=i%></option>
							<%} %>
							</select>
							월
							<select name="sBirthD">
							<%for(int i=1; i<32; i++){ %>
							<option value="<%=i%>" selected="selected"><%=i%></option>
							<%} %>
							</select>
							일
							</td>
					<tr>
						<td style="font-size: 10pt;">이메일*</td>
						<td> <input type="text" name="sEmail" id="sEmail" placeholder="이메일을 입력하세요"> </td>
					</tr>		
					<tr>
						<td style="font-size: 10pt;">주소*</td>
						
						<td><input type="text" name = "sPostalCode" id="sPostalCode" placeholder="우편번호" readonly="readonly">
							<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
							<input type="text" name = "sAddress1" id="sAddress1" placeholder="도로명주소" readonly="readonly">
							<span id="guide" style="color:#999;display:none"></span>
							<input type="text" name = "sAddress2" id="sAddress2" placeholder="상세주소">
						</td>
					</tr>
					<tr>
						<td style="font-size: 10pt;">상점이름*</td>
						<td> <input type="text" name="sShopName" id="sShopName" placeholder="상점이름을 입력하세요"> </td>
					</tr>		
					<tr>
						<td style="font-size: 10pt;">사업자 등록 번호*</td>
						<td> <input type="text" name="sNumber" id="sNumber" placeholder="사업자등록번호 10자리를 입력하세요(-생략)" size="30"> </td>
					</tr>		
				</table>
			</div>
			<input type="submit" value="회원가입" class="signUpBtn">
		</form>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>