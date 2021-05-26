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

.container_wrap {
	background-color: #FAFAFA;
	padding: 30px 0;
}
.container {
	width: 1000px;
	margin: auto;
	background-color: #fff;
}
	
.button{
	border : 0;
	outline : 0;
	font-size : 0.90em;
	font-weight : 400;
	background-color : white;
}
.leftbar {
	padding: 30px;
	position: absolute;
	left: 50%;
	margin-left: -680px;
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
		<div class="shopping">
			<h3>쇼핑정보</h3>
			<form action="OrderList_View.do" method="post">
				<input class="button" type="submit" value="주문목록/배송조회">
			</form>
			
			<form action="OrderCancelList_View.do" method="post">
				<input class="button" type="submit" value="주문취소목록">
			</form>
			
			<form action="CustomerCart_View.do" method="post">
				<input class="button" type="submit" value="장바구니">
			</form>
			
			<form action="ReviewRegistrationList_View.do" method="post">
				<input class="button" type="submit" value="리뷰 등록">
			</form>
			
			<form action="ReviewDelete_View.do" method="post">
				<input class="button" type="submit" value="리뷰 삭제">
			</form>
		</div>
		
		<div class="user">
			<h3>회원정보</h3>
			<form action="CustomerInfoUpdate_View.do" method="post">
				<input class="button" type="submit" value="회원정보 변경">
			</form>
			<form action="CustomerPwUpdate_View.do" method="post">
				<input class="button" type="submit" value="비밀번호 변경">
			</form>
			<form action="CustomerSignOut_View.do" method="post">
				<input class="button" type="submit" value="회원 탈퇴">
			</form>
		</div>
	</div>
</body>
</html>