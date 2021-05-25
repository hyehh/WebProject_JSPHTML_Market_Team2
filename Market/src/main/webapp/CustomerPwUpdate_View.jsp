<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 2021.05.13 조혜지 비밀번호 변경 view -->
<title>비밀번호 변경</title>
<script type="text/javascript">
	function pwUpdate() {
		
		// 비밀번호 정규식 확인
		var regExpcPw = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{6,20}$/;
		var form = document.pwUpdateForm;
		var cPw = form.cPw.value;
		var cPwCheck = form.cPwCheck.value;

	    if (!regExpcPw.test(cPw)) {
			alert("비밀번호는 문자,숫자,특수문자 포함 6~20자입니다!");
			form.cPw.select();
			return;
		}
 	    if (cPw != cPwCheck) { 
	    	alert ("비밀번호가 동일하지 않습니다!");
			form.cPwCheck.select();
	    	return; 
	    }
		form.submit();
		alert("비밀번호가 변경되었습니다!");
	}
</script>
<style>
	/* .container_wrap {
		display: block;
	} */
	#pwUpdate {
 		padding: 5px 20px;
		position: absolute;
		top: 50%;
		left: 50%;
		width: 450px; height: 250px;
		margin-left: -220px;
		margin-top : -250px; 
	}
	caption {
		font-style: 200px; font-weight: bold; margin: 50px;
		font-size: x-large;
	}
	.table {
		margin: auto;
		border-collapse: collapse;
	}

	.table, th, td {
		padding: 10px;
		text-align: left;
	}
	.buttonJSP{
		width: 70px;
	    background-color: #646464;
	    border: none;
	    color: #fff;
	    padding: 6px 0;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 15px;
	    margin: 10px;
	    cursor: pointer;
		border-radius: 10px;
	}
</style>
</head>
<body>
	<%@include file = "header.jsp" %>
	<%@include file = "LeftMenuBar.jsp" %>
	<div class="container" id="pwUpdate">
		<form action="CustomerPwUpdate.do" name="pwUpdateForm" method="post">
			<table border="0">
				<caption>비밀번호 변경</caption>
					<tr>
						<td>변경할 비밀번호</td>
						<td><input type="password" name="cPw" size="30" style="color:black; background-color=white; padding-top:2px;
						border-width:2px; border-color:gray; border-top-style:none; border-right-style:none; border-left-style:none; border-bottom-style:line;" 
						placeholder="문자,숫자,특수문자 포함해 입력하세요"></td>
					</tr>
					<tr>
						<td>변경할 비밀번호 확인</td>
						<td><input type="password" name="cPwCheck" size="30" style="color:black; background-color=white; padding-top:2px;
						border-width:2px; border-color:gray; border-top-style:none; border-right-style:none; border-left-style:none; border-bottom-style:line;" 
						placeholder="변경할 비밀번호를 동일하게 입력하세요"></td>
					</tr>
					<tr>
						<td></td>
						<td><input class="buttonJSP" type="button" style="float:right" value="확인" onclick="pwUpdate()"></td>
					</tr>
			</table>
		</form>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>