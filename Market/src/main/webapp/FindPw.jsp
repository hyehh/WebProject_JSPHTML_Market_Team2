<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!-- 체크박스 하나만 체크하기 -->
<script type="text/javascript">
function chkValue() {
	var form = document.findPwForm
	
	if(form.inputId.value == ""){
		alert("ID를 입력해주세요");
		form.inputId.focus();
		return false;
	}else if (form.inputName.value == ""){
		alert("이름을 입력해주세요");
		form.inputName.focus();
		return false;
	}else if (form.inputEmail.value == ""){
		alert("이메일을 입력해주세요");
		form.inputEmail.focus();
		return false;
	}
	form.submit();
}

function checkOnlyOne(element) {
	  
	  const checkboxes 
	      = document.getElementsByName("animal");
	  
	  checkboxes.forEach((cb) => {
	    cb.checked = false;
	  })
	  
	  element.checked = true;
	}
	</script>
	
<script type="text/javascript">
	function findId(frm) {
		frm.action='FindId.jsp'
		frm.submit();
		return true;
	}
</script>

<style>
	.container_wrap {
		background-color: #FAFAFA;
		padding: 30px 0;
	}
	.container {
		width: 1000px;
		margin: auto;
		background-color: #fff;
		padding: 30px 0; 
		text-align: center;
	}
	h4 {
		margin: 20px 0 30px 0;
	}
	hr {
		width: 80%;
		margin: auto;
	}
	form {
		margin: auto;
		margin-top: 30px;
		width: 380px;
	}
	input[type=button] {
		padding: 6px 10px;
		background-color: gray;
		color: #fff;
		border: none;
		border-radius: 10px;
	}
</style>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container_wrap">
			<div class="container">
				<br>
				<h4>비밀번호 찾기</h4>
				<hr>
					<form name="findPwForm" id="findPwForm" action="findPw.do" method="post">
							<h5 style="text-align: center;"> 비밀번호 찾기 </h5>
							<br>
								<p style="line-height: 10pt">
								<input type="text" name="inputId" id="inputId" placeholder="아이디를 입력하세요" size="50" style="text-align: left; height: 15pt"></p>
								<br>
								<p style="line-height: 10pt">
								<input type="text" name="inputName" id="inputName" placeholder="이름을 입력하세요" size="50" style="text-align: left; height: 15pt"></P>
								<br>
								<p style="line-height: 10pt">
								<input type="text" name="inputEmail" id="inputEmail" placeholder="이메일을 입력하세요" size="50" style="text-align: left; height: 15pt"></p>
								<br>
								<input type="button" onclick="chkValue()" value="비밀번호찾기">
								<br>
								<br>
								<div style="font-size: 9pt">
									<p style="line-height: 10pt">	
										 <input type="checkbox" name="findUserType" value="구매회원" checked="checked" onclick='checkOnlyOne(this)' "> 구매회원
										 &nbsp;&nbsp;&nbsp;
										 <input type="checkbox" name="findUserType" value="판매회원" onclick='checkOnlyOne(this)' style="font-size: 9pt;"> 판매회원
								 	</p>
								 	<br>
								</div>
				
							아이디가 기억나지 않으세요?
							<input type="button" name="Idcheck" value="아이디 찾기" onclick='return findId(this.form);'>
						
					</form>
				<br>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>