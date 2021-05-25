<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 2021.05.13 조혜지 왼쪽 바 view -->
<title>Insert title here</title>
</head>
<style>
.button{
	border : 0;
	outline : 0;
	font-size : 0.90em;
	font-weight : 400;
	background-color : white;
}
.leftbar {
	display: inline-block;
	width: 600px;
}
.container {
	display: inline-block;
}

</style>
<body>
	<div class="leftbar">
		________
		<p>쇼핑정보</p>
		<ul>
			<form action="OrderList_View.do" method="post">
			<li><input class="button" type="submit" value="주문목록/배송조회"></li>
			</form>
			<form action="OrderCancelList_View.do" method="post">
			<li><input class="button" type="submit" value="주문취소목록"></li>
			</form>
			<form action="CustomerCart_View.do" method="post">
			<li><input class="button" type="submit" value="장바구니"></li>
			</form>
			<form action="ReviewRegistrationList_View.do" method="post">
			<li><input class="button" type="submit" value="리뷰 등록"></li>
			</form>
			<form action="ReviewDelete_View.do" method="post">
			<li><input class="button" type="submit" value="리뷰 삭제"></li>
			</form>
			<br><br>
		</ul>
		________
		<p>회원정보</p>
		<ul>
			<form action="CustomerInfoUpdate_View.do" method="post">
			<li><input class="button" type="submit" value="회원정보 변경"></li>
			</form>
			<form action="CustomerPwUpdate_View.do" method="post">
			<li><input class="button" type="submit" value="비밀번호 변경"></li>
			</form>
			<form action="CustomerSignOut_View.do" method="post">
			<li><input class="button" type="submit" value="회원 탈퇴"></li>
			</form>
			<br><br>
		</ul>
	</div>
</body>
</html>