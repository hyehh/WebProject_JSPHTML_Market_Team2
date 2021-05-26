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

function checkOnlyOne(element) {
	  
	  const checkboxes 
	      = document.getElementsByName("findUserType");
	  
	  checkboxes.forEach((cb) => {
	    cb.checked = false;
	  })
	  
	  element.checked = true;
	}
	</script>
<!-- 버튼 액션1 :비밀번호찾기로 이동 -->
<script>
	function chkValue() {
		var form = document.findIdForm;
		if(form.findName.value == ""){
			alert('이름을 입력해주세요');
			return false;
		}else if (form.findEmail.value == ""){
			alert('이메일을 입력해주세요')
			return false;
		}
		form.submit();
		
		return true;
	}
	function findPw(frm) {
		frm.action='FindPw.jsp';
		frm.submit();
		return true;
	}
<!-- 버튼 액션 2 : 로그인으로 이동 -->

	function backLogin(frm) {
		frm.action='Login_View.jsp';
		frm.submit();
		return true;
	}
</script>


<body>
<jsp:include page="header.jsp"></jsp:include>
<br>
	<h4>아이디 찾기</h4>
	<hr>
	<center>
		<form name="findIdForm" id="findIdForm" action="findId.do" method="post">
					<h5 style="text-align: center;">회원아이디 찾기</h5>
					<br>
					<p style="line-height: 10pt"><input type="text" name="findName" id="findName" 
						placeholder="이름을 입력하세요"  size="50" style="text-align: left; height: 15pt"></p>
					<br>
					<p style="line-height: 10pt"><input type="text" name="findEmail" id="findEmail"
						placeholder="이메일을 입력하세요" size="50" style="text-align: left; height: 15pt;"></p>
					<br>
					<input type="button" value="아이디 찾기" onclick="chkValue()" width: 290pt; height: 18pt;">
					<br>
					<br>
						<div style="font-size: 9pt">
					<p style="line-height: 10pt">	
						 <input type="checkbox" name="findUserType" value="구매회원" checked="checked" onclick='checkOnlyOne(this)' "> 구매회원
						 &nbsp;&nbsp;&nbsp;
						 <input type="checkbox" name="findUserType" value="판매회원" onclick='checkOnlyOne(this)' style="font-size: 9pt;"> 판매회원
				 	</p>
						 </div>
					<br>
						 
				<input type="button" name="Idcheck" value="비밀번호찾기" onclick=" return findPw(this.form);" style="width: 90pt">
				&nbsp;&nbsp;
				<input type="button" name="Idcheck" value="로그인하기" onclick='return backLogin(this.form);' style="width: 90pt">
			
		</form>
	</center>
	<br>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>