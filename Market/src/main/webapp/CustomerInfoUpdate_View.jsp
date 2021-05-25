<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 2021.05.13 조혜지 회원정보 수정 view -->
<title>회원정보 변경</title>
</head>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function PostalCode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var roadAddr = data.roadAddress;
                var extraRoadAddr = '';
                
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                
                var guideTextBox = document.getElementById("guide");
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
	function infoUpdate() {
		
		var regExpcName = /^[가-힣a-zA-z]{1,30}$/;
		var regExpcTel = /^[0-9]{11,12}$/;
		var regExpcEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		var form = document.customerInfoUpdateForm;
		var cName = form.cName.value;
		var cTel = form.cTel.value;
		var cEmail = form.cEmail.value;
		
		if (!regExpcName.test(cName)) {
			alert("이름은 한글,영어 1~30자로 입력해주세요!");
			form.cName.select();
			return;
		}
		if (!regExpcTel.test(cTel)) {
			alert("전화번호는 -없이 숫자11~12자리로 입력해주세요!");
			form.cTel.select();
			return;
		}
		if (!regExpcEmail.test(cEmail)) {
			alert("이메일 형식을 확인해주세요!");
			form.cEmail.select();
			return;
		}
		if(form.cPostalCode.value == ""){
			alert("우편번호 찾기를 통해 우편번호를 입력해 주세요!");
			form.cPostalCode.focus();
			return;
		}
		if(form.cAddress1.value == ""){
			alert("우편번호 찾기를 통해 도로명주소를 입력해 주세요!");
			form.cAddress1.focus();
			return;
		}
		if(form.cAddress2.value == ""){
			alert("상세 주소를 입력해 주세요!");
			form.cAddress2.focus();
			return;
		}
		form.submit();
		alert("회원정보가 수정되었습니다!");
	}
</script>
<style>
	/* .container_wrap {
		display: block;
	} */
/* 	#pwUpdate {
 		padding: 5px 20px;
		position: absolute;
		top: 50%;
		left: 50%;
		width: 450px; height: 250px;
		margin-left: -220px;
		margin-top : -250px; 
	} */
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
	.buttonAdd{
		width: 90px;
	    background-color: #828282;
	    border: none;
	    color: #fff;
	    padding: 2px 0;
	    text-align: center;
	    text-decoration: none;
	    display: inline-block;
	    font-size: 13px;
	    margin: 10px;
	    cursor: pointer;
		border-radius: 10px;
	}
</style>
<body>
	<%@include file = "header.jsp" %>
	<%@include file = "LeftMenuBar.jsp" %>
	<div class="container">
		<form name="customerInfoUpdateForm" action="CustomerInfoUpdate.do" method="post">
			<table border="0">
				<caption>회원정보 변경</caption>
					<tr>
						<td>ID</td>
						<td>${CUSTOMERINFO.cId }</td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" name="cName" value="${CUSTOMERINFO.cName }" size="25" style="color:black; background-color=white; padding-top:2px;
						border-width:2px; border-color:gray; border-top-style:none; border-right-style:none; border-left-style:none; border-bottom-style:line;" 
						placeholder="이름을 입력하세요."></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><input type="text" name="cTel" value="${CUSTOMERINFO.cTel }" size="25" style="color:black; background-color=white; padding-top:2px;
						border-width:2px; border-color:gray; border-top-style:none; border-right-style:none; border-left-style:none; border-bottom-style:line;" 
						placeholder="-를 생략하고 입력하세요."></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="text" name="cEmail" value="${CUSTOMERINFO.cEmail }" size="25" style="color:black; background-color=white; padding-top:2px;
						border-width:2px; border-color:gray; border-top-style:none; border-right-style:none; border-left-style:none; border-bottom-style:line;" 
						placeholder="이메일을 입력하세요."></td>
					</tr>
					<tr>
						<td>생년월일</td>
		 				<td> 
		 					<select name="cBirthY">
								<c:forEach var="i" begin="1900" end="2021" step="1" >
									<c:choose>
									<c:when test ="${fn:substring(CUSTOMERINFO.cBirth,0,4) == i }">
										<option value="${i }" selected="selected">${i }</option>
									</c:when>
		  							<c:otherwise>
										<option value="${i }">${i }</option>
									</c:otherwise>  
									</c:choose>
								</c:forEach>
							</select>
							년 
		  				  	<select name="cBirthM">
								<c:forEach var="i" begin="1" end="12" step="1" >
									<c:choose>
									<c:when test ="${fn:substring(CUSTOMERINFO.cBirth,5,7) == i }">
										<option value="${i }" selected="selected">${i }</option>
									</c:when>
		  							<c:otherwise>
										<option value="${i }">${i }</option>
									</c:otherwise>  
									</c:choose>
								</c:forEach>
							</select>
							월 
		 				  	<select name="cBirthD">
								<c:forEach var="i" begin="1" end="31" step="1" >
									<c:choose>
									<c:when test ="${fn:substring(CUSTOMERINFO.cBirth,8,10) == i }">
										<option value="${i }" selected="selected">${i }</option>
									</c:when>
		  							<c:otherwise>
										<option value="${i }">${i }</option>
									</c:otherwise>  
									</c:choose>
								</c:forEach>
							</select>
							일 
						</td>
					</tr>	
					<tr>
						<td>주소</td>
						<td><input type="text" id="sample4_postcode" name="cPostalCode" style="color:black; background-color=white; padding-top:2px;
						border-width:2px; border-color:gray; border-top-style:none; border-right-style:none; border-left-style:none; border-bottom-style:line;" 
						placeholder="우편번호를 입력하세요." value="${CUSTOMERINFO.cPostalCode }">
							<input class="buttonAdd" type="button" onclick="PostalCode()" value="우편번호 찾기"><br>
							<input type="text" id="sample4_roadAddress" name="cAddress1" style="color:black; background-color=white; padding-top:2px;
						border-width:2px; border-color:gray; border-top-style:none; border-right-style:none; border-left-style:none; border-bottom-style:line;" 
							placeholder="도로명주소를 입력하세요." value="${CUSTOMERINFO.cAddress1 }">
							<span id="guide" style="color:#999;display:none"></span>
							<input type="text" id="sample4_detailAddress" name="cAddress2" style="color:black; background-color=white; padding-top:2px;
						border-width:2px; border-color:gray; border-top-style:none; border-right-style:none; border-left-style:none; border-bottom-style:line;" 
							placeholder="상세주소를 입력하세요." value="${CUSTOMERINFO.cAddress2 }">
					</tr>
					<tr>
						<td></td>
						<td><input class="buttonJSP" type="button" style="float:right" value="확인" onclick="infoUpdate()"></td>
					</tr>
			</table>
		</form>
	</div>
	<%@include file = "footer.jsp" %>
</body>
</html>