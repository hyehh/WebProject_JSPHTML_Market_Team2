<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert tile here</title>
	<link rel="stylesheet" href="css2.css">
</head>

<body>
	<header>
		<!-- 최상단 메뉴 -->
		<div class="top">
			<ul class="top_list">
				<li><a href="logout.do">로그아웃</a></li>
				<li><a href="SellerInfoUpdate_View.do">마이페이지</a></li>
			</ul>
		</div>
		<!-- //최상단 메뉴 -->
		
		<!-- 로고 -->
		<div class="logo">
			<a href="sellerStore.do"><h1>떨이마켓</h1></a>
		</div>
		<!-- //로고 -->
		
		<!-- 카테고리 -->
		<div class="category">
			<nav>
				<ul class="category_list">
					<li><a href="productlist.do">상품관리</a></li>
					<li><a href="insertProduct.jsp">상품등록</a></li>
					<li><a href="buyList.do">주문관리</a></li>
					<li><a href="DeliveryList.do">배송관리</a></li>
					<li><a href="QnAList.do">문의관리</a></li>
				</ul>
			</nav>
		</div>
		<!-- //카테고리 -->
	</header>
</body>
</html>