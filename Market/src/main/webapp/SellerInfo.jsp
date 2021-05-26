<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<style>
.button{
	border : 0;
	outline : 0;
	font-size : 0.90em;
	font-weight : 400;
	background-color : white;
}
.sellerbar {
	display: inline-block;
	width: 600px;
}
.container {
	display: inline-block;
}
</style>
<body>
	<div class="sellerbar">

		<form action="SellerInfoUpdate_View.do" method="post">
			<input class="button" type="submit" value="판매자 정보 수정">
		</form>
		<form action="SellerPwUpdate_View.do" method="post">
			<input class="button" type="submit" value="비밀번호 변경">
		</form>
		<form action="SellerSignOut_View.do" method="post">
			<input class="button" type="submit" value="회원 탈퇴">
		</form>
	</div>
</body>
</html>