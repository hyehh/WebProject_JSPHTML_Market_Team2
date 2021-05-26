<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="cssSH.css">
<title>Log in</title>
<style type="text/css">
.failmsg {
	margin: auto;
	margin-top: 15px;
	text-align: center;
	font-style:italic;
	font-size: 12px;
	color: red;
}
</style>
</head>
<!-- 선긋고 그 중간에 텍스트 입력하기 폼 2021.05.13 최수훈 -->
<!-- 체크박스 하나만 체크하기 -->
<script type="text/javascript">

function checkOnlyOne(element) {
	  
	  const checkboxes 
	      = document.getElementsByName("userType");
	  
	  checkboxes.forEach((cb) => {
	    cb.checked = false;
	  })
	  
	  element.checked = true;
	}
	
function chkValue() {
	f= document.loginActionForm;
	if(f.loginId.value == ""){
		alert('아이디를 입력해주세요');
		return false;
	}
	else if(f.loginPw.value ==""){
		alert('패스워드를 입력해주세요');
		return false;
	}
	f.submit();
	return true;
}
	</script>

<body>
 <jsp:include page="header.jsp"></jsp:include>
 <br>
	<div class="wrap">
		<div class="login">
			<h3>Log-In</h3>
				<form action="loginaction.do" method="post" name="loginActionForm">
						<div class="signup">
							<div class="loginId">
								<h4><i>ID</i></h4>
								<input type="text" name="loginId" placeholder="ID를 입력하세요" id="loginId" >
							</div>
							
									<br>
									
									<div class="loginPw">
										<h4><i>Password</i></h4>
										<input type="password" placeholder="패스워드를 입력하세요" id="loginPw" name="loginPw" >
									</div>
									<div class="failmsg">
									가입되지 않은 아이디이거나, 잘못된 비밀번호입니다.
									</div>
									<div class="login_etc">
									<input type="checkbox" name="userType" value="구매회원" checked="checked" onclick='checkOnlyOne(this)'> 구매회원 
									<input type="checkbox" name="userType" value="판매회원" onclick='checkOnlyOne(this)'>판매회원
									</div>
									<div class="findAction">
									<a href = "FindId.jsp"><i style="font-size: 3pt; ">아이디</i></a> 
									<a href = "FindPw.jsp"><i style="font-size: 3pt; ">비밀번호 찾기</i></a>
									</div>
									<div class="submit">
									<input type="button" value="login" onclick="chkValue()">
									</div>
						</div>
									<br>
									<div class="hr-sect">아직 회원이 아니신가요?</div>
									<br>
				</form>
				
				<form action="AgreementTerms.jsp" method="post">
				<div class="gosignup">
					<input type="submit" value="회원가입">
				</div>
				</form>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>