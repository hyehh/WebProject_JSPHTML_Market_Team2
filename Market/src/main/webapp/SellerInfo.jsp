<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
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
}
.user form .button{
	border : 0;
	outline : 0;
	font-size : 1.1em;
	font-weight : 400;
	background-color : #FAFAFA;
	color: #000;
	padding: 0;
}
.leftbar {
	padding: 30px;
	position: absolute;
	left: 50%;
	margin-left: -650px;
}
.shopping {
	margin-bottom: 20px;
}
.shopping h3, .user h3 {
	padding-bottom: 5px;
}
.shopping form, .user form {
	padding: 5px;
}
</style>
<body>
	<div class="leftbar">
		<div class="user">
			<h3>판매자정보</h3>
			<form action="SellerInfoUpdate_View.do" method="post">
				<input class="button" type="submit" value="판매자정보 변경">
			</form>
			<form action="SellerPwUpdate_View.do" method="post">
				<input class="button" type="submit" value="비밀번호 변경">
			</form>
			<form action="SellerSignOut_View.do" method="post">
				<input class="button" type="submit" value="판매자 탈퇴">
			</form>
		</div>
	</div>

</body>
</html>