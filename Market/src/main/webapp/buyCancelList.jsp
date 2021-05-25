<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문관리</title>
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
	<h2 align="center">주문 관리</h2>
	<br>
	<form action="buySearchList.do" method = "post">
		<table class="table">
			<tr>
				<td>주문현황</td>
				<td>총 주문 <a href = "buyList.do">${BUYCOUNT }</a>건</td>
				<td>결제완료 <a href = "buyTure.do">${BUYTRUE }</a>건</td>
				<td>취소 <a href = "buyCancel.do">${BUYCANCEL }</a>건</td>
			</tr>
			<tr>
				<td>검색 필터</td>
				<td><select name = "search">
				<option value = " and P.pName">상품명</option>
				<option value = " and P.pCategory">종류명</option>
				<option value = " and B.bNumber">주문번호</option>
				</select></td>
				<td colspan="2"><input type = "text" name = "searchTxt" size = "30"></td>
				<td><input type = "submit" value = "검색" class="button button5"></td>
			</tr>
		</table>
	</form>
	<br>
	<br>
	<br>
	<br>
	<h2 align="center">주문 목록</h2>
	<br>
	<table class="table">
		<tr>
			<th>주문번호</th>		
			<th>상품 종류</th>		
			<th>상품명</th>		
			<th>주문일</th>		
			<th>주문현황</th>		
		</tr>
		<c:forEach items="${list}" var="dto">
		<tr class="value" onclick="location.href='selectBuy.do?bNumber=${dto.bNumber }'"><!-- B.bNumber, P.pCategory, p.pName, B.bState -->
			<td>${dto.bNumber }</td>
			<td>${dto.pCategory }</td>
			<td>${dto.pName }</td>
			<td>${dto.bBuyDate }</td>
			<td>${dto.buyCancel }</td>
		</tr>
		</c:forEach>
	</table>
	<br>
	<table class="table">
		<tr>
			<td align="center">
			
		<a href="buyCancel.do"><button type="button" class="buttonNum buttonNum">◀◀</button></a>
        <a href="buyCancel.do?pg=${FROMPAGE }"><button type="button" class="buttonNum buttonNum">◀</button></a>
        	<c:forEach items = "${pageCount }" var = "page" varStatus="ftp">
        		<a href="buyCancel.do?pg=${ftp.count}"><button type="button" class="buttonNum buttonNum">${ftp.count }</button></a>
       		</c:forEach>
        <a href="buyCancel.do?pg=${TOPAGE }"><button type="button" class="buttonNum buttonNum">▶</button></a>
        <a href="buyCancel.do?pg=${ALLPAGE }"><button type="button" class="buttonNum buttonNum">▶▶</button></a>


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