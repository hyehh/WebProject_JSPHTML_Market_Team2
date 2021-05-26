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
	width: 300px;
}
.container {
	display: inline-block;
}
</style>
<body>
	<header>
		<!-- 최상단 메뉴 -->
		<div class="top">
			<ul class="top_list">
				<li><a href="">로그아웃</a></li>
				<li><a href="SellerInfoUpdate_View.do">마이페이지</a></li>
			</ul>
		</div>
		<!-- //최상단 메뉴 -->
		
		<!-- 로고 -->
		<div class="logo">
			<a href="sellerStore.do"><h1>마감임박!!</h1></a>
		</div>
		<!-- //로고 -->
		
		<!-- 카테고리 -->
		<div class="category">
			<nav>
				<ul class="category_list">
					<li><a href="SellerInfoUpdate_View.do">정보 수정</a></li>
					<li><a href="SellerPwUpdate_View.do">비밀번호 변경</a></li>
					<li><a href="SellerSignOut_View.do">회원 탈퇴</a></li>
				<!-- 	<li><a href="DeliveryList.do">배송관리</a></li>
					<li><a href="QnAList.do">문의관리</a></li> -->
				</ul>
			</nav>
		</div>
		<!-- //카테고리 -->
	</header>

<!-- 
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
	</div> -->
</body>
</html>