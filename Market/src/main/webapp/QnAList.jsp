<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의내역</title>
<link rel="stylesheet" href="css.css">
</head>
<body>
<header>
		<!-- 최상단 메뉴 -->
		<div class="top">
			<ul class="top_list">
				<!-- <li><a href="">장바구니</a></li> -->
				<!-- 판매자이기때문에 장바구니는 없어도 되니까 숨김 -->
				<li><a href="">로그아웃</a></li> <!-- 로그인창으로 갈 수이 ㅆ게끔 -->
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
	<h2 align="center">문의내역</h2>
	<form action="searchQnAList.do" method = "post">
		<table  class="table">
			<tr>
				<td>문의현황</td>
				<td>총 문의 <a href = "QnAList.do">${allQnACount }</a>건</td>
				<td>답변완료 <a href = "QnATure.do">${QnATure }</a>건</td>
				<td>미답변 <a href = "QnAFalse.do">${QnAFalse }</a>건</td>
			</tr>
			<tr>
				<td>검색</td>
				<td>주문번호</td>
				<td colspan="2"><input type = "text" name = "searchTxt" size = "30"
				value = "${searchtxt == null ? null : searchtxt }"></td>
				<td><input type = "submit" value = "검색" class="button button5"></td>
			</tr>
		</table>
	</form>
	<br>
	<br>
	<br>
	<br>
	<h2  align="center">문의 목록</h2>
	<table class="table">
		<tr>
			<th>문의번호</th>		
			<th>주문번호</th>		
			<th>문의 제목</th>		
			<th>등록일</th>		
			<th>답변상태</th>		
		</tr>
		<c:forEach items="${list}" var="dto">
		<tr class="value" onclick="location.href='selectQnA.do?bNumber=${dto.bNumber }'">
			<td>${dto.qnACode }</td>
			<td>${dto.bNumber }</td>
			<td>${dto.qTitle }</td>
			<td>${dto.qAddDate }</td>
			<td>${dto.qAnswerStatus }</td>
		</tr>
		</c:forEach>
	</table>
	<br>
	<table  class="table">
		<tr>
			<td align="center">
		<a href="QnAList.do"><button type="button" class="buttonNum buttonNum">◀◀</button></a>
        <a href="QnAList.do?pg=${FROMPAGE }"><button type="button" class="buttonNum buttonNum">◀</button></a>
        <c:forEach items = "${pageCount }" var = "page" varStatus="ftp">
        <a href="QnAList.do?pg=${ftp.count}"><button type="button" class="buttonNum buttonNum">${ftp.count }</button></a>
        </c:forEach>
        <a href="QnAList.do?pg=${TOPAGE }"><button type="button" class="buttonNum buttonNum">▶</button></a>
        <a href="QnAList.do?pg=${ALLPAGE }"><button type="button" class="buttonNum buttonNum">▶▶</button></a>

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