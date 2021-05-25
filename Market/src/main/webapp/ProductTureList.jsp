<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 관리</title>
<link rel="stylesheet" href="css.css">
</head>
<!-- <script language="javascript">document.google.category.value=document.google.cate.value;</script> -->
<script type="text/javascript">
	document.search.value = $
	{
		search
	}
</script>

<body>
	<header>
		<!-- 최상단 메뉴 -->
		<div class="top">
			<ul class="top_list">
				<li><a href="">로그아웃</a></li>
				<!-- 로그인창으로 갈 수이 ㅆ게끔 -->
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
	<h2 align="center">상품 관리</h2>
	<br>
	<form action="searchList.do" method="post">
		<table class="table">
			<tr>
				<td>상품현황</td>
				<td>총 상품 <a href="productlist.do">${SALESCOUNT }</a>건
				</td>
				<td>판매중 <a href="searchTure.do">${SALESTURE }</a>건
				</td>
				<td>품절 <a href="searchFalse.do">${SALESFALSE }</a>건
				</td>
			</tr>
			<tr>
				<td>검색 필터</td>
				<td><select name="search" id="search">
						<option value=" and pName">상품명</option>
						<option value=" and pCategory">종류명</option>
				</select></td>
				<td colspan="2"><input type="text" name="searchTxt" size="30"></td>
				<td><input type="submit" value="검색" class="button button5"></td>
			</tr>
		</table>
	</form>
	<br>
	<br>
	<br>
	<br>
	<h2 align="center">상품 목록</h2>
	<br>
	<table class="table">
		<tr>
			<th>번호</th>
			<th>상품종류</th>
			<th>상품명</th>
			<th>가격</th>
			<th>할인가격</th>
			<th>유통기한</th>
			<th>수량</th>
			<th>품절여부</th>
			<th>등록일</th>
		</tr>
		<c:forEach items="${list}" var="dto">
			<tr class="value"
				onclick="location.href='selectProduct.do?pCode=${dto.pCode }'">
				<!-- pCode, pCategory, pName, pPrice, pExpirationDate, pQuantity -->
				<td>${dto.pCode }</td>
				<td>${dto.pCategory }</td>
				<td>${dto.pName }</td>
				<td>${dto.pPrice }</td>
				<td>${dto.pPriceDC }</td>
				<td>${dto.pExpirationDate }</td>
				<td>${dto.pProductEA }</td>
				<td>${dto.pStatus }</td>
				<td>${dto.pAddDate }</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<table class="table">
		<tr>
			<td align="center">
			<a href="searchTure.do"><button type="button" class="buttonNum buttonNum">◀◀</button></a>
			<a href="searchTure.do?pg=${FROMPAGE} }"><button type="button" class="buttonNum buttonNum">◀</button></a> 
				<c:forEach items="${pageCount }" var="page" varStatus="ftp">
					<a href="searchTure.do?pg=${ftp.count}"><button type="button" class="buttonNum buttonNum">${ftp.count }</button></a>
				</c:forEach> 
			<a href="searchTure.do?pg=${TOPAGE }"><button type="button" class="buttonNum buttonNum">▶</button></a> 
			<a href="searchTure.do?pg=${ALLPAGE }"><button type="button" class="buttonNum buttonNum">▶▶</button></a></td>
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