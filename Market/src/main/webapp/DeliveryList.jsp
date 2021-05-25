<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송관리</title>
<link rel="stylesheet" href="css.css">
</head>
<body>
<header>
		<!-- 최상단 메뉴 -->
		<div class="top">
			<ul class="top_list">
				<li><a href="">로그아웃</a></li> <!-- 로그인창으로 갈 수이 ㅆ게끔 -->
				<li><a href="">마이페이지</a></li>
			</ul>
		</div>
		<!-- //최상단 메뉴 -->
		
		<!-- 로고 -->
		<div class="logo">
			<a href=""><h1>마감임박!!</h1></a>
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
<h2 align="center">배송 관리</h2>
	<form action="deliverySearchList.do" method = "post">
		<table class="table">
			<tr>
				<td>배송현황</td>
				<td>총 건수 <a href = "DeliveryList.do">${DELIVERYALL }</a>건</td>
				<td>배송완료 <a href = "DeliveryEnd.do">${DELIVERYEND }</a>건</td>
				<td>배송중 <a href = "DeliveryING.do">${DELIVERYING }</a>건</td>
			</tr>
			<tr>
				<td rowspan="2">검색 필터</td>
				<td style="margin: auto;"><input type = "radio" name = "search" value = "allshow" checked="checked"> 전체검색
				<input type = "radio" name = "search" value = " where B.bNumber"> 주문번호
				<input type = "radio" name = "search" value = " where D.dCondition"> 배송현황
				</td>
			</tr>
			<tr>
 				<td colspan="2"><input type = "text" name = "searchTxt" size = "30"></td>
				<td rowspan="2"><input type = "submit" value = "검색" class="button button5"></td>
			</tr>
		</table>
	</form>
	<br>
	<br>
	<br>
	<br>
	<h2 align="center">배송 목록</h2>
	<table class="table">
		<tr >
			<th>주문번호</th>		
			<th>상품종류</th>		
			<th>상품명</th>		
			<th>배송현황</th>		
		</tr>
		<c:forEach items="${list}" var="dto">
		<tr class="value" onclick="location.href='selectOrderPage.do?bNumber=${dto.bNumber }'">
			<td>${dto.bNumber }</td>
			<td>${dto.pCategory }</td>
			<td>${dto.pName }</td>
			<td>${dto.dCondition }</td>
		</tr>
		</c:forEach>
	</table>
	<br>
	<table class="table">
		<tr>
			<td align="center">
        
        [<a href="DeliveryList.do"> ◀◀ </a>] 
        [<a href="DeliveryList.do?pg=${FROMPAGE }">◀</a>]
        <c:forEach items = "${pageCount }" var = "page" varStatus="ftp">
        <a href="DeliveryList.do?pg=${ftp.count}">[ ${ftp.count } ]</a>
        </c:forEach>
        [<a href="DeliveryList.do?pg=${TOPAGE }">▶</a>] 
        [<a href="DeliveryList.do?pg=${ALLPAGE }">▶▶</a>]


			</td>
		</tr>
	</table>
	<footer>
		<div class="content">
			<div>
				주식회사 Team2 | 주소: 서울특별시 서초구 서초4동 강남대로 441 | 대표: 김철수 | 사업자등록번호: 000-00-00000 | 팩스번호: 00-000-0000 | 메일: team2@gamil.com
			</div>
		</div>
	</footer>
</body>
</html>