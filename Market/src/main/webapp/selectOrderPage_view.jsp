<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송관리-상세보기</title>
<link rel="stylesheet" href="selectPage.css">
</head>
<body>
<header>
		<!-- 최상단 메뉴 -->
		<div class="top">
			<ul class="top_list">
				<!-- <li><a href="">장바구니</a></li> -->
				<!-- 판매자이기때문에 장바구니는 없어도 되니까 숨김 -->
				<li><a href="">로그아웃</a></li>
				<!-- 로그인창으로 갈 수이 ㅆ게끔 -->
				<li><a href="">마이페이지</a></li>
				<!-- <li><a href="">1:1 문의</a></li> -->
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
	<br>
	<form method = "post">
		<div class="mainTable">
		<table style="margin: auto;">
		<tr>
			<th colspan="2">상세보기</th>
		</tr>
		<tr><!-- B.bNumber, B.bDate, C.cName, C.cTel, C.cAddress, D.dStartDate, D.dEndDate, D.dCondition  -->
			<td>주문번호</td>
			<td><input type = "text" name = "bNumber" readonly="readonly" value = "${selectOrderPage.bNumber }"></td>
		</tr>
		<tr>
			<td>주문일자</td>
			<td><input type = "text" name = "bBuyDate" readonly="readonly" value = "${selectOrderPage.bBuyDate }"></td>
		</tr>
		<tr>
			<td>구매자 이름</td>
			<td><input type = "text" name = "cName" readonly="readonly" value = "${selectOrderPage.bRecName }"></td>
		</tr>
		<tr>
			<td>구매자 번호</td>
			<td><input type = "text" name = "cTel" readonly="readonly" value = "${selectOrderPage.bRecTel }"></td>
		</tr>
		<tr>
			<td>구매자 우편주소</td>
			<td><input type = "text" name = "cPostalCode" readonly="readonly" value = "${selectOrderPage.bRecPostalCode }" size = "5"></td>
		</tr>
		<tr>
			<td>구매자 주소</td>
			<td><input type = "text" name = "cAddress1" readonly="readonly" value = "${selectOrderPage.bRecAddress1 }"></td>
		</tr>
		<tr>
			<td>구매자 상세주소</td>
			<td><input type = "text" name = "cAddress2" readonly="readonly" value = "${selectOrderPage.bRecAddress2 }"></td>
		</tr>
	</table>
	<table style="margin: auto;">
	<tr>
	<th colspan="3">구매리스트</th>
	</tr>
		<tr>
			<td>상품 종류</td>
			<td>상품 이름</td>
			<td>구매 수량</td>
		</tr>
		<c:forEach items = "${OrderPageList }" var = "list">
		<tr>
			<td>${list.pCategory }</td>
			<td>${list.pName }</td>
			<td>${list.bQuantity }</td>
		</tr>
		</c:forEach>
	</table>
	<table style="margin: auto;">
		<tr>
			<th colspan="2">배송현황</th>
		</tr>
		<tr>
			<td>배송 시작일</td>
			<!-- <td>//</td> -->
			<td>배송 완료일</td>
		</tr>
		<tr>
			<td>${selectOrderPage.dStartDate }</td>
			<!-- <td>//</td> -->
			<td>${selectOrderPage.dEndDate }</td>
		</tr>
		<tr>
			<th colspan="2">배송처리</th>
		</tr>
		<tr>
			<td>
			<input type = "submit" name = "deliveryFor" value = "배송중" formaction="deliveryFor.do" 
			class="buttonModify buttonModify">
			</td>
			<td>
			<input type = "submit" name = "deliveryFinish" value = "배송완료" formaction="deliveryFinish.do" 
			class="buttonModify buttonModify">
			<td>
		</tr>
	</table>
	</div>
	</form>
	
	<footer>
		<div class="content">
			<div>주식회사 Team2 | 주소: 서울특별시 서초구 서초4동 강남대로 441 | 대표: 김철수 |
				사업자등록번호: 000-00-00000 | 팩스번호: 00-000-0000 | 메일: team2@gamil.com</div>
		</div>
	</footer>
</body>
</html>