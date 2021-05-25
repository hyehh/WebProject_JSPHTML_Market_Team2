<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<script type="text/javascript">
		function cIdChk() {
				var frm = document.cIdchkForm;
				frm.action='signupcIdCheck.do';
				frm.submit();
				return true;
			}
</script>
<!--결과불러오고 버튼숨기는 창--> 
<!--사용하기 버튼이 보이게되면 중복체크 결과, 사용가능한 아이디 이기때문에 idDuplication에 
else 맨 아래와같이 value를 줌으로 중복체크를 마무리 할 수 있도록 한다. -->
<script type="text/javascript">
	function ccallBack(){
	          
			if(${cIdchk} == "1"){
				 document.getElementById("cancelBtn").style.visibility='visible';
	             document.getElementById("useBtn").style.visibility='hidden';
			} else {
				 document.getElementById("cancelBtn").style.visibility='hidden';
	             document.getElementById("useBtn").style.visibility='visible';
	             document.cIdchkForm.idDuplication.value ="idCheck";
			}
	}
</script>
<script>
    function sendCheckValue(){
    	if(document.cIdchkForm.idDuplication.value == "idCheck" ){
            opener.document.signupCustomerForm.idDuplication.value ="idCheck";
            opener.document.signupCustomerForm.cId.value = document.getElementById("cId").value;
            if (opener != null) {
                opener.chkForm = null;
                self.close();
                <%session.invalidate();%>
            }    
    	}else if(document.cIdchkForm.idDuplication.value == "idUncheck"){
    		alert('중복체크를 진행해 주세요!');
    	}
    }
</script>

<script>
    function inputcIdChk() {
		document.cIdchkForm.idDuplication.value ="idUncheck";
	}
    function changeHidden() {
		document.cIdchkForm.idDuplication.value ="idCheck";
		
	}
</script>
<script>
$(function(){
  $.validator.addMethod("regx",function(value,elemqnt,regexpr){
      return regexpr.test(value);
  });  
  
  $("form").validate({
      //규칙
      rules: {
    	  cId: {
              required : true,
              regx : /^[a-z0-9]{6,20}$/
          },
      },
      //규칙체크 실패시 출력될 메시지
      messages : {
          cId: {
                required : "필수입력사항입니다.",
                regx : "아이디는 영문 소문자, 숫자 포함 6~20자입니다"
            },
        }
  });
})

</script>

<%
	request.setCharacterEncoding("utf-8");
	String cId = request.getParameter("cId");
%>
<body onload="ccallBack()">

<div>
	<center>
		<h3 align = "center">회원가입</h3>
		<hr style = "width: 80%" >
	</center>
</div>

<form name="cIdchkForm"  method="post">
<div>
	<center>
		
		<div>
		<input type="text" name="cId" id="cId" style="width: 20; text-align: left;" value="<%=cId%>" onkeydown="inputcIdChk()">
		<input type="button" value="중복체크" onclick="cIdChk()">
		<br>
		<input type="hidden" name ="idDuplication" id="idDuplication" value="idUncheck" size="1">
		</div>	
	</center>
</div>
</form>
<div id="msg"></div>
        <br>
<div>
		<center>  
			<input id="cancelBtn" type="button" value="취소" onclick="window.close()" style="visibility: hidden;">
	        <input id="useBtn" type="button" value="사용하기" onclick="sendCheckValue()" style="visibility: hidden;">
		</center>
</div>
</body>
</html>