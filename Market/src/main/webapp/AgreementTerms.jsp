<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>약관 동의</title>

<script type="text/javascript">
	function selectAll(){
	    if(document.getElementById("chkAll").checked == true){ 
			document.getElementById('check1').checked = true;
			document.getElementById('check2').checked = true;
			document.getElementById('check3').checked = true;
	    }
	    if(document.getElementById("chkAll").checked == false){ 
			document.getElementById('check1').checked = false;
			document.getElementById('check2').checked = false;
			document.getElementById('check3').checked = false;
	    }
	}
	function signupCustomer(){
		var f = document.signupTypeForm;
		if(document.getElementById("check1").checked == false || document.getElementById("check2").checked == false || document.getElementById("check3").checked == false){
			alert('약관동의에 체크해주세요.');
			return false;
		}else{
			f.action="signupCustomer.jsp";
			f.submit();
			return true;
		}
		
	} 
	function signupSeller(){
		var f = document.signupTypeForm;
		if(document.getElementById("check1").checked == false || document.getElementById("check2").checked == false || document.getElementById("check3").checked == false){
			alert('약관동의에 체크해주세요.');
			return false;
		}else{
			f.action="signupSeller.jsp";
			f.submit();
			return true;
		}
		
	}
	function dechk() {
		if(document.getElementById("check1").checked == false || document.getElementById("check2").checked == false || document.getElementById("check3").checked == false){
			document.getElementById("chkAll").checked = false;
		}
	}
</script>
 
</head>
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
	}
	form {
		width: 80%;
		margin: auto;
		text-align: center;
	}
	h3 {
		margin: 20px 0;
	}
	.agree {
		text-align: left;
		margin: 30px 0 50px 0;
		padding-left: 40px;
	}
	input[type=submit], input[type=button] {
		margin: 0 10px;
		background-color: gray;
		color: #fff;
		border: none;
		padding: 6px 10px;
		border-radius: 10px;
	}
</style>
<body>
	<%@include file = "header.jsp" %>
	<div class="container_wrap">
		<div class="container">
				<form name="signupTypeForm" method="post">
					<h3>약관동의</h3>
					<hr>
					<div class="agree">
						<input type="checkbox" name="check_all" id="chkAll" value="agree" onchange="selectAll()"> 전체 동의하기 <!-- 누르면 전체 체크되고 다시 누르면 전체 해제하기 -->
						<br>
						<br>
						<input type="checkbox" name="check" id='check1' value="agree" onchange="dechk()"> <B>(필수)</B> 본인은 만 14세 이상입니다.
						<br>
						<br>
						<input type="checkbox" name="check" id='check2'  value="agree" onchange="dechk()"> <B>(필수)</B> 이용악관 동의 <a>전체보기></a>
						<br>
						<br>
						<textarea rows="10" cols="90"></textarea>
						<br>
						<br>
						<input type="checkbox" name="check" id='check3'  value="agree" onchange="dechk()"> <B>(필수)</B> 개인정보 수집 및 이용. <a>전체보기></a>
					</div>
					<input type="submit" value=" 이전단계 " style="text-align: center">
					<input type="button" value=" 판매회원으로 가입하기 " onclick="signupSeller()"> 
					<input type="button" value=" 구매회원으로 가입하기 " onclick="signupCustomer()">
				</form>	
		</div>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>