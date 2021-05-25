<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매자 스토어</title>
<link rel="stylesheet" href="sellerStore.css">
</head>
<body>
	<header>
		<!-- 최상단 메뉴 -->
		<div class="top">
			<ul class="top_list">
				<li><a href="">로그아웃</a></li>
				<li><a href="">마이페이지</a></li>
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
	<br>
	<br>
	<div class = "mainTable" style="width: 800px; margin: auto;">
		<table style="float: left">
			<tr>
				<th colspan="2">상품관리</th>
			</tr>
			<tr>
				<td>총 상품</td>
				<td><a href="productlist.do">${SALESCOUNT } </a>건</td>
			</tr>
			<tr>
				<td>판매중</td>
				<td><a href="searchTure.do">${SALESTURE } </a>건</td>
			</tr>
			<tr>
				<td>품절</td>
				<td><a href="searchFalse.do">${SALESFALSE } </a>건
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<th colspan="2">주문관리</th>
			</tr>
			<tr style="background-color: #f2f2f2;">
				<td>총 주문</td>
				<td><a href="buyList.do">${BUYCOUNT } </a>건</td>
			</tr>
			<tr>
				<td>결제완료</td>
				<td><a href="buyTure.do">${BUYTRUE } </a>건</td>
			</tr>
			<tr style="background-color: #f2f2f2;">
				<td>취소</td>
				<td><a href="buyCancel.do">${BUYCANCEL } </a>건
				</td>
			</tr>
		</table>
		<table style="float: left">
			<tr>
				<th colspan="2">배송관리</th>
			</tr>
			<tr style="background-color: #f2f2f2;">
				<td>총 건수</td>
				<td><a href="DeliveryList.do">${DELIVERYALL } </a>건</td>
			</tr>
			<tr>
				<td>배송완료</td>
				<td><a href="DeliveryEnd.do">${DELIVERYEND } </a>건</td>
			</tr>
			<tr style="background-color: #f2f2f2;">
				<td>취소</td>
				<td><a href="DeliveryING.do">${DELIVERYING } </a>건
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<th colspan="2">문의관리</th>
			</tr>
			<tr style="background-color: #f2f2f2;">
				<td>총 건수</td>
				<td><a href="QnAList.do">${allQnACount } </a>건</td>
			</tr>
			<tr>
				<td>배송완료</td>
				<td><a href="QnATure.do">${QnATure } </a>건</td>
			</tr>
			<tr style="background-color: #f2f2f2;">
				<td>취소</td>
				<td><a href="QnAFalse.do">${QnAFalse } </a>건
				</td>
			</tr>
		</table>
	</div>
	<br>
	<br>

	<footer>
		<div class="content">
			<div>주식회사 Team2 | 주소: 서울특별시 서초구 서초4동 강남대로 441 | 대표: 김철수 |
				사업자등록번호: 000-00-00000 | 팩스번호: 00-000-0000 | 메일: team2@gamil.com</div>
		</div>
	</footer>
</body>
</html>