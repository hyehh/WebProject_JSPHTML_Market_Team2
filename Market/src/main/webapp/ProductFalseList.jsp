<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 관리</title>
<link rel="stylesheet" href="css.css">
</head>
<!-- <script language="javascript">document.google.category.value=document.google.cate.value;</script> -->
<script type="text/javascript">
	document.search.value = ${search}
</script>

<body>
	<%@include file = "headerSeller.jsp" %>
	<br>
	<br>
	<br>
	<h2 align="center">상품 관리</h2>
	<br>
	<form action="searchList.do" method = "post">
		<table class="table">
			<tr>
				<td>상품현황</td>
				<td>총 상품 <a href = "productlist.do">${SALESCOUNT }</a>건</td>
				<td>판매중 <a href = "searchTure.do">${SALESTURE }</a>건</td>
				<td>품절 <a href = "searchFalse.do">${SALESFALSE }</a>건</td>
			</tr>
			<tr>
				<td>검색 필터</td>
				<td><select name = "search" id = "search">
				<option value = " and pName">상품명</option>
				<option value = " and pCategory">종류명</option>
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
			<a href="searchFalse.do"><button type="button" class="buttonNum buttonNum">◀◀</button></a>
			<a href="searchFalse.do?pg=${FROMPAGE }"><button type="button" class="buttonNum buttonNum">◀</button></a> 
				<c:forEach items="${pageCount }" var="page" varStatus="ftp">
					<a href="searchFalse.do?pg=${ftp.count}"><button type="button" class="buttonNum buttonNum">${ftp.count }</button></a>
				</c:forEach> 
			<a href="searchFalse.do?pg=${TOPAGE }"><button type="button" class="buttonNum buttonNum">▶</button></a> 
			<a href="searchFalse.do?pg=${ALLPAGE }"><button type="button" class="buttonNum buttonNum">▶▶</button></a></td>

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